package com.example.bilet3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaAutovehicule extends AppCompatActivity {
    private ListView lvAuto;
    private Intent intent;
    private ArrayList<Autovehicul> vehiculePreluateList;
    private ArrayAdapter adapter;
    public static final String EDIT_VEHICULE = "EDIT_KEY";
    public static final String INDEX_VEH = "index_key";
    public static final int EDIT_REQ = 211;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_autovehicule);
        lvAuto = findViewById(R.id.lv_vehicule);
        intent = getIntent();
        vehiculePreluateList = (ArrayList<Autovehicul>) intent.getSerializableExtra(MainActivity.LIST_VEH_KEY);
        adapter= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, vehiculePreluateList);
        lvAuto.setAdapter(adapter);

        lvAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Apasat", Toast.LENGTH_SHORT).show();
                Intent editIntent = new Intent(getApplicationContext(), AddVehiculActivity.class);
                editIntent.putExtra(EDIT_VEHICULE, vehiculePreluateList.get(position));
                editIntent.putExtra(INDEX_VEH, position);
                startActivityForResult(editIntent, EDIT_REQ);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_REQ && resultCode == RESULT_OK && data != null){
            Autovehicul vehEditat = (Autovehicul)data.getSerializableExtra(AddVehiculActivity.VEHICUL_EDT_KEY);
            int index = (int)data.getSerializableExtra(INDEX_VEH);
            Autovehicul lvItemVeh = vehiculePreluateList.get(index);
            lvItemVeh.setNumarAuto(vehEditat.getNumarAuto());
            lvItemVeh.setDataInregistare(vehEditat.getDataInregistare());
            lvItemVeh.setIdLocParcare(vehEditat.getIdLocParcare());
            lvItemVeh.setaPlatit(vehEditat.isaPlatit());
            ArrayAdapter adapter = (ArrayAdapter) lvAuto.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }
}