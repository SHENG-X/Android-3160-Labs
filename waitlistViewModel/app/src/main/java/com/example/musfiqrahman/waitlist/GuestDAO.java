package com.example.musfiqrahman.waitlist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

/**
 * Created by ShengXiao on 2018-01-17.
 */
@Dao
public interface GuestDAO {
    @Query("SELECT * FROM GuestInfo")
    Cursor getAllGuestsInfo();

    @Insert
    void insertGuestInfo(GuestInfo...guestInfo);

}
