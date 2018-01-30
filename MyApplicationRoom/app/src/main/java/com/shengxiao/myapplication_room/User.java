package com.shengxiao.myapplication_room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ShengXiao on 2018-01-17.
 */

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    
    @ColumnInfo
    private String name;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public void setUid(int uid) {
    
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUid() {
    
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @ColumnInfo
    
    private String password;
    
}
