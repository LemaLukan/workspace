/*
 * Recorder.java
 *
 * Handles recording of voice data using MediaRecorder.
 */

package com.android.data;

import java.io.IOException;

import android.media.MediaRecorder;
public class Recorder
{
    private MediaRecorder rec;
    private String filename;

    public Recorder()
    {}

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public void setupRecorder()
    {
        rec = new MediaRecorder();
        rec.setAudioSource(MediaRecorder.AudioSource.MIC);
        rec.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        rec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        rec.setOutputFile(filename);
        try
        {
            rec.prepare();
        }
        catch (IOException e)
        {}
    }

    public Integer getAmplitude ()
    {
    	return rec.getMaxAmplitude();
    }

    public void startRec()
    {
        rec.start();
    }

    public void stopRec()
    {
        rec.stop();
        rec.release();
    }
}
