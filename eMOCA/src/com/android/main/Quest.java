package com.android.main;

import com.android.data.IDUtils;
import com.android.data.QuestHandler;
import com.android.main.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;

/**
 * @deprecated Keeping just in case hte client changes their mind
 */

public class Quest extends ExtendedActivity
{
    private String surveyID;
    private String name;
    private String location;
    private String date;
    private Bundle data;
    private QuestHandler qh;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);
    }

    public void doDecision(View v)
    {
        if ( !isRealData() )
        {
            surveyID = IDUtils.getID("FAKE");
            name = "FAKE";
            location = "FAKE";
            date = IDUtils.getTimeStamp();
        }
        else
        {
            surveyID = IDUtils.getID("REAL");
            date = IDUtils.getTimeStamp();
            getClinicianName();
        }

        qh = new QuestHandler(surveyID, name, location, date);
        saveAns();
        data = qh.getData();
        Intent next = new Intent(Quest.this, QTBridge.class);
        next.putExtra("data", data);
        startActivity(next);
    }

    public void saveAns()
    {
        // Collect data from questionnaire.

        Q1_Ans();
        Q2_Ans();
        Q3_Ans();
        Q4_Ans();
        Q5_Ans();
        Q6_Ans();
        Q7_Ans();
        Q8_Ans();
        Q9_Ans();
        Q10_Ans();
        Q11_Ans();
        Q12_Ans();
        Q13a_Ans();
        Q13b_Ans();
        Q14_Ans();
        Q15_Ans();
        Q16_Ans();
        Q17_Ans();
        Q18_Ans();
        Q19_Ans();
        Q20_Ans();
        Q21_Ans();
        Q22_Ans();
        Q23_Ans();
        Q24_Ans();
    }

    public boolean isRealData()
    {
       return (((CheckBox)this.findViewById(R.id.checkBox0_0)).isChecked()? true : false);
    }

    public void getClinicianName()
    {
        name = ((EditText)this.findViewById(R.id.editText0_1)).getText().toString();
        location = ((EditText)this.findViewById(R.id.editText0_2)).getText().toString();
    }

    public void Q1_Ans()
    {
        String ans = "";
        String other;
        // Checked boxes stored as binary encoded string.
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_1)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_2)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_3)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_4)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_5)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_6)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_7)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_8)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox1_9)).isChecked()? "1": "0";
        other = ((EditText)this.findViewById(R.id.otherField1)).getText().toString();

        qh.insertCheck(ans);
        qh.insertCheck(other);
    }

    public void Q2_Ans()
    {
        String ans = "";
        String other;
        // Checked boxes stored as binary encoded string.
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_1)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_2)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_3)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_4)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_5)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_6)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_7)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_8)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_9)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox2_10)).isChecked()? "1": "0";
        other =  ((EditText)this.findViewById(R.id.otherField2)).getText().toString();

        qh.insertCheck(ans);
        qh.insertCheck(other);
    }

    public void Q3_Ans()
    {
        String ans = "";
        ans += ((CheckBox)this.findViewById(R.id.checkBox3_1)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox3_2)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox3_3)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox3_4)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox3_5)).isChecked()? "1": "0";
        ans += ((CheckBox)this.findViewById(R.id.checkBox3_6)).isChecked()? "1": "0";
        qh.insertField(ans);
        qh.insertField(((EditText)this.findViewById(R.id.editText3_1)).getText().toString());
        qh.insertField(((EditText)this.findViewById(R.id.editText3_2)).getText().toString());
        qh.insertField(((EditText)this.findViewById(R.id.editText3_3)).getText().toString());
        qh.insertField(((EditText)this.findViewById(R.id.editText3_4)).getText().toString());
        qh.insertField(((EditText)this.findViewById(R.id.editText3_5)).getText().toString());
        qh.insertField(((EditText)this.findViewById(R.id.editText3_6)).getText().toString());
        qh.insertField(((EditText)this.findViewById(R.id.otherField3_1)).getText().toString());
        qh.insertField(((EditText)this.findViewById(R.id.otherField3_2)).getText().toString());
    }

    public void Q4_Ans()
    {
        String text;

        text = ((EditText)this.findViewById(R.id.editText4_1)).getText().toString();

        qh.insertField(text);
    }

    public void Q5_Ans()
    {

        int id = ((RadioGroup)this.findViewById(R.id.radioGroup5)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();

        qh.insertRadio(choice);
    }

    public void Q6_Ans()
    {
        String text;

        text = ((EditText)this.findViewById(R.id.editText6_1)).getText().toString();

        qh.insertField(text);
    }

    public void Q7_Ans()
    {
        String text;

        text = ((EditText)this.findViewById(R.id.editText7_1)).getText().toString();

        qh.insertField(text);
    }

    public void Q8_Ans()
    {
        String text;

        text = ((EditText)this.findViewById(R.id.editText8_1)).getText().toString();

        qh.insertField(text);
    }

    public void Q9_Ans()
    {
        String text;

        text = ((EditText)this.findViewById(R.id.editText9_1)).getText().toString();

        qh.insertField(text);
    }

    public void Q10_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup10)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();

        qh.insertRadio(choice);
    }

    public void Q11_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup11)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();

        qh.insertRadio(choice);
    }

    public void Q12_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup12)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

    public void Q13a_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup13a)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

    public void Q13b_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup13b)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

    public void Q14_Ans()
    {
        String text;

        text = ((EditText)this.findViewById(R.id.editText14_1)).getText().toString();
        if ( !text.matches("[0-9]+") )
        {
            text = "0";
        }
        qh.insertField(text);
    }

    public void Q15_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup15)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

    public void Q16_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup16)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

    public void Q17_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup17)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        String other = ((EditText)this.findViewById(R.id.editText17_1)).getText().toString();
        qh.insertRadio(choice);
        qh.insertRadio(other);
    }

    public void Q18_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup18)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

    public void Q19_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup19)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

    public void Q20_Ans()
    {
        String ans = "";
        if ( ((CheckBox)this.findViewById(R.id.checkBox20_1)).isChecked() )
        {
            ans = "00000000";
        }
        else
        {
            ans += ((CheckBox)this.findViewById(R.id.checkBox20_1)).isChecked()? "1": "0";
            ans += ((CheckBox)this.findViewById(R.id.checkBox20_2)).isChecked()? "1": "0";
            ans += ((CheckBox)this.findViewById(R.id.checkBox20_3)).isChecked()? "1": "0";
            ans += ((CheckBox)this.findViewById(R.id.checkBox20_4)).isChecked()? "1": "0";
            ans += ((CheckBox)this.findViewById(R.id.checkBox20_5)).isChecked()? "1": "0";
            ans += ((CheckBox)this.findViewById(R.id.checkBox20_6)).isChecked()? "1": "0";
            ans += ((CheckBox)this.findViewById(R.id.checkBox20_7)).isChecked()? "1": "0";
            ans += ((CheckBox)this.findViewById(R.id.checkBox20_8)).isChecked()? "1": "0";
        }

        qh.insertCheck(ans);
    }

    public void Q21_Ans()
    {
        String kg, pounds;

        kg = ((EditText)this.findViewById(R.id.editText21_1)).getText().toString();
        pounds = ((EditText)this.findViewById(R.id.editText21_2)).getText().toString();

        if (kg.length() == 0 && pounds.length() > 0)
        {
            kg = (Integer.parseInt(pounds) * 0.453) + "";
        }
        else if ( kg.length() == 0)
        {
            kg = "0";
        }
        qh.insertField(kg);
    }

    public void Q22_Ans()
    {
        String feet, inches, cm;

        feet = ((EditText)this.findViewById(R.id.editText22_1)).getText().toString();
        inches = ((EditText)this.findViewById(R.id.editText22_2)).getText().toString();
        cm = ((EditText)this.findViewById(R.id.editText22_3)).getText().toString();

        if ( cm.length() == 0 && (feet.length() > 0 || inches.length() > 0) )
        {
            cm = ((Integer.parseInt(feet) + (Integer.parseInt(inches) * 0.083)) * 30.48) + "";
        }
        else if ( cm.length() == 0 )
        {
            cm = "0";
        }
        qh.insertField(cm);
    }

    public void Q23_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup23)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

    public void Q24_Ans()
    {
        int id = ((RadioGroup)this.findViewById(R.id.radioGroup24)).getCheckedRadioButtonId();
        String choice = "0";
        if ( id != -1 )
             choice = ((RadioButton)this.findViewById(id)).getTag().toString();
        qh.insertRadio(choice);
    }

}
