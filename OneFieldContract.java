package com.example.thakr.sensorvaluestry1;

import android.provider.BaseColumns;

public class OneFieldContract {

    public static String Name;

    public OneFieldContract(String S) {
        Name = S;
    }

    public static class TableEntry implements BaseColumns {
        public static String TABLE_NAME = Name;
        public static String TIMESTAMP = "TIMESTAMP";
        public static String VALUE = "VALUE";
    }

}
