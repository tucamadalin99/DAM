package com.example.subiect4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.subiect4.db.SesizariDAO;
import com.example.subiect4.db.SesizariDB;

import java.util.Date;

public class AddActivity extends AppCompatActivity {
    private EditText etTitlu;
    private EditText etDesc;
    private Spinner spnTip;
    private EditText etData;
    private Button addBtn;
    private Intent intent;
    public static final String SESIZARE_KEY = "k1";
    private DateConverter dateConverter = new DateConverter();
    private SesizariDAO sesizariDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
        intent = getIntent();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.tipuri, android.R.layout.simple_spinner_dropdown_item);
        spnTip.setAdapter(adapter);
        addBtn.setOnClickListener(addSesizare());
        populateValues();
        sesizariDAO = Room.databaseBuilder(getApplicationContext(), SesizariDB.class, "BD")
                .fallbackToDestructiveMigration()
                .build().getSesizariDAO();
    }

    private void initViews(){
        etTitlu = findViewById(R.id.etTitlu);
        etDesc = findViewById(R.id.etDescriere);
        spnTip = findViewById(R.id.spnTip);
        etData = findViewById(R.id.etData);
        addBtn = findViewById(R.id.btnAdd);

    }

    private boolean validate(){
        if(etTitlu.getText().toString().isEmpty() || etTitlu.getText().toString().trim().length() < 3){
            Toast.makeText(this, "Titlu necorespunzator", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etDesc.getText().toString().isEmpty() || etDesc.getText().toString().trim().length() < 10){
            Toast.makeText(this, "Descriere insuficienta", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etData.getText().toString().isEmpty()){
            Toast.makeText(this, "Data invalida", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }

    private void populateValues(){
        Sesizare sesizare = (Sesizare)intent.getSerializableExtra(ListActivity.EDT_KEY);
        //int index = (int)intent.getSerializableExtra(ListActivity.INDEX_KEY);
        if(sesizare != null){
            etTitlu.setText(sesizare.getTitlu());
            etData.setText(dateConverter.toString(sesizare.getDataInregistrare()));
            etDesc.setText(sesizare.getDescriere());
            spnTip.setSelection(sesizare.getTip().value);
            addBtn.setText("Editeaza");
        }
    }

    private View.OnClickListener addSesizare(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String titlu = etTitlu.getText().toString();
                    Tip tip = Tip.drumuri;
                    if(spnTip.getSelectedItem().toString().equals("Personal"))
                        tip = Tip.personal;
                    else if(spnTip.getSelectedItem().toString().equals("Iluminat"))
                        tip = Tip.iluminat;
                    else if(spnTip.getSelectedItem().toString().equals("Gaze"))
                        tip = Tip.gaze;
                    else if(spnTip.getSelectedItem().toString().equals("Apa"))
                        tip = Tip.apa;
                    else if(spnTip.getSelectedItem().toString().equals("Canalizare"))
                        tip = Tip.canalizare;
                    String desc = etDesc.getText().toString();
                    Sesizare sesizareEdt = (Sesizare) intent.getSerializableExtra(ListActivity.EDT_KEY);
                    Date date = dateConverter.fromString(etData.getText().toString());
                    final Sesizare sesizare = new Sesizare(titlu,desc,tip,date);
                    if(sesizareEdt != null){
                       sesizareEdt.setTitlu(titlu);
                       sesizareEdt.setDataInregistrare(date);
                       sesizareEdt.setDescriere(desc);
                       sesizareEdt.setTip(tip);
                       intent.putExtra(ListActivity.EDT_KEY, sesizareEdt);
                       int index = (int) intent.getSerializableExtra(ListActivity.INDEX_KEY);
                       intent.putExtra(ListActivity.INDEX_KEY, index);
                       setResult(RESULT_OK,intent);
                       finish();
                    }else{
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sesizariDAO.insert(sesizare);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.v("Obiect introdus in bd: ", sesizare.toString());
                                    }
                                });
                            }
                        }).start();
                        intent.putExtra(SESIZARE_KEY, sesizare);
                        setResult(RESULT_OK,intent);
                        finish();
                    }


                }
            }
        };
    }
}