package com.shengxiao.location2address;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final int REQ_LOC_CODE=100;
    MyLocationViewModel myLocationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLocationViewModel= ViewModelProviders.of(this).get(MyLocationViewModel.class);
        checkLocationPermission();


    }


    public boolean checkLocationPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQ_LOC_CODE);
        int locationPermissionCode=ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        return locationPermissionCode == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQ_LOC_CODE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        myLocationViewModel.getLocation().observe(this, new Observer<Location>() {
                            @Override
                            public void onChanged(@Nullable Location location) {
                                Log.d("Location","Working here");
                            }
                        });


                        Log.d("Location","Permission Problem");

                }
                else {
                    Log.d("Location: ","Location Permission Required");
                }
        }
    }
}
