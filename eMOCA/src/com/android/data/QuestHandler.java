package com.android.data;

import java.util.ArrayList;

import android.os.Bundle;

public class QuestHandler
{
    private Bundle data;
    private ArrayList<String> field;
    private ArrayList<String> check;
    private ArrayList<String> radio;
    private String name;
    private String location;
    private String surveyID;
    private String date;

    public QuestHandler(String surveyID, String name, String location, String date)
    {
        this.surveyID = surveyID;
        this.name = name;
        this.location = location;
        this.date = date;
        field = new ArrayList<String>();
        check = new ArrayList<String>();
        radio = new ArrayList<String>();
    }

    public void insertName(String string)
    {
        name = string;
    }

    public void insertField(String string)
    {
        field.add(string);
    }

    public void insertCheck(String string)
    {
        check.add(string);
    }

    public void insertRadio(String string)
    {
        radio.add(string);
    }

    public Bundle getData()
    {
        data = new Bundle();
        data.putString("surveyID", surveyID);
        data.putString("name", name);
        data.putString("location", location);
        data.putString("date", date);
        data.putStringArrayList("field", field);
        data.putStringArrayList("check", check);
        data.putStringArrayList("radio", radio);
        return data;
    }

}
