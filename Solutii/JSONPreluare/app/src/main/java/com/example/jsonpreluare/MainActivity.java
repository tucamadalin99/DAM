package com.example.jsonpreluare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BookAdapter bookAdapter;
    private ListView lvCarti;
    Button btnDiag;
    private final int REQ_CODE = 100;
    private ArrayList<Book> books = new ArrayList<>();
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCarti=  findViewById(R.id.lv_carti);
        btnDiag = findViewById(R.id.btn_dialog);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(addCarte());


        DownloadManager.getInstance().getBookData(new IBookResponse() {
            @Override
            public void onSuccess(final ArrayList<Book> success) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(Book b: success){
                            Log.v("TAG", b.toString());
                        }
                        bookAdapter = new BookAdapter(getApplicationContext(),R.layout.lv_book_item,success,getLayoutInflater());
                        lvCarti.setAdapter(bookAdapter);
                        books = success;
                    }
                });
            }

            @Override
            public void onSuccessLib(final Library lib) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnDiag.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                                dialog.setTitle("Library name: " + lib.getLibraryName());
                                dialog.setMessage("Users: " + String.valueOf(lib.getUsers()) + ", Schedule: " + lib.getSchedule());
                                dialog.setButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(int errCode, Throwable err) {

            }
        });
    }

    private View.OnClickListener addCarte(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddCarte.class);
                startActivityForResult(intent, REQ_CODE);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE && resultCode == RESULT_OK && data != null){
            Book b = (Book) data.getSerializableExtra(AddCarte.BOOK_KEY);
            books.add(b);
            BookAdapter adapter = (BookAdapter) lvCarti.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }
}