package sv.edu.catolica.project_final;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sv.edu.catolica.project_final.Interfaces.TradesSeekerApi;
import sv.edu.catolica.project_final.Models.WorkStoreModel;
import sv.edu.catolica.project_final.Models.WorkerModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TextInputEditText textDate;
    private View root;
    String[] list_genders = {"Masculino", "Femenino"};
    String[] list_departamentos = {"Ahuachapán","Cabañas","Chalatenango","Cuscatlán","La Libertad","Morazán", "La Paz","Santa Ana", "San Miguel","San Salvador","San Vicente","Sonsonate","La Unión","Usulután"};

    AutoCompleteTextView autoCompleteGenders;
    AutoCompleteTextView autoCompleteDepartamento;
    ArrayAdapter<String> adapterDepartamentos;
    ArrayAdapter<String> adaptarGenders;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://trades-seeker.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        textDate = root.findViewById(R.id.setting_BornDate);

        autoCompleteDepartamento = root.findViewById(R.id.list_department);
        adapterDepartamentos = new ArrayAdapter<String>(container.getContext(),R.layout.genero_list, list_departamentos);

        autoCompleteDepartamento.setAdapter(adapterDepartamentos);

        autoCompleteDepartamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
            }
        });

        autoCompleteGenders = root.findViewById(R.id.gender_list);
        adaptarGenders = new ArrayAdapter<String>(container.getContext(), R.layout.genero_list, list_genders);

        autoCompleteGenders.setAdapter(adaptarGenders);

        autoCompleteGenders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
            }
        });

        try {
            TextInputEditText setting_txtname = root.findViewById(R.id.setting_txtname);
            TextView setting_nombre = root.findViewById(R.id.setting_nombre);
            TextView setting_Ubicacion = root.findViewById(R.id.setting_Ubicacion);
            TextInputEditText setting_email = root.findViewById(R.id.setting_email);
            TextInputEditText setting_phone = root.findViewById(R.id.setting_phone);

            Gson gson = new Gson();

            SharedPreferences sharedPref = getContext().getSharedPreferences("myPreferences", getContext().MODE_PRIVATE);
            String json = sharedPref.getString("user", "");

            WorkerModel worker = gson.fromJson(json, WorkerModel.class);

            if (worker != null){
                setting_txtname.setText(worker.getName());
                setting_nombre.setText(worker.getName());
                setting_Ubicacion.setText(worker.getProfession());
                textDate.setText(worker.getBirthday().toString());
                setting_email.setText(worker.getEmail());
                setting_phone.setText(worker.getPhone());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // Inflate the layout for this fragment
        return root;

    }


    public void logout(View view) {
        TradesSeekerApi tradesSeekerApi = retrofit.create(TradesSeekerApi.class);

        SharedPreferences sharedPref = getContext().getSharedPreferences("myPreferences", getContext().MODE_PRIVATE);

        String token = sharedPref.getString("token", null);
        String json = sharedPref.getString("user", "");

        Gson gson = new Gson();

        WorkerModel worker = gson.fromJson(json, WorkerModel.class);

        Call<ResponseBody> call = tradesSeekerApi.logout(token);

        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.message());
                if (response.isSuccessful()){
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Error codigo: "+t.getMessage());
            }
        });
    }
}