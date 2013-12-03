package com.android.main;

import android.view.View;
import android.view.View.OnClickListener;

/*
 * Allows for the ability to pass in the surveyID to the OnClickListener.
 */
public abstract class ClickListener implements OnClickListener {

    protected String surveyID;

    public ClickListener (String surveyID) {
        this.surveyID = surveyID;
    }

    public abstract void onClick(View v);
}
