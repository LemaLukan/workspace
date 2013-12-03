package com.android.data;

public class DBQuery
{
    /*
     * Contains Clinician information and surveys
     */
    public static final String DB_ADMIN =
        "CREATE TABLE admin ("
        +"_id INTEGER PRIMARY KEY, "
        +"surveyID TEXT NOT NULL UNIQUE, "
        +"clinician_name TEXT, "
        +"clinician_location TEXT, "
        +"test_type TEXT, "
        +"date TEXT, "
        +"file_name VARCHAR(50));";

    /*
     * Contains information from questionnaire fields including question 3 (pseudo-checkbox)
     * Note: Convert any weight/height into kg/cm
     */
    public static final String DB_QUEST_FIELD =
        "CREATE TABLE quest_field ("
        +"_id INTEGER PRIMARY KEY, "
        +"surveyID TEXT NOT NULL UNIQUE, "
        +"q3 TEXT, "
        +"q3a1 TEXT, "
        +"q3a2 TEXT, "
        +"q3a3 TEXT, "
        +"q3a4 TEXT, "
        +"q3a5 TEXT, "
        +"q3a6 TEXT, "
        +"q3other_name TEXT, "
        +"q3other_years TEXT, "
        +"q4 INTEGER, "
        +"q6 INTEGER, "
        +"q7 INTEGER, "
        +"q8 INTEGER, "
        +"q9 INTEGER, "
        +"q14 INTEGER, "
        +"q21_kg INTEGER, "
        +"q22_cm INTEGER, "
        +"FOREIGN KEY(surveyID) REFERENCES admin(surveyID) ON DELETE CASCADE);";

    public static final String[] QUEST_FIELD_KEY = { "q3", "q3a1", "q3a2", "q3a3", "q3a4", "q3a5", "q3a6", "q3other_name",
                                                    "q3other_years", "q4", "q6", "q7", "q8", "q9", "q14",
                                                    "q21_kg", "q22_cm" };
    /*
     * Contains information from questionnaire radio buttons and related questions.
     */
    public static final String DB_QUEST_RADIO =
        "CREATE TABLE quest_radio ("
        +"_id INTEGER PRIMARY KEY, "
        +"surveyID TEXT NOT NULL UNIQUE, "
        +"q5 INTEGER, "
        +"q10 INTEGER, "
        +"q11 INTEGER, "
        +"q12 INTEGER, "
        +"q13a INTEGER, "
        +"q13b INTEGER, "
        +"q15 INTEGER, "
        +"q16 INTEGER, "
        +"q17 INTEGER, "
        +"q17_other TEXT, "
        +"q18 INTEGER, "
        +"q19 INTEGER, "
        +"q23 INTEGER, "
        +"q24 INTEGER, "
        +"FOREIGN KEY(surveyID) REFERENCES admin(surveyID) ON DELETE CASCADE);";

    public static final String[] QUEST_RADIO_KEY = { "q5", "q10", "q11", "q12", "q13a", "q13b",
                                                    "q15", "q16", "q17", "q17_other", "q18", "q19",
                                                    "q23", "q24" };

/*
 * Contains information from questionnaire check boxes and related questions.
 * Note: Use binary-encoded strings for checkbox values
 */
    public static final String DB_QUEST_CHECK =
        "CREATE TABLE quest_check ("
        +"_id INTEGER PRIMARY KEY, "
        +"surveyID TEXT NOT NULL UNIQUE, "
        +"q1_check TEXT, "
        +"q1_other TEXT, "
        +"q2_check TEXT, "
        +"q2_other TEXT, "
        +"q20 TEXT, "
        +"FOREIGN KEY(surveyID) REFERENCES admin(surveyID) ON DELETE CASCADE);";

    public static final String[] QUEST_CHECK_KEY = { "q1_check", "q1_other", "q2_check", "q2_other", "q20" };



    /*
     * Contains all the timers for the eMOCA test, including tap timers.
     */
    public static final String DB_EMOCA_TIME =
        "CREATE TABLE test_time ( "
        +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"surveyID TEXT NOT NULL UNIQUE, "
        +"q1_time INTEGER, "
        +"q2_time INTEGER, "
        +"q3_time INTEGER, "
        +"q4_time INTEGER, "
        +"q5_time INTEGER, "
        +"q6_time INTEGER, "
        +"q7_time INTEGER, "
        +"q8_time INTEGER, "
        +"q9_time INTEGER, "
        +"q10_time INTEGER, "
        +"q11_time TEXT, "
        +"q12_time INTEGER, "
        +"q13_time INTEGER, "
        +"q14_time INTEGER, "
        +"q15_time INTEGER, "
        +"q16_time INTEGER, "
        +"q17_time INTEGER, "
        +"q18_time INTEGER, "
        +"q19_time INTEGER, "
        +"q20_time INTEGER, "
        +"q21_time INTEGER, "
        +"q22_time INTEGER, "
        +"q23_time INTEGER, "
        +"q24_time INTEGER, "
        +"q25_time INTEGER, "
        +"q26_time INTEGER, "
        +"q27_time INTEGER, "
        +"q28_time INTEGER, "
        +"q29_time INTEGER, "
        +"q30_time INTEGER, "
        +"q31_time INTEGER, "
        +"q32_time INTEGER, "
        +"q33_time INTEGER, "
        +"q34_time INTEGER, "
        +"q35_time INTEGER, "
        +"FOREIGN KEY(surveyID) REFERENCES admin(surveyID) ON DELETE CASCADE);";

    /*
     * Contains the captured image/audio data paths NOT the actual data.
     */
    public static final String DB_EMOCA_SRC =
        "CREATE TABLE test_src ( "
        +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"surveyID TEXT NOT NULL UNIQUE, "
        +"q1_src TEXT, "
        +"q2_src TEXT, "
        +"q3_src TEXT, "
        +"q4_src TEXT, "
        +"q5_src TEXT, "
        +"q6_src TEXT, "
        +"q7_src TEXT, "
        +"q8_src TEXT, "
        +"q9_src TEXT, "
        +"q10_src TEXT, "
        +"q11_src TEXT, "
        +"q12_src TEXT, "
        +"q13_src TEXT, "
        +"q14_src TEXT, "
        +"q15_src TEXT, "
        +"q16_src TEXT, "
        +"q17_src TEXT, "
        +"q18_src TEXT, "
        +"q19_src TEXT, "
        +"q20_src TEXT, "
        +"q21_src TEXT, "
        +"q22_src TEXT, "
        +"q23_src TEXT, "
        +"q24_src TEXT, "
        +"q25_src TEXT, "
        +"q26_src TEXT, "
        +"q27_src TEXT, "
        +"q28_src TEXT, "
        +"q29_src TEXT, "
        +"q30_src TEXT, "
        +"q31_src TEXT, "
        +"q32_src TEXT, "
        +"q33_src TEXT, "
        +"q34_src TEXT, "
        +"q35_src TEXT, "
        +"FOREIGN KEY(surveyID) REFERENCES admin(surveyID) ON DELETE CASCADE);";

    /*
     * Contains the captured image/audio data paths NOT the actual data.
     */
    public static final String DB_EMOCA_REPLAY =
        "CREATE TABLE test_replay ( "
        +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"surveyID TEXT NOT NULL UNIQUE, "
        +"q1_rep TEXT, "
        +"q2_rep TEXT, "
        +"q3_rep TEXT, "
        +"q4_rep TEXT, "
        +"q5_rep TEXT, "
        +"q6_rep TEXT, "
        +"q7_rep TEXT, "
        +"q8_rep TEXT, "
        +"q9_rep TEXT, "
        +"q10_rep TEXT, "
        +"q11_rep TEXT, "
        +"q12_rep TEXT, "
        +"q13_rep TEXT, "
        +"q14_rep TEXT, "
        +"q15_rep TEXT, "
        +"q16_rep TEXT, "
        +"q17_rep TEXT, "
        +"q18_rep TEXT, "
        +"q19_rep TEXT, "
        +"q20_rep TEXT, "
        +"q21_rep TEXT, "
        +"q22_rep TEXT, "
        +"q23_rep TEXT, "
        +"q24_rep TEXT, "
        +"q25_rep TEXT, "
        +"q26_rep TEXT, "
        +"q27_rep TEXT, "
        +"q28_rep TEXT, "
        +"q29_rep TEXT, "
        +"q30_rep TEXT, "
        +"q31_rep TEXT, "
        +"q32_rep TEXT, "
        +"q33_rep TEXT, "
        +"q34_rep TEXT, "
        +"q35_rep TEXT, "
        +"FOREIGN KEY(surveyID) REFERENCES admin(surveyID) ON DELETE CASCADE);";

    /*
     * Contains the score data for survey
     */

    public static final String DB_EMOCA_SCORE =
        "CREATE TABLE test_score ( "
        +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"surveyID TEXT, "
        +"education_bonus TEXT, "
        +"section1 INTEGER, "
        +"section2 INTEGER, "
        +"section3 INTEGER, "
        +"section4 INTEGER, "
        +"section5 INTEGER, "
        +"section6 INTEGER, "
        +"section7 INTEGER, "
        +"section8 INTEGER, "
        +"section9 INTEGER, "
        +"section10 INTEGER, "
        +"total_score INTEGER, "
        +"FOREIGN KEY(surveyID) REFERENCES admin(surveyID) ON DELETE CASCADE);";

    public static final String DB_EMOCA_MARKS =
        "CREATE TABLE test_marking ( "
        +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"surveyID TEXT, "
        +"scorer_name TEXT, "
        +"scorer_location TEXT, "
        +"education_bonus TEXT, "
        +"q1_mark INTEGER, "
        +"q2_mark INTEGER, "
        +"q3_mark TEXT, "
        +"q4_mark TEXT, "
        +"q5_mark TEXT, "
        +"q6_mark TEXT, "
        +"q7_mark INTEGER, "
        +"q8_mark INTEGER, "
        +"q9_mark INTEGER, "
        +"q10_mark TEXT, "
        +"q11_mark INTEGER, "
        +"q12_mark INTEGER, "
        +"q13_mark INTEGER, "
        +"q13words TEXT, "
        +"q14_mark INTEGER, "
        +"q15_mark INTEGER, "
        +"q16_mark INTEGER, "
        +"q17_mark TEXT, "
        +"q18_mark TEXT, "
        +"q19_mark TEXT, "
        +"q20_mark TEXT, "
        +"q21_mark TEXT, "
        +"FOREIGN KEY(surveyID) REFERENCES admin(surveyID) ON DELETE CASCADE);";

    /*
     * Queries used for destroying the database
     *
     * Make sure you drop the admin table last
     */

    public static final String DB_DROP_ADMIN = "DROP TABLE IF EXISTS admin;";
    public static final String DB_DROP_QUEST_FIELD = "DROP TABLE IF EXISTS quest_field;";
    public static final String DB_DROP_QUEST_RADIO = "DROP TABLE IF EXISTS quest_radio;";
    public static final String DB_DROP_QUEST_CHECK = "DROP TABLE IF EXISTS quest_check;";
    public static final String DB_DROP_EMOCA_TIME = "DROP TABLE IF EXISTS test_time;";
    public static final String DB_DROP_EMOCA_SRC = "DROP TABLE IF EXISTS test_src;";
    public static final String DB_DROP_EMOCA_REPLAY = "DROP TABLE IF EXISTS test_replay;";
    public static final String DB_DROP_EMOCA_SCORE = "DROP TABLE IF EXISTS test_score;";
    public static final String DB_DROP_EMOCA_MARKS = "DROP TABLE IF EXISTS test_marking;";
}
