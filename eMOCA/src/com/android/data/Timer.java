/*
 *
 * Timer.java
 *
 * Simple stop-watch implemented using Date
 */

package com.android.data;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

public class Timer
{
    private static long curTime;
    private static long endTime;
    private static long delay;
    private ArrayList<String> tapList;

    public Timer()
    {
        tapList = new ArrayList<String>();
        curTime = 0;
        endTime = 0;
        delay = 0;
    }

    public void startTimer()
    {
        curTime = Calendar.getInstance().getTimeInMillis();
    }

    public void stopTimer()
    {
        endTime = Calendar.getInstance().getTimeInMillis();
    }

    public void addDelay()
    {
        delay = Calendar.getInstance().getTimeInMillis();
        Log.w("Timer", "Adding delay: " + delay);
        tapList.clear();
    }

    public void addTap()
    {
        long tap;
        Log.e("Tap", "addTap was called");

        if ( delay > 0 )
        {
            tap = ((Calendar.getInstance().getTimeInMillis()) - delay - 10000) / 1000;
        }
        else
        {
            tap = ((Calendar.getInstance().getTimeInMillis()) - curTime - 10000) / 1000;
        }

        if (tap > 0)
        {
            tapList.add(stringify(tap));
        }
    }

    private String stringify(long value)
    {
        return value + "";
    }


    public boolean hasTaps()
    {
        return !tapList.isEmpty();
    }

    public String getElapsed()
    {
        if ( (endTime - curTime) < 0)
            return "0";
        else
            return stringify(endTime - curTime);
    }


    public String getTaps()
    {
        String tapString = "";
        for (int i = 0; i < tapList.size()-1; ++i)
        {
            tapString += tapList.get(i) + ",";
        }
        tapString += tapList.get(tapList.size()-1);
        return tapString;
    }
}
