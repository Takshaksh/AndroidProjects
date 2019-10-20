package com.linuxh2o.menutab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PHONE_CALL = 1;  // We have to define a positive contact no here.
    private  double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mapButton = findViewById(R.id.mapButton);
        Button tabButton = findViewById(R.id.drawLayout);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);


        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetMyLocation getMyLocation = new GetMyLocation(getApplicationContext());
                Location location = getMyLocation.getLocation();
                if (location != null){
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                }

                if (lat == 0.0 && lon == 0.0){

                    Toast.makeText(getApplicationContext(), "Enable GPS or it is still searching location", Toast.LENGTH_LONG).show();

                }else {
                    Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                    i.putExtra("lon", lon);
                    i.putExtra("lat", lat);
                    startActivity(i);
                }


            }
        });

        tabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DrawActivity.class);
                startActivity(i);
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
            case R.id.call:
                //perform any action;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:198"));
                   // Here We are checking for the permission
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // Here We're requesting the permission                                  // We need only one permission that is why only one entry in this string array
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else {
                    // Finally, we are starting the intent for call
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
  // Note: Don't forget to specify the permission in manifests file.