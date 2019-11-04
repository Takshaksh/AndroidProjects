package com.linuxh2o.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MyWallService extends Service implements Runnable {

    private int wallID[] =  {R.drawable.wall1, R.drawable.wall2, R.drawable.wall3};
    private int time;
    private int FLAG = 0;
    private Thread t;
    private Bitmap bitmap3, bitmap1, bitmap2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Bundle bundle = intent.getExtras();
        time = bundle.getInt("time");

        bitmap1 = BitmapFactory.decodeResource(getResources(), wallID[0]);
        bitmap2 = BitmapFactory.decodeResource(getResources(), wallID[1]);
        bitmap3 = BitmapFactory.decodeResource(getResources(), wallID[2]);

        t = new Thread(this);
        t.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.exit(
                0
        );
    }

    @Override
    public void run() {

        try {
            while (true){
                if (FLAG == 0 ){
                    this.setWallpaper(bitmap1);
                    FLAG++;
                }else if (FLAG == 1){
                    this.setWallpaper(bitmap2);
                    FLAG++;
                }else{
                    this.setWallpaper(bitmap3);
                    FLAG = 0;
                }
                Thread.sleep(1000*time);
            }
        }catch (IOException e){}
        catch (InterruptedException e){}

    }
}
