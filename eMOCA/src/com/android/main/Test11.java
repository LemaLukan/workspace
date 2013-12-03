package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test11 extends TestObjectTap
{
    private final int TESTID = 11;
    private final Class<?> NEXT = Test12.class;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.startTest(savedInstanceState, TESTID, NEXT);
    }

    public void replayInst(View v)
    {
        super.replayInstructions();
    }

    public void addTap(View v)
    {
        super.addTap();
    }

    public void nextTest(View v)
    {
        super.nextTest();
    }
}
