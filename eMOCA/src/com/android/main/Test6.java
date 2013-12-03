package com.android.main;

import android.os.Bundle;
import android.view.View;

public class Test6 extends TestObjectAnimal
{
    private final int TESTID = 6;
    private final int LAYOUT = R.layout.camel_test;
    private final Class<?> NEXT = Test7.class;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.startTest(savedInstanceState, TESTID, LAYOUT, NEXT);
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
