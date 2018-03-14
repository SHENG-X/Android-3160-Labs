package com.shengxiao.livelocation;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

/**
 * Created by ShengXiao on 2018-03-12.
 */

public class MyLocationLiveData extends LiveData<Location> {

    private static MyLocationLiveData instance = null;
    private static Context context;
    private static FusedLocationProviderClient fusedLocationProviderClient;
    private static LocationCallback locationCallback;
    private final String TAG = "MY_LOG_LIVE_DATE";
    private LocationRequest locationRequest;

    public static MyLocationLiveData getInstance(Context ctx) {
        if (instance == null) {
            instance = new MyLocationLiveData(ctx);
        }
        return instance;
    }


    private MyLocationLiveData(Context context) {
        this.context = context;

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        //setup location  callback
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) return;
//                for(Location location:locationResult.getLocations()){
//                }
                setValue(locationResult.getLastLocation());
            }

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        };

        //setup location request
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }


    @Override
    protected void onActive() {
        super.onActive();
        startLocationUpdates();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (fusedLocationProviderClient != null) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }


    private void startLocationUpdates() {

        try{
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
        catch (SecurityException ex){
            Log.d(TAG,ex.toString());
        }

    }





}
