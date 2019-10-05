package com.linuxh2o.clickui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button dialButton, movieButton;
    EditText phoneNumber, phoneNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialButton = findViewById(R.id.dial);
        phoneNumber = findViewById(R.id.phone);
        phoneNumber2 = findViewById(R.id.phone2);
        movieButton = findViewById(R.id.movieButton);


        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer num1 = Integer.parseInt(phoneNumber.getText().toString().trim());
                Integer num2 = Integer.parseInt(phoneNumber2.getText().toString().trim());

                int sum = num1 + num2;

//                Toast.makeText(getApplicationContext(), Integer.toString(sum), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Welcome To Second Activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

//                intent.putExtra("sum" , String.valueOf(sum));
                intent.putExtra("sum" , sum);
                startActivity(intent);
            }
        });

        movieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(i);
            }
        });
    }
}
