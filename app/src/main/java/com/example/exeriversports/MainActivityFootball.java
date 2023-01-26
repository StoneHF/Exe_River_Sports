package com.example.exeriversports;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivityFootball extends AppCompatActivity implements OnMapReadyCallback {

    private Button btnHome;
    private Button btnOrder;
    private Button btnMap;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_football);

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityFootball.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityFootball.this, MainActivityTicketOrder.class);
                startActivity(intent);
            }
        });

/*
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapViewEmirates);
        mapFragment.getMapAsync(this);

 */
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker for the location of the sports match and move the camera
        LatLng emiratesStadium = new LatLng(51.5548, -0.1084);
        mMap.addMarker(new MarkerOptions().position(emiratesStadium).title("EmiratesStadium"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(emiratesStadium, 15));
    }
}

