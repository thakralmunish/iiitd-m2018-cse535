package com.example.thakr.sensorvaluestry1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int SamplingPeriod = 100000;

    public static TextView AccelerometerX, AccelerometerY, AccelerometerZ;
    public static TextView GyroscopeX, GyroscopeY, GyroscopeZ;
    public static TextView OrientationX, OrientationY, OrientationZ;
    public static TextView GPSX, GPSY;
    public static TextView ProximityX;

    public static View view;

    public static Button AccelerometerStart, GyroscopeStart, OrientationStart, GPSStart, ProximityStart;

    public static String AccelerometerTableName = "ACCELEROMETER";
    public static String GyroscopeTableName = "GYROSCOPE";
    public static String OrientationTableName = "ORIENTATION";
    public static String GPSTableName = "GPS";
    public static String ProximityTableName = "PROXIMITY";

    public SensorManager sensorManager;
    LocationManager locationManager;
    Sensor Accelerometer, Gyroscope, Orientation, GPS, Proximity;

    public AccelerometerListener accelerometerListener;
    public GyroscopeListener gyroscopeListener;
    public OrientationListener orientationListener;
    public GPSListener gpsListener;
    public ProximityListener proximityListener;

    public static ThreeFieldDBHelper AccelerometerHelper, GyroscopeHelper, OrientationHelper;
    public static TwoFieldDBHelper GPSHelper;
    public static OneFieldDBHelper ProximityHelper;

    public static SQLiteDatabase AccelerometerSQL, GyroscopeSQL, OrientationSQL, GPSSQL, ProximitySQL;

    private int PERMISSION_REQUEST_1 = 1;
    private int PERMISSION_REQUEST_2 = 1;
    private int PERMISSION_REQUEST_3 = 1;
    private int PERMISSION_REQUEST_4 = 1;
    private int PERMISSION_REQUEST_5 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.Main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BODY_SENSORS}, PERMISSION_REQUEST_1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_2);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_3);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_4);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_5);
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        accelerometerListener = new AccelerometerListener();
        gyroscopeListener = new GyroscopeListener();
        orientationListener = new OrientationListener();
        gpsListener = new GPSListener();
        proximityListener = new ProximityListener();

        Accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        Proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        AccelerometerHelper = new ThreeFieldDBHelper(getApplicationContext(), AccelerometerTableName);
        AccelerometerSQL = AccelerometerHelper.getWritableDatabase();
        GyroscopeHelper = new ThreeFieldDBHelper(getApplicationContext(), GyroscopeTableName);
        GyroscopeSQL = GyroscopeHelper.getWritableDatabase();
        OrientationHelper = new ThreeFieldDBHelper(getApplicationContext(), OrientationTableName);
        OrientationSQL = OrientationHelper.getWritableDatabase();
        GPSHelper = new TwoFieldDBHelper(getApplicationContext(), GPSTableName);
        GPSSQL = GPSHelper.getWritableDatabase();
        ProximityHelper = new OneFieldDBHelper(getApplicationContext(), ProximityTableName);
        ProximitySQL = ProximityHelper.getWritableDatabase();

        AccelerometerX = findViewById(R.id.AccelerometerX);
        AccelerometerY = findViewById(R.id.AccelerometerY);
        AccelerometerZ = findViewById(R.id.AccelerometerZ);
        AccelerometerStart = findViewById(R.id.AccelerometerStart);
        GyroscopeX = findViewById(R.id.GyroscopeX);
        GyroscopeY = findViewById(R.id.GyroscopeY);
        GyroscopeZ = findViewById(R.id.GyroscopeZ);
        GyroscopeStart = findViewById(R.id.GyroscopeStart);
        OrientationX = findViewById(R.id.OrientationX);
        OrientationY = findViewById(R.id.OrientationY);
        OrientationZ = findViewById(R.id.OrientationZ);
        OrientationStart = findViewById(R.id.OrientationStart);
        GPSX = findViewById(R.id.GPSX);
        GPSY = findViewById(R.id.GPSY);
        GPSStart = findViewById(R.id.GPSStart);
        ProximityX = findViewById(R.id.ProximityX);
        ProximityStart = findViewById(R.id.ProximityStart);

        Log.d("ABC", "3");

        AccelerometerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AccelerometerStart.getText().toString().equals("Start")) {
                    Log.d("ABC", "10");
                    sensorManager.registerListener(accelerometerListener, Accelerometer, SamplingPeriod);
                    Log.d("ABC", "11");
                    AccelerometerStart.setText("Stop");
                    Log.d("ABC", "12");
                }
                else {
                    sensorManager.unregisterListener(accelerometerListener);
                    Log.d("ABC", "13");
                    accelerometerListener.Reset();
                    Log.d("ABC", "14");
                    AccelerometerStart.setText("Start");
                    Log.d("ABC", "15");
                }
            }
        });

        GyroscopeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GyroscopeStart.getText().toString().equals("Start")) {
                    sensorManager.registerListener(gyroscopeListener, Gyroscope, SamplingPeriod);
                    GyroscopeStart.setText("Stop");
                }
                else {
                    sensorManager.unregisterListener(gyroscopeListener);
                    GyroscopeStart.setText("Start");
                }
            }
        });

        OrientationStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OrientationStart.getText().toString().equals("Start")) {
                    sensorManager.registerListener(orientationListener, Orientation, SamplingPeriod);
                    OrientationStart.setText("Stop");
                }
                else {
                    sensorManager.unregisterListener(orientationListener);
                    OrientationStart.setText("Start");
                }
            }
        });

        GPSStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GPSStart.getText().toString().equals("Start")) {
                    try {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, gpsListener);
                        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                        if (location == null) {
                            GPSX.setText(String.valueOf("NULL"));
                            GPSY.setText(String.valueOf("NULL"));
                        }
                        else {
                            GPSX.setText(String.valueOf(location.getLatitude()));
                            GPSY.setText(String.valueOf(location.getLongitude()));
                        }
                        GPSStart.setText("Stop");
                    }
                    catch (SecurityException e) {
                        Toast.makeText(getApplicationContext(), "LOCATION OFF", Toast.LENGTH_SHORT).show();
                        GPSStart.setText("Start");
                    }
                }
                else {
                    locationManager.removeUpdates(gpsListener);
                    GPSStart.setText("Start");
                }
            }
        });

        ProximityStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ProximityStart.getText().toString().equals("Start")) {
                    sensorManager.registerListener(proximityListener, Proximity, SamplingPeriod);
                    ProximityStart.setText("Stop");
                }
                else {
                    sensorManager.unregisterListener(proximityListener);
                    ProximityStart.setText("Start");
                }
            }
        });

        Log.d("ABC", "4");

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelerometerListener);
        accelerometerListener.Reset();
        sensorManager.unregisterListener(gyroscopeListener);
        sensorManager.unregisterListener(orientationListener);
        locationManager.removeUpdates(gpsListener);
        sensorManager.unregisterListener(proximityListener);
        AccelerometerSQL.close();
        GyroscopeSQL.close();
        OrientationSQL.close();
        GPSSQL.close();
        ProximitySQL.close();
        AccelerometerStart.setText("Start");
        GyroscopeStart.setText("Start");
        OrientationStart.setText("Start");
        GPSStart.setText("Start");
        ProximityStart.setText("Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AccelerometerStart.setText("Start");
        GyroscopeStart.setText("Start");
        OrientationStart.setText("Start");
        GPSStart.setText("Start");
        ProximityStart.setText("Start");
        AccelerometerSQL = AccelerometerHelper.getWritableDatabase();
        GyroscopeSQL = GyroscopeHelper.getWritableDatabase();
        OrientationSQL = OrientationHelper.getWritableDatabase();
        GPSSQL = GPSHelper.getWritableDatabase();
        ProximitySQL = ProximityHelper.getWritableDatabase();
    }

    public static void ThreeFieldUpdate(float T, float x, float y, float z, String Sensor) {
        if (Sensor.equals(AccelerometerTableName)) {
            AccelerometerHelper.AddInfo(T, x, y, z, AccelerometerSQL);
        }
        else if (Sensor.equals(GyroscopeTableName)) {
            GyroscopeHelper.AddInfo(T, x, y, z, GyroscopeSQL);
        }
        else if (Sensor.equals(OrientationTableName)) {
            OrientationHelper.AddInfo(T, x, y, z, OrientationSQL);
        }
    }

    public static void TwoFieldUpdate(float T, float x, float y, String Sensor) {
        if (Sensor.equals(GPSTableName)) {
            GPSHelper.AddInfo(T, x, y, GPSSQL);
        }
    }

    public static void OneFieldUpdate(float T, float v, String Sensor) {
        if (Sensor.equals(ProximityTableName)) {
            ProximityHelper.AddInfo(T, v, ProximitySQL);
        }
    }

    public static void MakeSnackBar() {
        final Snackbar snackbar = Snackbar.make(MainActivity.view, "SHAKE DETECTED", Snackbar.LENGTH_LONG);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

}
