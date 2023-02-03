package com.example.exeriversports;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private Button ViewButton;
    private Button LogoutButton;
    private Button ProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Get member_id from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("siteMembers", MODE_PRIVATE);
        int member_id = sharedPreferences.getInt("member_id", 0);

        // Set the text for the member_id TextView
        TextView memberIdTextView = (TextView) findViewById(R.id.member_id_textview);
        memberIdTextView.setText("Hi member " + member_id);

        // Set onClickListener for the ProfileButton
        ProfileButton = (Button) findViewById(R.id.btnProfile);
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity2.this, MainActivityUserPage.class);
                startActivity(myIntent);
            }
        });

        // Set onClickListener for the ViewButton
        ViewButton = (Button) findViewById(R.id.btnSportsEvents);
        ViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(myIntent);
            }
        });

        // Set onClickListener for the LogoutButton
        LogoutButton = (Button) findViewById(R.id.btnLogout);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("siteMembers", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}


