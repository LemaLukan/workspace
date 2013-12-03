package com.android.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.os.Environment;

public class Canvas_SaveToFile
{
    private String filepath;

    public Canvas_SaveToFile(String filepath)
    {
        this.filepath = filepath;
    }

    public void saveToFile(Bitmap bitmap)
    {
        //String path = "";
        try
        {
            //path = writeFile(bitmap);
            writeFile(bitmap);
            //readFile(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public String writeFile(Bitmap bitmap) throws IOException
    {
        String path = "";

        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        }
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        }
        else
        {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }


        File file = new File(filepath);
        file.createNewFile();

        OutputStream out = null;
        try
        {
            out = (OutputStream)new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, out);
            out.flush();
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
        return path;
    }
}
