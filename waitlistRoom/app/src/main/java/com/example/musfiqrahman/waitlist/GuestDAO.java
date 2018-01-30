package com.example.musfiqrahman.waitlist;

import android.arch.lifecycle.LiveData;
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
public interface GuestDAO {
    @Query("SELECT * FROM GuestInfo")
    LiveData<List<GuestInfo>> getAllGuestsInfo();

    @Insert
    void insertGuestInfo(GuestInfo...guestInfo);

    @Update
    void updateGuestInfo(GuestInfo guestInfo);

    @Delete
    void deleteGuestInfo(GuestInfo guestInfo);
}
