package com.android.main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.android.data.DataHandler;


public class TestObjectTap extends ExtendedActivity implements OnCompletionListener
{
    // Overall Data
    protected String surveyID;
    private DataHandler dt;
    private Bundle data;

    // Test-Specific
    private int testID;
    private int res;
    private int delay;
    private Class<?> nextClass;
    private String filepath;

    // Utility
    private MediaPlayer player;
    private Countdown cdt;

    // Flags
    private boolean replay = false;
    private boolean nextPending = false;
    private boolean started = false;
    private boolean call_init = true;


    /**************************************************************************
     * SETUP
     *************************************************************************/

    public void startTest(Bundle savedInstanceState, int testID, Class<?> nextClass)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap_test);
        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.question);
        this.nextClass = nextClass;
        this.testID = testID;
        this.res = TestConstants.SOUND[testID-1];
        this.delay = TestConstants.DELAY[testID-1];
        this.filepath = "none";
        Log.w("SETUP", "Starting Initial Setup (Tap Test Constructor)");
        initialSetup();
    }

    public void initialSetup()
    {
        this.call_init = false;
        data = getIntent().getBundleExtra("data");
        surveyID = data.getString("surveyID");
        dt = new DataHandler(testID, data);
        if ( dt.isTracking() == false )
        {
            dt.startTracking(filepath);
        }
        Log.w("SETUP", "Setup complete, starting initial playback");
        playInstructions();
    }

    /**************************************************************************
     * AUDIO
     *************************************************************************/

    protected void playInstructions()
    {
        this.started = true;
        player = MediaPlayer.create(this, res);
        player.setOnCompletionListener(this);
        player.start();
    }

    protected void replayInstructions()
    {
        if (!replay)
        {
            Log.w("Replay", "Replaying instructions.");
            if ( cdt != null )
            {
                cdt.cancel();
            }
            replay = true;
            dt.addReplay();
            player.stop();
            player.release();
            dt.addDelay();
            playInstructions();
        }
    }

    @Override
    public void onCompletion(MediaPlayer arg0)
    {
        cdt = new Countdown(delay, 1000);
        cdt.start();
        Log.w("Start Test", "Starting/restarting test.");
    }

    /*
     * Stop playback and/or release player.
     */
    public boolean stopSound()
    {
        Log.w("Sound", "Stoping playback and releasing player.");
        if ( this.started )
        {
            player.stop();
            player.release();
            this.started = false;

            return true;
        }
        player.release();

        return false;
    }

    /**************************************************************************
     * TIMING
     *************************************************************************/

    /*
     * Countdown Timer for Test Time Limit
     */
    class Countdown extends CountDownTimer
    {
        public Countdown(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish()
        {
            onTimeout();
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            Log.w("Countdown", "Remaining: " + (millisUntilFinished / 1000));
        }
    }

    public void onTimeout()
    {
        if ( replay == false )
        {
            Log.w("Time-out", "Time-out 1");
            replayInstructions();
        }
        else
        {
            Log.w("Time-out", "Time-out 2");
            nextTest();
        }
    }

    /**************************************************************************
     * START/END TEST + TEST RELATED
     *************************************************************************/

    public void addTap()
    {
        dt.addTap();
    }

    protected void nextTest()
    {
        if ( nextPending == false )
        {
            Log.w("Next Test", "Capturing data and cleaning up.");
            nextPending = true;
            dt.stopTracking(stopSound());

            if ( cdt != null )
            {
                cdt.cancel();
            }
            Intent next = new Intent(this, nextClass);
            next.putExtra("data", dt.bundleData());
            next.setAction(Intent.ACTION_MAIN);
            next.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(next);
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.call_init = true;
        if ( cdt != null)
        {
            cdt.cancel();
        }
        dt.stopTracking(stopSound());
        this.replay = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.call_init)
        {
            initialSetup();
        }
    }


}
