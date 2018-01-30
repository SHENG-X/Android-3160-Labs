package com.shengxiao.myapplication_room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by ShengXiao on 2018-01-17.
 */
@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void insertUser(User...users);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
