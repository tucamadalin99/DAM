package com.example.jsonpreluare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private List<Book> books;
    private LayoutInflater inflater;
    private int resource;
    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.books = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource,parent,false);
        Book book = books.get(position);
        if(book != null){
            TextView tvCarte = view.findViewById(R.id.tv_carte);
            tvCarte.setText(book.getName());
            TextView tvAutor = view.findViewById(R.id.tv_autor);
            tvAutor.setText(book.getAuthor());
        }
        return view;
    }
}
