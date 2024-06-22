package com.example.sularm.activity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.sularm.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("AlarmReceiver", "Alarm triggered!");

        int position = intent.getIntExtra("position", -1);

        Intent nextActivity = new Intent(context, NotificationActivity.class);
        nextActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        nextActivity.putExtra("position", position);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, position, nextActivity, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "apaseh")
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Reminder")
                .setContentText("BANGONNNNNNNN")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.e("AlarmReceiver", "Notification permission not granted");
            return;
        }
        notificationManager.notify(123, builder.build());
    }
}
