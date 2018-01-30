package com.shengxiao.myapplication_room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ShengXiao on 2018-01-17.
 */
@Database(entities={User.class},version=1)
public abstract class MyDB extends RoomDatabase {


    public abstract UserDAO userDAO();

    private static MyDB instance=null;

    public static MyDB getDB(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,MyDB.class,"Users").build();
        }
        return instance;
    }

}