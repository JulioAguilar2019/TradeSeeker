package sv.edu.catolica.project_final;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sv.edu.catolica.project_final.Interfaces.TradesSeekerApi;
import sv.edu.catolica.project_final.Models.WorkModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View root;
    Retrofit retrofit;
    CircularProgressIndicator loading;

    TradesSeekerApi tradesSeekerApi;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<ListWorker> listWorkers;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_search, container, false);

        loading = root.findViewById(R.id.loading);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://trades-seeker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tradesSeekerApi = retrofit.create(TradesSeekerApi.class);

        root.findViewById(R.id.containedButton).setOnClickListener(view -> onSearch());

        getApiData(root);
        // Inflate the layout for this fragment
        return root;
    }

    public void getApiData(View root) {
        Call<List<WorkModel>> call = tradesSeekerApi.getWorks();

        CircularProgressIndicator loading = root.findViewById(R.id.loading);

        call.enqueue(new Callback<List<WorkModel>>() {
            @Override
            public void onResponse(Call<List<WorkModel>> call, Response<List<WorkModel>> response) {
                if (!response.isSuccessful()){
                    System.out.println("Error codigo: "+response.code());
                    return;
                }

                List<WorkModel> workModels = response.body();

                try {
                    listWorkers = new ArrayList<>();

                    for (WorkModel work: workModels){
                        listWorkers.add(new ListWorker(work, work.getWorker()));
                    }

                    ListWorkerAdapter listWorkerAdapter = new ListWorkerAdapter(listWorkers, root.getContext());

                    RecyclerView recyclerView = root.findViewById(R.id.listRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

                    listWorkerAdapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(getContext(), ProfileWorker.class);
                            myIntent.putExtra("listwork", (Serializable) listWorkers.get(recyclerView.getChildAdapterPosition(view)));
                            startActivity(myIntent);
                        }
                    });

                    recyclerView.setAdapter(listWorkerAdapter);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                loading.hide();
            }

            @Override
            public void onFailure(Call<List<WorkModel>> call, Throwable t) {
                System.out.println("Error codigo: "+t.getMessage());
                loading.hide();
                return;
            }
        });
    }


    public void onSearch() {
        TextInputEditText search_input = root.findViewById(R.id.search_input);

        Call<List<WorkModel>> call = tradesSeekerApi.findWorkers(search_input.getText().toString());

        loading.show();

        call.enqueue(new Callback<List<WorkModel>>() {
            @Override
            public void onResponse(Call<List<WorkModel>> call, Response<List<WorkModel>> response) {
                if (!response.isSuccessful()){
                    System.out.println("Error codigo: "+response.code());
                    return;
                }

                try {
                    System.out.println(response.body());
                    List<WorkModel> workModels = response.body();

                    listWorkers = new ArrayList<>();

                    for (WorkModel work: workModels){
                        listWorkers.add(new ListWorker(work, work.getWorker()));
                    }

                    ListWorkerAdapter listWorkerAdapter = new ListWorkerAdapter(listWorkers, root.getContext());

                    RecyclerView recyclerView = root.findViewById(R.id.listRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

                    listWorkerAdapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(getContext(), ProfileWorker.class);
                            myIntent.putExtra("listwork", (Serializable) listWorkers.get(recyclerView.getChildAdapterPosition(view)));
                            startActivity(myIntent);
                        }
                    });

                    recyclerView.setAdapter(listWorkerAdapter);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                loading.hide();
            }

            @Override
            public void onFailure(Call<List<WorkModel>> call, Throwable t) {
                System.out.println("Error codigo: "+t.getMessage());
                loading.hide();
                return;
            }
        });
    }
}