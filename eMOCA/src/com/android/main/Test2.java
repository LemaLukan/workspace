package com.android.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Test2 extends TestObjectDrawing
{
    private final int TESTID = 2;
    private final int LAYOUT = R.layout.cube_test;
    private final Class<?> NEXT = Test3.class;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.startTest(savedInstanceState, TESTID, LAYOUT, NEXT);
        Log.e("Test2", "creation");
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

