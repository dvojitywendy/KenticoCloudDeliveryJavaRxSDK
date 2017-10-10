package kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateHelper {

    public static Date parseIso8601(@NonNull String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return df.parse(date);
    }
}
