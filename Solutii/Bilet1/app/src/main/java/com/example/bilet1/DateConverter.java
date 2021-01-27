package com.example.bilet1;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    private static final String FORMAT_DATE = "dd/MM/yyyy";
    private final SimpleDateFormat formatter;

    public DateConverter() {
        //SimpleDateFormat este o clasa din pachetul java.text,
        // utilizata pentru realizarea conversiilor de la String la Date si invers
        formatter = new SimpleDateFormat(FORMAT_DATE, Locale.US);
    }

    @TypeConverter
    public Date fromString(String value) {
        try {
            //metoda parsa este folosita pentru conversia String to Date
            return formatter.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }
    @TypeConverter
    public String toString(Date value) {
        if (value == null) {
            return null;
        }
        //metoda format este utilizata pentru conversia Date to String
        return formatter.format(value);
    }

    @TypeConverter
    public TipTaxi EnumfromString(String value){
        if(value == null)
            return null;
        if(value.equals("normal"))
            return TipTaxi.normal;
        else
            return TipTaxi.premium;
    }

    @TypeConverter
    public String StringToEnum(TipTaxi tip){
        if(tip == null)
            return null;
        if(tip.equals(TipTaxi.normal))
            return "normal";
        else
            return "premium";
    }
}
