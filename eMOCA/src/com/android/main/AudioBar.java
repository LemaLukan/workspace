package com.android.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AudioBar extends View
{

    private Canvas canvas;
    private Paint paint;
    //final private int height = 166;
    //final private int width = 187;
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audio0);
    private int oldValue = 0;

    public AudioBar(Context context,  AttributeSet attrs)
    {
        super(context, attrs);
        canvas = new Canvas();
        canvas.drawColor(Color.WHITE);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    private int convertRatio (double value)
    {
        final double newMax = 700;
        final double newMin = 0;
        final double oldMax = 32767;
        final double oldMin = 0;
        double oldRange = oldMax - oldMin;
        double newRange = newMax - newMin;
        return (int) ((((value - oldMin) * newRange) / oldRange) + newMin);
    }

    public void updateBar (double value) {
        Log.w("Audio", "updateBar was called!!");
        // added some padding so the microphone doesn't seem so insensitive
        int convertedValue = this.convertRatio(value);
        if (convertedValue > this.oldValue) {
            this.oldValue = convertedValue;
        } else {
            this.oldValue = this.oldValue - 25;
            convertedValue = this.oldValue;
        }

        Log.w("Audio", Integer.toString(convertedValue));

        if (convertedValue >= 600) {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audio6);
        } else if (convertedValue >= 450) {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audio5);
        } else if (convertedValue >= 300) {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audio4);
        } else if (convertedValue >= 200) {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audio3);
        } else if (convertedValue >= 100) {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audio2);
        } else if (convertedValue >= 50) {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audio1);
        } else {
            this.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audio0);
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(this.bitmap, 0, 0, this.paint);
        //this.bitmap.recycle();
    }

}
