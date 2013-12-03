package com.android.main;

import java.io.File;

import com.android.data.DBHandler;
import com.android.data.IDUtils;
import com.android.data.QuestHandler;
import com.android.main.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;


public class QTBridge extends ExtendedActivity
{
    private Bundle data;
    private DBHandler dbh;
    private QuestHandler qh;
    private final String TYPE = "Electronic";

    private String surveyID;
    private String name;
    private String location;
    private String date;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_emoca);
    }


    public void startTest(View v)
    {
        surveyID = IDUtils.getID("REAL");
        date = IDUtils.getTimeStamp();
        name = ((EditText)this.findViewById(R.id.editText0_1)).getText().toString();
        location = ((EditText)this.findViewById(R.id.editText0_2)).getText().toString();

        qh = new QuestHandler(surveyID, name, location, date);
        data = qh.getData();
        Intent next = new Intent(QTBridge.this, Instructions1.class);
        next.putExtra("data", data);
        if (dbh != null)
        {
            dbh.insertAdminData(surveyID, data.getString("date"), data.getString("name"), data.getString("location"), TYPE);
            dbh.insertFieldData(surveyID, data.getStringArrayList("field"));
            dbh.insertRadioData(surveyID, data.getStringArrayList("radio"));
            dbh.insertCheckData(surveyID, data.getStringArrayList("check"));
        }
        else
        {
            File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"eMOCA_Files"+File.separator+surveyID);
            directory.mkdirs();
        }
        startActivity(next);
        finish();
    }

    public void exitTest(View v)
    {
        dbh.close();
        Intent exit = new Intent(QTBridge.this, eMOCA.class);
        startActivity(exit);
    }

}
