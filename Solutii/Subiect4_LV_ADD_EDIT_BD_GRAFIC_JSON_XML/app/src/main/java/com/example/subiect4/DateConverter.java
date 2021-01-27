package com.example.subiect4;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm";
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
    public Tip EnumfromString(String value){
        Tip tipConvert = Tip.drumuri;
        if(value == null)
            return null;
        if(value.equals("personal"))
            tipConvert = Tip.personal;
        else if(value.equals("iluminat"))
            tipConvert = Tip.iluminat;
        else if(value.equals("gaze"))
            tipConvert = Tip.gaze;
        else if(value.equals("apa"))
            tipConvert = Tip.apa;
        else if(value.equals("canalizare"))
            tipConvert = Tip.canalizare;
        return tipConvert;
    }

    @TypeConverter
    public String StringToEnum(Tip tip){
        String tipStr = "";
        if(tip == null)
            return null;
        if(tip.equals(Tip.personal))
            tipStr = "personal";
        else if(tip.equals(Tip.iluminat))
            tipStr = "iluminat";
        else if(tip.equals(Tip.gaze))
            tipStr = "gaze";
        else if(tip.equals(Tip.apa))
            tipStr = "apa";
        else if(tip.equals(Tip.canalizare))
            tipStr = "canalizare";
        return tipStr;
    }


}
