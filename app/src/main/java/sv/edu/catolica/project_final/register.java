package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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
}