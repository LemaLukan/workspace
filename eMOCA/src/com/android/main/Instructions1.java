package com.android.main;

import com.android.main.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Instructions1 extends ExtendedActivity
{
    private Bundle data;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_1);

        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.instructions);

        data = getIntent().getBundleExtra("data");
    }

    public void startTest(View v)
    {
        Intent next = new Intent(Instructions1.this, Instructions2.class);
        next.putExtra("data", data);
        startActivity(next);
        finish();
    }
}

