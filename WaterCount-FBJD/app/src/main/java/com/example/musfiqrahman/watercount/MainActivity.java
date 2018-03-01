package com.example.musfiqrahman.watercount;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musfiqrahman.watercount.BatteryService.ChargingMonitor;
import com.example.musfiqrahman.watercount.sync.ReminderTasks;
import com.example.musfiqrahman.watercount.sync.WaterReminderIntentService;
import com.example.musfiqrahman.watercount.utilities.NotificaitonUtilities;
import com.example.musfiqrahman.watercount.utilities.PreferenceUtilities;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView mWaterCountDisplay;
    private TextView mChargingCountDisplay;
    private ImageView mChargingImageView;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Get the views **/
        mWaterCountDisplay = (TextView) findViewById(R.id.tv_water_count);
        mChargingCountDisplay = (TextView) findViewById(R.id.tv_charging_reminder_count);
        mChargingImageView = (ImageView) findViewById(R.id.iv_power_increment);

        /** Set the original values in the UI **/
        updateWaterCount();
        updateChargingReminderCount();

        /** Setup the shared preference listener **/
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);




        // Create a new dispatcher using the Google Play driver.
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));

            Bundle myExtrasBundle = new Bundle();
            myExtrasBundle.putString("some_key", "some_value");

        Job myJob = dispatcher.newJobBuilder()
                    // the JobService that will be called
                    .setService(ChargingMonitor.class)
                    // uniquely identifies the job
                    .setTag("my-unique-tag")
                    // one-off job
                    .setRecurring(true)
                    // don't persist past a device reboot
                    .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                    // start between 0 and 60 seconds from now
                    .setTrigger(Trigger.executionWindow(5, 5))
                    // don't overwrite an existing job with the same tag
                    .setReplaceCurrent(false)
                    // retry with exponential backoff
                    .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                    // constraints that need to be satisfied for the job to run
                    .setConstraints(
                            // only run when the device is charging
                            Constraint.DEVICE_CHARGING
                    )
                    .setExtras(myExtrasBundle)
                    .build();
        dispatcher.mustSchedule(myJob);
    }




    /**
     * Updates the TextView to display the new water count from SharedPreferences
     */
    private void updateWaterCount() {
        int waterCount = PreferenceUtilities.getWaterCount(this);
        mWaterCountDisplay.setText(waterCount+"");

    }

    /**
     * Updates the TextView to display the new charging reminder count from SharedPreferences
     */
    private void updateChargingReminderCount() {
        int chargingReminders = PreferenceUtilities.getChargingReminderCount(this);
        String formattedChargingReminders = getResources().getQuantityString(
                R.plurals.charge_notification_count, chargingReminders, chargingReminders);
        mChargingCountDisplay.setText(formattedChargingReminders);

    }

    /**
     * Adds one to the water count and shows a toast
     */
    public void incrementWater(View view) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(this, R.string.water_chug_toast, Toast.LENGTH_SHORT);
        mToast.show();
        // TODO (15) Create an explicit intent for WaterReminderIntentService
        // TODO (16) Set the action of the intent to ACTION_INCREMENT_WATER_COUNT
        // TODO (17) Call startService and pass the explicit intent you just created
        Intent myintent = new Intent(this, WaterReminderIntentService.class);
        myintent.setAction(ReminderTasks.ACTION_INCREMENT_WATER_COUNT);
        startService(myintent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /** Cleanup the shared preference listener **/
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * This is a listener that will update the UI when the water count or charging reminder counts
     * change
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (PreferenceUtilities.KEY_WATER_COUNT.equals(key)) {
            updateWaterCount();
        } else if (PreferenceUtilities.KEY_CHARGING_REMINDER_COUNT.equals(key)) {
            updateChargingReminderCount();
        }
    }


    public void showNotificaiton(View view){
        NotificaitonUtilities.remindUser(this);
    }
}