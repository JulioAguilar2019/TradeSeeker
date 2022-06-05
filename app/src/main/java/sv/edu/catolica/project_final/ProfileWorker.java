package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import sv.edu.catolica.project_final.Models.WorkerModel;

public class ProfileWorker extends AppCompatActivity {
    private ListWorker listWorker;
    private TextView name, location, profession, description, price, phone, category;

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

        name.setText(listWorker.getWorker().getName());
        location.setText(listWorker.getWork().getState().getName());
        profession.setText(listWorker.getWork().getProfession());
        description.setText(listWorker.getWork().getDescription());
        price.setText(listWorker.getWork().getPrice());
        phone.setText(listWorker.getWorker().getPhone());
        category.setText(listWorker.getWork().getCategory().getName());
    }

    public void onPop(View view) {
        onBackPressed();
    }
}