package com.android.main;

import com.android.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MarkSplashScreen extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_splash_screen);
        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.marking_title);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroller);
        // For smaller layouts
        if (scrollView != null) {
            scrollView.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    public void startMarking(View v)
    {
        Intent next = new Intent(this, Scoring.class);
        startActivity(next);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent next = new Intent(getApplicationContext(), eMOCA.class);
            next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            next.setAction(Intent.ACTION_MAIN);
            next.addCategory(Intent.CATEGORY_HOME);
            startActivity(next);
        }
        return super.onKeyDown(keyCode, event);
    }
}

