package com.linuxh2o.clickui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    TextView showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        showData = findViewById(R.id.showData);

        String data = getIntent().getStringExtra("Data");

        showData.setText(data);
    }
}
