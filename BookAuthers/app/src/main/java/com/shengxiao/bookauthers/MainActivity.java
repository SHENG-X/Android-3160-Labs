package com.shengxiao.bookauthers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MFileObserver observer;
    EditText editText;
    Button button;
    NetworkInfo networkInfo;
    ConnectivityManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.bookname);
        button=(Button)findViewById(R.id.sendname);

        manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo=manager.getActiveNetworkInfo();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(networkInfo!=null && networkInfo.isConnected()){
                    loadBookData(editText.getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void loadBookData(String s) {
        new AsyncTask<String,Void,String>(){


            @Override
            protected String doInBackground(String... strings) {
                String res=NetworkUtility.getBookInfo(strings[0]);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                observer=new MFileObserver("/data/user/0/com.shengxiao.bookauthers/files",getApplicationContext());
                observer.startWatching();
//                Log.d("Main", NetworkUtility.parseJson(s));
                FileManager fileManager=new FileManager(getApplicationContext());
                fileManager.writeFile(getApplicationContext(),"t2.txt",s);

            }
        }.execute(s);
    }

}
