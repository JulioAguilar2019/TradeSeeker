package sv.edu.catolica.project_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.catolica.project_final.Models.Departamento;

public class PublicarTrabajo extends AppCompatActivity {


    AutoCompleteTextView autoCompleteDepartamento;
    ArrayAdapter<String> adapterDepartamentos;
    String[] list_departamentos = {"Ahuachapán","Cabañas","Chalatenango","Cuscatlán","La Libertad","Morazán", "La Paz","Santa Ana", "San Miguel","San Salvador","San Vicente","Sonsonate","La Unión","Usulután"};

    AutoCompleteTextView autoCompleteDispo;
    ArrayAdapter<String> adapterDisponi;
    String[] list_Dispo = {"Tiempo completo", "Jornada parcial"};

    ArrayAdapter<String> adapterTime;
    AutoCompleteTextView autoCompleteTime;
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
            }
        });

        autoCompleteDispo = findViewById(R.id.list_disponibilidad);
        adapterDisponi = new ArrayAdapter<String>(getBaseContext(),R.layout.genero_list, list_Dispo);

        autoCompleteDispo.setAdapter(adapterDisponi);

        autoCompleteDispo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
            }
        });
    }

    public void close(View view) {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);

    }





//
//    public void setDataDeparment() {
//        ArrayList<Departamento> departamentoArrayList = new ArrayList<>();
//
//        departamentoArrayList.add(new Departamento("1","Ahuachapán"));
//        departamentoArrayList.add(new Departamento("2","Cabañas"));
//        departamentoArrayList.add(new Departamento("3","Chalatenango"));
//        departamentoArrayList.add(new Departamento("4","Cuscatlán"));
//        departamentoArrayList.add(new Departamento("5","La Libertad"));
//        departamentoArrayList.add(new Departamento("6","La Paz"));
//        departamentoArrayList.add(new Departamento("7","La Unión"));
//        departamentoArrayList.add(new Departamento("8","Morazán"));
//        departamentoArrayList.add(new Departamento("9","San Miguel"));
//        departamentoArrayList.add(new Departamento("10","San Salvador"));
//        departamentoArrayList.add(new Departamento("11","San Vicente"));
//        departamentoArrayList.add(new Departamento("12","Santa Ana"));
//        departamentoArrayList.add(new Departamento("13","Sonsonate"));
//        departamentoArrayList.add(new Departamento("14","Usulután"));
//
//        autoCompleteDepartamento = findViewById(R.id.list_department);
//        ArrayAdapter<Departamento> departamentoArrayAdapter = new ArrayAdapter<Departamento>(getBaseContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, departamentoArrayList);
//
//        autoCompleteDepartamento.setAdapter(departamentoArrayAdapter);
//
//        autoCompleteDepartamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                Departamento item = (Departamento) parent.getSelectedItem();
//                Toast.makeText(getBaseContext(), "Departamento: " + item.getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }


}