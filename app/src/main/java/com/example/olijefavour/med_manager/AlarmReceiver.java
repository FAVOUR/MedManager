package com.example.olijefavour.med_manager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Olije favour on 4/14/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private int NOTIFICATION_ID =1124;

    @Override
    public void onReceive(Context context, Intent intent) {
//        MediaPlayer mediaPlayer= MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
//        mediaPlayer.start();

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        //Create the content intent for the notification, which launches this activity
        Intent contentIntent = new Intent(context, CatalogActivity.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);


//                .setContentText("Take the following: "+"\n" + "Panadol" +"\n" + "1 Tablet")
        builder.setAutoCancel(true)
               .setDefaults(Notification.DEFAULT_ALL)
               .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(contentPendingIntent)
                .setSound(alarmSound)
                .setContentTitle("Alarm Activated")
                .setContentText("Take "+ "Panadol " +"1 " + "Tablet ")
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentInfo("info")
                .setSmallIcon(R.drawable.ic_menu_camera);


        NotificationManager notificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }


}
