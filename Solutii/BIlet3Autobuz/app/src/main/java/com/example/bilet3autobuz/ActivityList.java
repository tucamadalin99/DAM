package com.example.bilet3autobuz;

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

public class ActivityList extends AppCompatActivity {
    private Intent intent;
    private ListView lvAutobuze;
    public static final String EDT_BUS = "Edit_key";
    public static final String INDEX_BUS = "Index";
    public static final int EDIT_REQ = 215;
    ArrayList<Autobuz>autobuze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvAutobuze = findViewById(R.id.lv_autobuze);
        intent = getIntent();
        autobuze = (ArrayList<Autobuz>) intent.getSerializableExtra(MainActivity.LIST_CODE);
        if(autobuze != null){
            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, autobuze);
            lvAutobuze.setAdapter(adapter);
        }

        lvAutobuze.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getApplicationContext(), AddAutobuz.class);
                intent.putExtra(INDEX_BUS, position);
                intent.putExtra(EDT_BUS, autobuze.get(position));
                setResult(RESULT_OK);
                startActivityForResult(intent, EDIT_REQ);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_REQ && resultCode == RESULT_OK && data != null){
            Autobuz bus = (Autobuz)data.getSerializableExtra(AddAutobuz.AUTOBUZ_KEY);
            Log.v("EDITAT", bus.toString());
            int index = (int)data.getSerializableExtra(INDEX_BUS);
            Autobuz editat = autobuze.get(index);
            editat.setLinie(bus.getLinie());
            editat.setNrInmatriculare(bus.getNrInmatriculare());
            editat.setSofer(bus.getSofer());
            editat.setNrLocuri(bus.getNrLocuri());
            ArrayAdapter adapter = (ArrayAdapter) lvAutobuze.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }
}