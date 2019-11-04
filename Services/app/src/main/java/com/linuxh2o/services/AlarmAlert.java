package com.linuxh2o.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

public class AlarmAlert extends BroadcastReceiver {
    MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    public void onReceive(Context context, Intent intent) {

        startme(context);
        Toast.makeText(context, "Alarming", Toast.LENGTH_LONG).show();

    }
    public void startme(Context context){
        mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.start();
    }
    public void stopme(){
        mediaPlayer.stop();
    }
}
