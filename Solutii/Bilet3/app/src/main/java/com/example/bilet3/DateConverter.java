package com.example.bilet3;

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

    public Date fromString(String value) {
        try {
            //metoda parsa este folosita pentru conversia String to Date
            return formatter.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    public String toString(Date value) {
        if (value == null) {
            return null;
        }
        //metoda format este utilizata pentru conversia Date to String
        return formatter.format(value);
    }
}
