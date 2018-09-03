package com.example.thakr.finalapp2;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;

public class DownloaderService extends Service {

    DownloadManager DM;

    public DownloaderService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        DownloadFile();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return
        return START_STICKY;
    }

    public void DownloadFile() {
        //File Storage = new File(getApplicationContext().getFilesDir() + "/MunishDownloadFiles");

        String location = getFilesDir().toString();



        DM = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //request.setDestinationInExternalPublicDir(location, "s1.mp3");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = DM.enqueue(request);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
