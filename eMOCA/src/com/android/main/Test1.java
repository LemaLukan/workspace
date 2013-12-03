package com.android.main;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Test1 extends TestObjectDrawing
{
    private final int TESTID = 1;
    private final int LAYOUT = R.layout.node_test;
    private final Class<?> NEXT = Test2.class;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.startTest(savedInstanceState, TESTID, LAYOUT, NEXT);
        Log.e("Test1", "creation");
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
