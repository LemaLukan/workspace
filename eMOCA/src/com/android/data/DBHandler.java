package com.android.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.data.DBQuery;
import android.util.Log;
import java.util.ArrayList;


public class DBHandler
{
    private final Context context;

    private static final String DB_ADMIN = DBQuery.DB_ADMIN;
    private static final String DB_QUEST_FIELD = DBQuery.DB_QUEST_FIELD;
    private static final String DB_QUEST_RADIO = DBQuery.DB_QUEST_RADIO;
    private static final String DB_QUEST_CHECK = DBQuery.DB_QUEST_CHECK;

    private static final String DB_EMOCA_TIME = DBQuery.DB_EMOCA_TIME;
    private static final String DB_EMOCA_SRC = DBQuery.DB_EMOCA_SRC;
    private static final String DB_EMOCA_REPLAY = DBQuery.DB_EMOCA_REPLAY;
    private static final String DB_EMOCA_MARKS = DBQuery.DB_EMOCA_MARKS;
    private static final String DB_EMOCA_SCORE = DBQuery.DB_EMOCA_SCORE;


    private static final String DB_NAME = "emoca";
    private static final String ADMIN = "admin";
    private static final String QUEST_FIELD = "quest_field";
    private static final String QUEST_CHECK = "quest_check";
    private static final String QUEST_RADIO = "quest_radio";

    private static final String SRC_TABLE = "test_src";
    private static final String TIME_TABLE = "test_time";
    private static final String REP_TABLE = "test_replay";
    private static final String MARK_TABLE = "test_marking";
    private static final String SCORE_TABLE = "test_score";

    private static final int DB_VERSION = 4;

    private DBHelper DBHelper;
    private SQLiteDatabase DB;

    public DBHandler(Context context)
    {
        this.context = context;
    }

    /*
     * Gets writable copy of database.
     */
    public DBHandler open() throws SQLException
    {
        DBHelper = new DBHelper(context);
        DB = DBHelper.getWritableDatabase();
        return this;
    }

    /*
     * Commits changes to database and destroys writable copy.
     */
    public void close()
    {
        DBHelper.close();
    }

    private static class DBHelper extends SQLiteOpenHelper
    {
        DBHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        /*
         * Creates database if it doesn't exist.
         */
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DB_ADMIN);
            db.execSQL(DB_QUEST_FIELD);
            db.execSQL(DB_QUEST_RADIO);
            db.execSQL(DB_QUEST_CHECK);
            db.execSQL(DB_EMOCA_TIME);
            db.execSQL(DB_EMOCA_SRC);
            db.execSQL(DB_EMOCA_REPLAY);
            db.execSQL(DB_EMOCA_MARKS);
            db.execSQL(DB_EMOCA_SCORE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
        	// Note: This function will only be called if you change the `DB_VERSION`
        	db.execSQL(DBQuery.DB_DROP_QUEST_FIELD);
        	db.execSQL(DBQuery.DB_DROP_QUEST_RADIO);
        	db.execSQL(DBQuery.DB_DROP_QUEST_CHECK);
        	db.execSQL(DBQuery.DB_DROP_EMOCA_TIME);
        	db.execSQL(DBQuery.DB_DROP_EMOCA_SRC);
        	db.execSQL(DBQuery.DB_DROP_EMOCA_REPLAY);
        	db.execSQL(DBQuery.DB_DROP_EMOCA_SCORE);
        	db.execSQL(DBQuery.DB_DROP_EMOCA_MARKS);
        	db.execSQL(DBQuery.DB_DROP_ADMIN);
        	this.onCreate(db);
        }
    }

    public void insertTimerData(String surveyID, ArrayList<String> timers)
    {
        ContentValues timeValues = new ContentValues();
        timeValues.put("surveyID", surveyID);
        for (int i = 0; i < timers.size(); ++i)
        {
            Log.e("index", Integer.toString(i+1));
            Log.e("value", timers.get(i));
            timeValues.put("q" + (i+1) + "_time", timers.get(i));
        }

        DB.insert(TIME_TABLE, null, timeValues);
    }

    public void insertSrcData(String surveyID, ArrayList<String> src)
    {
        ContentValues srcValues = new ContentValues();
        srcValues.put("surveyID", surveyID);
        for (int i = 0; i < src.size(); ++i)
        {
            srcValues.put("q" + (i+1) + "_src", src.get(i));
        }

        DB.insert(SRC_TABLE, null, srcValues);
    }

    public void insertReplayData(String surveyID, ArrayList<String> replay)
    {
        ContentValues replayValues = new ContentValues();
        replayValues.put("surveyID", surveyID);
        for (int i = 0; i < replay.size(); ++i)
        {
            replayValues.put("q" + (i+1) + "_rep", replay.get(i));
        }

        DB.insert(REP_TABLE, null, replayValues);
    }

    public void insertAdminData(String surveyID, String date, String name, String location, String type)
    {
        ContentValues adminValues = new ContentValues();
        adminValues.put("surveyID", surveyID);
        adminValues.put("date", date);
        adminValues.put("clinician_name", name);
        adminValues.put("clinician_location", location);
        adminValues.put("test_type", type);
        DB.insert(ADMIN, null, adminValues);

    }
    public void insertFieldData(String surveyID, ArrayList<String> field)
    {
        String[] keys = DBQuery.QUEST_FIELD_KEY;
        ContentValues fieldValues = new ContentValues();
        fieldValues.put("surveyID", surveyID);
        for (int i = 0; i < field.size(); ++i)
        {
            fieldValues.put(keys[i], field.get(i));
        }
        DB.insert(QUEST_FIELD, null, fieldValues);
    }

    public void insertRadioData(String surveyID, ArrayList<String> radio)
    {
        String[] keys = DBQuery.QUEST_RADIO_KEY;
        ContentValues radioValues = new ContentValues();
        radioValues.put("surveyID", surveyID);
        for (int i = 0; i < radio.size(); ++i)
        {
            radioValues.put(keys[i], radio.get(i));
        }
        DB.insert(QUEST_RADIO, null, radioValues);
    }

    public void insertCheckData(String surveyID, ArrayList<String> check)
    {
        String[] keys = DBQuery.QUEST_CHECK_KEY;
        ContentValues checkValues = new ContentValues();
        checkValues.put("surveyID", surveyID);
        for (int i = 0; i < check.size(); ++i)
        {
            checkValues.put(keys[i], check.get(i));
        }
        DB.insert(QUEST_CHECK, null, checkValues);
    }

    public void insertScoreData(String surveyID, String marker, String location, int bonus, ArrayList<String> marks, ArrayList<Integer> score, String wordNum)
    {
        int totalScore = bonus;
        ContentValues markValues = new ContentValues();
        ContentValues scoreValues = new ContentValues();

        markValues.put("surveyID", surveyID);
        scoreValues.put("surveyID", surveyID);

        markValues.put("scorer_name", marker);
        markValues.put("scorer_location", location);

        markValues.put("education_bonus", bonus);
        scoreValues.put("education_bonus", bonus);

        markValues.put("q13words", wordNum);

        for (int i = 0; i < marks.size(); ++i)
        {
            markValues.put("q" + (i+1) + "_mark", marks.get(i));
        }

        DB.insert(MARK_TABLE, null, markValues);

        for (int i = 0; i < score.size(); ++i)
        {
            totalScore += score.get(i);
            scoreValues.put("section" + (i+1), score.get(i));
        }

        scoreValues.put("total_score", totalScore);
        DB.insert(SCORE_TABLE, null, scoreValues);
    }

    public void updateFileName(String surveyID, String file_name)
    {
    	ContentValues data = new ContentValues();
    	data.put("file_name", file_name);
    	DB.update(ADMIN, data, "surveyID = \"" + surveyID + "\"", null);
    }

    public void deleteTest(String surveyID)
    {
    	DB.delete(ADMIN, "surveyID=?", new String [] {surveyID});
    }

    public Cursor fetchAllSurveys()
    {
        return DB.query(ADMIN, new String[] { "_id", "date", "surveyID", "clinician_name", "clinician_location", "test_type", "file_name" }, null, null, null, null, null);
    }

    public Cursor fetchYearOfBirth(String surveyID, String column)
    {
        return DB.query(QUEST_FIELD, new String[] { "_id", "surveyID", column }, "surveyID = '"+surveyID+"'", null, null, null, null);
    }

    public Cursor fetchTapTimes(String surveyID, String column)
    {
        return DB.query(TIME_TABLE, new String[] { "_id", "surveyID", column }, "surveyID = '"+surveyID+"'", null, null, null, null);

    }

}
