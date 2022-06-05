package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import sv.edu.catolica.project_final.Models.WorkerModel;

public class ProfileWorker extends AppCompatActivity {
    private ListWorker worker;
    private TextView name;
    private TextView location;
    private TextView profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_worker);

        worker = (ListWorker) getIntent().getSerializableExtra("worker");

        name = findViewById(R.id.profile_name);
        location = findViewById(R.id.profile_localtion);
        profession = findViewById(R.id.profile_profession);

        name.setText(worker.getNombre());
        location.setText(worker.getDepartamento());
        profession.setText(worker.getProfesion());
    }

    public void onPop(View view) {
        onBackPressed();
    }
}