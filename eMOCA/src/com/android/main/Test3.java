package com.android.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Test3 extends TestObjectDrawing
{
    private final int TESTID = 3;
    private final int LAYOUT = R.layout.clock_test;
    private final Class<?> NEXT = Test4.class;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.startTest(savedInstanceState, TESTID, LAYOUT, NEXT);
        Log.e("Test3", "creation");
    }

    public void replayInst(View v)
    {
        super.replayInstructions();
    }

    public void nextTest(View v)
    {
        super.nextTest();
    }
}
