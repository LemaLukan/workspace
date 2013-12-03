package com.android.main;

import java.util.ArrayList;

import com.android.main.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndScore extends ExtendedActivity
{
    private final int LAYOUT = R.layout.score_end;
    private String surveyID;
    private ArrayList<Integer> scores;
    private int bonus;
    private int[] subtotals = { 5, 3, 2, 1, 3, 2, 1, 2, 5, 6 };


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        this.surveyID = getIntent().getStringExtra("surveyID");
        this.scores = getIntent().getIntegerArrayListExtra("scores");
        this.bonus = getIntent().getIntExtra("bonus", 0);
        fillScores();
    }


    /*
     * Write captured data to database.
     */
    public void fillScores()
    {
        int total = 0;
        TextView text = (TextView) findViewById(R.id.titleText);
        text.setText("Score for: " + surveyID);
        text = (TextView) findViewById(R.id.sec_1);
        text.setText(scores.get(0) + "/" + subtotals[0]);
        text = (TextView) findViewById(R.id.sec_2);
        text.setText(scores.get(1) + "/" + subtotals[1]);
        text = (TextView) findViewById(R.id.sec_3);
        text.setText(scores.get(2) + "/" + subtotals[2]);
        text = (TextView) findViewById(R.id.sec_4);
        text.setText(scores.get(3) + "/" + subtotals[3]);
        text = (TextView) findViewById(R.id.sec_5);
        text.setText(scores.get(4) + "/" + subtotals[4]);
        text = (TextView) findViewById(R.id.sec_6);
        text.setText(scores.get(5) + "/" + subtotals[5]);
        text = (TextView) findViewById(R.id.sec_7);
        text.setText(scores.get(6) + "/" + subtotals[6]);
        text = (TextView) findViewById(R.id.sec_8);
        text.setText(scores.get(7) + "/" + subtotals[7]);
        text = (TextView) findViewById(R.id.sec_9);
        text.setText(scores.get(8) + "/" + subtotals[8]);
        text = (TextView) findViewById(R.id.sec_10);
        text.setText(scores.get(9) + "/" + subtotals[9]);
        text = (TextView) findViewById(R.id.sec_10);
        text.setText(scores.get(9) + "/" + subtotals[9]);
        text = (TextView) findViewById(R.id.bonusText);
        text.setText(bonus +"/1");
        text = (TextView) findViewById(R.id.totalText);

        for (Integer i : scores)
        {
            total += i;
        }
        text.setText(total +"/30");

    }


    public void exitScore(View v)
    {
        Intent next = new Intent(this, eMOCA.class);
        startActivity(next);
    }

}
