package com.example.thakr.finalapp2;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SongsListDisplay extends Fragment {

    View View_SongsList;

    TextView TestView;

    Button DownloadButton;
    ImageView PlayPauseButtton;

    String SongName;
    String ArtistName;

    Intent PlayIntent;
    PlaySongService PSS;

    ArrayList<String> SongsArray;
    ArrayList<String> SongArtistArray;
    ArrayList<String> SongLocations;
    ArrayAdapter<String> ListAdapter;
    ContentResolver DataResolver;
    Uri SongType;
    String ThisSong;

    ImageView TestButton;

    ListView ListView_SongsList;

    String CurrentStatus = "Play";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SongsArray = new ArrayList<>();
        SongArtistArray = new ArrayList<>();
        SongLocations = new ArrayList<>();
        ListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, SongsArray);
        DataResolver = getContext().getContentResolver();
        GetMusicList();
    }



    public void GetMusicList() {
        SongType = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor SongsDB = DataResolver.query(SongType, null, null, null, null);

        if (SongsDB != null && SongsDB.moveToFirst()) {
            int SongDisplayName = SongsDB.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
            int SongArtist = SongsDB.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int SongLocation = SongsDB.getColumnIndex(MediaStore.Audio.Media.DATA);

            String DisplayName = SongsDB.getString(SongDisplayName);
            String Artist = SongsDB.getString(SongArtist);
            String Location = SongsDB.getString(SongLocation);

            SongsArray.add(DisplayName);
            SongArtistArray.add(Artist);
            SongLocations.add(Location);

            while (SongsDB.moveToNext()) {
                DisplayName = SongsDB.getString(SongDisplayName);
                Artist = SongsDB.getString(SongArtist);
                Location = SongsDB.getString(SongLocation);
                SongsArray.add(DisplayName);
                SongArtistArray.add(Artist);
                SongLocations.add(Location);
            }
        }

    }

    public void SongCLickEventListener() {

        ListView_SongsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThisSong = SongLocations.get(position);
                SongName = SongsArray.get(position);
                ArtistName = SongArtistArray.get(position);
                PlayIntent = new Intent(getActivity(), PlaySongService.class);
                PlayIntent.putExtra(PlaySongService.ThisSong, SongLocations.get(position));
                CurrentStatus = "Pause";
                getActivity().startService(PlayIntent);
                TestButton = getActivity().findViewById(R.id.Button_PlayPause);
                TestButton.setImageResource(R.drawable.pause);

                TestView = (TextView) getActivity().findViewById(R.id.TestView);
                TestView.setText(SongName);

                TestView = (TextView) getActivity().findViewById(R.id.ArtistName);
                TestView.setText(ArtistName);


                Log.i("GHI", "1");
                Log.i("GHI", "5");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View_SongsList = inflater.inflate(R.layout.fragment_songs_list_display, container, false);

        ListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, SongsArray);
        ListView_SongsList = View_SongsList.findViewById(R.id.ListView_SongsList);
        ListView_SongsList.setAdapter(ListAdapter);

        PSS = new PlaySongService();

        PlayPauseButtton = View_SongsList.findViewById(R.id.Button_PlayPause);
        PlayPauseButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CurrentStatus.equals("Play")) {
                    PlayPauseButtton.setImageResource(R.drawable.pause);
                    CurrentStatus = "Pause";
                    PlayIntent = new Intent(getActivity(), PlaySongService.class);
                    if (ThisSong == null) {
                        ThisSong = SongLocations.get(0);
                        SongName = SongsArray.get(0);
                        ArtistName = SongArtistArray.get(0);
                    }
                    PlayIntent.putExtra(PlaySongService.ThisSong, ThisSong);
                    getActivity().startService(PlayIntent);

                    TestView = (TextView) getActivity().findViewById(R.id.TestView);
                    TestView.setText(SongName);

                    TestView = (TextView) getActivity().findViewById(R.id.ArtistName);
                    TestView.setText(ArtistName);


                }
                else {
                    PlayPauseButtton.setImageResource(R.drawable.play);
                    CurrentStatus = "Play";
                    getActivity().stopService(PlayIntent);
                }
            }
        });

        DownloadButton = View_SongsList.findViewById(R.id.Button_DownloadSong);
        DownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager CM = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ActiveNetwork = CM.getActiveNetworkInfo();
                boolean IsConnected = (ActiveNetwork != null) && (ActiveNetwork.isConnected());
                if (IsConnected) {
                    Toast.makeText(getActivity().getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    DownloadTheSong(v);
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SongCLickEventListener();
        return View_SongsList;
    }

    public void DownloadTheSong(View V) {
        Intent DownloadIntent = new Intent(getContext(), DownloaderService.class);
        getActivity().startService(DownloadIntent);
        getActivity().stopService(DownloadIntent);
    }

    public interface SongClicked {
        public void TestChangeText(String S);
    }

}
