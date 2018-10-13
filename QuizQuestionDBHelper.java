package com.example.thakr.quizapp_test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuizQuestionDBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "DB_QUESTIONS";
    private static int DB_VERSION = 1;

    public static String TABLE_CREATE = "CREATE TABLE " + QuizQuestionContract.TableEntry.TABLE_NAME + "(" +
            QuizQuestionContract.TableEntry.QUESTION_NUMBER + " INTEGER PRIMARY KEY," +
            QuizQuestionContract.TableEntry.QUESTION + " TEXT NOT NULL," +
            QuizQuestionContract.TableEntry.OPTIONS + " TEXT," +
            QuizQuestionContract.TableEntry.QUESTION_ANSWER + " TEXT NOT NULL," +
            QuizQuestionContract.TableEntry.USER_ANSWER + " TEXT);";

    public static String TABLE_DROP = "DROP TABLE IF EXISTS " + QuizQuestionContract.TableEntry.TABLE_NAME + ";";


    public QuizQuestionDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public QuizQuestionDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.d("ABC", "4");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }

    public void AddInfo(int N, String Q, String[] O, String QA, String UA, SQLiteDatabase db) {
        Log.d("ABC", "5");
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuizQuestionContract.TableEntry.QUESTION_NUMBER, N);
        contentValues.put(QuizQuestionContract.TableEntry.QUESTION, Q);
        String temp = "";
        for (int i = 0; i < O.length; i++) {
            temp += O[i] + "/";
        }
        contentValues.put(QuizQuestionContract.TableEntry.OPTIONS, temp);
        contentValues.put(QuizQuestionContract.TableEntry.QUESTION_ANSWER, QA);
        contentValues.put(QuizQuestionContract.TableEntry.USER_ANSWER, UA);
        long rowID = db.insert(QuizQuestionContract.TableEntry.TABLE_NAME, null, contentValues);
        Log.d("ABC", "6");
    }

    public void AddInfoToDatabase(QuizQuestion Q,  SQLiteDatabase db) {
        AddInfo(Q.Number, Q.Question, Q.Options, Q.QuestionAnswer, Q.UserAnswered, db);
    }

    public Cursor GetInfoFromDatabase(SQLiteDatabase db) {
        db = getReadableDatabase();
        String[] projection = {
                QuizQuestionContract.TableEntry.QUESTION_NUMBER,
                QuizQuestionContract.TableEntry.QUESTION,
                QuizQuestionContract.TableEntry.OPTIONS,
                QuizQuestionContract.TableEntry.QUESTION_ANSWER,
                QuizQuestionContract.TableEntry.USER_ANSWER };

        Cursor cursor = db.query(QuizQuestionContract.TableEntry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

    public void UpdateInfoInDatabase(QuizQuestion Q, SQLiteDatabase db) {
        UpdateInfo(Q.Number, Q.Question, Q.Options, Q.QuestionAnswer, Q.UserAnswered, db);
    }

    public long UpdateInfo(int N, String Q, String[] O, String QA, String UA, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuizQuestionContract.TableEntry.QUESTION_NUMBER, N);
        contentValues.put(QuizQuestionContract.TableEntry.QUESTION, Q);
        String temp = "";
        for (int i = 0; i < O.length; i++) {
            temp += O[i] + "/";
        }
        contentValues.put(QuizQuestionContract.TableEntry.OPTIONS, temp);
        contentValues.put(QuizQuestionContract.TableEntry.QUESTION_ANSWER, QA);
        contentValues.put(QuizQuestionContract.TableEntry.USER_ANSWER, UA);
        long rowID = db.update(QuizQuestionContract.TableEntry.TABLE_NAME, contentValues,  QuizQuestionContract.TableEntry.QUESTION_NUMBER + " = ?", new String[] {Integer.toString(N)});
        return rowID;
    }




}
