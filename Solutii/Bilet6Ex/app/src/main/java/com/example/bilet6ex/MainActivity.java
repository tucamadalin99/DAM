package com.example.bilet6ex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE = 1;
    public static final String BON_KEY = "bon_key";
    public static final String LIST_KEY = "list_key";
    private List<Bon> bonuri = new ArrayList<>();
    private Bon bon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.menu_despre){
            Toast.makeText(getApplicationContext(),"Tuca Madalin", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.menu_add_bon){
            Intent intent = new Intent(getApplicationContext(), AddBonActivity.class);
            startActivityForResult(intent, REQ_CODE);
        }
        if(item.getItemId() == R.id.menu_lista_bon){
            Intent intent = new Intent(getApplicationContext(), ListaBonuriActivity.class);
            intent.putExtra(LIST_KEY, (Serializable) bonuri);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.menu_preluare){

        }
        if(item.getItemId() == R.id.menu_raport){

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE && resultCode == RESULT_OK && data != null){
            bon =(Bon) data.getSerializableExtra(AddBonActivity.BON_KEY);
            Toast.makeText(getApplicationContext(),bon.toString(), Toast.LENGTH_SHORT).show();
            bonuri.add(bon);
            Log.i("LISTA", bonuri.toString());
            Toast.makeText(getApplicationContext(), "Bon adaugat", Toast.LENGTH_SHORT).show();
        }
    }
}