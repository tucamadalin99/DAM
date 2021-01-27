package com.example.bilet6ex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AddBonActivity extends AppCompatActivity {

    private TextInputEditText tieNumar;
    private TextInputEditText tieData;
    private TextInputEditText tieGhiseu;
    private RadioGroup rgServiciu;
    private Button btnAdd;
    private Button btnEdit;
    private DateConverter converter;
    private Intent intent;
    public static final String BON_KEY = "bon_key";
    public static final String EDT_BON_KET = "edt_bon_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bon);
        initViews();
        converter = new DateConverter();
        btnAdd.setOnClickListener(addBon());
        intent = getIntent();
        populateValues();
        btnEdit.setOnClickListener(editBon());

    }

    private void initViews(){
        tieNumar = findViewById(R.id.tie_numar);
        tieData = findViewById(R.id.tie_data);
        tieGhiseu = findViewById(R.id.tie_ghiseu);
        rgServiciu = findViewById(R.id.rg_serviciu);
        btnAdd = findViewById(R.id.btn_submit);
        btnEdit = findViewById(R.id.edit_btn);
    }

    private View.OnClickListener addBon(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    int numar = Integer.parseInt(tieNumar.getText().toString());
                    ServiceType serviciu = ServiceType.plata;
                    if(rgServiciu.getCheckedRadioButtonId() == R.id.rb_abonament)
                        serviciu = ServiceType.abonament;
                    if(rgServiciu.getCheckedRadioButtonId() == R.id.rb_ach)
                        serviciu = ServiceType.achizitie;
                    Date data = converter.fromString(tieData.getText().toString());
                    int ghiseu = Integer.parseInt(tieGhiseu.getText().toString());
                    Bon bon = new Bon(numar,serviciu,data,ghiseu);
                    intent.putExtra(BON_KEY, bon);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private View.OnClickListener editBon(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    int numar = Integer.parseInt(tieNumar.getText().toString());
                    ServiceType serviciu =  ServiceType.plata;
                    if(rgServiciu.getCheckedRadioButtonId() == R.id.rb_abonament)
                        serviciu = ServiceType.abonament;
                    if(rgServiciu.getCheckedRadioButtonId() == R.id.rb_ach);
                        serviciu = ServiceType.achizitie;

                        Date data = converter.fromString(tieData.getText().toString());
                        int ghiseu = Integer.parseInt(tieGhiseu.getText().toString());
                        Bon bon = new Bon(numar,serviciu,data,ghiseu);
                        intent.putExtra(EDT_BON_KET, bon);
                        setResult(RESULT_OK,intent);
                        Log.i("TAG EDIT", bon.toString());
                        finish();
                }
            }
        };
    }

    private boolean validate(){
        if(tieNumar.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Numar necompletat", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tieData.getText() == null || converter.fromString(tieData.getText().toString().trim()) == null){
            Toast.makeText(getApplicationContext(),"Data invalida", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tieGhiseu.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Ghiseu invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void populateValues() {
        Bon bon = (Bon) intent.getSerializableExtra(BON_KEY);
        if (bon != null) {
            tieNumar.setText(String.valueOf(bon.getNumar()));
            tieGhiseu.setText(String.valueOf(bon.getGhiseu()));
            tieData.setText(converter.toString(bon.getData()));
            rgServiciu.check(R.id.rb_plata);
            if(bon.getServiciu().toString().equals("plata"))
                rgServiciu.check(R.id.rb_plata);
            if(bon.getServiciu().toString().equals("abonament"))
                rgServiciu.check(R.id.rb_abonament);
            else
                rgServiciu.check(R.id.rb_ach);
        }
    }
}