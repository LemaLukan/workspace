package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test16 extends TestObject
{
    private final int TESTID = 16;
    private final int RES = R.raw.q_16end;
    private final Class<?> NEXT = Test17.class;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.startTest(savedInstanceState, TESTID, RES, NEXT);
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
