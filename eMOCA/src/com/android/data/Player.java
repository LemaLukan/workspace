/*
 *
 * Player.java
 *
 * Pool of sound files for application using MediaPlayer.
 *
 */

package com.android.data;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Player implements OnCompletionListener
{
    private MediaPlayer mp1, mp2;
    private long duration;

    public boolean completed = false;

    public Player(Context ctx, int res1)
    {
        mp1 = MediaPlayer.create(ctx, res1);
        duration = mp1.getDuration();
        mp1.start();
    }


    public Player(Context ctx, int res1, int res2)
    {
        mp1 = MediaPlayer.create(ctx, res1);
        mp2 = MediaPlayer.create(ctx, res2);
        duration = mp1.getDuration() + mp2.getDuration();
        mp1.setOnCompletionListener(this);
        mp1.start();
    }

    public void onCompletion(MediaPlayer mp)
    {
        mp2.start();
        return;
    }

    public long getDuration()
    {
        return duration;
    }

    public void stopSound()
    {
        mp1.stop();
        mp1.release();
        if (mp2 != null)
        {
            mp2.stop();
            mp2.release();
        }

    }
}
