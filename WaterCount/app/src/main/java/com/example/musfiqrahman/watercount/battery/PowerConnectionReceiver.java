package com.example.musfiqrahman.watercount.battery;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.musfiqrahman.watercount.R;

/**
 * Created by ShengXiao on 2018-02-07.
 */

public class PowerConnectionReceiver extends BroadcastReceiver {
    boolean charging ;

    @Override
    public  void onReceive(final Context context, Intent intent) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        charging = (chargePlug == BatteryManager.BATTERY_PLUGGED_USB||chargePlug == BatteryManager.BATTERY_PLUGGED_AC);



        sendNotification(context);


        Toast.makeText(
                context,
                "MyBatteryReceiver:"+charging,
                Toast.LENGTH_SHORT).show();

    }

    public Runnable sendNotification(Context context){
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setAutoCancel(true);
        Notification notification=mBuilder.build();
        NotificationManagerCompat manager= NotificationManagerCompat.from(context);
        manager.notify(101,notification);
        return null;
    }

}
