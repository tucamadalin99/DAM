package com.example.xmlretea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CarAdapter adapter;
    private ListView lvMasini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMasini = findViewById(R.id.lv_masini);

        DownloadManager.getInstance().getMasiniData(new IMasinaResponse() {
            @Override
            public void onSuccess(final ArrayList<Masina> masini) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(Masina m: masini){
                            Log.v("TAG", m.toString());
                        }
                        adapter = new CarAdapter(getApplicationContext(),R.layout.lv_car_item, masini, getLayoutInflater());
                        lvMasini.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onFailure(int errCode, Throwable err) {

            }
        });
    }
}