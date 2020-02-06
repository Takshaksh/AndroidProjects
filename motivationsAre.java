package com.linuxh2o.onreceive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver, broadcastReceivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new Receiver();

        broadcastReceivers = new BroadcastReceiver() {
            private static final String string_q = "@echo";
            private static final String RECEIVED_SMS = "android.provider.Telephony.SMS_RECEIVED";

            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(RECEIVED_SMS));

                SmsManager smsManager = SmsManager.getDefault();
                Bundle bundle = intent.getExtras();

                if (bundle != null){
                    Object[] pdus = (Object[]) bundle.get("pdus");

                    SmsMessage[] messages = new SmsMessage[pdus.length];

                    for (int i = 0; i < pdus.length; i++){
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }

                    for (SmsMessage message : messages){
                        String msg = message.getMessageBody();
                        String to = message.getOriginatingAddress();

                        if (msg.toLowerCase().startsWith(string_q)){
                            String out = msg.substring(string_q.length());
                            Toast.makeText(MainActivity.this, out, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };
        
    } // onCreate()

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(broadcastReceivers, intentFilter);

        /*IntentFilter intentFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        registerReceiver(broadcastReceiver, intentFilter);*/
    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unregisterReceiver(broadcastReceiver);
//    }

}
