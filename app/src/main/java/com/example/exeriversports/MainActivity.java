package com.example.exeriversports;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button SignupButton;
    private Button LoginButton;

    TextView nameview;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("siteMembers", MODE_PRIVATE);


        SignupButton = (Button) findViewById(R.id.btnSignupForm);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivitySignUp.class);
                startActivity(myIntent);
            }
        });

        // ValueFirstName
        // ValueEmail
        // ValuePassword
        // tvResultText

        nameview =(TextView)findViewById(R.id.tvResults);

        // Display the string
        String UserNameIn;
        String UserEmailIn;
        String UserPasswordIn;

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                UserNameIn = bundle.getString("ValueFirstName");
                UserEmailIn = bundle.getString("ValueEmail");
                UserPasswordIn = bundle.getString("ValuePassword");
            } else {
                UserNameIn = "";
                UserEmailIn = "";
                UserPasswordIn = "";
            }
        } else {
            UserNameIn = "";
            UserEmailIn = "";
            UserPasswordIn = "";
        }

        // nameview.setText(InputString);

        //create a database call
        createDatabase(UserNameIn,UserEmailIn,UserPasswordIn);

    }



    // a is for argument
    public void createDatabase(String aUserNameIn,String aUserEmailIn,String aUserPasswordIn){
        try{
            StringBuilder mySB = new StringBuilder();

            SQLiteDatabase myERSdb = this.openOrCreateDatabase("siteMembers",MODE_PRIVATE,null);

            myERSdb.execSQL("CREATE TABLE IF NOT EXISTS tbl_members (member_id INT(5)," +
                    " fldFName VARCHAR, fldEmail VARCHAR, fldPassword VARCHAR) ");

             /*
            myERSdb.execSQL("CREATE TABLE IF NOT EXISTS tbl_OrderedTickets (fldTicketID INT(5)," +
                    "member_ID INT) ");

            myERSdb.execSQL("CREATE TABLE IF NOT EXISTS tbl_sports(fldSportID INT(5)," +
                    " fldSportName VARCHAR) ");

            myERSdb.execSQL("CREATE TABLE IF NOT EXISTS tbl_matches (fldMatchID INT(5)," +
                    " fldLocation VARCHAR, fldDate VARCHAR, fldSportID INT)");



            myDB.execSQL("INSERT INTO tbl_members (member_id, fldFName, fldEmail, fldPassword)" +
                    "VALUES (00001, 'Harry', 'HLid@email.com, password')");
            */

            // Add a new record into database

            String SQLString = "SELECT * FROM tbl_members";
            Cursor myNewcursor = myERSdb.rawQuery(SQLString, null);

            Integer rowCount;
            rowCount = myNewcursor.getCount();

            myNewcursor.close();

            // Create an insert into database
            myERSdb.execSQL("INSERT INTO tbl_members (member_id, fldFname, fldEmail, fldPassword)" +
                    "VALUES ("+rowCount+",'"+aUserNameIn+"', '"+aUserEmailIn+"', '"+aUserPasswordIn+"' )");

            nameview.setText(" New Record Added");


            SQLString = "SELECT * FROM tbl_members";
            Cursor myCursor = myERSdb.rawQuery(SQLString,  null);

            int idIndex = myCursor.getColumnIndex("member_id");
            int nameIndex = myCursor.getColumnIndex("fldFname");
            int EmailIndex = myCursor.getColumnIndex("fldEmail");
            int PasswordIndex = myCursor.getColumnIndex("fldPassword");

            // move to first
            myCursor.moveToFirst();

            for (int i=0; i < myCursor.getCount(); i++) {
                // Log.i("id", Integer.toString(myCursor.getInt(idIndex)));
                // Log.i("name", myCursor.getString(nameIndex));
                // Log.i("Lname", myCursor.getString(lastNameIndex));

                mySB.append(Integer.toString(myCursor.getInt(idIndex)) + ",");
                mySB.append(myCursor.getString(nameIndex) + ",");
                mySB.append(myCursor.getString(EmailIndex) + ",");
                mySB.append(myCursor.getString(PasswordIndex) + "\n");


                myCursor.moveToNext();
            }

            String SqlResult = mySB.toString();
            Toast.makeText(getApplicationContext(), SqlResult,
                    Toast.LENGTH_LONG).show();


            myCursor.close();

            // myERSdb.execSQL("DROP TABLE IF EXISTS siteMembers");
            // wouldn't usually drop the database
            myERSdb.close();


        } catch(Exception e) {

            e.printStackTrace();

        }
        LoginButton = (Button) findViewById(R.id.btnLogin);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the email and password entered by the user
                EditText etLoginEmail = (EditText) findViewById(R.id.etLoginEmail);
                EditText etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
                String email = etLoginEmail.getText().toString();
                String password = etLoginPassword.getText().toString();

                // check if email and password fields are not empty
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_LONG).show();
                    return;
                }

                // query the database to check if a matching record exists
                SQLiteDatabase myERSdb = openOrCreateDatabase("siteMembers", MODE_PRIVATE, null);
                Cursor cursor = myERSdb.rawQuery("SELECT * FROM tbl_members WHERE fldEmail = ? AND fldPassword = ?", new String[] {email, password});

                if (cursor.moveToFirst()) {
                    // login successful
                    int member_id = cursor.getInt(0); // assuming the member_id column is the first column
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("member_id", member_id);
                    editor.apply();
                    // navigate to the MainActivity2
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                } else {
                    // login failed
                    Toast.makeText(MainActivity.this, "Incorrect email or password", Toast.LENGTH_LONG).show();
                }
                cursor.close();


            }




        }
        );
    }

}


























