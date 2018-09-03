package com.example.thakr.finalapp2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class CurrentSongAndDownload extends Fragment {

    View Downloader_View;

    Button DownloadButton;
    DownloaderService DS;

    String ABC;

    public CurrentSongAndDownload() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DS = new DownloaderService();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Downloader_View = inflater.inflate(R.layout.fragment_current_song_and_download, container, false);
        return Downloader_View;
    }

}
