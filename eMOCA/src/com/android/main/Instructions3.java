package com.android.main;

import com.android.main.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Instructions3 extends ExtendedActivity
{
    private Bundle data;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_3);

        data = getIntent().getBundleExtra("data");

        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.instructions);
    }

    public void startTest(View v)
    {
        Intent next = new Intent(Instructions3.this, Test1.class);
        next.putExtra("data", data);
        startActivity(next);
        finish();
    }

}

