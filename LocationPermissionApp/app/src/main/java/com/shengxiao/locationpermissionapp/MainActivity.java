package com.shengxiao.locationpermissionapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private Button locationBtn;
    private final int REQ_CODE = 0;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationBtn = (Button) findViewById(R.id.locbtn);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);



    }

    private void locationUpdate() {
        try{
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Log.d("Location","Last Known Locaiton: "+location.toString());

                            }
                            else {
                                Log.d("location","Cannot get location!");
                            }
                        }
                    });
        }
        catch (SecurityException e){
            Log.d("Location",e.getLocalizedMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQ_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocaiton();
                } else {
                    Log.d("Location", "Permission Denied");
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION);

                }
            }
            return;

        }
    }

    private void fetchLocaiton() {
        Log.d("Location", "You location is here.");
        locationUpdate();

    }



    public void onClickBtn(View view){
        if(checkloactionPermission()){
            Log.d("Location","Permitted: "+checkloactionPermission());
            fetchLocaiton();
        }
        else {
            Log.d("Location","Request Permission");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQ_CODE);
        }


    }

    private boolean checkloactionPermission(){
        int permissionState= ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionState== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
}
