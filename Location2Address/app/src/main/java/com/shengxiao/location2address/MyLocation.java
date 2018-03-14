package com.shengxiao.location2address;

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

public class MyLocation extends LiveData<Location>{

    private static Context context;
    private static FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private static LocationCallback mLocationCallback;
    private static MyLocation mLocationInstance=null;



    public static MyLocation getMyLocationInstance(Context context){
        if(mLocationInstance==null){
            mLocationInstance=new MyLocation(context);
        }
        return mLocationInstance;
    }


    private MyLocation(Context context) {
        this.context = context;
        mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(context);
        mLocationCallback=new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if(locationResult==null)return;
                setValue(locationResult.getLastLocation());
            }

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        };
        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    @Override
    protected void onActive() {
        super.onActive();
        startLocationUpdate();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if(mFusedLocationProviderClient!=null){
            mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
        }

    }

    public void startLocationUpdate(){
        try{
            mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,mLocationCallback,null);
        }
        catch (SecurityException ex){
            Log.d("Security Issue: ", ex.toString());
        }
    }
}
