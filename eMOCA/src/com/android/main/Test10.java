package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test10 extends TestObject
{
    private final int TESTID = 10;
    private final Class<?> NEXT = Test11.class;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.startTest(savedInstanceState, TESTID, NEXT);
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
