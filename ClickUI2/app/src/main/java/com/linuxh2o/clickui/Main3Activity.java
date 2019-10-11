package com.linuxh2o.clickui;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity /*implements RatingBar.OnRatingBarChangeListener*/{

    private EditText movieName, userReview, releaseYear, movieDuration, movieStarring, movieDirector;
    private RatingBar yourRating;
    private Button mSaveButton;
    DatabaseHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myDatabase = new DatabaseHelper(this);

        mSaveButton = findViewById(R.id.save);
        movieName = findViewById(R.id.movie);
        userReview= findViewById(R.id.review);
        releaseYear= findViewById(R.id.year);
        movieDuration= findViewById(R.id.duration);
        movieStarring= findViewById(R.id.starring);
        movieDirector= findViewById(R.id.director);
        yourRating= findViewById(R.id.ratingBar);

//        yourRating.setOnRatingBarChangeListener(this);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str  = movieName.getText().toString();
//                String str1 = userReview.getText().toString();
                String str2 = releaseYear.getText().toString();
//                String str3 = movieDuration.getText().toString();
//                String str4 = movieDirector.getText().toString();
//                String str5 = movieStarring.getText().toString();
//
//                if (str.equalsIgnoreCase("")
//                        || str1.equalsIgnoreCase("")
//                        || str2.equalsIgnoreCase("")
//                        || str3.equalsIgnoreCase("")
//                        || str4.equalsIgnoreCase("")
//                        || str5.equalsIgnoreCase("") || yourRating.getRating() == 0.0){
//                    Toast.makeText(getApplicationContext(), "Fill it up", Toast.LENGTH_SHORT).show();
//
//                }else {
//                    Toast.makeText(getApplicationContext(), "All Filled up", Toast.LENGTH_SHORT).show();
//
//                }

                boolean result =  myDatabase.insertMovieData(str, str2);

                if (result = true)
                    Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Data not inserted", Toast.LENGTH_SHORT).show();

            }
        });

        // Second Button

        public void viewAllData(){

            Cursor res = myDatabase.getTheData();

            if (res.getCount() == 0){
                // message
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                buffer.append("ID : " + res.getString(0));
                buffer.append("Name:");
            }

            Toast = buffer.toString(); to text 

        }



    }

//    @Override
//    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//        yourRating.setRating(rating);
//    }
}
