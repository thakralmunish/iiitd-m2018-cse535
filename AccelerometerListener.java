package com.example.thakr.sensorvaluestry1;

import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerometerListener implements SensorEventListener {

    public static float PrevX = 0;
    public static float PrevY = 0;
    public static float PrevZ = 0;

    public static int Shake = 0;



    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("ABC", "20");
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        Log.d("ABC", "21");
        MainActivity.AccelerometerX.setText(String.valueOf(x));
        Log.d("ABC", "22");
        MainActivity.AccelerometerY.setText(String.valueOf(y));
        Log.d("ABC", "22");
        MainActivity.AccelerometerZ.setText(String.valueOf(z));
        Log.d("ABC", "22");
        String TimeStamp = String.valueOf(event.timestamp);
        Log.d("ABC", "23");
        MainActivity.ThreeFieldUpdate(Float.parseFloat(TimeStamp), event.values[0], event.values[1], event.values[2], MainActivity.AccelerometerTableName);
        Log.d("ABC", "24");

        if (PrevX == 0 && PrevY == 0 && PrevZ == 0) {
            // PASS
            Log.d("ABC", "25");
        }
        else {
            float Speed = (x - PrevX) + (y - PrevY) + (z - PrevZ);
            if (Math.abs(Speed) > 27) {
                Shake += 1;
                if (Shake >= 3) {
                    Log.d("ABCDEF", "SHAKE");
                    MainActivity.MakeSnackBar();
                    Shake = 0;
                }
            }
            else {
                Shake = 0;
            }
            Log.d("ABC", "26");
            Log.d("ABCDEF", String.valueOf(Speed));
            Log.d("ABC", "27");
        }

        PrevX = event.values[0];
        PrevY = event.values[1];
        PrevZ = event.values[2];
        Log.d("ABC", "28");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void Reset() {
        PrevX = 0;
        PrevY = 0;
        PrevZ = 0;
        Shake = 0;
    }

}
