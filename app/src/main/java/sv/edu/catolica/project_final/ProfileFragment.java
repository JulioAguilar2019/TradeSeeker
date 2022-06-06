package sv.edu.catolica.project_final;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

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
    String[] list_departamentos = {"Ahuachapán","Cabañas","Chalatenango","Cuscatlán","La Libertad","La Paz","La Unión","Morazán", "San Miguel","San Salvador","San Vicente","Santa Ana","Sonsonate","Usulután"};

    AutoCompleteTextView autoCompleteGenders;
    AutoCompleteTextView autoCompleteDepartamento;
    ArrayAdapter<String> adapterDepartamentos;
    ArrayAdapter<String> adaptarGenders;

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
        textDate = root.findViewById(R.id.BornDate);

        autoCompleteDepartamento = root.findViewById(R.id.list_department);
        adapterDepartamentos = new ArrayAdapter<String>(container.getContext(),R.layout.genero_list, list_departamentos);

        autoCompleteDepartamento.setAdapter(adapterDepartamentos);

        autoCompleteDepartamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
                Toast.makeText(container.getContext(), "Departamento: " + item, Toast.LENGTH_LONG).show();
            }
        });

        autoCompleteGenders = root.findViewById(R.id.gender_list);
        adaptarGenders = new ArrayAdapter<String>(container.getContext(), R.layout.genero_list, list_genders);

        autoCompleteGenders.setAdapter(adaptarGenders);

        autoCompleteGenders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item = parent.getItemAtPosition(i).toString();
                Toast.makeText(container.getContext(), "Genero: " + item, Toast.LENGTH_LONG).show();
            }
        });
        // Inflate the layout for this fragment
        return root;

    }




}