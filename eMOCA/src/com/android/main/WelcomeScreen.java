package com.android.main;

import com.android.main.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeScreen extends ExtendedActivity
{
    private Bundle data;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.about_title);

        data = getIntent().getBundleExtra("data");
    }

    public void startTest(View v)
    {
        Intent next = new Intent(this, Instructions1.class);
        next.putExtra("data", data);
        startActivity(next);
    }
}

