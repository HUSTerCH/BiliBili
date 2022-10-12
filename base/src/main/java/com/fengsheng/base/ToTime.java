package com.fengsheng.base;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ToTime {
    public static String timeStampToStrMMdd(long timeStamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.CHINA);

        String date = sdf.format(timeStamp * 1000);
        Log.e("ToTime", date);
        return date;

    }

    public static String timeStampToStryyyyMMdd(long timeStamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

        String date = sdf.format(timeStamp * 1000);
        Log.e("ToTime", date);
        return date;

    }
    public static String timeStampToStrAll(long timeStamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

        String date = sdf.format(timeStamp * 1000);
        Log.e("ToTime", date);
        return date;

    }
}
