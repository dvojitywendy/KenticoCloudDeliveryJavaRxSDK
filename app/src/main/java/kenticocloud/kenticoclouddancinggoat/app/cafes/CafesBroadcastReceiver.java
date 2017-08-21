package kenticocloud.kenticoclouddancinggoat.app.cafes;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import kenticocloud.kenticoclouddancinggoat.R;

import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by RichardS on 21. 8. 2017.
 */


public class CafesBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int mNotificationId = 001;

        Intent resultIntent = new Intent(context, CafesActivity.class);
        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE)
                        .setSmallIcon(R.drawable.ic_local_cafe)
                        .setContentTitle("New cafe")
                        .setContentText("Test / todo")
                        .setContentIntent(resultPendingIntent)
                        .setTicker("New cafe");

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
