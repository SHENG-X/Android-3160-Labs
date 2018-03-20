package com.example.shengx.md_service_testing;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.display);

        final SharedPreferences mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=mSharedPreferences.edit();

        if(mSharedPreferences.getInt("Counter",0)==0){
            editor.putInt("Counter",0);
            editor.apply();
        }

        Log.d("Counter",mSharedPreferences.getInt("Counter",1)+"");
        btn=(Button)findViewById(R.id.updatecount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsynUpdate asynUpdate=new AsynUpdate();
                asynUpdate.execute();
               int beforeUpdateVal= mSharedPreferences.getInt("Counter",0);
               int afterUpdateVal=beforeUpdateVal+1;
               mSharedPreferences.edit().putInt("Counter",afterUpdateVal).apply();
               Log.d("Counter","Before: "+beforeUpdateVal+" After: "+afterUpdateVal);
            }
        });

    }

    public class AsynUpdate extends AsyncTask<Void,Void,String>{


        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return "This is a testing output";
        }
    }
}
