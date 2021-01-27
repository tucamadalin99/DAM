package com.example.bilet1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bilet1.db.CurseDAO;
import com.example.bilet1.db.CurseDB;

import java.util.Date;

public class AddCursa extends AppCompatActivity {
    private EditText etDest;
    private EditText etData;
    private RadioGroup rgTip;
    private EditText etCost;
    private Button addBtn;
    private Intent intent;
    private DateConverter dateConverter = new DateConverter();
    private CurseDAO curseDAO;
    public static final String CURSA_KEY = "CURSA_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cursa);
        initViews();
        intent = getIntent();
        addBtn.setOnClickListener(addCursa());
        populateForm();
        curseDAO = Room.databaseBuilder(getApplicationContext(), CurseDB.class, "BD")
                .fallbackToDestructiveMigration()
                .build().getCurseDAO();
    }

    private void initViews(){
        etData = findViewById(R.id.etData);
        etCost = findViewById(R.id.etCost);
        etDest = findViewById(R.id.etDest);
        rgTip = findViewById(R.id.rg_tip);
        addBtn = findViewById(R.id.addBtn);
    }

    private View.OnClickListener addCursa(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String dest = etDest.getText().toString();
                    TipTaxi tip = TipTaxi.normal;
                    if(rgTip.getCheckedRadioButtonId() == R.id.rb_premium){
                        tip = TipTaxi.premium;
                    }
                    Date data = dateConverter.fromString(etData.getText().toString());
                    float cost = Float.parseFloat(etCost.getText().toString());
                    final Cursa c = new Cursa(dest,data,tip,cost);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            curseDAO.insert(c);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddCursa.this, "Inserted in DB", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).start();
                    intent.putExtra(CURSA_KEY, c);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        };
    }

    private boolean validate(){
        if(etDest.getText().toString().isEmpty() || etDest.getText().toString().trim().length() < 3){
            Toast.makeText(this, "Invalid destinatie", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etData.getText().toString().isEmpty() || etData.getText().toString().trim().length() < 3){
            Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etCost.getText().toString().isEmpty()){
            Toast.makeText(this, "Invalid cost", Toast.LENGTH_SHORT).show();
            return false;
        }  

        return true;
    }

    private void populateForm(){
        Cursa c = (Cursa) intent.getSerializableExtra(IstoricActivitate.SELECTED_CURSA_KEY);
        if(c != null){
            etCost.setText(String.valueOf(c.getCost()));
            etData.setText(dateConverter.toString(c.getData()));
            etDest.setText(c.getDestinatie());
            rgTip.check(R.id.rb_premium);
            if(c.getTipTaxi().equals(TipTaxi.normal)){
                rgTip.check(R.id.rb_normal);
            }
        }
    }
}