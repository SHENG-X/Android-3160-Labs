package com.shengxiao.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ShengXiao on 2018-01-22.
 */

public class ChronoViewModel extends ViewModel {
    private MutableLiveData<Long> startTime;

    public ChronoViewModel() {
        startTime=new MutableLiveData<>();
        final long currentTime=SystemClock.elapsedRealtime();
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        final long newTime=(SystemClock.elapsedRealtime()-currentTime)/1000;
                        startTime.postValue(newTime);
                    }
                },1000,1000
        );
    }

    public LiveData<Long> getStartTime() {
        return startTime;

    }

}
