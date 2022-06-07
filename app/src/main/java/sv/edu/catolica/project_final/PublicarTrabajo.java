package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sv.edu.catolica.project_final.Interfaces.TradesSeekerApi;
import sv.edu.catolica.project_final.Models.CategoryModel;
import sv.edu.catolica.project_final.Models.Departamento;
import sv.edu.catolica.project_final.Models.LoginModel;
import sv.edu.catolica.project_final.Models.LoginResponseModel;
import sv.edu.catolica.project_final.Models.WorkModel;
import sv.edu.catolica.project_final.Models.WorkResponseModel;
import sv.edu.catolica.project_final.Models.WorkStoreModel;
import sv.edu.catolica.project_final.Models.WorkerModel;

public class PublicarTrabajo extends AppCompatActivity {


    AutoCompleteTextView autoCompleteDepartamento;
    AutoCompleteTextView categoryInput;
    ArrayAdapter<String> adapterDepartamentos;
    String[] list_departamentos = {"Ahuachapán","Cabañas","Chalatenango","Cuscatlán","La Libertad","Morazán", "La Paz","Santa Ana", "San Miguel","San Salvador","San Vicente","Sonsonate","La Unión","Usulután"};

    AutoCompleteTextView autoCompleteDispo;
    ArrayAdapter<String> adapterDisponi;
    String[] list_Dispo = {"Tiempo completo", "Jornada parcial"};

    ArrayAdapter<String> adapterTime;
    AutoCompleteTextView autoCompleteTime;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://trades-seeker.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    int selectedCategory;
    int selectedState;
    String selectedSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_trabajo);

        autoCompleteDepartamento = findViewById(R.id.list_department);
        adapterDepartamentos = new ArrayAdapter<String>(getBaseContext(),R.layout.genero_list, list_departamentos);

        autoCompleteDepartamento.setAdapter(adapterDepartamentos);

        autoCompleteDepartamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
                selectedState = i + 1;
            }
        });

        autoCompleteDispo = findViewById(R.id.list_disponibilidad);
        adapterDisponi = new ArrayAdapter<String>(getBaseContext(),R.layout.genero_list, list_Dispo);

        autoCompleteDispo.setAdapter(adapterDisponi);

        autoCompleteDispo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
                selectedSchedule = item;
            }
        });

        categoryInput = findViewById(R.id.list_categoria);

        TradesSeekerApi tradesSeekerApi = retrofit.create(TradesSeekerApi.class);
        Call<List<CategoryModel>> call = tradesSeekerApi.getCategories();

        call.enqueue(new Callback<List<CategoryModel>>(){
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                System.out.println(response.code());
                if (response.isSuccessful()){


                    List<CategoryModel> categoryModel = response.body();

                    List<String> listCategories = new ArrayList<>();

                    for (CategoryModel category: categoryModel){
                        listCategories.add(category.getName());
                    }

                    ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.genero_list, listCategories);

                    categoryInput.setAdapter(categoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                System.out.println("Error "+ t.getMessage());
            }
        });

        categoryInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                selectedCategory = i + 1 ;
            }
        });
    }

    public void close(View view) {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);

    }

    public void createWork(View view) {
        TradesSeekerApi tradesSeekerApi = retrofit.create(TradesSeekerApi.class);

        TextInputEditText profession = findViewById(R.id.work_profession);
        AutoCompleteTextView cat = findViewById(R.id.list_categoria);
        AutoCompleteTextView location = findViewById(R.id.list_department);
        TextInputEditText exp = findViewById(R.id.exp);
        AutoCompleteTextView shedule = findViewById(R.id.list_disponibilidad);
        TextInputEditText info = findViewById(R.id.informationJob);
        TextInputEditText price = findViewById(R.id.price);

        SharedPreferences sharedPref = getBaseContext().getSharedPreferences("myPreferences", getBaseContext().MODE_PRIVATE);

        String token = sharedPref.getString("token", null);
        String json = sharedPref.getString("user", "");

        Gson gson = new Gson();

        WorkerModel worker = gson.fromJson(json, WorkerModel.class);

        WorkStoreModel workStoreModel = new WorkStoreModel(worker.getId(),selectedState, selectedCategory, profession.getText().toString(), exp.getText().toString(), selectedSchedule, price.getText().toString(), info.getText().toString() );

        Call<ResponseBody> call = tradesSeekerApi.saveWorks(workStoreModel);

        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    System.out.println(response.body());

                    Snackbar.make(view, "Trabajo publicado", Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Trabajo publicado", Toast.LENGTH_LONG).show();

                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Error codigo: "+t.getMessage());
            }
        });
    }


}