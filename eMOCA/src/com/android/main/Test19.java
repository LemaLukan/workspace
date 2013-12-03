package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test19 extends TestObject
{
    private final int TESTID = 19;
    private final Class<?> NEXT = Test20.class;


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
