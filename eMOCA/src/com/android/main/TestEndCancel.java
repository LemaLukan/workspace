package com.android.main;

import com.android.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class TestEndCancel extends Activity
{
    private final int LAYOUT = R.layout.test_end_cancel;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        TextView text = (TextView) findViewById(R.id.page_title);
        text.setText(R.string.test_end);

    }

    public void exitTest(View v)
    {
        Intent next = new Intent(TestEndCancel.this, eMOCA.class);
        startActivity(next);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent next = new Intent(this, eMOCA.class);
            startActivity(next);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
