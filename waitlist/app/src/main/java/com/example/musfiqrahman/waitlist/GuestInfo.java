package com.example.musfiqrahman.waitlist;

/**
 * Created by musfiqrahman on 2018-01-09.
 */

public class GuestInfo {
    private String guestName;
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
}
