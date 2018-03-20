package com.example.shengx.midtermexam;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by SHENG.X on 2018-03-19.
 */
@Database(entities = {Response.class},version = 1)
public abstract  class ResponseDB extends RoomDatabase {
    public abstract ResponseDAO responseDAO();

    private static ResponseDB responseDB=null;

    public static ResponseDB getDB(Context context){
        if(responseDB==null){
            responseDB= Room.databaseBuilder(context,ResponseDB.class,"response").build();
        }
        return responseDB;
    }
}
