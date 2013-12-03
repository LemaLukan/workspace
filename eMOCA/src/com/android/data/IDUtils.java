package com.android.data;

import java.util.Calendar;

public class IDUtils
{
    public static String getJulianFromUnix(String test_version)
    {
        double unixTime = ((System.currentTimeMillis() / 1000) / 86400.0) + 2440587.5;
        return unixTime + "_" + test_version;
    }

    public static String getID(String test_version)
    {
        Calendar cal = Calendar.getInstance();
        String id = "";
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);

        id += ((day < 10) ? "0" + day : day);
        id += ((month < 10) ? "0" + month : "" + month);
        id += "" + year + "_" + hours;
        id += ((minutes < 10) ? "H0" + minutes : "H" + minutes);
        id += ((seconds < 10) ? "M0" + seconds + "S" : "M" + seconds + "S");

        return id + "_" + test_version;
    }

    public static String getTimeStamp()
    {
        Calendar cal = Calendar.getInstance();
        String time = "";
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        time += ((day < 10) ? "0" + day : day);
        time += ((month < 10) ? "_0" + month : "_" + month);
        time += "_" + year + "_" + hours;
        time += ((minutes < 10) ? "H0" + minutes: "H" + minutes);

        return time;
    }

}
