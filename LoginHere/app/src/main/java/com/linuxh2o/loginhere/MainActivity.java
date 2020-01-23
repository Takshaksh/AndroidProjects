package com.linuxh2o.loginhere;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mLoginButton, mDialogButton;
    EditText mEmail, mPassword;
    DataCreater dataCreater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginButton       = findViewById(R.id.loginButton);
        mDialogButton      = findViewById(R.id.dialogButton);
        mEmail             = findViewById(R.id.email);
        mPassword          = findViewById(R.id.password);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dataCreater = new DataCreater(getApplicationContext());

                String email = mEmail.getText().toString();
                String passs = mPassword.getText().toString();

                boolean result = dataCreater.insertMovieData(email, passs);

                if (result == true)
                    Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Data not inserted", Toast.LENGTH_SHORT).show();

            }
        });

        mLoginButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Cursor res = dataCreater.getTheData();
                if (res.getCount() == 0){
                    Toast.makeText(getApplicationContext(), "No Data found", Toast.LENGTH_SHORT).show();
                    return false;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID - " + res.getString(0) + "\n");
                    buffer.append("MOVIENAME - " + res.getString(1) + "\n");
                    buffer.append("DIRECTOR - " + res.getString(2) + "\n\n");
                }

                Intent i = new Intent(getApplicationContext(), NextActivity.class);
                i.putExtra("Data", buffer.toString());
                startActivity(i);

                return false;
            }
        });


























        mDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                alertDialogBuilder.show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart() Called", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop() Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy() Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause() Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume() Called", Toast.LENGTH_SHORT).show();
    }
}
