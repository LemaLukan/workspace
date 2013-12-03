/*
 * DataTracker.java
 *
 * Tracks the timer, tapping and path information.
 * Uses String ArrayLists + Bundle for efficiency sake.
 *
 */

package com.android.data;

import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

public class DataHandler
{
    private int testID;
    private Bundle data;
    private Timer timer;
    private String path;
    private boolean usedReplay;
    private ArrayList<String> srcArray;
    private ArrayList<String> timerArray;
    private ArrayList<String> replayArray;
    private boolean isTracking = false;


    public DataHandler(int testID)
    {
        this.testID = testID;
        data = new Bundle();
        timer = new Timer();
        usedReplay = false;
    }

    /*
     * Primary Constructor
     */
    public DataHandler(int testID, Bundle data)
    {
        this.testID = testID;
        this.data = data;
        timer = new Timer();
        if ( data.containsKey("timer") == false )
        {
            timerArray = new ArrayList<String>();
            srcArray = new ArrayList<String>();
            replayArray = new ArrayList<String>();
        }
        else
        {
            timerArray = data.getStringArrayList("timer");
            srcArray = data.getStringArrayList("src");
            replayArray = data.getStringArrayList("replay");
        }
    }

    public void addTap()
    {
        timer.addTap();
    }

    public void addReplay()
    {
        usedReplay = true;
    }

    public void addDelay()
    {
        timer.addDelay();
    }

    public void startTracking(String path)
    {
        this.path = path;
        isTracking = true;
        timer.startTimer();
    }

    public boolean isTracking()
    {
        return isTracking;
    }

    public void stopTracking(boolean skipped)
    {
        timer.stopTimer();
        isTracking = false;

        if ( skipped )
        {
            if ( timer.hasTaps() )
            {
                timerArray.add(timer.getTaps());
            }
            else
            {
                if (usedReplay == true)
                {
                    timerArray.add(timer.getElapsed());
                }
                else
                {
                    Log.e("Addeding", "0");
                    timerArray.add("0");
                }
            }
            srcArray.add("none");
        }
        else
        {
            if ( timer.hasTaps() )
            {
                timerArray.add(timer.getTaps());
            }
            else
            {
                timerArray.add(timer.getElapsed());
            }
            srcArray.add(path);
        }
        replayArray.add(""+usedReplay);
    }

    /*
     * Updates data bundle.
     */
    public Bundle bundleData()
    {
        Log.i("bundleData", "was called.");
        data.putStringArrayList("timer", timerArray);
        data.putStringArrayList("src", srcArray);
        data.putStringArrayList("replay", replayArray);
        for (String item:timerArray) {
            Log.e("Item", item);
        }
        return data;
    }
}
