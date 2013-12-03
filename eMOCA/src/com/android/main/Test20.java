package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test20 extends TestObject
{
    private final int TESTID = 20;
    private final Class<?> NEXT = Test21.class;


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
