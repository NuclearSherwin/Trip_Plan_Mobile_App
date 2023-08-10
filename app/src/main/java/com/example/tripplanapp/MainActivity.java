package com.example.tripplanapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.EntityIterator;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText NameOfTripInput;
    EditText Destination;

    EditText DateOfTrip;

    Button submitButton;

    RadioButton radioButtonOption1;
    RadioButton radioButtonOption2;

    EditText Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NameOfTripInput = findViewById(R.id.idTextInputName);
        Destination = findViewById(R.id.idTextInputDestination);
        DateOfTrip = findViewById(R.id.idTextInputDateTrip);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioButtonOption1 = findViewById(R.id.radioButtonOption1);
        radioButtonOption2 = findViewById(R.id.radioButtonOption2);
        Description = findViewById(R.id.idInputDescription);


        submitButton = findViewById(R.id.button);


        // create intent for Info Activity
        Intent i = new Intent(this, InfoDisplay.class);



        final TextInputEditText editTextDatePicker = findViewById(R.id.idTextInputDateTrip);
        TextView textOpenDatePicker = findViewById(R.id.textView8);

        final DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setInputDateEditText(editTextDatePicker);

        textOpenDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });




        // for submit action
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get info of the trip from input
                String tripName = NameOfTripInput.getText().toString();
                String destination = Destination.getText().toString();
                String dateOfStrip = DateOfTrip.getText().toString();
                String description = Description.getText().toString();


                // Check if a radio button is selected
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    // No radio button is selected, show an error message
                    radioButtonOption1.setError("Please select an option");
                    radioButtonOption2.setError("Please select an option");
                    return;
                } else {
                    // At least one radio button is selected, clear any previous error
                    radioButtonOption1.setError(null);
                    radioButtonOption2.setError(null);
                }



                // put the value into extras
                i.putExtra(InfoDisplay.TRIPNAME, tripName);
                i.putExtra(InfoDisplay.DESTINATION, destination);
                i.putExtra(InfoDisplay.DATEOFTRIP, dateOfStrip);
                i.putExtra(InfoDisplay.DESCRIPTION, description);

                // Get the selected radio button's text and put it in extras
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedOption = selectedRadioButton.getText().toString();
                i.putExtra(InfoDisplay.RISKASSESSMENT, selectedOption);

                // start Info activity
                startActivity(i);
            }
        });




    }


}

