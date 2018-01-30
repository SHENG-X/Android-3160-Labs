package com.example.musfiqrahman.waitlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by ShengXiao on 2018-01-22.
 */

public class GuestViewModel extends ViewModel {

    private MutableLiveData<List<GuestInfo>> guestInfoMutableLiveData;

    public LiveData<List<GuestInfo>> getGuestInfoMutableLiveData() {
        return guestInfoMutableLiveData;
    }

    public void setGuestInfoMutableLiveData(MutableLiveData<List<GuestInfo>> guestInfoMutableLiveData) {
        this.guestInfoMutableLiveData = guestInfoMutableLiveData;
    }

}
