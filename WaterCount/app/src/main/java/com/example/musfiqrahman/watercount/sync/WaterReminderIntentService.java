package com.example.musfiqrahman.watercount.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by ShengXiao on 2018-02-07.
 */

public class WaterReminderIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public WaterReminderIntentService(String name) {
        super(name);
    }
    public WaterReminderIntentService(){
        super("WaterReminderIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ReminderTasks.executeTask(getApplicationContext(),intent.getAction());
    }
}
