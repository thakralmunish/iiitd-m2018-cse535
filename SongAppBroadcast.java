package com.example.thakr.finalapp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SongAppBroadcast extends BroadcastReceiver {

    public SongAppBroadcast() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String ActionDone = intent.getAction();
        if (ActionDone.equals("android.intent.action.AIRPLANE_MODE")) {
            ActionDone = "Airplane Mode";
        }
        Toast.makeText(context, ActionDone, Toast.LENGTH_SHORT).show();
        Log.i("BROADCAST", ActionDone);
    }
}
