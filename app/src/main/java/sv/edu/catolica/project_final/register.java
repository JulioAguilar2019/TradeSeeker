package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class register extends AppCompatActivity {

    String[] list_genders = {"Masculino", "Femenino"};
    String[] list_departamentos = {"Ahuachapán","Cabañas","Chalatenango","Cuscatlán","La Libertad","Morazán", "La Paz","Santa Ana", "San Miguel","San Salvador","San Vicente","Sonsonate","La Unión","Usulután"};

    AutoCompleteTextView autoCompleteGenders;
    AutoCompleteTextView autoCompleteDepartamento;
    ArrayAdapter<String> adapterDepartamentos;
    ArrayAdapter<String> adaptarGenders;
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
            }
        });

        autoCompleteGenders = findViewById(R.id.gender);
        adaptarGenders = new ArrayAdapter<String>(getBaseContext(), R.layout.genero_list, list_genders);

        autoCompleteGenders.setAdapter(adaptarGenders);

        autoCompleteGenders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
            }
        });

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
            }
       }

       catch (Exception e){

       }
    }
}