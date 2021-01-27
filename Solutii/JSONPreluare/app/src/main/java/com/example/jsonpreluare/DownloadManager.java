package com.example.jsonpreluare;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            instance = new DownloadManager();
        return instance;
    }

    public void getBookData(final  IBookResponse listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL("https://api.mocki.io/v1/ae6b4710");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream stream = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    reader.close();
                    stream.close();
                    listener.onSuccess(parseBookJson(stringBuilder.toString()));
                    listener.onSuccessLib(parseLibrary(stringBuilder.toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private ArrayList<Book> parseBookJson(String result){
        ArrayList<Book> books = new ArrayList<>();
        try{
            JSONObject resultJson = new JSONObject(result);
            JSONArray booksJson = resultJson.getJSONArray("books");
            Log.v("TAG", resultJson.get("users").toString());
            for(int i = 0; i < booksJson.length(); i++){
                JSONObject currentObj = booksJson.getJSONObject(i);
                String name = currentObj.getString("name");
                String author = currentObj.getString("author");
                books.add(new Book(name,author));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Library parseLibrary(String result){
        try{
            JSONObject resultJSON = new JSONObject(result);
            int users = Integer.parseInt(resultJSON.get("users").toString());
            String schedule = resultJSON.get("schedule").toString();
            String libName = resultJSON.get("library_name").toString();
            return new Library(users,schedule,libName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
