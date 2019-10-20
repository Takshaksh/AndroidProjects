package com.linuxh2o.menutab;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private  double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Getting the location data from MainActivity
        lon = getIntent().getDoubleExtra("lon", 0.0);
        lat = getIntent().getDoubleExtra("lat", 0.0);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Setting the location values to the map
        LatLng uHere = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(uHere).title("You are here!"));
        // Simply zooming the map with 18F float value
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uHere, 18F));
    }
}
