package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
import sv.edu.catolica.project_final.Models.WorkModel;
import sv.edu.catolica.project_final.Models.WorkerModel;

public class ProfileWorker extends AppCompatActivity {
    private ListWorker listWorker;
    private TextView name, location, profession, description, price, phone, category, exp, schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_worker);

        listWorker = (ListWorker) getIntent().getSerializableExtra("listwork");

        name = findViewById(R.id.profile_name);
        location = findViewById(R.id.profile_localtion);
        profession = findViewById(R.id.profile_profession);
        description = findViewById(R.id.profile_description);
        price = findViewById(R.id.profile_price);
        phone = findViewById(R.id.profile_phone);
        category = findViewById(R.id.profile_category);
        exp = findViewById(R.id.profile_exp);
        schedule = findViewById(R.id.profile_schedule);

        name.setText(listWorker.getWorker().getName());
        location.setText(listWorker.getWork().getState().getName());
        profession.setText(listWorker.getWork().getProfession());
        description.setText(listWorker.getWork().getDescription());
        price.setText("$ "+listWorker.getWork().getPrice());
        phone.setText(listWorker.getWorker().getPhone());
        category.setText(listWorker.getWork().getCategory().getName());
        exp.setText(listWorker.getWork().getExperience());
        schedule.setText(listWorker.getWork().getSchedule());



    }

    public void onPop(View view) {
        onBackPressed();
    }

    public void onPhoneCall(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+phone.getText()));
            startActivity(intent);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}