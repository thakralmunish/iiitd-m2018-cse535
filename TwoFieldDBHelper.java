package com.example.thakr.sensorvaluestry1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TwoFieldDBHelper extends SQLiteOpenHelper{

    public static String DB_NAME = "SENSOR_VALUES";
    public static int DB_VERSION = 1;
    public String TABLE_NAME;

    public String TABLE_CREATE = "";

    public String TABLE_DROP = "";

    public TwoFieldDBHelper(Context context, String TableName) {
        super(context, DB_NAME, null, DB_VERSION);
        TABLE_NAME = TableName;
        TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
                TwoFieldContract.TableEntry.TIMESTAMP + " TEXT PRIMARY KEY," +
                TwoFieldContract.TableEntry.X + " DECIMAL NOT NULL," +
                TwoFieldContract.TableEntry.Y + " DECIMAL NOT NULL);";
        TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }

    public void AddInfo(float T, float[] V, SQLiteDatabase db) {
        AddInfo(T, V[0], V[1], db);
    }

    public void AddInfo(float T, float x, float y, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TwoFieldContract.TableEntry.TIMESTAMP, T);
        contentValues.put(TwoFieldContract.TableEntry.X, x);
        contentValues.put(TwoFieldContract.TableEntry.Y, y);
        long rowID = db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor GetInfoFromDatabase(SQLiteDatabase db) {
        db = getReadableDatabase();
        String[] projection = {
                TwoFieldContract.TableEntry.TIMESTAMP,
                TwoFieldContract.TableEntry.X,
                TwoFieldContract.TableEntry.Y };

        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

}
