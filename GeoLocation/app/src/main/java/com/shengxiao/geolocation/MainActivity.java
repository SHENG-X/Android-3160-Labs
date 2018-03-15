package com.shengxiao.geolocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_LOCATION=100;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private TextView textView;
    private Geocoder mGeocoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textview);

        mGeocoder=new Geocoder(this, Locale.getDefault());

        checkLocationPermission();
        mFusedLocationProviderClient=new FusedLocationProviderClient(this);

        //!!!!!!important the call back only takes UI manipulation operations!!!
        mLocationCallback=new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if(locationResult==null){
                    textView.setText("No location available!");
                    Log.d("Location","No location available!");// not work
                    return;
                }
                try {
                    List<Address> addressList=mGeocoder.getFromLocation(locationResult.getLastLocation().getLatitude(),locationResult.getLastLocation().getLongitude(),1);
                    textView.setText(addressList.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("Locaiton",locationResult.getLastLocation().getLongitude()+"$$$$$$$$"+locationResult.getLastLocation().getLatitude());//not work.
            }
        };
        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    public boolean checkLocationPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQ_CODE_LOCATION);
        int permssionCode=ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permssionCode== PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQ_CODE_LOCATION:
                if(grantResults.length>0&&checkLocationPermission()){
                    getLocaiton();
                }
                else {
                    Log.d("Location","Permission Denied");
                }
        }
    }

    public void getLocaiton(){
        try{
            mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,mLocationCallback,null);
        }
        catch (SecurityException ex){
            Log.d("Location",ex.toString());

        }
    }
}
