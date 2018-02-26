package com.example.musfiqrahman.watercount.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.example.musfiqrahman.watercount.MainActivity;
import com.example.musfiqrahman.watercount.R;
import com.example.musfiqrahman.watercount.sync.ReminderTasks;
import com.example.musfiqrahman.watercount.sync.WaterReminderIntentService;

import static android.support.v4.app.NotificationCompat.Action;
import static android.support.v4.app.NotificationCompat.BigTextStyle;
import static android.support.v4.app.NotificationCompat.Builder;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;

/**
 * Created by ShengXiao on 2018-02-14.
 */

public class NotificaitonUtilities {
    public static final String NOTIFICATION_CHANNEL_ID="my_hydration_01";
    public static final int NOTIFICATION_ID=123;
    public static final int PENDING_INTENT_RC=111;
    public static final int PENDING_WATERCOUNT_INCREMENT_ID=222;
    public static final int PENDING_IGNORE=333;

    public static void remindUser(Context ctx){
        NotificationManager mNotificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
// The ID of the channel.
// The user visible name of the channel.
        CharSequence name = "Hydration Channel";
// The user visible description of the channel.
        String description = "Hydration Channel Description --> # Water Reminder Channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            // Configure the notification channel.
            mChannel.setDescription(description);
            mChannel.setShowBadge(false);
            mNotificationManager.createNotificationChannel(mChannel);
        }
        Builder mNofication=new Builder(ctx,NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(ctx, android.R.color.holo_green_light))
                .setSmallIcon(R.drawable.ic_power_grey_80px)
                .setLargeIcon(largeicon(ctx))
                .setContentTitle("Hydration Reminder")
                .setContentText("Please drink water!!!")
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setStyle(new BigTextStyle().bigText("Please drink water..."))
                .setContentIntent(contentIntent(ctx))
                .addAction(drinkWaterAction(ctx))
                .addAction(ignoreReminderAction(ctx))
                .setAutoCancel(true);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT<Build.VERSION_CODES.O ) {
               mNofication.setPriority(PRIORITY_HIGH);
        }
        mNotificationManager.notify(NOTIFICATION_ID,mNofication.build());
    }

    public static void clearAllNotifications(Context ctx){
        NotificationManager notificationManager=(NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    private static Action ignoreReminderAction(Context ctx) {
        Intent incrementWaterCount=new Intent(ctx, WaterReminderIntentService.class);
        incrementWaterCount.setAction(ReminderTasks.ACTION_INCREMENT_WATER_COUNT);
        PendingIntent pi=PendingIntent.getService(ctx,PENDING_WATERCOUNT_INCREMENT_ID,incrementWaterCount,PendingIntent.FLAG_CANCEL_CURRENT);
        Action drinkWaterAction=new Action(R.drawable.ic_local_drink_black_24px,"Already Drunk!",pi);
        return drinkWaterAction;
    }

    private static Action drinkWaterAction(Context ctx) {
        Intent incrementWaterCount=new Intent(ctx, WaterReminderIntentService.class);
        incrementWaterCount.setAction(ReminderTasks.ACTION_IGNORE_REMINDER);
        PendingIntent pi=PendingIntent.getService(ctx,PENDING_IGNORE,incrementWaterCount,PendingIntent.FLAG_CANCEL_CURRENT);
        Action ignoreAction=new Action(R.drawable.ic_local_drink_black_24px,"Dismiss!",pi);
        return ignoreAction;
    }

    private static PendingIntent contentIntent(Context ctx) {
        Intent startMainActivity=new Intent(ctx, MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(ctx,PENDING_INTENT_RC,startMainActivity,PendingIntent.FLAG_UPDATE_CURRENT);
        return pi;
    }


    private static Bitmap largeicon(Context ctx) {
        Bitmap largeicon;
        Resources res=ctx.getResources();
        largeicon= BitmapFactory.decodeResource(res,R.drawable.ic_drink_notification);
        return largeicon;
    }
}
