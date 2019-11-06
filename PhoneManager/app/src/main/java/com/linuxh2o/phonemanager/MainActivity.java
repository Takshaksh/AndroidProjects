package com.linuxh2o.phonemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView phoneData = findViewById(R.id.phoneData);

        final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        // Here We are checking for the permission & requesting
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE},1);
        }

        String imei = "IMEI :" + telephonyManager.getSimSerialNumber() + "\n";
               imei = imei + "IMEI :" + telephonyManager.getNetworkCountryIso() + "\n";
               imei = imei + "IMEI :" + telephonyManager.getSimOperatorName() + "\n";
               imei = imei + "IMEI :" + telephonyManager.getNetworkType() + "\n";
               imei = imei + "IMEI :" + telephonyManager.getLine1Number() + "\n";

               phoneData.setText(imei);


        // Getting the state

        PhoneStateListener phoneStateListener = new PhoneStateListener(){

            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);

                if (state == telephonyManager.CALL_STATE_RINGING){
                    Toast.makeText(getApplicationContext(), "Phone is ringing!",Toast.LENGTH_SHORT).show();
                }

                else if (state == telephonyManager.CALL_STATE_IDLE){
                    Toast.makeText(getApplicationContext(), "Phone is on idle!",Toast.LENGTH_SHORT).show();
                }

                else if (state == telephonyManager.CALL_STATE_OFFHOOK){
                    Toast.makeText(getApplicationContext(), "Phone is on call!",Toast.LENGTH_SHORT).show();
                }
            }
        };

        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);



    }
}
