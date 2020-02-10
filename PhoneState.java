package com.linuxh2o.phoneme;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText number;
    TextView phoneData;
    Button call;
    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneData = findViewById(R.id.phoneData);
        number = findViewById(R.id.number);
        call = findViewById(R.id.call);

        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num = number.getText().toString();

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions( new String[] {Manifest.permission.CALL_PHONE}, 20);
                    }
                }
                startActivity(intent);

                String data = telephonyManager.getSimOperatorName() + " " + telephonyManager.getLine1Number();
                phoneData.setText(data);
            }
        });

        PhoneStateListener phoneStateListener = new PhoneStateListener(){

            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);

                if(state == telephonyManager.CALL_STATE_IDLE){
                    Toast.makeText(MainActivity.this, "Phone is idle", Toast.LENGTH_SHORT).show();
                }

                else if (state == telephonyManager.CALL_STATE_RINGING){
                    Toast.makeText(MainActivity.this, "Ringing", Toast.LENGTH_SHORT).show();
                }
                else if(state == telephonyManager.CALL_STATE_OFFHOOK){
                    Toast.makeText(MainActivity.this, "Phone is on call", Toast.LENGTH_SHORT).show();
                }
            }
        };

        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
