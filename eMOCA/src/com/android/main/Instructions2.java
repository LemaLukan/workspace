package com.android.main;

import com.android.main.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Instructions2 extends ExtendedActivity
{
    private Bundle data;
    CanvasDrawing canvasDrawing;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_2);

        data = getIntent().getBundleExtra("data");

        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.instructions);

        canvasDrawing = (CanvasDrawing) this.findViewById(R.id.cd);
    }

    public void startTest(View v)
    {
        Intent next = new Intent(Instructions2.this, Instructions3.class);
        next.putExtra("data", data);
        startActivity(next);
        finish();
    }

    public void eraseCanvas(View v)
    {
        canvasDrawing.eraseCanvas();
    }
}

