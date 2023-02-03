package com.example.exeriversports;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityUserPage extends AppCompatActivity {

    private Button btnHome;
    private Button btnDeleteAccount;
    private Button LogoutButton;
    private TextView nameTextView;
    private TextView emailTextView;
    private SQLiteDatabase myERSdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_page);

        // Initialize the delete account button
        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
        // Set a click listener for the delete account button
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the member ID from shared preferences
                int memberId = getSharedPreferences("siteMembers", MODE_PRIVATE).getInt("member_id", 0);
                // Check if the member ID is found
                if (memberId == 0) {
                    // Display a toast message indicating the lack of member ID
                    Toast.makeText(MainActivityUserPage.this, "No member ID found", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Delete the member from the database
                myERSdb.delete("tbl_members", "member_id = ?", new String[]{String.valueOf(memberId)});
                // Remove the member ID from shared preferences
                getSharedPreferences("siteMembers", MODE_PRIVATE)
                        .edit()
                        .remove("member_id")
                        .apply();
                // Display a toast message indicating the success of the operation
                Toast.makeText(MainActivityUserPage.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                // Close the current activity
                finish();
                // Start the MainActivity
                startActivity(new Intent(MainActivityUserPage.this, MainActivity.class));
            }
        });

        // Initialize the home button
        btnHome = (Button) findViewById(R.id.btnHome);
        // Set a click listener for the home button
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the MainActivity2
                Intent intent = new Intent(MainActivityUserPage.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // Initialize the logout button
        LogoutButton = (Button) findViewById(R.id.btnLogout);
        // Set a click listener for the logout button
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the shared preferences
                SharedPreferences sharedPreferences = getSharedPreferences("siteMembers", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                // Start the MainActivity
                Intent intent = new Intent(MainActivityUserPage.this, MainActivity.class);
                startActivity(intent);
                // Close the current activity
                finish();
            }
        });

        // Initialize the TextViews
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);

        // Initialize the database
        myERSdb = openOrCreateDatabase("siteMembers", MODE_PRIVATE, null);

        // Get the member ID from the shared preferences
        int memberId = getSharedPreferences("siteMembers", MODE_PRIVATE).getInt("member_id", 0);

        if (memberId == 0) {
            Toast.makeText(this, "No member ID found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a query to select the user's information
        String query = "SELECT fldFName, fldEmail FROM tbl_members WHERE member_id = " + memberId;

        // Execute the query and get the result
        Cursor cursor = myERSdb.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No member found with id " + memberId, Toast.LENGTH_SHORT).show();
            return;
        }

        // Move to the first result
        cursor.moveToFirst();

        // Get the user's name and email
        String name = cursor.getString(cursor.getColumnIndex("fldFName"));
        String email = cursor.getString(cursor.getColumnIndex("fldEmail"));

        // Display the user's information
        nameTextView.setText(name);
        emailTextView.setText(email);

        cursor.close();
    }
}

