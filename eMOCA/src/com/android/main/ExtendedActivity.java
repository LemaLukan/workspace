package com.android.main;

import android.app.Activity;
import android.app.Dialog;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Intent;


public abstract class ExtendedActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroller);
        // For smaller layouts
        if (scrollView != null) {
            scrollView.fullScroll(ScrollView.FOCUS_UP);
        }
    }
    /**************************************************************************
     * ANDROID FUNCTIONALITY
     *************************************************************************/

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {

            final Dialog dialog = new Dialog(this, R.style.CustomDialogTheme);
            dialog.setContentView(R.layout.dialog_layout);
            TextView text = (TextView) dialog.findViewById(R.id.message);
            text.setText(R.string.default_dialog_message);
            dialog.setTitle(R.string.exit_survey);

            Button yes = (Button) dialog.findViewById(R.id.ok_button);
            yes.setOnClickListener(new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent next = new Intent(getApplicationContext(), eMOCA.class);
                    startActivity(next);
                }
            });

            Button no = (Button) dialog.findViewById(R.id.cancel_button);
            no.setOnClickListener(new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
