package com.example.bilet6ex;

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
import java.util.List;

public class ListaBonuriActivity extends AppCompatActivity {
    private ListView lvBonuri;
    private Intent intent;
    private ArrayList<Bon> bonuri;
    private ArrayAdapter arrayAdapter;
    public static int REQ_CODE_EDIT = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE_EDIT && resultCode == RESULT_OK && data != null){
            Bon bon = (Bon) data.getSerializableExtra(AddBonActivity.EDT_BON_KET);
            int index = bonuri.indexOf(bon) + 1;
            Bon bonEditat = bonuri.get(index);
            bonEditat.setNumar(bon.getNumar());
            bonEditat.setServiciu(bon.getServiciu());
            bonEditat.setData(bon.getData());
            bonEditat.setGhiseu(bon.getGhiseu());
            Log.i("OBIECT EDITAT", bon.toString());
            ArrayAdapter adapter = (ArrayAdapter) lvBonuri.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bonuri);
        lvBonuri = findViewById(R.id.lv_bonuri);
        intent = getIntent();
        bonuri = (ArrayList<Bon>) intent.getSerializableExtra(MainActivity.LIST_KEY);
        if(bonuri != null){
            Log.i("LISTA BONURI", bonuri.toString());
            arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, bonuri);
            lvBonuri.setAdapter(arrayAdapter);

            arrayAdapter.notifyDataSetChanged();
        }

        lvBonuri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),bonuri.get(position).toString(), Toast.LENGTH_SHORT).show();
              //  intent.putExtra(MainActivity.BON_KEY, bonuri.get(position));
                Intent editare = new Intent(getApplicationContext(), AddBonActivity.class);
                editare.putExtra(MainActivity.BON_KEY, bonuri.get(position));
                startActivityForResult(editare,REQ_CODE_EDIT);
            }
        });




    }
}