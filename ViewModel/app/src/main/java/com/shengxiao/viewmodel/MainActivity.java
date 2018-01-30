package com.shengxiao.viewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ChronoViewModel viewModel;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);

        viewModel= ViewModelProviders.of(this).get(ChronoViewModel.class);

        subscribe();

    }

    private void subscribe(){
       final Observer<Long> timeObserver=new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                textView.setText(aLong.toString());
                Log.d("LiveData",aLong.toString());
            }
        };
       viewModel.getStartTime().observe(this,timeObserver);
    }
}
