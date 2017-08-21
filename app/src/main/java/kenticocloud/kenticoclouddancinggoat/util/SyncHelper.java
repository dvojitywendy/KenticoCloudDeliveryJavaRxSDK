package kenticocloud.kenticoclouddancinggoat.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

import kenticocloud.kenticoclouddancinggoat.app.cafes.CafesBroadcastReceiver;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by RichardS on 21. 8. 2017.
 */

public class SyncHelper {
    public static void setSyncClock(Context context, int timeIntervalInSeconds, Intent intentReceiver)
    {
        int timeInMs = timeIntervalInSeconds * 1000;

        // create pending intent out of intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 234, intentReceiver, 0);

        // create instance of alarm manager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        // set repeating alarm
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + timeInMs,
                timeInMs, pendingIntent);
    }
}
