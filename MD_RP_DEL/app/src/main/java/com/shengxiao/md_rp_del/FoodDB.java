package com.shengxiao.md_rp_del;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ShengXiao on 2018-03-11.
 */
@Database(entities = {FoodInfo.class},version = 1)
public abstract class FoodDB extends RoomDatabase{
    public abstract FoodDAO foodDAO();

    public static FoodDB myFoodDB=null;

    public static FoodDB getDB(Context ctx){
        if(myFoodDB==null){
            myFoodDB= Room.databaseBuilder(ctx,FoodDB.class,"foodinfo").build();
        }
        return myFoodDB;
    }

}
