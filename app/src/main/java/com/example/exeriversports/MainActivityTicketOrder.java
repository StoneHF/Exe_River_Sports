package com.example.exeriversports;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityTicketOrder extends AppCompatActivity {
    private Button PurchaseButton;
    private Button btnHome;

    double totalCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ticket_order);

        AdultTicket adultTicket = new AdultTicket();
        double adultTicketPrice = adultTicket.getPrice();

        ChildTicket childTicket = new ChildTicket();
        double childTicketPrice = childTicket.getPrice();

        TextView adultTicketPriceTextView = (TextView) findViewById(R.id.adult_ticket_price);
        TextView childTicketPriceTextView = (TextView) findViewById(R.id.child_ticket_price);

        adultTicketPriceTextView.setText(adultTicket.getTicketInformation());
        childTicketPriceTextView.setText(childTicket.getTicketInformation());




        Spinner spinnerA = (Spinner) findViewById(R.id.spinnerAdult);
        Spinner spinnerC = (Spinner) findViewById(R.id.spinnerChild);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterA = ArrayAdapter.createFromResource(this,
                R.array.ticket_quantity_options, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterC = ArrayAdapter.createFromResource(this,
                R.array.ticket_quantity_options, android.R.layout.simple_spinner_item);



// Specify the layout to use when the list of choices appears
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinnerA.setAdapter(adapterA);
        spinnerC.setAdapter(adapterC);

        Button purchaseButton = (Button) findViewById(R.id.btnPurchase);
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked goes here
                // Get selected quantity of adult tickets
                int selectedAdultQuantity = Integer.parseInt(spinnerA.getSelectedItem().toString());
                // Multiply with adult ticket price and add to total cost
                totalCost += selectedAdultQuantity * adultTicket.getPrice();

                // Get selected quantity of child tickets
                int selectedChildQuantity = Integer.parseInt(spinnerC.getSelectedItem().toString());
                // Multiply with child ticket price and add to total cost
                totalCost += selectedChildQuantity * childTicket.getPrice();





                // Update total cost text view
                TextView totalCostTextView = (TextView) findViewById(R.id.total_cost);
                totalCostTextView.setText("Total Cost: £" + totalCost);

                Toast.makeText(getApplicationContext(), "Thank you for ordering your ticket. Your total cost is £" + totalCost, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivityTicketOrder.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityTicketOrder.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}



