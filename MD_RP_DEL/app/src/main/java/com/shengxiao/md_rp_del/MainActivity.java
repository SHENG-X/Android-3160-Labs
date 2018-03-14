package com.shengxiao.md_rp_del;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    FoodDB foodDB;
    FoodDBHelper foodDBHelper;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodDB=FoodDB.getDB(this);
        foodDBHelper=new FoodDBHelper(foodDB);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodInfo myfood=new FoodInfo("Nice Food"+Math.floor(Math.random()*10),"123",1);
                foodDBHelper.insertFood(myfood);
                foodDBHelper.getAllFood();
            }
        });

    }
}
