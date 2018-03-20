package com.example.shengx.midtermexam;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by SHENG.X on 2018-03-19.
 */
@Dao
public interface ResponseDAO {
    @Query("SELECT * FROM RESPONSE WHERE number=:num LIMIT 1")
    boolean getSpecificNumber(int num);

    @Insert
    void insertNumber(Response...response);
}
