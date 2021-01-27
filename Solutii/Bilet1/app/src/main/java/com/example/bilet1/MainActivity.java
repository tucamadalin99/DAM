package com.example.bilet1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnIstoric;
    private Button btnGrafic;
    private Button btnDespre;
    private TextView tvCopyright;
    private ArrayList<Cursa> curse = new ArrayList<>();
    public static int ADD_REQ_CODE = 110;
    public static final String LIST_KEY = "List_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnDespre.setOnClickListener(afiseazaNume());
        btnAdd.setOnClickListener(adaugaMasina());
        btnIstoric.setOnClickListener(deschideIstoric());
        btnGrafic.setOnClickListener(deschideGrafic());
    }

    private void initViews(){
         btnAdd = findViewById(R.id.btn_add);
         btnIstoric = findViewById(R.id.btn_istoric);
         btnGrafic = findViewById(R.id.btn_grafic);
         btnDespre = findViewById(R.id.btn_despre);
         tvCopyright = findViewById(R.id.tv_copyright);
    }

    private View.OnClickListener afiseazaNume(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] cuvinte = tvCopyright.getText().toString().split(" ");
                Toast.makeText(MainActivity.this, cuvinte[1] + " " + cuvinte[2], Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener adaugaMasina(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCursa.class);
                startActivityForResult(intent, ADD_REQ_CODE);
            }
        };
    }

    private View.OnClickListener deschideIstoric(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IstoricActivitate.class);
                Collections.sort(curse, new Comparator<Cursa>() {
                    @Override
                    public int compare(Cursa o1, Cursa o2) {
                        return o2.getData().compareTo(o1.getData());
                    }
                });
                intent.putExtra(LIST_KEY, curse);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener deschideGrafic(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_REQ_CODE && resultCode == RESULT_OK && data != null){
            //impl
            Cursa cPrel = (Cursa) data.getSerializableExtra(AddCursa.CURSA_KEY);
            Log.v("PARAM", cPrel.toString());
            curse.add(cPrel);

        }
    }
}