package sv.edu.catolica.project_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sv.edu.catolica.project_final.Interfaces.TradesSeekerApi;
import sv.edu.catolica.project_final.Models.HomeModel;
import sv.edu.catolica.project_final.Models.WorkModel;
import sv.edu.catolica.project_final.Models.WorkerModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txt_user_name;
    View root;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        root = inflater.inflate(R.layout.fragment_home, container, false);

        txt_user_name = root.findViewById(R.id.home_user_name);

        Gson gson = new Gson();

        SharedPreferences sharedPref = getContext().getSharedPreferences("myPreferences", getContext().MODE_PRIVATE);
        String json = sharedPref.getString("user", "");

        WorkerModel worker = gson.fromJson(json, WorkerModel.class);

        if (worker != null){
            txt_user_name.setText("Bienvenido de vuelta \n"+worker.getName());
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trades-seeker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TradesSeekerApi tradesSeekerApi = retrofit.create(TradesSeekerApi.class);

        Call<HomeModel> call = tradesSeekerApi.getHome();

        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                if (!response.isSuccessful()){
                    System.out.println("Error codigo: "+response.code());
                    return;
                }

                HomeModel homeModel = response.body();
                TextView count1 = root.findViewById(R.id.home_count1);
                TextView count2 = root.findViewById(R.id.home_count2);
                TextView count3 = root.findViewById(R.id.home_count3);

                TextView name1 = root.findViewById(R.id.home_name1);
                TextView name2 = root.findViewById(R.id.home_name2);
                TextView name3 = root.findViewById(R.id.home_name3);

                count1.setText(homeModel.getPopular_locations().get(0).getCount());
                count2.setText(homeModel.getPopular_locations().get(1).getCount());
                count3.setText(homeModel.getPopular_locations().get(2).getCount());

                name1.setText(homeModel.getPopular_locations().get(0).getName());
                name2.setText(homeModel.getPopular_locations().get(1).getName());
                name3.setText(homeModel.getPopular_locations().get(2).getName());

                List<ListWorker> listWorkers = new ArrayList<>();

                for (WorkModel work: homeModel.getLast_works()){
                    listWorkers.add(new ListWorker(work, work.getWorker()));
                }

                ListWorkerAdapter listWorkerAdapter = new ListWorkerAdapter(listWorkers, root.getContext());

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);

                RecyclerView recyclerView = root.findViewById(R.id.listCompactRecyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);

                listWorkerAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(getContext(), ProfileWorker.class);
                        myIntent.putExtra("listwork", (Serializable) listWorkers.get(recyclerView.getChildAdapterPosition(view)));
                        startActivity(myIntent);
                    }
                });

                recyclerView.setAdapter(listWorkerAdapter);
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                System.out.println("Error codigo: "+t.getMessage());
                return;
            }
        });

        // Inflate the layout for this fragment
        return root;
    }
}