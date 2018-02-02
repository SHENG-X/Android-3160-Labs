package com.example.musfiqrahman.waitlist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

/**
 * Created by musfiqrahman on 2018-01-09.
 */

@Entity
public class GuestInfo {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = GuestContract.GuestEntity.COLUMN_ID)
    private int guestPK;

    @ColumnInfo(name = GuestContract.GuestEntity.COLUMN_GUEST_NAME)
    private String guestName;

    @ColumnInfo(name = GuestContract.GuestEntity.COLUMN_PARTY_SIZE)
    private int partySize;

    public GuestInfo(String guestName, int partySize) {
        this.guestName = guestName;
        this.partySize = partySize;
    }
    public GuestInfo(){}

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

    public static GuestInfo fromContentValues(ContentValues values) {
        final GuestInfo guestInfo = new GuestInfo();
        if (values.containsKey(GuestContract.GuestEntity.COLUMN_ID)) {
            guestInfo.setGuestPK(values.getAsInteger(GuestContract.GuestEntity.COLUMN_ID));
        }
        if (values.containsKey(GuestContract.GuestEntity.COLUMN_GUEST_NAME)) {
            guestInfo.setGuestName(values.getAsInteger(GuestContract.GuestEntity.COLUMN_GUEST_NAME).toString());
        }
        if (values.containsKey(GuestContract.GuestEntity.COLUMN_PARTY_SIZE)) {
            guestInfo.setPartySize(values.getAsInteger(GuestContract.GuestEntity.COLUMN_PARTY_SIZE));
        }
        return guestInfo;
    }
}
