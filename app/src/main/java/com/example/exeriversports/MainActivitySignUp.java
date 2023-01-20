package com.example.exeriversports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        // btnSubmit

        SubmitButton = (Button) findViewById(R.id.btnSubmit);



        SubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String ArgFirstName = FirstName.getText() .toString();
                String ArgEmail = Email.getText() .toString();
                String ArgPassword = Password.getText() .toString();
                goToNextActivity(ArgFirstName, ArgEmail, ArgPassword);
            }
        });
    }

    public void goToNextActivity(String enteredFirstName, String enteredEmail, String enteredPassword){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("ValueFirstName",enteredFirstName);
        myIntent.putExtra("ValueEmail",enteredEmail);
        myIntent.putExtra("ValuePassword",enteredPassword);
        startActivity(myIntent);
    }

}
/*
    private boolean isValidForm() {
        boolean isValid = true;

        //validating name
        if(!isValidName(FirstName.getText().toString())) {
            FirstName.setError("Invalid name format");
            isValid = false;
        }

        //validating email
        if(!isValidEmail(Email.getText().toString())) {
            Email.setError("Invalid email format");
            isValid = false;
        }

        //validating password
        if(!isValidPassword(Password.getText().toString())) {
            Password.setError("Password must be at least 6 characters");
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidName(String name) {
        //put your name validation here
        return true;
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

}
*/
