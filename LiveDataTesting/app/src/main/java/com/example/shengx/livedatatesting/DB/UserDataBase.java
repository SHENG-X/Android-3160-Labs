package com.example.shengx.livedatatesting.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by SHENG.X on 2018-03-18.
 */

@Database(entities ={User.class},version = 1)
public abstract class UserDataBase extends RoomDatabase {
    public abstract UserDAO userDAO();
    private static UserDataBase dbInstance=null;

    public static UserDataBase getUserDB(Context context){
        if(dbInstance==null){
            dbInstance= Room.databaseBuilder(context,UserDataBase.class,"User").build();
        }
        return dbInstance;
    }
}
