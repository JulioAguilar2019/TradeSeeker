package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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

public class Login extends AppCompatActivity {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://trades-seeker.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Thread.sleep(2000);
            setTheme(R.style.Theme_ProjectFinal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences("myPreferences", getBaseContext().MODE_PRIVATE);

        String token = sharedPref.getString("token", null);

        if (token != null){
            Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(myIntent);
        }
    }


    public void onLogin(View view) {
        try {


            TextInputEditText email = this.findViewById(R.id.txtLoginEmail);
            TextInputEditText password = this.findViewById(R.id.txtLoginPassword);

            if (email == null || email.getText().toString().isEmpty() || password == null ||password.getText().toString().isEmpty()) {
               Snackbar snackbar = Snackbar.make(view, "Los campos no pueden estar vacios", Snackbar.LENGTH_LONG);
               snackbar.show();
            }
            LoginModel login = new LoginModel(email.getText().toString(), password.getText().toString());

            LoginApi loginApi = retrofit.create(LoginApi.class);

            Call<LoginResponseModel> call = loginApi.login(login);

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
        } catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public void goRegister(View view) {
        Intent myIntent = new Intent(getBaseContext(), register.class);
        startActivity(myIntent);
    }
}