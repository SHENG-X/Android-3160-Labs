package com.shengxiao.mt_recyclerview_delete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    public String[] items;
    ItemAdapter itemAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        items= getResources().getStringArray(R.array.items);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        itemAdapter=new ItemAdapter(items);
        mRecyclerView=(RecyclerView)findViewById(R.id.item_container);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(itemAdapter);

    }
}
