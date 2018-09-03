package com.example.thakr.finalapp2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Intent.ACTION_AIRPLANE_MODE_CHANGED;
import static android.content.Intent.ACTION_POWER_CONNECTED;
import static android.content.Intent.ACTION_POWER_DISCONNECTED;
import static android.content.Intent.ACTION_BOOT_COMPLETED;

public class MainSongApp extends FragmentActivity {

    Fragment BF, CS, CSandD, HD, SLD;

    BroadcastReceiver BR;

    Button TestDownloadButton;

    static boolean PlayingMusic = false;

    private int My_Permission_Request = 1;

    boolean PermissionGrant = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().remove(BF).commit();
                    getSupportFragmentManager().beginTransaction().remove(CS).commit();
                    getSupportFragmentManager().beginTransaction().remove(CSandD).commit();
                    getSupportFragmentManager().beginTransaction().remove(SLD).commit();

                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_SongsList, HD).commit();
                    return true;

                case R.id.navigation_viewsongs:
                    Log.i("ABC", "5");
                    getSupportFragmentManager().beginTransaction().remove(HD).commit();
                    if (TestDownloadButton.getText().equals("Download")) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_SongsList, SLD, "SONGSLISTTAG").commit();
                    }
                    else {
                        TestDownloadButton.setText("Download");
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_SongsList, SLD).commit();
                    }
                    Log.i("ABC", "6");
                    Log.i("ABC", "11");
                    Log.i("ABC", "12");
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_song_app);

        Log.i("GHI", "6");
        Log.i("GHI", "7");
        Log.i("GHI", "8");

        Log.i("ABC", "13");

        BR = new SongAppBroadcast();
        IntentFilter IF = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        IF.addAction(ACTION_AIRPLANE_MODE_CHANGED);
        IF.addAction(ACTION_BOOT_COMPLETED);
        IF.addAction(ACTION_POWER_CONNECTED);
        IF.addAction(ACTION_POWER_DISCONNECTED);
        registerReceiver(BR, IF);

        Log.i("ABC", "14");

        BF = new BlankFragment();
        CS = new CurrentSong();
        CSandD = new CurrentSongAndDownload();
        HD = new HomeDisplay();

        Log.i("ABC", "15");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        Log.i("ABC", "16");

        getSupportFragmentManager().beginTransaction().add(R.id.Frame_SongsList, HD).commit();

        Log.i("ABC", "17");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, My_Permission_Request);
                Log.i("ABC", "18");
            }
            else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, My_Permission_Request);
                Log.i("ABC", "19");
            }
        }
        else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, My_Permission_Request);
            Log.i("ABC", "20");
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        TestDownloadButton = findViewById(R.id.TestDownload);

        TestDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TestDownloadButton.getText().equals("Download")) {
                    TestDownloadButton.setText("View Songs List");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_SongsList, BF).commit();
                    ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo ActiveNetwork = CM.getActiveNetworkInfo();
                    boolean IsConnected = (ActiveNetwork != null) && (ActiveNetwork.isConnected());
                    if (IsConnected) {
                        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                        DownloadTheSong(v);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    TestDownloadButton.setText("Download");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_SongsList, SLD).commit();
                }
            }
        });

    }

    public void DownloadTheSong(View V) {
        Intent DownloadIntent = new Intent(this, DownloaderService.class);
        startService(DownloadIntent);
        stopService(DownloadIntent);
    }

    public boolean PermissionGranted(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == My_Permission_Request) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i("ABC", "21");
        boolean PermissionYes = PermissionGranted(requestCode, permissions, grantResults);
        if (PermissionYes) {
            Log.i("ABC", "22");
            PermissionGrant = true;
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            SLD = new SongsListDisplay();
            Log.i("ABC", "23");
        }
        else {
            Log.i("ABC", "24");
            PermissionGrant = false;
            Toast.makeText(this, "No Permission Granted", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    public void DownloadFromTheNet(View V) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void TestChangeTest(String S) {
        Log.i("GHI", "6");
        Toast.makeText(this, S, Toast.LENGTH_SHORT);
        Log.i("GHI", "7");
    }
}
