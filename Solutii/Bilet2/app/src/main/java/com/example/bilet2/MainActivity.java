package com.example.bilet2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        if(item.getItemId() == R.id.nav_add){
            selectedFragment = new FragmentAdd();
        }
        if(item.getItemId() == R.id.nav_lista){
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            Bundle bundle = new Bundle();
            Jucator jucator = bundle.getParcelable(FragmentAdd.JUCATOR_KEY);
            Log.v("TAG", jucator.toString());
            //startActivity(intent);
        }
        if(item.getItemId() == R.id.nav_formatie){

        }
        if(item.getItemId() == R.id.nav_despre){
            Toast.makeText(this, "Aplicatie realizata de Tuca Madalin", Toast.LENGTH_SHORT).show();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
        return true;
    }
}