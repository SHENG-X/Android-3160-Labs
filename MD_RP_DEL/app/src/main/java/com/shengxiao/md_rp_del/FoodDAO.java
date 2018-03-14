package com.shengxiao.md_rp_del;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by ShengXiao on 2018-03-11.
 */
@Dao
public interface FoodDAO {
    @Query("SELECT * FROM foodinfo")
    List<FoodInfo> getAllFoodInfo();

    @Query("SELECT * FROM foodinfo WHERE fid=:fid")
    FoodInfo getSelectedFoodInfo(int fid);

    @Insert
    void insertFood(FoodInfo...food);

    @Delete
    void deleteFood(FoodInfo food);
}
