package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test32 extends TestObject
{
    private final int TESTID = 32;
    private final Class<?> NEXT = Test33.class;


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
