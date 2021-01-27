package com.example.subiect4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.subiect4.db.SesizariDAO;
import com.example.subiect4.db.SesizariDB;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final Date DATE = new Date(System.currentTimeMillis());
    public static final String DATE_KEY = "time";
    public static int ADD_REQ = 123;
    public static final String LIST_KEY = "k2";
    public SesizariDAO sesizariDAO;
    private ArrayList<Sesizare> sesizari = new ArrayList<>();
    ArrayList<Sesizare> sesizariSelect;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long date = DATE.getTime();
         sharedPreferences  = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(DATE_KEY, date);
        editor.apply();
        sesizariDAO = Room.databaseBuilder(getApplicationContext(), SesizariDB.class, "BD")
                .fallbackToDestructiveMigration()
                .build().getSesizariDAO();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sesizariSelect = (ArrayList<Sesizare>) sesizariDAO.getAllDB();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("Lista din DB: ", sesizariSelect.toString());
                    }
                });
            }
        }).start();
      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_add){
            Intent intent = new Intent(getApplicationContext(),AddActivity.class);
            startActivityForResult(intent,ADD_REQ);
        }
        if(item.getItemId() == R.id.nav_lista)
        {
            Intent intent = new Intent(getApplicationContext(),ListActivity.class);
            intent.putExtra(LIST_KEY,sesizari);
            startActivity(intent);

        }
        if(item.getItemId() == R.id.nav_preluareJSON){
            DownloadManager.getInstance().getSesizariData(new ISesizariResponse() {
                @Override
                public void onSucces(final ArrayList<Sesizare> sesizariRetea) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for(Sesizare s : sesizariRetea){
                                sesizari.add(s);
                            }
                        }
                    });
                }

                @Override
                public void onSuccesXML(final ArrayList<Sesizare> sesizariReteaXML) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for(Sesizare s : sesizariReteaXML){
                                Log.v("XML", s.toString());
                                sesizari.add(s);
                            }
                        }
                    });
                }

                @Override
                public void onFailure(int errCode, Throwable err) {
                    Log.v("ERRORS", err.toString());
                }
            });
            Toast.makeText(this, "JSON Preluat", Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId() == R.id.nav_preluareXML){
            DownloadManager.getInstance().getSesizariXML(new ISesizariResponse() {


                @Override
                public void onSucces(ArrayList<Sesizare> sesizariRetea) {
                    
                }

                @Override
                public void onSuccesXML(final ArrayList<Sesizare> sesizariReteaXML) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for(Sesizare s : sesizariReteaXML){
                                Log.v("XML", s.toString());
                                sesizari.add(s);
                            }
                        }
                    });
                }

                @Override
                public void onFailure(int errCode, Throwable err) {
                    Log.v("ERRORS", err.toString());
                }
            });

            Toast.makeText(this, "XML Preluat", Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId() == R.id.nav_raport){
            Intent intent = new Intent(getApplicationContext(),RaportActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.nav_despre){
            long date = sharedPreferences.getLong(DATE_KEY,0);
            Date data = new Date(date);
            Toast.makeText(this, getString(R.string.autor) + data.toString(), Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_REQ && resultCode == RESULT_OK && data != null){
            Sesizare sesi = (Sesizare)data.getSerializableExtra(AddActivity.SESIZARE_KEY);
            Log.v("TAG", sesi.toString());
            sesizari.add(sesi);
        }
    }
}