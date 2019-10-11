package com.linuxh2o.clickui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView mytext = findViewById(R.id.textView);

//        String a = getIntent().getStringExtra("sum");

        int a = getIntent().getIntExtra("sum", 0);
        mytext.setText(String.valueOf(a));

    }
}
