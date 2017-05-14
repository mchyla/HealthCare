package com.example.marcin.liderap.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.marcin.liderap.R;

import java.util.Calendar;

import static com.example.marcin.liderap.R.string.eventDate;

/**
 * Created by mchyl on 11/05/2017.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        EditText eventDate = (EditText) getActivity().findViewById(R.id.textEventDate);
        month+=1;

        boolean bday = day<= 9;
        boolean bmonth = month <= 9;

        if(bday && bmonth){
            eventDate.setText(0 +""+ day + "." + 0 + month + "." + year);
        } else if (bday) {
            eventDate.setText(0 +""+ day + "." + month + "." + year);
        } else if (bmonth){
            eventDate.setText(day + "." + 0 + month + "." + year);
        } else {
            eventDate.setText(day + "." + month + "." + year);
        }


        /*if(month <=9) {
            if(day <= 9) {
                eventDate.setText(0 + day + "." + 0 + month + "." + year);
            } else {
                eventDate.setText(day + "." + 0 + month + "." + year);
            }
        } else {
            if(day <= 9) {
                eventDate.setText(0 + day + "." + month + "." + year);
            } else {
                eventDate.setText(day + "." + month + "." + year);
            }
        }*/
    }
}
