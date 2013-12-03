package com.android.main;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.MediaRecorder;
import android.util.Log;

/**
 * Controls the visualization for a MediaRecorder
 *
 * Sets a Timer to run every few milliseconds calling getMaxAmplitude().
 * Using the data received it creates a visual effect to show the user they
 * are recording their voice.
 *
 * @author		<randy@redpistion.com>
 * @version		1
 * @since 		2013-05-01
 *
 */

public class VisualMediaRecorder extends MediaRecorder
{

    protected Timer timer;
    public Activity activity;
    public int viewId;
    public Boolean stopped = false;

    /**
     * Stores arguments for use
     *
     * @param activity 	What activity the visualization will run on
     * @param viewId 	the View's id that will hold the visualization
     */

    public VisualMediaRecorder (Activity activity, int viewId)
    {
        super();
        this.activity = activity;
        this.viewId = viewId;
    }

    /**
     * Custom Runnable
     *
     * Allows to the passing on an Activity and Integer which is used to
     * make changes to the UI
     */

    private class VisualizationRunnable implements Runnable
    {

        private int value;
        private Activity activity;
        private int viewId;
        private AudioBar audioBar;

        public VisualizationRunnable (Activity activity, int viewId, int value)
        {
            this.value = value;
            this.activity = activity;
            this.viewId = viewId;
            audioBar = (AudioBar) this.activity.findViewById(this.viewId);
            Log.i("VisualMediaRecorder", "Runnable was called");
        }

        /**
         * Finds the View ampText and sets its value
         */

        @Override
        public void run() {
            Log.i("AMP", Integer.toString(value));
            //audioBar.setVisibility(AudioBar.VISIBLE);
            audioBar.updateBar((double)value);
        }

    }

    /**
     *	Custom TimerTask, allows for the passing of an Activity and MediaRecorder
     */

    private class VisualizationTimerTask extends TimerTask
    {
        private Activity activity;
        private VisualMediaRecorder recorder;
        private int viewId;

        public VisualizationTimerTask (Activity activity, int viewId, VisualMediaRecorder recorder)
        {
            this.activity = activity;
            this.recorder = recorder;
            this.viewId = viewId;
            Log.i("VisualMediaRecorder", "TimerTask was called");
        }

        /**
         * Gets the data from getMaxAmplitude and calls a Runnable to edit
         * the activity's UI
         */
        @Override
        public void run() {
            int value = 0;
            if (this.recorder.stopped == false)
            {
                value = this.recorder.getMaxAmplitude();
            }
            /*
             * Sometimes getMaxAmplitude() returns 0. In those cases
             * we want to ignore that value
             */
            if (value != 0)
            {
                this.activity.runOnUiThread(new VisualizationRunnable((Activity) this.activity, this.viewId, value));
            }
        }
    }

    /**
     * Schedules the Timer and deals with the visualization
     *
     * Creates a Timer and schedules it run every 50 milliseconds.
     *
     */

    public void start()
    {
        Log.i("VisualMediaRecorder", "start was called");
        super.start();
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new VisualizationTimerTask(this.activity, this.viewId, this), 0, 50);
    }

    /**
     * Stops the Timer and prevents more schedules from being set
     *
     *  To start a new Timer you must call .start() again
     */

    public void stop()
    {
        Log.i("VisualMediaRecorder", "stop was called");
        this.stopped = true;
        super.stop();
        if (timer != null)
        {
            timer.purge();
            timer.cancel();
            timer = null;
        }
    }

    /**
     * Stops the Timer and prevents more schedules from being set
     *
     *  To start a new Timer you must call .start() again
     */

    public void reset()
    {
        super.reset();
        if (timer != null)
        {
            timer.purge();
            timer.cancel();
            timer = null;
        }
    }

}
