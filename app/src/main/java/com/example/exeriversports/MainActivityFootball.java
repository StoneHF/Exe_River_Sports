package com.example.exeriversports;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;


 // ( add this for google maps) public class MainActivityFootball extends AppCompatActivity implements OnMapReadyCallback
public class MainActivityFootball extends AppCompatActivity {

    private Button btnHome;
    private Button btnOrder;
    private Button btnMap;
    private GoogleMap mMap;
    private Button LogoutButton;
    private Button ProfileButton;

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

        ProfileButton = (Button) findViewById(R.id.btnProfile);
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityFootball.this, MainActivityUserPage.class);
                startActivity(myIntent);
            }
        });

        LogoutButton = (Button) findViewById(R.id.btnLogout);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("siteMembers", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivityFootball.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

/*
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapViewEmirates);
        mapFragment.getMapAsync(this);

 */
    }

   // @Override
   // public void onMapReady(GoogleMap googleMap) {
   //     mMap = googleMap;

        // Add a marker for the location of the sports match and move the camera
   //     LatLng emiratesStadium = new LatLng(51.5548, -0.1084);
    //    mMap.addMarker(new MarkerOptions().position(emiratesStadium).title("EmiratesStadium"));
    //    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(emiratesStadium, 15));
  // }
}

