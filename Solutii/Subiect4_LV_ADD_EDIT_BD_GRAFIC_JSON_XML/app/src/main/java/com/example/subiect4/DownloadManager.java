package com.example.subiect4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.Date;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class DownloadManager {
    private static DownloadManager instance;
    private  DateConverter dateConverter = new DateConverter();
    public DownloadManager(){}
    public static DownloadManager getInstance(){
        if(instance == null)
            return new DownloadManager();
        return instance;
    }

    public void getSesizariXML(final ISesizariResponse listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlPullParserFactory.newPullParser();
                    URL url = new URL("https://pastebin.com/raw/z5SgBsJd");
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    InputStream is = conn.getInputStream();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
                    parser.setInput(is,null);
                    listener.onSuccesXML(parseXML(parser));
                } catch (XmlPullParserException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public ArrayList<Sesizare> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Sesizare> sesizares = new ArrayList<>();
        int evType = 0;
        evType = parser.getEventType();
        Sesizare sesizareCurr = null;
        while (evType != XmlPullParser.END_DOCUMENT){
            String elNume = null;
            switch (evType){
                case XmlPullParser.START_TAG:
                    elNume = parser.getName();
                    if("sesizare".equals(elNume)){
                        sesizareCurr = new Sesizare();
                        sesizares.add(sesizareCurr);
                    }else if(sesizareCurr != null){
                        if("titlu".equals(elNume))
                            sesizareCurr.setTitlu(parser.nextText());
                        else if("descriere".equals(elNume))
                            sesizareCurr.setDescriere(parser.nextText());
                        else if("tip".equals(elNume))
                            sesizareCurr.setTip(dateConverter.EnumfromString(parser.nextText()));
                        else if("dataInregistrarii".equals(elNume))
                            sesizareCurr.setDataInregistrare(dateConverter.fromString(parser.nextText()));
                    }
                    break;
            }
            evType= parser.next();
        }
        return sesizares;
    }

    public void getSesizariData(final ISesizariResponse listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.mocki.io/v1/2cf78ce4");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bf = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while((line = bf.readLine()) != null){
                        sb.append(line);
                    }
                    bf.close();
                    isr.close();
                    is.close();
                    listener.onSucces(parseSesizareJSON(sb.toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private ArrayList<Sesizare> parseSesizareJSON(String result) throws JSONException {
        ArrayList<Sesizare> sesizari = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(result);
        JSONArray sesizariJSON = jsonObject.getJSONArray("sesizari");
        for(int i = 0; i < sesizariJSON.length(); i++){
            JSONObject obj = sesizariJSON.getJSONObject(i);
            String titlu = obj.getString("titlu");
            String desc = obj.getString("descriere");
            Date date = dateConverter.fromString(obj.getString("dataInregistrarii"));
            Tip tip = dateConverter.EnumfromString(obj.getString("tip"));
            sesizari.add(new Sesizare(titlu,desc,tip,date));
        }
        return sesizari;
    }
}
