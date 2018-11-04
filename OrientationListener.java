package com.example.thakr.sensorvaluestry1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class OrientationListener implements SensorEventListener {

    @Override
    public void onSensorChanged(SensorEvent event) {
        MainActivity.OrientationX.setText(String.valueOf(event.values[0]));
        MainActivity.OrientationY.setText(String.valueOf(event.values[1]));
        MainActivity.OrientationZ.setText(String.valueOf(event.values[2]));
        String TimeStamp = String.valueOf(event.timestamp);
        MainActivity.ThreeFieldUpdate(Float.parseFloat(TimeStamp), event.values[0], event.values[1], event.values[2], MainActivity.OrientationTableName);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
