package com.example.subiect4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView lvSesizari;
    private Intent intent;
    private SesizareAdapter adapter;
    private ArrayList<Sesizare>sesizariPreluate;
    public static final String EDT_KEY ="edtkey";
    public static final String INDEX_KEY = "index_lv";
    public static final int EDT_REQ = 120;
    public static final String TIP_EDT = "editare_lv_item";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDT_REQ && resultCode == RESULT_OK && data != null){
            Sesizare sesizareEditata = (Sesizare)data.getSerializableExtra(EDT_KEY);
            int index = (int)data.getSerializableExtra(INDEX_KEY);
            sesizariPreluate.get(index).setTitlu(sesizareEditata.getTitlu());
            sesizariPreluate.get(index).setTip(sesizareEditata.getTip());
            sesizariPreluate.get(index).setDataInregistrare(sesizareEditata.getDataInregistrare());
            sesizariPreluate.get(index).setDescriere(sesizareEditata.getDescriere());
            SesizareAdapter adapter = (SesizareAdapter) lvSesizari.getAdapter();
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvSesizari = findViewById(R.id.lv_sesizari);
        intent = getIntent();
        sesizariPreluate = (ArrayList<Sesizare>) intent.getSerializableExtra(MainActivity.LIST_KEY);
        adapter = new SesizareAdapter(getApplicationContext(),R.layout.lv_item, sesizariPreluate, getLayoutInflater());
        lvSesizari.setAdapter(adapter);

        lvSesizari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra(INDEX_KEY,position);
                intent.putExtra(EDT_KEY,sesizariPreluate.get(position));
                Log.v("INDEX", position+"");
                setResult(RESULT_OK,intent);
                startActivityForResult(intent, EDT_REQ);
            }
        });



    }
}