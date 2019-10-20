package com.linuxh2o.menutab;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

public class GetMyLocation implements LocationListener {

    // Defining Context object, this will be used below
    Context context;

    public GetMyLocation(Context ctx){
        context = ctx;
    }


   public Location getLocation(){

        // Checking for permission
       if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           Toast.makeText(context, "Location permission not granted", Toast.LENGTH_SHORT).show();
          return null;
       }
        // Creating a location manager to manage the location
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // Setting the location provider as we can get location from many providers like Wifi, gps, internet
        boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // if true we are returning the location
        if (isGpsEnabled){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, this);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return location;
        }else {
            Toast.makeText(context, "Please enable gps", Toast.LENGTH_SHORT).show();
        }

        return null;

    }

    // These are unused Abstract class methods, must to implement
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
