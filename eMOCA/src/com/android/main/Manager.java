package com.android.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import com.android.data.DBHandler;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Manager extends Activity {

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
                view = inflater.inflate(R.layout.manager_row, null);
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

                TextView name = (TextView) view.findViewById(R.id.manager_display_name);
                LinearLayout texts_parent = ((LinearLayout) name.getParent());
                texts_parent.setBackgroundColor(color);
                name.setBackgroundColor(color);

                TextView date = (TextView) view.findViewById(R.id.manager_display_date);
                date.setBackgroundColor(color);

                ImageView export = (ImageView) view.findViewById(R.id.manager_button_export);
                LinearLayout buttons_parent = ((LinearLayout) export.getParent());
                buttons_parent.setBackgroundColor(color);

                ImageView rename = (ImageView) view.findViewById(R.id.manager_button_rename);
                ImageView delete = (ImageView) view.findViewById(R.id.manager_button_delete);

                name.setText(row.file_name);
                date.setText(row.date);

                export.setOnClickListener(new ExportClickListener(row.surveyID, position));
                rename.setOnClickListener(new RenameClickListener(row.surveyID, position));
                delete.setOnClickListener(new DeleteClickListener(row.surveyID, position));

            }
            return view;
        }

        public void dataSetChanged()
        {
            runOnUiThread(new Runnable() {
                public void run() {
                    RowAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        if (db != null)
        {
            db.close();
        }
        db = new DBHandler(this);
        db.open();
        list_layout = (ListView) findViewById(R.id.list);
        populateView();

    }

    public void populateView ()
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
            adapter = new RowAdapter(this, R.layout.manager_row, rows);
            list_layout.setAdapter(adapter);
        }
        else
        {
            this.noData();
        }
    }

    private void noData()
    {
        ArrayList<String> rows = new ArrayList<String>();
        rows.add(getString(R.string.row_empty));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row_empty, R.id.row_empty, rows);
        list_layout.setAdapter(arrayAdapter);
    }

    private String formatName(Row row)
    {
        String name = row.file_name + "_" + row.date;
        return name;
    }

    private void updateFileName (String surveyID, String file_name, int position)
    {
        db.updateFileName(surveyID, file_name);
        Row row = adapter.getItem(position);
        row.file_name = file_name;
        adapter.dataSetChanged();
    }

    protected void deleteTest(String surveyID, int position)
    {
        db.deleteTest(surveyID);
        Row row = adapter.getItem(position);
        adapter.remove(row);
        if (adapter.isEmpty())
        {
            this.noData();
        }
        else
        {
            adapter.dataSetChanged();
        }
    }

    private class ExportClickListener extends ClickListener
    {

        private int position;

        public ExportClickListener(String surveyID, int position)
        {
            super(surveyID);
            this.position = position;
        }

        @Override
        public void onClick(View v)
        {
            File databasePath = Manager.this.getDatabasePath("emoca");
            File backupDir = new File(Environment.getExternalStorageDirectory()+File.separator+"eMOCA_Files"+File.separator+"database");
            backupDir.mkdirs();

            Row row = adapter.getItem(position);
            String formattedName = formatName(row);

            CharSequence text = "Export successful!";
            try
            {
                File backupDB = new File(backupDir, formattedName);

                if (databasePath.exists())
                {
                    FileInputStream inputStream = new FileInputStream(Manager.this.getDatabasePath("emoca"));
                    FileOutputStream outputStream = new FileOutputStream(backupDB);
                    FileChannel src = inputStream.getChannel();
                    FileChannel dst = outputStream.getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    inputStream.close();
                    outputStream.close();
                }

            } catch (Exception e) {
                text = "Export Failed!";
            } finally {
                Toast toast = Toast.makeText(Manager.this, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    private class RenameClickListener extends ClickListener
    {
        private int position;

        public RenameClickListener(String surveyID, int position) {
            super(surveyID);
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            final Dialog dialog = new Dialog(Manager.this, R.style.CustomDialogTheme);
            dialog.setContentView(R.layout.dialog_rename_layout);
            final EditText input = (EditText) dialog.findViewById(R.id.rename);

            dialog.setTitle(R.string.rename);

            Button yes = (Button) dialog.findViewById(R.id.ok_button);
            yes.setText(R.string.rename);
            yes.setOnClickListener(new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    String file_name = input.getText().toString().trim();

                    Manager.this.updateFileName(surveyID, file_name, position);
                    dialog.dismiss();
                }
            });

            Button no = (Button) dialog.findViewById(R.id.cancel_button);
            no.setText(R.string.cancel);
            no.setOnClickListener(new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }

    private class DeleteClickListener extends ClickListener
    {

        private int position;

        public DeleteClickListener(String surveyID, int position) {
            super(surveyID);
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            final Dialog dialog = new Dialog(Manager.this, R.style.CustomDialogTheme);

            Row row = adapter.getItem(position);

            dialog.setContentView(R.layout.dialog_layout);
            TextView text = (TextView) dialog.findViewById(R.id.message);
            text.setText( getString(R.string.confirm_delete) + " " + row.file_name + "?" );
            dialog.setTitle(R.string.delete);

            Button yes = (Button) dialog.findViewById(R.id.ok_button);
            yes.setOnClickListener(new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Manager.this.deleteTest(surveyID, position);
                    dialog.dismiss();
                }
            });

            Button no = (Button) dialog.findViewById(R.id.cancel_button);
            no.setOnClickListener(new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manager, menu);
        return true;
    }
}
