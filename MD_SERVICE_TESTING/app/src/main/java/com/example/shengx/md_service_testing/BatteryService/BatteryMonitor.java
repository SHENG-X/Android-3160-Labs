package com.example.shengx.md_service_testing.BatteryService;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by SHENG.X on 2018-03-17.
 */

public class BatteryMonitor extends IntentService {

    public BatteryMonitor(String name) {
        super(name);
    }
    public BatteryMonitor() {
        super("ADD");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
