package com.example.bilet2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FragmentAdd extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etNume;
    private EditText etNumar;
    private RadioGroup rgPoz;
    private Button addBtn;
    public static final String JUCATOR_KEY = "key-j";

    public FragmentAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        initViews(view);
        addBtn.setOnClickListener(addJucator());

        return view;
    }

    private void initViews(View v){
        etNume = v.findViewById(R.id.et_nume);
        etNumar = v.findViewById(R.id.et_numar);
        rgPoz = v.findViewById(R.id.rg_poz);
        addBtn = v.findViewById(R.id.btn_add);
    }

    private View.OnClickListener addJucator(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String nume = etNume.getText().toString();
                    int numar = Integer.parseInt(etNumar.getText().toString());
                    Pozitie poz = Pozitie.portar;
                    if(rgPoz.getCheckedRadioButtonId() == R.id.rb_fundas){
                        poz = Pozitie.fundas;
                    }
                    else if(rgPoz.getCheckedRadioButtonId() == R.id.rb_mijlocas){
                        poz = Pozitie.mijlocas;
                    }
                    else if(rgPoz.getCheckedRadioButtonId() == R.id.rb_atacant){
                        poz = Pozitie.atacant;
                    }
                    Jucator jucator = new Jucator(nume,numar,poz);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(JUCATOR_KEY, jucator);
                    Toast.makeText(getContext(), "Jucator Adaugat", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    private boolean validate(){
        if(etNume.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Nume gol", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etNumar.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Numar gol", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
}