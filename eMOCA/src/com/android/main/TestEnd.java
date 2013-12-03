package com.android.main;

import com.android.data.DBHandler;
import com.android.main.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TestEnd extends Activity
{
    private final int LAYOUT = R.layout.test_end;
    private final int RES = R.raw.testend;
    private final String TYPE = "Electronic";
    private MediaPlayer player;
    private Bundle data;
    private DBHandler db;
    private Boolean playing = false;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.test_end);
        playInstructions();
        db = new DBHandler(this);
        db.open();
        data = getIntent().getBundleExtra("data");
        fillDatabase();
        // Sets the `admin`.file_name default here so if the activity is
        // destroyed before exitTest is called that field won't be blank
    	db.updateFileName(data.getString("surveyID"), "GUEST");
    }

    protected void playInstructions()
    {
        player = MediaPlayer.create(this, RES);
        this.playing = true;
        player.start();
    }


    /*
     * Write captured data to database.
     */
    public void fillDatabase()
    {
        String surveyID = data.getString("surveyID");
        db.insertSrcData(surveyID, data.getStringArrayList("src"));
        db.insertTimerData(surveyID, data.getStringArrayList("timer"));
        db.insertReplayData(surveyID, data.getStringArrayList("replay"));
        db.insertAdminData(surveyID, data.getString("date"), data.getString("name"), data.getString("location"), TYPE);
        db.insertFieldData(surveyID, data.getStringArrayList("field"));
        db.insertRadioData(surveyID, data.getStringArrayList("radio"));
        db.insertCheckData(surveyID, data.getStringArrayList("check"));

    }

    public boolean stopSound()
    {
        if ( this.playing )
        {
            player.stop();
            player.release();
            this.playing = false;
            return true;
        }
        player.release();
        return false;

    }

    public void exitTest(View v)
    {
    	String file_name = ((EditText) this.findViewById(R.id.editText_file_name)).getText().toString().trim();
    	if (file_name.length() != 0) {
	    	db.updateFileName(data.getString("surveyID"), file_name);
    	}
        this.stopSound();
        this.finish();
        Intent next = new Intent(TestEnd.this, eMOCA.class);
        startActivity(next);
    }

    public void exitApp(View v)
    {
        this.stopSound();
        Intent home = new Intent(TestEnd.this, eMOCA.class);
        home.putExtra("finish", true);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.setAction(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.stopSound();
            Intent next = new Intent(this, eMOCA.class);
            startActivity(next);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
        db.close();
        stopSound();
    }

}
