package com.android.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import com.android.data.DBHandler;
import com.android.main.R;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MarkingElectronic extends ExtendedActivity
{
    private String surveyID;
    private String marker;
    private String location;
    private ArrayList<Bitmap> bmp;
    private ArrayList<String> marks;
    private ArrayList<Integer> scores;
    private String wordNum;
    private int educationBonus = 0;
    private int sectionMark;

    private MediaPlayer player;
    private String resNum;
    private DBHandler dbh;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_electronic);
        this.surveyID = getIntent().getStringExtra("surveyID");
        dbh = new DBHandler(this);
        dbh.open();

        addDate();
        prepareResources();
        //getEducationBonus();
        getTapTimes();
    }

    public void addDate()
    {
        TextView text = (TextView) findViewById(R.id.orientElec);
        if ( text != null)
        {
            text.append(" - Assessment Date (DD/MM/YYYY): " + surveyID.substring(0, 2) + "/" + surveyID.substring(2, 4) + "/" + surveyID.substring(4, 8));
        }
    }

    public void getEducationBonus()
    {
        int dateOfBirth = 0;
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        Cursor cursor;

        cursor = dbh.fetchYearOfBirth(surveyID, "q14");
        startManagingCursor(cursor);
        if (cursor.moveToFirst())
        {
            dateOfBirth = Integer.parseInt(cursor.getString(cursor.getColumnIndex("q14")));
        }

        if ( curYear - dateOfBirth < 18 && curYear - dateOfBirth > 0)
        {
            educationBonus = 1;
        }

    }

    public void getTapTimes()
    {
        TextView text = ((TextView) findViewById(R.id.tapTimeText));
        Cursor taps;
        String tapText;
        String tapNum[];

        taps = dbh.fetchTapTimes(surveyID, "q5_time");
        startManagingCursor(taps);
        if (taps.moveToFirst())
        {
            tapText = taps.getString(taps.getColumnIndex("q5_time"));
            Log.e("Taps value", tapText);
            if ( tapText.compareTo("0") != 0)
            {
                tapNum = tapText.split(",");
                tapText += " (Number of Taps: " + tapNum.length + ")";
            }
        }
        else
        {
            tapText= "No Data";
        }
        text.setText(tapText);

    }

    public void prepareResources()
    {
        bmp = new ArrayList<Bitmap>();

        for (int i = 1; i < 4; ++i)
        {
            bmp.add(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+
                        "/eMOCA_Files/" + surveyID + "/quest" + i + ".png"));
        }

        loadImages();
    }

    public void loadImages()
    {

        ImageView img1;
        img1 = ((ImageView) findViewById(R.id.nodeImage));
        img1.setImageBitmap(bmp.get(0));
        ImageView img2;
        img2 = ((ImageView) findViewById(R.id.cubeImage));
        img2.setImageBitmap(bmp.get(1));
        ImageView img3;
        img3 = ((ImageView) findViewById(R.id.clockImage));
        img3.setImageBitmap(bmp.get(2));
    }

    public void decPicker(View v)
    {
        EditText text = ((EditText) findViewById(R.id.wordlistEdit));
        if ( text != null )
        {
            int num = Integer.parseInt(text.getText().toString());
            if ( num != 0)
            {
                --num;
            }
            text.setText(""+num);
        }
    }

    public void incPicker(View v)
    {
        EditText text = ((EditText) findViewById(R.id.wordlistEdit));
        if ( text != null )
        {
            int num = Integer.parseInt(text.getText().toString());
            text.setText(""+(++num));
        }
    }

    public void playAudio(View v)
    {
        resNum = v.getTag().toString();
        long fileSize = (new File(Environment.getExternalStorageDirectory()+
                "/eMOCA_Files/" + surveyID + "/quest" + resNum + ".mp4")).length();
        if (fileSize > 0)
        {
            startPlayback();
        }
    }

    public void startPlayback()
    {
        if (player != null)
        {
            player.release();
        }
            player = new MediaPlayer();
        try
        {
            player.setDataSource(Environment.getExternalStorageDirectory()+
                    "/eMOCA_Files/" + surveyID + "/quest" + resNum + ".mp4");
            player.prepare();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (player != null)
        {
            player.start();
        }
    }

    public void stopPlayback(View v)
    {
        if ( player != null )
        {
            player.release();
        }
    }

    public void submitScore(View v)
    {
        marks = new ArrayList<String>(21);
        scores = new ArrayList<Integer>(9);

        getSections();

        dbh.insertScoreData(surveyID, marker, location, educationBonus, marks, scores, wordNum);

        dbh.close();
        if ( player != null )
        {
            player.release();
        }
        Intent next = new Intent(this, EndScore.class);
        next.putExtra("surveyID", surveyID);
        next.putExtra("scores", scores);
        next.putExtra("bonus", educationBonus);
        startActivity(next);
    }

    public void getSections()
    {
        getMarker();
        getSectionVSExec();
        getSectionNaming();
        getSectionMemory();
        getSectionAttention();
        getSectionLanguage();
        getSectionAbstraction();
        getSectionDelayedRecall();
        getSectionOrientation();

    }

    public void getMarker()
    {
        marker = ((EditText)this.findViewById(R.id.nameElec)).getText().toString();
        location = ((EditText)this.findViewById(R.id.locationElec)).getText().toString();
    }

    public String extractAnswer(int id)
    {
        int retVal = ((CheckBox)this.findViewById(id)).isChecked() ? 1 : 0;
        sectionMark += retVal;
        return retVal + "";
    }

    public void getSectionVSExec()
    {
        String points = "";
        sectionMark = 0;

        marks.add(extractAnswer(R.id.nodeCheck));
        marks.add(extractAnswer(R.id.cubeCheck));

        points += extractAnswer(R.id.contourCheck);
        points += extractAnswer(R.id.numbersCheck);
        points += extractAnswer(R.id.handsCheck);
        marks.add(points);
        scores.add(sectionMark);
    }

    public void getSectionNaming()
    {
        String points = "";
        sectionMark = 0;

        points += extractAnswer(R.id.lionCheck);
        points += extractAnswer(R.id.rhinoCheck);
        points += extractAnswer(R.id.camelCheck);
        marks.add(points);
        scores.add(sectionMark);
    }

    public void getSectionMemory()
    {
        String points = "";

        points += extractAnswer(R.id.faceCheck1);
        points += extractAnswer(R.id.velvetCheck1);
        points += extractAnswer(R.id.churchCheck1);
        points += extractAnswer(R.id.daisyCheck1);
        points += extractAnswer(R.id.redCheck1);
        marks.add(points);

        points = "";
        points += extractAnswer(R.id.faceCheck2);
        points += extractAnswer(R.id.velvetCheck2);
        points += extractAnswer(R.id.churchCheck2);
        points += extractAnswer(R.id.daisyCheck2);
        points += extractAnswer(R.id.redCheck2);
        marks.add(points);
    }

    public void getSectionAttention()
    {
        String points = "";
        sectionMark = 0;

        marks.add(extractAnswer(R.id.forwardCheck));
        marks.add(extractAnswer(R.id.backwardCheck));
        scores.add(sectionMark);

        sectionMark = 0;
        marks.add(extractAnswer(R.id.letterlistCheck));
        scores.add(sectionMark);

        sectionMark = 0;
        points += extractAnswer(R.id.subCheck1);
        points += extractAnswer(R.id.subCheck2);
        points += extractAnswer(R.id.subCheck3);
        points += extractAnswer(R.id.subCheck4);
        points += extractAnswer(R.id.subCheck5);
        marks.add(points);

        if ( sectionMark > 3 )
        {
            scores.add(3);
        }
        else if ( sectionMark > 1)
        {
            scores.add(2);
        }
        else
        {
            scores.add(sectionMark);
        }
    }

    public void getSectionLanguage()
    {
        sectionMark = 0;
        marks.add(extractAnswer(R.id.sentenceCheck1));
        marks.add(extractAnswer(R.id.sentenceCheck2));
        scores.add(sectionMark);

        sectionMark = 0;
        marks.add(extractAnswer(R.id.wordlistCheck));
        scores.add(sectionMark);
        wordNum = ((EditText)this.findViewById(R.id.wordlistEdit)).getText().toString();

    }

    public void getSectionAbstraction()
    {

        marks.add(extractAnswer(R.id.absCheck1));
        sectionMark = 0;
        marks.add(extractAnswer(R.id.absCheck2));
        marks.add(extractAnswer(R.id.absCheck3));
        scores.add(sectionMark);

    }

    public void getSectionDelayedRecall()
    {
        String points = "";
        sectionMark = 0;

        points += extractAnswer(R.id.faceCheck3);
        points += extractAnswer(R.id.velvetCheck3);
        points += extractAnswer(R.id.churchCheck3);
        points += extractAnswer(R.id.daisyCheck3);
        points += extractAnswer(R.id.redCheck3);
        Log.w("Alert", ""+ points);
        marks.add(points);
        Log.w("Alert", ""+ sectionMark);
        scores.add(sectionMark);

        points = "";
        points += extractAnswer(R.id.faceCheck4);
        points += extractAnswer(R.id.velvetCheck4);
        points += extractAnswer(R.id.churchCheck4);
        points += extractAnswer(R.id.daisyCheck4);
        points += extractAnswer(R.id.redCheck4);
        marks.add(points);

        points = "";
        points += extractAnswer(R.id.faceCheck5);
        points += extractAnswer(R.id.velvetCheck5);
        points += extractAnswer(R.id.churchCheck5);
        points += extractAnswer(R.id.daisyCheck5);
        points += extractAnswer(R.id.redCheck5);


        marks.add(points);
    }

    public void getSectionOrientation()
    {
        String points = "";
        sectionMark = 0;

        points += extractAnswer(R.id.orientCheck1);
        points += extractAnswer(R.id.orientCheck2);
        points += extractAnswer(R.id.orientCheck3);
        points += extractAnswer(R.id.orientCheck4);
        marks.add(points);

        points = "";
        points += extractAnswer(R.id.orientCheck5);
        points += extractAnswer(R.id.orientCheck6);
        marks.add(points);
        scores.add(sectionMark);
    }

}
