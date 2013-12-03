package com.android.main;


import java.util.ArrayList;

import com.android.data.DBHandler;
import com.android.main.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Scoring extends Activity
{
    private DBHandler db;
    private Cursor idCursor;
    private RowAdapter adapter;
    private ListView list_layout;

    private class Row
    {
        public String surveyID;
        public String file_name;
        public String date;
    }

    private class RowAdapter extends ArrayAdapter<Row>
    {

        private ArrayList<Row> rows;

        public RowAdapter(Context context, int textViewResourceId,
                ArrayList<Row> objects)
        {
            super(context, textViewResourceId, objects);
            this.rows = objects;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.mark_row, null);
            }
            Row row = this.rows.get(position);
            if (row != null)
            {
                int color;
                if ((position + 1) % 2 == 0) {
                    color = getResources().getColor(R.color.white);
                } else {
                    color = getResources().getColor(R.color.light_gray);
                }

                view.setBackgroundColor(color);

                TextView name = (TextView) view.findViewById(R.id.mark_display_name);
                LinearLayout texts_parent = ((LinearLayout) name.getParent());
                texts_parent.setBackgroundColor(color);
                name.setBackgroundColor(color);
                TextView date = (TextView) view.findViewById(R.id.mark_display_date);
                date.setBackgroundColor(color);
                ImageView mark = (ImageView) view.findViewById(R.id.button_mark);

                name.setText(row.file_name);
                date.setText(row.date);

                mark.setOnClickListener(new MarkClickListener(row.surveyID));

            }
            return view;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        if ( db != null)
        {
            db.close();
        }
        db = new DBHandler(this);
        db.open();
        list_layout = (ListView) findViewById(R.id.list);
        fillData();
        db.close();
    }

    private class MarkClickListener extends ClickListener
    {
        public MarkClickListener(String surveyID) {
            super(surveyID);
        }

        @Override
        public void onClick(View v)
        {
            Intent next = new Intent(Scoring.this, MarkingElectronic.class);
            next.putExtra("surveyID", this.surveyID);
            startActivity(next);
        }
    }

    private void fillData()
    {
        this.idCursor = db.fetchAllSurveys();
        if (idCursor.moveToFirst())
        {
            ArrayList<Row> rows = new ArrayList<Row>();
            do
            {
                String file_name = idCursor.getString(idCursor.getColumnIndex("file_name"));
                String date = idCursor.getString(idCursor.getColumnIndex("date"));
                String surveyID = idCursor.getString(idCursor.getColumnIndex("surveyID"));

                Row row = new Row();
                row.file_name = file_name;
                row.date = date;
                row.surveyID = surveyID;

                rows.add(row);

            }
            while (idCursor.moveToNext());
            idCursor.close();
            adapter = new RowAdapter(this, R.layout.mark_row, rows);
            list_layout.setAdapter(adapter);
        }
        else
        {
            ArrayList<String> rows = new ArrayList<String>();
            rows.add(getString(R.string.row_empty));
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_empty, R.id.row_empty, rows);
            list_layout.setAdapter(arrayAdapter);
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            Intent home = new Intent(this, eMOCA.class);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
