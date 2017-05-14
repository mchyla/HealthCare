package com.example.marcin.liderap.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.marcin.liderap.R;

import java.util.Calendar;

/**
 * Created by mchyl on 14/05/2017.
 */

public class MeetingStartTimePickerFragment  extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        // Create a new instance of TimePickerDialog and return it

        return new TimePickerDialog(getActivity(), this, hour, minute,
                true);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        EditText startHour = (EditText) getActivity().findViewById(R.id.textMeetingTime);


        boolean bHourOfDay = hourOfDay <= 9;
        boolean bMinute = minute <= 9;

        if(bHourOfDay && bMinute){
            startHour.setText(0 +""+ hourOfDay + ":" + 0 + minute);
        } else if (bHourOfDay) {
            startHour.setText(0 +""+ hourOfDay + ":" + minute);
        } else if (bMinute){
            startHour.setText(hourOfDay + ":" + 0 + minute);
        } else {
            startHour.setText(hourOfDay + ":" + minute);
        }
    }
}
