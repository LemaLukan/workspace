package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test26 extends TestObject
{
    private final int TESTID = 26;
    private final Class<?> NEXT = Test27.class;


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
