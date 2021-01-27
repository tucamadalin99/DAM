package com.example.xmlretea;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DownloadManager {

    private static DownloadManager instance;

    private DownloadManager(){}

    public static DownloadManager getInstance(){
        if(instance == null)
            instance=new DownloadManager();
        return instance;
    }

    public void getMasiniData(final IMasinaResponse listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = parserFactory.newPullParser();
                    URL url = new URL("https://pastebin.com/raw/42xH5YYn");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream is = connection.getInputStream();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(is, null);
                    listener.onSuccess(parseMasiniXML(parser));

                }
                 catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private ArrayList<Masina> parseMasiniXML(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<Masina>masini = new ArrayList<>();
        int eventType = 0;
        try{
            eventType = parser.getEventType();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        Masina masinaCurenta = null;
        while (eventType != XmlPullParser.END_DOCUMENT){
            String elName = null;
            String brand;
            float motor;
            int cp;
            switch (eventType){
                case  XmlPullParser.START_TAG:
                    elName = parser.getName();
                    if(elName.equals("masina")){
                        masinaCurenta = new Masina();
                        masini.add(masinaCurenta);
                    }else if(masinaCurenta != null){
                        if(elName.equals("brand")){
                            masinaCurenta.setBrand(parser.nextText());
                        }else if(elName.equals("motor")){
                            masinaCurenta.setMotor(Float.parseFloat(parser.nextText()));
                        }else if(elName.equals("cp")){
                            masinaCurenta.setCp(Integer.parseInt(parser.nextText()));
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        return masini;
    }
}
