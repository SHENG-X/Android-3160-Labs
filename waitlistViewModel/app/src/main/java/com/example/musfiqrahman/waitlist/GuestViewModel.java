package com.example.musfiqrahman.waitlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.database.Cursor;

/**
 * Created by ShengXiao on 2018-01-22.
 */

public class GuestViewModel extends AndroidViewModel {
    private GuestRepository myGuestRepository;
    private Cursor guestInfoMutableLiveData;


    public GuestViewModel(Application app) {
        super(app);
        myGuestRepository=new GuestRepository(app);
        guestInfoMutableLiveData=myGuestRepository.getMyGuestInfo();
    }

    public Cursor getGuestInfoMutableLiveData() {
        return guestInfoMutableLiveData;
    }

    public void setGuestInfoMutableLiveData(GuestInfo guestInfo) {
        myGuestRepository.insertGuest(guestInfo);
    }

}
