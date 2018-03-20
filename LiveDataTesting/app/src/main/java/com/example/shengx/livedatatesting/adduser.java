package com.example.shengx.livedatatesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class adduser extends AppCompatActivity {
    EditText username,age;
    Button adduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        username=(EditText)findViewById(R.id.username);
        age=(EditText)findViewById(R.id.age);
        adduser=(Button)findViewById(R.id.adduserbtn);
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=username.getText().toString();
                String uage=age.getText().toString();

                Log.d("User",uname+"$$$$"+uage);
            }
        });
    }
}
