package com.linuxh2o.smsbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        share = findViewById(R.id.shareBTN);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out this app: https://play.google.com/store/apps/details?id=com.google.android.apps.androidify");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Share to - "));
            }
        });
    }
}
