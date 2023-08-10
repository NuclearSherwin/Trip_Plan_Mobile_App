package com.example.tripplanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoDisplay extends AppCompatActivity {

    public static final String TRIPNAME = "tripName";
    public static final String DESTINATION = "destination";
    public static final String DATEOFTRIP = "dateOfTrip";

    public static final String RISKASSESSMENT = "RiskAssessment";

    public static final String DESCRIPTION = "Discription";


    TextView tripNameDisplay;
    TextView destinationDisplay;

    TextView dateOfTheTripDisplay;

    TextView riskAssessmentDisplay;

    TextView descriptionDisplay;

    TextView errorMessageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_display);

        tripNameDisplay = findViewById(R.id.trip_name_display);
        destinationDisplay = findViewById(R.id.destination_display);
        dateOfTheTripDisplay = findViewById(R.id.date_of_trip);
        riskAssessmentDisplay = findViewById(R.id.riskAssessment);
        descriptionDisplay = findViewById(R.id.DescriptionDisplay);
        errorMessageDisplay = findViewById(R.id.ErrorMessage);



        // get the extras from the current activity
        Bundle extras = getIntent().getExtras();

        if (extras == null){
            errorMessageDisplay.setText("ERROR ...");
        }

        // get the value from the current extras
        String tripName = extras.getString(TRIPNAME);
        String destination = extras.getString(DESTINATION);
        String dateOfTheTrip = extras.getString(DATEOFTRIP);
        String riskAssessment = extras.getString(RISKASSESSMENT);
        String description = extras.getString(DESCRIPTION);

        boolean isAnyFieldEmpty =
                   tripName.trim().isEmpty()
                || destination.trim().isEmpty()
                || dateOfTheTrip.trim().isEmpty()
                || riskAssessment.trim().isEmpty()
                || description.trim().isEmpty();

        // Check Trip Name
        if (isAnyFieldEmpty) {
            // Display error if any field is empty and hide the other views
            errorMessageDisplay.setTextColor(Color.RED);
            errorMessageDisplay.setText("Please fill out all the fields!");


            // Hide other views
            tripNameDisplay.setVisibility(View.GONE);
            destinationDisplay.setVisibility(View.GONE);
            dateOfTheTripDisplay.setVisibility(View.GONE);
            riskAssessmentDisplay.setVisibility(View.GONE);
            descriptionDisplay.setVisibility(View.GONE);
        }
        else {
            // If all values are filled, you can display the other views
            tripNameDisplay.setText("Name: " + tripName);
            destinationDisplay.setText("Destination: " + destination);
            dateOfTheTripDisplay.setText("Date of the Trip : " + dateOfTheTrip);
            riskAssessmentDisplay.setText("Risk Assessment : " + riskAssessment);
            descriptionDisplay.setText("Description: " + description);

            // Set the visibility of other views back to View.VISIBLE
            tripNameDisplay.setVisibility(View.VISIBLE);
            destinationDisplay.setVisibility(View.VISIBLE);
            dateOfTheTripDisplay.setVisibility(View.VISIBLE);
            riskAssessmentDisplay.setVisibility(View.VISIBLE);
            descriptionDisplay.setVisibility(View.VISIBLE);
            errorMessageDisplay.setVisibility(View.GONE);
        }


        // Close action to navigate back to the main activity
        TextView closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate back to main activity
                Intent intent = new Intent(InfoDisplay.this, MainActivity.class);
                startActivity(intent);
                finish(); // close the activity if needed
            }
        });

    }
}