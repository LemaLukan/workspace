package com.android.main;

import java.io.IOException;

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

import com.android.data.DataHandler;


public class TestObject extends ExtendedActivity implements OnCompletionListener
{
    // Overall Data
    protected String surveyID;
    private DataHandler dt;
    private Bundle data;

    // Test-Specific
    private int testID;
    private int res;
    private int delay;
    private int endRes;
    private Class<?> nextClass;
    private String filepath;

    // Utility
    private MediaPlayer player;
    private VisualMediaRecorder recorder;
    private Countdown cdt;

    // Flags
    private boolean isRecording = false;
    protected boolean endingAudio = false;
    private boolean replay = false;
    private boolean nextPending = false;
    private boolean started = false;
    private boolean call_init = true;

    /**************************************************************************
     * SETUP
     *************************************************************************/

    /*
     * Initial Setup for test.
     */
    public void startTest(Bundle savedInstanceState, int testID,  Class<?> nextClass)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_test);
        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.question);
        this.nextClass = nextClass;
        this.testID = testID;
        this.res = TestConstants.SOUND[testID-1];
        this.delay = TestConstants.DELAY[testID-1];
        Log.w("SETUP", "Starting Initial Setup (Default Constructor 1)");
        initialSetup();
    }

    /*
     * Initial Setup for tests with ending audio.
     */
    public void startTest(Bundle savedInstanceState, int testID, int endRes, Class<?> nextClass)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_test);
        this.nextClass = nextClass;
        this.testID = testID;
        this.res = TestConstants.SOUND[testID-1];
        this.endRes = endRes;
        this.delay = TestConstants.DELAY[testID-1];
        this.endingAudio = true;
        Log.w("SETUP", "Starting Initial Setup (Constructor 2)");
        initialSetup();
    }

    public void initialSetup()
    {
        this.call_init = false;
        data = getIntent().getBundleExtra("data");
        surveyID = data.getString("surveyID");
        dt = new DataHandler(testID, data);
        filepath = "/eMOCA_Files/" + surveyID + "/quest" + testID + ".mp4";

        setupRecording();
        Log.w("SETUP", "Setup complete, starting initial playback");
        playInstructions();
    }

    /*
     * Sets up MediaRecorder.
     * Note: Order of set statements is important.
     * See: http://developer.android.com/images/mediarecorder_state_diagram.gif
     */
    protected void setupRecording()
    {
        recorder = new VisualMediaRecorder(this, R.id.ampText);
        recorder.setAudioSource(VisualMediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(VisualMediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(VisualMediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(Environment.getExternalStorageDirectory() + filepath);
        try
        {
            recorder.prepare();
        }
        catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**************************************************************************
     * AUDIO
     *************************************************************************/

    /*
     * Instruction Playback
     */
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
            playInstructions();
        }
    }

    /*
     * (non-Javadoc)
     * @see android.media.MediaPlayer.OnCompletionListener#onCompletion(android.media.MediaPlayer)
     *
     * After completing instructions playback
     */
    @Override
    public void onCompletion(MediaPlayer arg0)
    {
        if ( isRecording == false)
        {
            isRecording = true;
            recorder.start();
        }
        beginTest();
    }

    /*
     * Play End-cap audio
     */
    public void playEndingAudio()
    {
        player = MediaPlayer.create(this, endRes);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                {
                    @Override
                    public void onCompletion(MediaPlayer mp)
        {
            Intent next = new Intent(getApplicationContext(), nextClass);
            next.putExtra("data", dt.bundleData());
            startActivity(next);
        }
        });
        player.start();
    }

    /*
     * Stop playback and/or release player.
     */
    public boolean stopSound()
    {
        Log.w("Sound", "Stoping playback/recording and releasing player/recorder.");
        if ( isRecording )
        {
            recorder.stop();
            recorder.reset();
            isRecording = false;
        }
        recorder.release();

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
        TextView text = (TextView) findViewById(R.id.readyText);

        if ( text != null )
        {
            text.setVisibility(0);
        }
    }

    protected void nextTest()
    {
        if ( nextPending == false )
        {
            Log.w("Next Test", "Capturing data and cleaning up.");
            nextPending = true;
            if ( cdt != null )
            {
                cdt.cancel();
            }
            dt.stopTracking(stopSound());

            if ( endingAudio )
            {
                playEndingAudio();
            }
            else
            {
                Intent next = new Intent(this, nextClass);
                next.putExtra("data", dt.bundleData());
                next.setAction(Intent.ACTION_MAIN);
                next.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(next);
                finish();
            }
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
