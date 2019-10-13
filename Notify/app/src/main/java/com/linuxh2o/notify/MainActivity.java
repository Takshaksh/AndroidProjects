package com.linuxh2o.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button toast, snakbar, datePicker, dialog, speakButton;
    private EditText speakText;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toast       = findViewById(R.id.toast);
        snakbar     = findViewById(R.id.snakbar);
        datePicker  = findViewById(R.id.datePicker);
        dialog      = findViewById(R.id.dialog);
        speakButton = findViewById(R.id.speakButton);
        speakText   = findViewById(R.id.speakText);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        toast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getApplicationContext(), "I'm a Toast Message", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 1,5);
                        toast.show();
                    }
                }
        );

        snakbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.notifyLayout), "This is Snackbar!", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        // Date picker event with Calender class object to fetch current date

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                Toast.makeText(getApplicationContext(), "Selected Date : " +day+"-"+month+"-"+year, Toast.LENGTH_SHORT).show();
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();

            }
        });

        // Dialog to show exit pop-up

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("Do you want to close the app?");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                finish();
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Text-to-speech onclick listner

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });

        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speakingText = speakText.getText().toString();
                if (speakingText.isEmpty()){
                    speakText.setError("Enter something");
                    speakText.requestFocusFromTouch();
                }
                tts.speak(speakingText, TextToSpeech.QUEUE_FLUSH, null);



            }
        });
    }

    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
