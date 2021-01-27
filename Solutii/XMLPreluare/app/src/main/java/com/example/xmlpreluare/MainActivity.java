package com.example.xmlpreluare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvXml = findViewById(R.id.tv_xml);
        parseXML();
    }

    private void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("data.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(is, null);

            processParsing(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<Player> players  = new ArrayList<>();
        int eventType = 0;
        try {
            eventType = parser.getEventType();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        Player currPlayer = null;
        while(eventType != XmlPullParser.END_DOCUMENT){
            String eltName = null;
            switch(eventType){
                case XmlPullParser.START_TAG :
                    eltName = parser.getName();
                    if("player".equals(eltName)){
                        currPlayer = new Player();
                        players.add(currPlayer);
                    }else if(currPlayer != null){
                        if("name".equals(eltName)){
                            currPlayer.setName(parser.nextText());
                        }else if("age".equals(eltName)){
                            currPlayer.age = parser.nextText();
                        }else if("position".equals(eltName)){
                            currPlayer.position = parser.nextText();
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        printPlayers(players);
    }

    private void printPlayers(ArrayList<Player> players) {
        StringBuilder builder = new StringBuilder();
        for(Player pl:players){
            builder.append(pl.getName()).append("\n").append(pl.getAge()).append("\n").append(pl.getPosition()).append("\n\n");
        }
        tvXml.setText(builder.toString());
    }
}