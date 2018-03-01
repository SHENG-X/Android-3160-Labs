package com.example.musfiqrahman.watercount.BatteryService;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by ShengXiao on 2018-02-25.
 */

public class ChargingMonitor extends JobService {
    @Override
    public boolean onStartJob(final JobParameters job) {

        Log.d("hello","job start");

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.d("hello","done");
        return false;
    }
}
