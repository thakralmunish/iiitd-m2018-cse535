package com.example.thakr.sensorvaluestry1;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class GPSListener implements LocationListener {

    @Override
    public void onLocationChanged(Location location) {
        String Latitude = String.valueOf(location.getLatitude());
        String Longitude = String.valueOf(location.getLongitude());
        MainActivity.GPSX.setText(Latitude);
        MainActivity.GPSY.setText(Longitude);
        String TimeStamp = String.valueOf(location.getTime());
        MainActivity.TwoFieldUpdate(Float.parseFloat(TimeStamp), Float.parseFloat(Latitude), Float.parseFloat(Longitude), MainActivity.GPSTableName);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
