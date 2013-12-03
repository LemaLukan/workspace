package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test15 extends TestObjectLimit
{
    private final int TESTID = 15;
    private final int RES = R.raw.q_15end;
    private final Class<?> NEXT = Test16.class;


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
