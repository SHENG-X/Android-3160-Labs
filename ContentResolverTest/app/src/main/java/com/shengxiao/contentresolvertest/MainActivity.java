package com.shengxiao.contentresolvertest;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ada","ad213124");
        new GetWord().execute();
    }



    public class GetWord extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            ContentResolver resolver = getContentResolver();
            String[] projection= new String[]{BaseColumns._ID, UserDictionary.Words.WORD};
            Cursor cursor = resolver.query(
                    UserDictionary.Words.CONTENT_URI,projection,null,null,null
            );
            if(cursor.moveToFirst()){
                while (cursor.moveToNext()){
                    long id=cursor.getLong(cursor.getColumnIndex(UserDictionary.Words._ID));
                    String word=cursor.getString(cursor.getColumnIndex(UserDictionary.Words.WORD));
                    Log.d("Main Acitivity",word);
                }
            }
            else {
                Log.d("Main Activity","No data found.");
            }
            cursor.close();

            return null;
        }
    }
}
