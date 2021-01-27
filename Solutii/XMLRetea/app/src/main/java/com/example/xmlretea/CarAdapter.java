package com.example.xmlretea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends ArrayAdapter<Masina> {
    Context context;
    List<Masina>masini;
    private int resId;
    LayoutInflater inflater;

    public CarAdapter(@NonNull Context context, int resource, @NonNull List<Masina> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.masini = objects;
        this.resId = resource;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resId,parent,false);
        Masina m = masini.get(position);
        if(m != null){
            TextView tvBrand = view.findViewById(R.id.tv_brand);
            tvBrand.setText("Brand: " + m.getBrand());
            TextView tvMotor = view.findViewById(R.id.tv_motor);
            tvMotor.setText("Capacitate: " + String.valueOf(m.getMotor()));
            TextView tvCp = view.findViewById(R.id.tv_cp);
            tvCp.setText("CP: " + String.valueOf(m.getCp()));
        }
        return view;
    }
}
