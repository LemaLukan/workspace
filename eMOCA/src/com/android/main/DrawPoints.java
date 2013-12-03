package com.android.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;


public class DrawPoints extends View
{
    private Canvas canvas = null;
    private Bitmap bitmap;
    private Paint paint = new Paint();

    private float previousX = -1;
    private float previousY = -1;
    public int resetFlag = 1;
    private ArrayList<Float> points;

    public DrawPoints(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        canvas = new Canvas();

        points = new ArrayList<Float>(200);
    }

    public void addPoint(float x, float y)
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

    public float[] convertToArray()
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
        initTestView(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        canvas.drawLines(convertToArray(), paint);
    }

    private void initTestView(Canvas canvas)
    {
    	int numNodes = 5;
    	String letters[] = {"A", "B", "C", "D", "E"};
    	// Coordinates are represented in percentages to take into account different canvas sizes.
    	double letterLocationX[] = { 0.6, 0.53, 0.308, 0.137, 0.248 };
    	double letterLocationY[] = { 0.118, 0.294, 0.659, 0.494, 0.141 };

    	double numberLocationX[] = { 0.308, 0.856, 0.856, 0.479, 0.068 };
    	double numberLocationY[] = { 0.376, 0.329, 0.529, 0.471, 0.247 };

    	double lineX[] = { 0.325, 0.575, 0.575, 0.621, 0.835, 0.835 };
    	double lineY[] = { 0.353, 0.127, 0.127, 0.127, 0.311, 0.311 };
    	double endLineX[] = { 0.575, 0.548, 0.565, 0.835, 0.825, 0.808 };
    	double endLineY[] = { 0.127, 0.127, 0.165, 0.311, 0.273, 0.311 };

    	int width = this.getWidth();
    	int height = this.getHeight();

    	for (int i = 0; i < numNodes; ++i)
    	{
			letterLocationX[i] = getTruePos(letterLocationX[i], width);
    		letterLocationY[i] = getTruePos(letterLocationY[i], height);
    		numberLocationX[i] = getTruePos(numberLocationX[i], width);
    		numberLocationY[i] = getTruePos(numberLocationY[i], height);
    	}

    	for (int i = 0; i < lineX.length; ++i)
    	{
    		lineX[i] = getTruePos(lineX[i], width);
    		lineY[i] = getTruePos(lineY[i], height);
    		endLineX[i] = getTruePos(endLineX[i], width);
    		endLineY[i] = getTruePos(endLineY[i], height);
    	}

        paint.setColor(Color.RED);
        paint.setStrokeWidth(0);

        //canvas.drawText("x: " + touchedX + " || y: " + touchedY, 10, 15, paint);

        paint.setTextSize(17);
        paint.setFakeBoldText(true);

        //draw the letters inside of the circles
        for (int a = 0; a < numNodes; a++)
        {
            paint.setColor(Color.BLACK);
            canvas.drawCircle((float)letterLocationX[a], (float)letterLocationY[a], 15, paint);
            paint.setColor(Color.LTGRAY);
            canvas.drawCircle((float)letterLocationX[a], (float)letterLocationY[a], 13, paint);

            paint.setColor(Color.BLACK);
            canvas.drawText(letters[a], (float)letterLocationX[a]-4, (float)letterLocationY[a]+7, paint);
        }

        //draw the numbers inside of the circles
        for (int a = 0; a < numNodes; a++)
        {
            paint.setColor(Color.BLACK);
            canvas.drawCircle((float)numberLocationX[a], (float)numberLocationY[a], 15, paint);
            paint.setColor(Color.LTGRAY);
            canvas.drawCircle((float)numberLocationX[a], (float)numberLocationY[a], 13, paint);

            paint.setColor(Color.BLACK);
            canvas.drawText("" + (a + 1), (float)numberLocationX[a]-4, (float)numberLocationY[a]+8, paint);
        }

        //add the "Begin" and "End" texts
        paint.setTextSize(14);
        paint.setFakeBoldText(true);
        canvas.drawText("Begin", (float)numberLocationX[0]-17, (float)numberLocationY[0]+28, paint);
        canvas.drawText("End", (float)letterLocationX[numNodes-1]-11, (float)letterLocationY[numNodes-1]+28, paint);

        //draw the initial two lines: 1 -> A -> 2
        for (int i = 0; i < lineX.length; ++i)
        {
	        canvas.drawLine((float)lineX[i], (float)lineY[i], (float)endLineX[i], (float)endLineY[i], paint);
        }
    }

    public double getTruePos(double location, int size)
    {
    	return Math.ceil(location*size);
    }

    public void eraseCanvas()
    {
        points.clear();
        invalidate();
    }

    @SuppressLint("WrongCall")
	public void save(String filepath)
    {
    	Canvas_SaveToFile save = new Canvas_SaveToFile(filepath);
        bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.RGB_565);
        canvas.setBitmap(bitmap);
        onDraw(canvas);
    	save.saveToFile(bitmap);

    }
}

