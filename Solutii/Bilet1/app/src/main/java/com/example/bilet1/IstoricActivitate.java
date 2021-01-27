package com.example.bilet1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bilet1.db.CurseDAO;
import com.example.bilet1.db.CurseDB;

import java.util.ArrayList;
import java.util.List;

public class IstoricActivitate extends AppCompatActivity {
    private Intent intent;
    private ListView lvCurse;
    private List<Cursa> curse;
    private ArrayAdapter adapter;
    private CurseDAO curseDAO;
    public final static String SELECTED_CURSA_KEY = "Cursa_select_key";
    public final static int REQ_VIEW_ITEM = 201;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric_activitate);
        lvCurse = findViewById(R.id.lvCurse);
        intent = getIntent();
        curseDAO = Room.databaseBuilder(getApplicationContext(), CurseDB.class, "BD")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build().getCurseDAO();
       // curse = (ArrayList<Cursa>) intent.getSerializableExtra(MainActivity.LIST_KEY);
        curse = curseDAO.getCurse();
        adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, curse);
        lvCurse.setAdapter(adapter);
        lvCurse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getApplicationContext(), AddCursa.class);
                intent.putExtra(SELECTED_CURSA_KEY, curse.get(position));
                setResult(RESULT_OK,intent);
                startActivityForResult(intent,REQ_VIEW_ITEM);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_VIEW_ITEM && requestCode == RESULT_OK && data != null){
            Cursa cursa = (Cursa) data.getSerializableExtra(AddCursa.CURSA_KEY);
            Log.v("Cursa vizualizata", cursa.toString());
        }
    }
}