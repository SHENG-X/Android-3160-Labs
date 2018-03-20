package com.example.shengx.midtermexam;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by SHENG.X on 2018-03-19.
 */

public class GetNumberService extends IntentService {
    private static final String NUMBER_URL="http://numbersapi.com";
    private static String numberBuilder="";
    public static String numberMethod_Math="math";
    public static String numberMethod_trivia="trivia";
    private static String numberMethod="";
    public static String accessURL=NUMBER_URL+"/"+numberBuilder+"/"+numberMethod+"?json";

    public static void setNumber(String number){
        numberBuilder=number;
    }

    public static void setNumberMethod(String method){
        numberMethod=method;
    }

    public static String getURL(){
       return accessURL=NUMBER_URL+"/"+numberBuilder+"/"+numberMethod+"?json";
    }
    public static String getNumBerInfo(String queryString){
        HttpURLConnection httpURLConnection=null;
        BufferedReader br=null;
        StringBuffer buffer=new StringBuffer();
        try{
            URL requestURl=new URL(getURL());
            Log.d("Log",requestURl.toString());
            httpURLConnection=(HttpURLConnection)requestURl.openConnection();
            br=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line=br.readLine())!=null){
                buffer.append(line);
                buffer.append("\n");
            }
            if(buffer.length()==0){
                return null;
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if(br!=null){
                try{
                    br.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.d("Log",buffer.toString());
        return buffer.toString();
    }



    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GetNumberService(String name) {
        super(name);
    }
    public GetNumberService() {
        super("GetNumberService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action=intent.getAction();
        String number=intent.getStringExtra("number");
        Log.d("Log",number+"&&&&&"+action);

        GetNumberService.setNumber(number);
        GetNumberService.setNumberMethod(action);
        GetNumberService.getNumBerInfo(GetNumberService.accessURL);
    }
}
