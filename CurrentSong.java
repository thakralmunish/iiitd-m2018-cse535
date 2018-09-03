package com.example.thakr.finalapp2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CurrentSong extends Fragment {

    String ThisSong;
    String ThisArtist;

    View View_CurrentSong;

    TextView SongName;
    TextView ArtistName;

    static String Name = "Nothing To Show";
    static String Artist = "Unknown";

    String CurName;
    String CurArtist;

    public CurrentSong() {

    }

    @Override
    public void onStart() {
        super.onStart();

        //SongName = View_CurrentSong.findViewById(R.id.TextView_CurrentSong)
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        Log.i("got it", "1");
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View_CurrentSong = inflater.inflate(R.layout.fragment_current_song, container, false);

        SongName = View_CurrentSong.findViewById(R.id.TextView_CurrentSong);
        ArtistName = View_CurrentSong.findViewById(R.id.TextView_CurrentSongArtist);

        return View_CurrentSong;
    }

}
