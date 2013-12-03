package com.android.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Test4 extends TestObjectAnimal
{
    private final int TESTID = 4;
    private final int LAYOUT = R.layout.lion_test;
    private final Class<?> NEXT = Test5.class;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	Log.i("Animal", "onCreate was called");
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
