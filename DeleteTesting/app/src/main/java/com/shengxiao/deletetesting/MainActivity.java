package com.shengxiao.deletetesting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    RecursiveFileObserver directoryFileObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        directoryFileObserver = new RecursiveFileObserver("/app/src/main/java/com/shengxiao/deletetesting");
        directoryFileObserver.startWatching();

    }
}
