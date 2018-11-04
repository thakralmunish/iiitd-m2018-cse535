package com.example.thakr.sensorvaluestry1;

import android.provider.BaseColumns;

public class AccelerometerContract {

    public AccelerometerContract() {

    }

    public static class TableEntry implements BaseColumns {
        public static String TABLE_NAME = "ACCELEROMETER";
        public static String TIMESTAMP = "TIMESTAMP";
        public static String X = "X";
        public static String Y = "Y";
        public static String Z = "Z";
    }

}
