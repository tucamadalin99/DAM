package com.example.subiect4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SesizareAdapter extends ArrayAdapter<Sesizare> {
    LayoutInflater inflater;
    int resource;
    List<Sesizare>sesizari;
    Context context;
    DateConverter dc = new DateConverter();
    public SesizareAdapter(@NonNull Context context, int resource, @NonNull List<Sesizare> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.sesizari = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource,parent,false);
        TextView tvTitlu = view.findViewById(R.id.tv_titlu);
        tvTitlu.setText("Titlu Sesizare: " + sesizari.get(position).titlu);
        TextView tvTip = view.findViewById(R.id.tv_tip);
        tvTip.setText("Tip: " + sesizari.get(position).tip.toString());
        TextView tvDesc = view.findViewById(R.id.tv_desc);
        tvDesc.setText("Descriere: " + sesizari.get(position).descriere);
        TextView tvData = view.findViewById(R.id.tv_data);
        tvData.setText(dc.toString(sesizari.get(position).dataInregistrare));
        return view;
    }
}
