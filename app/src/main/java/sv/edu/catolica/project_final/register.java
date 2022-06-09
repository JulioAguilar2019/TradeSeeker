package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sv.edu.catolica.project_final.Interfaces.LoginApi;
import sv.edu.catolica.project_final.Models.LoginModel;
import sv.edu.catolica.project_final.Models.LoginResponseModel;
import sv.edu.catolica.project_final.Models.RegisterModel;

public class register extends AppCompatActivity {

    String[] list_genders = {"Masculino", "Femenino"};
    String[] list_departamentos = {"Ahuachapán","Cabañas","Chalatenango","Cuscatlán","La Libertad","Morazán", "La Paz","Santa Ana", "San Miguel","San Salvador","San Vicente","Sonsonate","La Unión","Usulután"};

    AutoCompleteTextView autoCompleteGenders;
    AutoCompleteTextView autoCompleteDepartamento;
    ArrayAdapter<String> adapterDepartamentos;
    ArrayAdapter<String> adaptarGenders;
    TextInputEditText birthday;
    String selectedGender;
    int selectedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        autoCompleteDepartamento = findViewById(R.id.state_id);
        adapterDepartamentos = new ArrayAdapter<String>(getBaseContext(),R.layout.genero_list, list_departamentos);

        autoCompleteDepartamento.setAdapter(adapterDepartamentos);

        autoCompleteDepartamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
                selectedState = i + 1;
            }
        });

        autoCompleteGenders = findViewById(R.id.gender);
        adaptarGenders = new ArrayAdapter<String>(getBaseContext(), R.layout.genero_list, list_genders);

        autoCompleteGenders.setAdapter(adaptarGenders);

        autoCompleteGenders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                selectedGender = parent.getItemAtPosition(i).toString();
            }
        });

        birthday = findViewById(R.id.birthday);

        birthday.setOnClickListener(view -> showDatePickerDialog());

    }

    private void showDatePickerDialog() {
        MaterialDatePicker dateRangePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select dates")
                        .build();

        dateRangePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                birthday.setText(dateRangePicker.getHeaderText());
            }
        });

        dateRangePicker.show(getSupportFragmentManager(), "TAG");
    }

    public void goLogin(View view) {
        Intent myIntent = new Intent(getBaseContext(), Login.class);
        startActivity(myIntent);
    }

    public void onRegister(View view) {

        TextInputEditText name = this.findViewById(R.id.name);
        TextInputEditText email = this.findViewById(R.id.email);
        TextInputEditText date = this.findViewById(R.id.birthday);
        AutoCompleteTextView gender = this.findViewById(R.id.gender);
        AutoCompleteTextView department = this.findViewById(R.id.state_id);
        TextInputEditText password = this.findViewById(R.id.password);
        TextInputEditText confirm_password = this.findViewById(R.id.password_confirmed);
        TextInputEditText profession = this.findViewById(R.id.profession);
        TextInputEditText phone = this.findViewById(R.id.phone);

       try {
            if (name.getText().toString().isEmpty() || name == null
                    || email.getText().toString().isEmpty() || email == null
                    || date.getText().toString().isEmpty() || date == null
                    || gender == null || gender.getText().toString().isEmpty()
                    || department.getText().toString().isEmpty() || department == null
                    || password.getText().toString().isEmpty() || password == null
                    || confirm_password.getText().toString().isEmpty() || confirm_password == null
            ){
                Snackbar snackbar = Snackbar.make(view, "Los campos no pueden estar vacios", Snackbar.LENGTH_SHORT);
                snackbar.show();
                return;
            }

           Retrofit retrofit = new Retrofit.Builder()
                   .baseUrl("https://trades-seeker.herokuapp.com/")
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();

           RegisterModel register = new RegisterModel(name.getText().toString(), email.getText().toString(), password.getText().toString(), confirm_password.getText().toString(), profession.getText().toString(), birthday.getText().toString(), selectedGender, selectedState, phone.getText().toString());

           LoginApi loginApi = retrofit.create(LoginApi.class);

           Call<LoginResponseModel> call = loginApi.signup(register);

           call.enqueue(new Callback<LoginResponseModel>(){

               @Override
               public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                   System.out.println(response.code());
                   if (response.isSuccessful()){
                       SharedPreferences sharedPref = getBaseContext().getSharedPreferences("myPreferences", getBaseContext().MODE_PRIVATE);

                       SharedPreferences.Editor editor = sharedPref.edit();
                       Gson gson = new Gson();

                       String token = response.body().getToken();

                       System.out.println(token);

                       editor.putString("token", token);
                       editor.putInt("user_id", response.body().getUser().getId());

                       String json = gson.toJson(response.body().getUser());
                       editor.putString("user", json);
                       editor.apply();

                       Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                       startActivity(myIntent);
                   }

               }

               @Override
               public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                   System.out.println("Error codigo: "+t.getMessage());
               }
           });
       }

       catch (Exception e){

       }
    }
}