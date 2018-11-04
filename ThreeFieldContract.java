package com.example.thakr.sensorvaluestry1;

import android.provider.BaseColumns;

public class ThreeFieldContract {

    public static String Name;

    public ThreeFieldContract(String S) {
        Name = S;
    }

    public static class TableEntry implements BaseColumns {
        public static String TABLE_NAME = Name;
        public static String TIMESTAMP = "TIMESTAMP";
        public static String X = "X";
        public static String Y = "Y";
        public static String Z = "Z";
    }

}
