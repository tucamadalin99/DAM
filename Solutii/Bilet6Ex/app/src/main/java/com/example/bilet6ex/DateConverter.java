package com.example.bilet6ex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    private static final String formatDate = "dd/MM/yyyy";
    private final SimpleDateFormat format;

    public DateConverter() {
        format = new SimpleDateFormat(formatDate, Locale.US);
    }

    public Date fromString(String value){
        try {
            return format.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString(Date date){
        if(date == null)
            return null;
       return format.format(date);
    }
}
