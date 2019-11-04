package com.linuxh2o.broadcaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button broadcast, checkEarphone;
    BroadcastReceiver broadcastReceiver = null;
    private int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcast = findViewById(R.id.checkNetwork);

        broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Bundle bundle = intent.getExtras();

                        NetworkInfo info = bundle.getParcelable("networkInfo");
                        NetworkInfo.State state = info.getState();

                        if (state == NetworkInfo.State.CONNECTED){
                            Toast.makeText(getApplicationContext(), "Network is connected", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "No connectivity", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                final IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                registerReceiver(broadcastReceiver, intentFilter);

            }
        });


        // check Earphone

        checkEarphone = findViewById(R.id.checkEarphone);

        checkEarphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {

                        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                            state = intent.getIntExtra("state", -1);
                        }

                        if (state == 1){
                            Toast.makeText(getApplicationContext(), "Headset is connected", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "No headset is connected", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                final IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
                registerReceiver(broadcastReceiver, intentFilter);

            }

        });
    }
}
