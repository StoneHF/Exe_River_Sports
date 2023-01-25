package com.example.exeriversports;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivitySignUp extends AppCompatActivity {
    private EditText FirstName;
    private EditText Email;
    private EditText Password;
    private Button SubmitButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

        //  etFirstName  & etEmail & etTextPassword

        FirstName = (EditText) findViewById(R.id.etFirstName);
        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        SubmitButton = (Button) findViewById(R.id.btnSubmit);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidForm()) {
                    return;
                }
                String ArgFirstName = FirstName.getText().toString();
                String ArgEmail = Email.getText().toString();
                String ArgPassword = Password.getText().toString();
                goToNextActivity(ArgFirstName, ArgEmail, ArgPassword);
            }
        });
    }

    public void goToNextActivity(String enteredFirstName, String enteredEmail, String enteredPassword) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("ValueFirstName", enteredFirstName);
        myIntent.putExtra("ValueEmail", enteredEmail);
        myIntent.putExtra("ValuePassword", enteredPassword);
        startActivity(myIntent);
    }


    private boolean isValidForm() {
        boolean isValid = true;

        //validating name
        String name = FirstName.getText().toString();
        if (name.isEmpty() || name.contains(" ")) {
            FirstName.setError("Name cannot be empty or contain spaces.");
            isValid = false;
        } else if (!isValidName(name)) {
            FirstName.setError("Invalid name format");
            isValid = false;
        }

        //validating email
        String email = Email.getText().toString();
        if (email.isEmpty() || email.contains(" ")) {
            Email.setError("Email cannot be empty or contain spaces.");
            isValid = false;
        } else if (!isValidEmail(email)) {
            Email.setError("Invalid email format");
            isValid = false;
        }

        //validating password
        String password = Password.getText().toString();
        if (password.isEmpty() || password.contains(" ")) {
            Password.setError("Password cannot be empty or contain spaces.");
            isValid = false;
        } else if (!isValidPassword(password)) {
            Password.setError("Password must be at least 6 characters");
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+$");
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }





}
