package com.example.bilet3autobuz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
public static final int ADD_REQ_CODE = 201;
public static final String LIST_CODE = "lista_cod";
private ArrayList<Autobuz>autobuze = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.main_menu, menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.nav_despre){
            Toast.makeText(getApplicationContext(),"Tuca Madalin",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.nav_add_autobuz){
            Intent intent = new Intent(getApplicationContext(), AddAutobuz.class);
            startActivityForResult(intent,ADD_REQ_CODE);
        }
        if(item.getItemId()==R.id.nav_list){
            Intent intent = new Intent(getApplicationContext(),ActivityList.class);
            intent.putExtra(LIST_CODE, autobuze);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.nav_raport){
            Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
            startActivity(intent);
        }

        return true;
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_REQ_CODE && resultCode == RESULT_OK && data != null){
            Log.v("TAG","TEST");
            Autobuz autobuz = (Autobuz) data.getSerializableExtra(AddAutobuz.AUTOBUZ_KEY);
            Log.v("AUTOBUZ", autobuz.toString());
            autobuze.add(autobuz);
        }
    }
}