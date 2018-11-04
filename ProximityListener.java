package com.example.thakr.sensorvaluestry1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class ProximityListener implements SensorEventListener {

    @Override
    public void onSensorChanged(SensorEvent event) {
        MainActivity.ProximityX.setText(String.valueOf(event.values[0]));
        String TimeStamp = String.valueOf(event.timestamp);
        MainActivity.OneFieldUpdate(Float.parseFloat(TimeStamp), event.values[0], MainActivity.ProximityTableName);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
