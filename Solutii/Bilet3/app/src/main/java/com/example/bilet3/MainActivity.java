package com.example.bilet3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bilet3.db.AutovehiculBD;
import com.example.bilet3.db.AutovehiculDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_VEH_REQ = 100;
    public static final String LIST_VEH_KEY="LIST_KEY";
    private ArrayList<Autovehicul> vehicule = new ArrayList<>();
    private AutovehiculDAO vehiculDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vehiculDAO = Room.databaseBuilder(getApplicationContext(), AutovehiculBD.class, "BD")
                .fallbackToDestructiveMigration()
                .build().getAutovehiculDAO();

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
        if(item.getItemId() == R.id.nav_add_veh){
            Intent intent = new Intent(getApplicationContext(), AddVehiculActivity.class);
            startActivityForResult(intent, ADD_VEH_REQ);
        }
        if(item.getItemId() == R.id.nav_list_veh){
            Intent intent = new Intent(getApplicationContext(), ListaAutovehicule.class);
            intent.putExtra(LIST_VEH_KEY, vehicule);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.nav_preluare){

        }
        if(item.getItemId() == R.id.nav_raport){
            Intent intent = new Intent(getApplicationContext(), RaportActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.nav_despre){
            Toast.makeText(getApplicationContext(),getString(R.string.nume_autor),Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_VEH_REQ && resultCode == RESULT_OK && data != null){
            Autovehicul vehicul = (Autovehicul) data.getSerializableExtra(AddVehiculActivity.VEHICUL_KEY);
            Log.i("VEHICUL PRELUAT" , vehicul.toString());
            vehicule.add(vehicul);
        }
    }
}