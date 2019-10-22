package com.linuxh2o.smsbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mobileNumber, yourMessage;
    private Button send, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileNumber = findViewById(R.id.mNumber);
        yourMessage  = findViewById(R.id.mMessage);
        send         = findViewById(R.id.send);
        reset        = findViewById(R.id.reset);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number  = mobileNumber.getText().toString();
                String message = yourMessage.getText().toString();

                // Here We are checking for the permission & requesting
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},1);
                }
                else {
                    if (number.isEmpty()){
                        mobileNumber.setError("Required");
                        mobileNumber.requestFocus();
                        return;
                    }
                    else if (message.isEmpty()){
                        yourMessage.setError("Required");
                        mobileNumber.requestFocus();
                        return;
                    }
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null,
                            message, null, null);

                    mobileNumber.setText("");
                    yourMessage.setText("");

                    Toast.makeText(getApplicationContext(), "Message Sent!",Toast.LENGTH_LONG).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileNumber.setText("");
                yourMessage.setText("");
            }
        });
    }

    // Setting the menu xml file in this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
                return true;

            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.androidify");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Share to - "));
                return true;

            case R.id.exit:
                System.exit(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
