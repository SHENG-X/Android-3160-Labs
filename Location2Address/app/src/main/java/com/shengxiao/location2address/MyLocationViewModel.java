package com.shengxiao.location2address;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.location.Location;
import android.support.annotation.NonNull;

/**
 * Created by ShengXiao on 2018-03-12.
 */

public class MyLocationViewModel extends AndroidViewModel {
    private LiveData<Location> myLocation;

    public MyLocationViewModel(@NonNull Application application) {
        super(application);
        myLocation=MyLocation.getMyLocationInstance(application);
    }

    public LiveData<Location> getLocation(){
        return myLocation;
    }
}
