package com.example.bilet3autobuz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bilet3autobuz.db.AutobuzDAO;
import com.example.bilet3autobuz.db.AutobuzDB;

public class AddAutobuz extends AppCompatActivity {
    private EditText etNrInmac;
    private Spinner spLinie;
    private EditText etSofer;
    private EditText etNrLoc;
    private Button btnAdd;
    private Button btnEdt;
    private Integer[] spnValues = {1,2,3,4,5,6};
    private Intent intent;
    public static String AUTOBUZ_KEY =  "Autobuz_key";
    private AutobuzDAO autobuzDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_autobuz);
        initViews();
        ArrayAdapter spnAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,spnValues);
        spLinie.setAdapter(spnAdapter);
        intent = getIntent();
        btnAdd.setOnClickListener(addAutobuz());
        populateValues();
        btnEdt.setOnClickListener(editAutobuz());
        autobuzDAO = (AutobuzDAO) Room.databaseBuilder(getApplicationContext(),AutobuzDB.class,"BD1").fallbackToDestructiveMigration().build().getAutobuzDAO();

    }

    private void initViews(){
        etNrInmac = findViewById(R.id.et_inmatriculare);
        spLinie = findViewById(R.id.sp_linie);
        etSofer = findViewById(R.id.et_sofer);
        etNrLoc = findViewById(R.id.et_nrLoc);
        btnAdd = findViewById(R.id.btn_add);
        btnEdt = findViewById(R.id.btn_edit);
    }

    private View.OnClickListener addAutobuz(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String nrInmac=etNrInmac.getText().toString();
                    int linie = Integer.parseInt(spLinie.getSelectedItem().toString());
                    String sofer = etSofer.getText().toString();
                    int nrLocuri = Integer.parseInt(etNrLoc.getText().toString());
                    final Autobuz autobuz = new Autobuz(nrInmac,linie,sofer,nrLocuri);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            autobuzDAO.insert(autobuz);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.v("Adaugat in bd",autobuz.toString());
                                }
                            });
                        }
                    }).start();
                    intent.putExtra(AUTOBUZ_KEY,autobuz);
                    setResult(RESULT_OK,intent);
                    Log.v("TEST", autobuz.toString());
                    finish();
                }

            }
        };
    }

    private boolean validate(){
        if(etNrInmac.getText().toString().isEmpty() || etNrInmac.getText().toString().trim().length() < 7) {
            Toast.makeText(getApplicationContext(), "Nr. inmatriculare gol/invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(spLinie.getSelectedItem() == null){
            Toast.makeText(this, "Linie invalida", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etSofer.getText().toString().isEmpty()){
            Toast.makeText(this, "Sofer invalid", Toast.LENGTH_SHORT).show();
        }
        if(etNrLoc.getText().toString().isEmpty()){
            Toast.makeText(this, "Nr locuri gol", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private void populateValues(){
        Autobuz bus = (Autobuz) intent.getSerializableExtra(ActivityList.EDT_BUS);
        if(bus != null){
            Log.v("TAG2", "TEST");
            etNrInmac.setText(String.valueOf(bus.getNrInmatriculare()));
            spLinie.setSelection(bus.getLinie() - 1);
            etSofer.setText(bus.getSofer());
            etNrLoc.setText(String.valueOf(bus.getNrLocuri()));
        }

    }

    private View.OnClickListener editAutobuz(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String nrInmat = etNrInmac.getText().toString();
                    int linie =Integer.parseInt(spLinie.getSelectedItem().toString());
                    String sofer = etSofer.getText().toString();
                    int nrLoc = Integer.parseInt(etNrLoc.getText().toString());
                    Autobuz bus = new Autobuz(nrInmat,linie,sofer,nrLoc);
                    int index = (int)intent.getSerializableExtra(ActivityList.INDEX_BUS);
                    intent.putExtra(AUTOBUZ_KEY, bus);
                    intent.putExtra(ActivityList.INDEX_BUS, index);
                    setResult(RESULT_OK, intent);
                    finish();

                }
            }
        };
    }
}