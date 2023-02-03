package com.example.exeriversports;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private Button LogoutButton;
    private Button ProfileButton;
    private Button btnHome;
    private Button footballButton;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Initialize the home button
        btnHome = (Button) findViewById(R.id.btnHome);
        // Set an onClickListener for the home button
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity2 when the home button is clicked
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // Initialize the football button
        footballButton = findViewById(R.id.football_button);
        // Set an onClickListener for the football button
        footballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivityFootball when the football button is clicked
                Intent intent = new Intent(MainActivity3.this, MainActivityFootball.class);
                startActivity(intent);
            }
        });

        // Initialize the profile button
        ProfileButton = (Button) findViewById(R.id.btnProfile);
        // Set an onClickListener for the profile button
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivityUserPage when the profile button is clicked
                Intent myIntent = new Intent(MainActivity3.this, MainActivityUserPage.class);
                startActivity(myIntent);
            }
        });

        // Initialize the logout button
        LogoutButton = (Button) findViewById(R.id.btnLogout);
        // Set an onClickListener for the logout button
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear shared preferences and start MainActivity when the logout button is clicked
                SharedPreferences sharedPreferences = getSharedPreferences("siteMembers", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Initialize the ScaleGestureDetector
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        // Get the root view of the layout
        rootView = findViewById(android.R.id.content);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Handle the touch event with the ScaleGestureDetector
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    // Inner class for the ScaleListener
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // Update the scale factor based on the scale
            mScaleFactor *= detector.getScaleFactor();
            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            rootView.setScaleX(mScaleFactor);
            rootView.setScaleY(mScaleFactor);
            return true;
        }
    }
}


