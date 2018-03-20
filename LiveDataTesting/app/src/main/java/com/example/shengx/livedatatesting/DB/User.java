package com.example.shengx.livedatatesting.DB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by SHENG.X on 2018-03-18.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    int UserID;



    @ColumnInfo

    private String username,age;


    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {

        return username;
    }

    public String getAge() {
        return age;
    }

    public User(String username, String age) {

        this.username = username;
        this.age = age;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {

        UserID = userID;
    }
}
