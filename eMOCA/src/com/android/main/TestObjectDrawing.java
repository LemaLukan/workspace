package com.android.main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.android.main.TestConstants;

import com.android.data.DataHandler;


public class TestObjectDrawing extends ExtendedActivity implements OnCompletionListener
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
    private DrawPoints drawPoints;
    private CanvasDrawing canvasDrawing;

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

    public void startTest(Bundle savedInstanceState, int testID, int layout, Class<?> nextClass)
    {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.question);
        this.nextClass = nextClass;
        this.testID = testID;
        this.res = TestConstants.SOUND[testID-1];
        this.delay = TestConstants.DELAY[testID-1];
        Log.w("SETUP", "Starting Initial Setup (Drawing Test Constructor)");
        initialSetup();
    }

    public void initialSetup()
    {
        this.call_init = false;
        data = getIntent().getBundleExtra("data");
        surveyID = data.getString("surveyID");
        dt = new DataHandler(testID, data);
        filepath = "/eMOCA_Files/" + surveyID + "/quest" + testID + ".png";

        if ( testID == 1)
        {
            drawPoints = (DrawPoints) this.findViewById(R.id.dp);
        }
        else
        {
            canvasDrawing = (CanvasDrawing) this.findViewById(R.id.cd);
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
            dt.addReplay();
            replay = true;
            player.stop();
            player.release();
            playInstructions();
        }
    }

    @Override
    public void onCompletion(MediaPlayer arg0)
    {
        beginTest();
    }

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
            player = MediaPlayer.create(this, R.raw.notification);
            startTimeoutAnimation();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                    {
                        @Override
                        public void onCompletion(MediaPlayer mp)
            {
                replayInstructions();
            }
            });
            player.start();
        }
        else
        {
            Log.w("Time-out", "Time-out 2");
            nextTest();
        }
    }

    public void startTimeoutAnimation()
    {
        Button button = (Button) this.findViewById(R.id.button2);
        if (button != null)
        {
            final Animation animation = new AlphaAnimation(1, 0);
            animation.setDuration(500); // duration - half a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(5); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE);
            button.startAnimation(animation);
        }
    }

    /**************************************************************************
     * IMAGE
     *************************************************************************/

    public void saveImages()
    {
        Log.w("Images", "Saving images.");
        if (drawPoints != null)
        {
            drawPoints.save(Environment.getExternalStorageDirectory() + filepath);
        }
        else if (canvasDrawing != null )
        {
            canvasDrawing.save(Environment.getExternalStorageDirectory() + filepath);
        }
    }


    /**************************************************************************
     * START/END TEST
     *************************************************************************/

    public void beginTest()
    {
        Log.w("Start Test", "Starting test.");
        if ( dt.isTracking() == false )
        {
            dt.startTracking(filepath);
        }
        cdt = new Countdown(delay, 1000);
        cdt.start();
    }

    protected void nextTest()
    {
        if ( nextPending == false )
        {
            Log.w("Next Test", "Capturing data and cleaning up.");
            nextPending = true;
            saveImages();

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

        if (drawPoints != null)
        {
            drawPoints.eraseCanvas();
        }
        else if (canvasDrawing != null )
        {
            canvasDrawing.eraseCanvas();
        }
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
