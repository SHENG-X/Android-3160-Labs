package com.shengxiao.myapplication_room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=MyDB.getDB(this);
    }

    public void onClick(View v){
        User user=new User("Sheng","12345");
        //db.userDAO().insertUser(user);
        new DBManager().execute(user);


    }

    public class DBManager extends AsyncTask<User, Void, User>{
        List<User> lsuser;
        @Override
        protected User doInBackground(User... users) {
            for(User us:users){
                db.userDAO().insertUser(us);
            }
            lsuser=db.userDAO().getAll();
            for(User ur:lsuser){
                Log.d("User",ur.getUid()+" %%% "+ur.getName()+" $$$  "+ur.getPassword());

            }

            return null;
        }

    }
}
