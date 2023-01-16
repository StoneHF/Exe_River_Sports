package com.example.exeriversports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button EnterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // btnEnter
        EnterButton = (Button) findViewById(R.id.btnEnterApp);

        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextactivity();



            }
        });
    }

    public void goToNextactivity() {

        Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(myIntent);

    }
}

