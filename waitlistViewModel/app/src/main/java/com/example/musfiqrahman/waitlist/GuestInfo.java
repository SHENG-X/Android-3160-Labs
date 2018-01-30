package com.example.musfiqrahman.waitlist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by musfiqrahman on 2018-01-09.
 */

@Entity
public class GuestInfo {


    @PrimaryKey(autoGenerate = true)
    private int guestPK;

    @ColumnInfo
    private String guestName;

    @ColumnInfo
    private int partySize;

    public GuestInfo(String guestName, int partySize) {
        this.guestName = guestName;
        this.partySize = partySize;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }
    public int getGuestPK() {
        return guestPK;
    }
    public void setGuestPK(int guestPK) {
        this.guestPK = guestPK;
    }
}
