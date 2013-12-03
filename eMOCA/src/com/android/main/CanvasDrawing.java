package com.android.main;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;


public class CanvasDrawing extends View
{
    private Canvas canvas = null;
    private Bitmap bitmap;
    private Paint paint = new Paint();

    private float previousX = -1;
    private float previousY = -1;
    public int resetFlag = 1;
    private ArrayList<Float> points;

    public CanvasDrawing(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        canvas = new Canvas();
        points = new ArrayList<Float>(200);
    }

    protected void addPoint(float x, float y)
    {
        if ( resetFlag == 1)
        {
            points.add(x);
            points.add(y);

            resetFlag = 0;
        }
        else
        {
            points.add(previousX);
            points.add(previousY);

        }

        points.add(x);
        points.add(y);
        previousX = x;
        previousY = y;
        invalidate();

    }

    protected float[] convertToArray()
    {
        float[] array = new float[points.size()];
        for ( int i = 0; i < points.size(); ++i)
        {
            array[i] = points.get(i);
        }
        return array;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
        {
            return false;
        }


        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            addPoint((int)event.getX(), (int)event.getY());
            resetFlag = 1;
        }
        else
        {
            addPoint((int)event.getX(), (int)event.getY());
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.LTGRAY);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);

        canvas.drawLines(convertToArray(), paint);
    }

    public void eraseCanvas()
    {
        points.clear();
        invalidate();
    }

    public void save(String filepath)
    {
        Canvas_SaveToFile save = new Canvas_SaveToFile(filepath);
        bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.RGB_565);
        canvas.setBitmap(bitmap);
        onDraw(canvas);
        save.saveToFile(bitmap);
    }
}

