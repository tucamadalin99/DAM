package com.example.bilet3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bilet3.db.AutovehiculBD;
import com.example.bilet3.db.AutovehiculDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddVehiculActivity extends AppCompatActivity {
    private EditText etNrAuto;
    private EditText etData;
    private EditText etId;
    private RadioGroup rgPlata;
    private Button btnAdd;
    private Button btnEdt;
    private DateConverter dateConverter = new DateConverter();
    private Intent intent;
    private AutovehiculDAO vehiculDAO;
    public static final String VEHICUL_KEY = "veh_key";
    public static final String VEHICUL_EDT_KEY = "veh_key_edt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicul);
        initViews();
        intent = getIntent();
        btnAdd.setOnClickListener(addVehicul());
        populateForm();
        btnEdt.setOnClickListener(editVehicul());
        vehiculDAO = Room.databaseBuilder(getApplicationContext(), AutovehiculBD.class, "BD")
                .fallbackToDestructiveMigration()
                .build().getAutovehiculDAO();

    }

    private void initViews(){
        etNrAuto = findViewById(R.id.et_nrAuto);
        etData = findViewById(R.id.et_data);
        etId = findViewById(R.id.et_id);
        rgPlata = findViewById(R.id.rg_plata);
        btnAdd = findViewById(R.id.btn_add);
        btnEdt = findViewById(R.id.btn_edt);

    }

    private boolean validate(){
        if(etNrAuto.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Numar auto gol", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etData.getText() == null ||etData.getText().toString().trim().length() < 3){
            Toast.makeText(getApplicationContext(), "Data goala", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etId.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"ID gol", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private View.OnClickListener addVehicul(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    int nrAuto = Integer.parseInt(etNrAuto.getText().toString());
                    String data = etData.getText().toString();
                    int id = Integer.parseInt(etId.getText().toString());
                    boolean aPlatit = false;
                    if(rgPlata.getCheckedRadioButtonId() == R.id.rb_platit)
                        aPlatit = true;
                    final Autovehicul veh = new Autovehicul(nrAuto,data,id,aPlatit);
                    Log.i("VEhicul", veh.toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            vehiculDAO.insert(veh);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("Inserted", veh.toString());
                                }
                            });
                        }
                    }).start();

                    intent.putExtra(VEHICUL_KEY, veh);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private View.OnClickListener editVehicul(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    int nr = Integer.parseInt(etNrAuto.getText().toString());
                    String data = etData.getText().toString();
                    int idLoc = Integer.parseInt(etId.getText().toString());
                    boolean aPlatit = false;
                    if(rgPlata.getCheckedRadioButtonId() == R.id.rb_platit)
                        aPlatit = true;
                    Autovehicul vehicul = new Autovehicul(nr,data,idLoc,aPlatit);
                    int index = (int) intent.getSerializableExtra(ListaAutovehicule.INDEX_VEH);
                    intent.putExtra(VEHICUL_EDT_KEY, vehicul);
                    intent.putExtra(ListaAutovehicule.INDEX_VEH, index);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void populateForm(){
        Autovehicul veh = (Autovehicul) intent.getSerializableExtra(ListaAutovehicule.EDIT_VEHICULE);
        if(veh != null){
            Log.i("VEHICUL EDITARE", veh.toString());
            etNrAuto.setText(String.valueOf(veh.getNumarAuto()));
            etData.setText(veh.getDataInregistare());
            etId.setText(String.valueOf(veh.getIdLocParcare()));
            if(veh.isaPlatit()){
                rgPlata.check(R.id.rb_platit);
            }else
                rgPlata.check(R.id.rb_nuplatit);
        }
    }
}