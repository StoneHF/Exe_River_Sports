package com.example.exeriversports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityUserPage extends AppCompatActivity {

    private Button btnHome;
    private Button btnDeleteAccount;

    private TextView nameTextView;
    private TextView emailTextView;
    private SQLiteDatabase myERSdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_page);



        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int memberId = getSharedPreferences("siteMembers", MODE_PRIVATE).getInt("member_id", 0);
                if (memberId == 0) {
                    Toast.makeText(MainActivityUserPage.this, "No member ID found", Toast.LENGTH_SHORT).show();
                    return;
                }
                myERSdb.delete("tbl_members", "member_id = ?", new String[]{String.valueOf(memberId)});
                getSharedPreferences("siteMembers", MODE_PRIVATE)
                        .edit()
                        .remove("member_id")
                        .apply();
                Toast.makeText(MainActivityUserPage.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(MainActivityUserPage.this, MainActivity2.class));


            }
        });

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityUserPage.this, MainActivity2.class);
                startActivity(intent);
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

