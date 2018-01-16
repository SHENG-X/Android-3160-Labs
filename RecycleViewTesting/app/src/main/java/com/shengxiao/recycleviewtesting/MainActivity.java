package com.shengxiao.recycleviewtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Student> studentList=new ArrayList<>();
    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentList.add(new Student("T001","ABC"));
        studentList.add(new Student("T002","3dC"));
        studentList.add(new Student("T003","esC"));
        studentList.add(new Student("T004","aBf"));
        studentList.add(new Student("T005","tBC"));
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        studentAdapter=new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);



    }
}
