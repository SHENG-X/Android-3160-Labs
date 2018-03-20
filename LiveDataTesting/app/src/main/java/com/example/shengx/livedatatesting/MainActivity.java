package com.example.shengx.livedatatesting;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.shengx.livedatatesting.DB.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton toAddUserPage;
    Intent intent;
    UserAdapter userAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<User> users=new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toAddUserPage=(FloatingActionButton)findViewById(R.id.adduserFloat);
        recyclerView=(RecyclerView)findViewById(R.id.adduserlist);
        linearLayoutManager=new LinearLayoutManager(this);
        users.add(new User("a","1"));
        users.add(new User("b","2"));
        users.add(new User("c","3"));
        recyclerView.setLayoutManager(linearLayoutManager);
        userAdapter=new UserAdapter(users);
        recyclerView.setAdapter(userAdapter);






        intent=new Intent(this,adduser.class);

        toAddUserPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
