package com.example.tripplanapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText inputDateEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle saveInstanceState) {
        // get the current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // make an instance of DatePickerDialog and return it
        return new DatePickerDialog(requireActivity(), this, year, month, day);
    }

    public void setInputDateEditText(TextInputEditText editTextToSet) {
        inputDateEditText = editTextToSet;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        // set the selected date to the TextInputEditText
        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        inputDateEditText.setText(selectedDate);
    }

}
