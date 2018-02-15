package com.shengxiao.bookauthers;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ShengXiao on 2018-02-05.
 */

public class NetworkUtility {
    private static final String BOOK_BASE_URL="https://www.googleapis.com/books/v1/volumes";
    private static final String QUERY_PEAM="q";
    private static final String MAX_RESUKT="maxResults";


    public static String getBookInfo(String queryString){
        HttpURLConnection httpURLConnection=null;

        Uri bookUri= Uri.parse(BOOK_BASE_URL)
                .buildUpon()
                .appendQueryParameter(QUERY_PEAM,queryString)
                .appendQueryParameter(MAX_RESUKT,"1")
                .build();
        BufferedReader br=null;
        StringBuffer buffer=new StringBuffer();
        try {
            URL requestURL=new URL(bookUri.toString());
            httpURLConnection=(HttpURLConnection)requestURL.openConnection();
            br=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));


            String line;
            while ((line=br.readLine())!=null){
                buffer.append(line);
                buffer.append("\n");

            }
            if(buffer.length()==0){
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }
    public static String parseJson(String jsonString){
        String author_name=null;
        try {
            JSONObject jsonObj=new JSONObject(jsonString);
            JSONArray jsonArray=jsonObj.getJSONArray("items");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject book=jsonArray.getJSONObject(i);
                JSONObject volumnInfo=book.getJSONObject("volumeInfo");
                String title=null, author=null;
                try{
                    title=volumnInfo.getString("title");
                    author=volumnInfo.getString("authors");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if(title!=null && author!=null){
                    return "Title: "+ title+"  Author: "+author;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
