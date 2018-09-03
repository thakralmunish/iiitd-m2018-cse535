package com.example.thakr.finalapp2;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;

public class PlaySongService extends Service {

    MediaPlayer MusicPlayer;

    static String ThisSong = "";
    String SongToPlay;

    ListView ListView_SongsList;

    public PlaySongService() {
        Log.i("DEF", "Create Start");
        MusicPlayer = new MediaPlayer();
        MusicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Log.i("DEF", "Create End");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("DEF", "3");
        SongToPlay = intent.getStringExtra(ThisSong);
        Log.i("DEF", "4");
        PlayMusic(SongToPlay);
        Log.i("DEF", "5");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        MusicPlayer.stop();
        MusicPlayer.reset();
    }

    public void PlayMusic (String Song) {
        Log.i("DEF", "6");
        if (MusicPlayer.isPlaying()) {
            Log.i("DEF", "7");
            MusicPlayer.stop();
            MusicPlayer.reset();
        }
        Log.i("DEF", "8");
        try {
            Log.i("DEF", "9");
            MusicPlayer.setDataSource(Song);
            Log.i("DEF", "10");
            MusicPlayer.prepare();
            Log.i("DEF", "11");
            MusicPlayer.start();
            Log.i("DEF", "12");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
