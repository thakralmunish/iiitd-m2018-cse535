package com.example.thakr.sensorvaluestry1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ThreeFieldDBHelper extends SQLiteOpenHelper{

    //public static String DB_NAME = "SENSOR_VALUES";
    public static int DB_VERSION = 1;
    public String TABLE_NAME;

    public String TABLE_CREATE = "";

    public String TABLE_DROP = "";

    public ThreeFieldDBHelper(Context context, String TableName) {
        super(context, TableName, null, DB_VERSION);
        TABLE_NAME = TableName;
        TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
                ThreeFieldContract.TableEntry.TIMESTAMP + " TEXT PRIMARY KEY," +
                ThreeFieldContract.TableEntry.X + " DECIMAL NOT NULL," +
                ThreeFieldContract.TableEntry.Y + " DECIMAL NOT NULL," +
                ThreeFieldContract.TableEntry.Z + " DECIMAL NOT NULL);";
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
        AddInfo(T, V[0], V[1], V[2], db);
    }

    public void AddInfo(float T, float x, float y, float z, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThreeFieldContract.TableEntry.TIMESTAMP, T);
        contentValues.put(ThreeFieldContract.TableEntry.X, x);
        contentValues.put(ThreeFieldContract.TableEntry.Y, y);
        contentValues.put(ThreeFieldContract.TableEntry.Z, z);
        long rowID = db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor GetInfoFromDatabase(SQLiteDatabase db) {
        db = getReadableDatabase();
        String[] projection = {
                ThreeFieldContract.TableEntry.TIMESTAMP,
                ThreeFieldContract.TableEntry.X,
                ThreeFieldContract.TableEntry.Y,
                ThreeFieldContract.TableEntry.Z };

        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

}
