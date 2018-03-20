package com.example.shengx.livedatatesting.DB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by SHENG.X on 2018-03-18.
 */
@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    @Insert
    void addUser(User...user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);


}
