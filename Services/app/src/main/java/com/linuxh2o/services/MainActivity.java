package com.linuxh2o.services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    public int mChangeTime = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button stopButton  = findViewById(R.id.stop);
        Button startButton = findViewById(R.id.start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), MyService.class));
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), MyService.class));

            }
        });

        Button setButton        = findViewById(R.id.setWall);
        Button unsetButton      = findViewById(R.id.unsetWall);
        RadioButton thirtySecs  = findViewById(R.id.thirtySeconds);
        final RadioButton oneMinute   = findViewById(R.id.oneMinute);
        final RadioGroup radioGroup   = findViewById(R.id.buttonGroup);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mRadioID = radioGroup.getCheckedRadioButtonId();

                if (oneMinute.getId() == mRadioID){
                    mChangeTime = 5;
                }else {
                    mChangeTime = 3;
                }

                Intent intent = new Intent(getApplicationContext(), MyWallService.class);
                Bundle bundles = new Bundle();
                bundles.putInt("time", mChangeTime);
                intent.putExtras(bundles);
                startService(intent);
            }
        });

        unsetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), MyWallService.class));

            }
        });


        Button setAlarm     = findViewById(R.id.setAlarm);
        Button stopAlarm  = findViewById(R.id.stopAlarm);

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), AlarmAlert.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, 10000, pendingIntent);
            }
        });

        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), AlarmAlert.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
                alarmManager.cancel(pendingIntent);

            }
        });



    }
}
