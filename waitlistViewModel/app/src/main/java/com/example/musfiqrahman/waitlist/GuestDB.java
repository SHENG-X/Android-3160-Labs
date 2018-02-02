package com.example.musfiqrahman.waitlist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ShengXiao on 2018-01-17.
 */
@Database(entities = {GuestInfo.class},version =1)
public abstract class GuestDB extends RoomDatabase{
    public abstract GuestDAO guestDAO();

    private static GuestDB myguestDB=null;

    public static GuestDB getDB(Context ctx){
        if(myguestDB==null){
         myguestDB= Room.databaseBuilder(ctx,GuestDB.class,"guestinfo").allowMainThreadQueries().build();
        }
        return myguestDB;
    }
}
