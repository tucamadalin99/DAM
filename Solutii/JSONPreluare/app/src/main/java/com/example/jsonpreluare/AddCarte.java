package com.example.jsonpreluare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddCarte extends AppCompatActivity {
    private TextInputEditText etCarte;
    private TextInputEditText etAutor;
    private Button addBtn;
    private Intent intent;
    public static final String BOOK_KEY = "jey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_carte);
        etCarte = findViewById(R.id.tie_carte);
        etAutor = findViewById(R.id.tie_autor);
        addBtn = findViewById(R.id.btn_add);
        intent = getIntent();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String nume = etCarte.getText().toString();
                    String autor = etAutor.getText().toString();
                    Book b = new Book(nume,autor);
                    intent.putExtra(BOOK_KEY, b);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        });
    }

    private boolean validate(){
        if(etCarte.getText().toString().isEmpty()){
            Toast.makeText(this, "Empty Carte", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(etAutor.getText().toString().isEmpty()){
            Toast.makeText(this, "Empty Autor", Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }
}