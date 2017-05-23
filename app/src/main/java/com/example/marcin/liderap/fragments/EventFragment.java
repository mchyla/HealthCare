package com.example.marcin.liderap.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcin.liderap.R;
import com.example.marcin.liderap.model.Leader;
import com.example.marcin.liderap.repository.LeaderRepository;
import com.example.marcin.liderap.utils.GenerateDocument;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by mchyl on 02/05/2017.
 */

public class EventFragment extends Fragment {

    private EditText textHours = null;
    private EditText building = null;
    private EditText route = null;
    private EditText eventDate = null;
    private EditText startHour = null;
    private EditText endHour = null;
    private Leader leader = null;
    private EditText meetingTime = null;
    private EditText meetingTimeEnd = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.generate_doc_layout, container, false);
        setHasOptionsMenu(true);
        Button enerateEvent = (Button) rootView.findViewById(R.id.generateEvent);
        Button cancelButton = (Button) rootView.findViewById(R.id.cancelEventCreationButton);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Dodaj wydarzenie");

        Bundle bundle = getArguments();
        try {
            leader = LeaderRepository.findAll(getActivity().getApplicationContext()).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Kid division= (Kid) bundle.getSerializable("kidToEdit");

        textHours = (EditText) rootView.findViewById(R.id.textHours);
        building = (EditText) rootView.findViewById(R.id.textBuilding);
        route = (EditText) rootView.findViewById(R.id.textRoute);
        eventDate = (EditText) rootView.findViewById(R.id.textEventDate);
        startHour = (EditText) rootView.findViewById(R.id.textStartHoursInput);
        endHour = (EditText) rootView.findViewById(R.id.textEndHoursInput);
        meetingTime = (EditText) rootView.findViewById(R.id.textMeetingTime);
        meetingTimeEnd = (EditText) rootView.findViewById(R.id.textMeetingEndTime);

        enerateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (building.getText().length() == 0) {
                        building.setError("Wydarzenie musi się gdzieś odbyć.");
                    } else if (route.getText().length() == 0) {
                        route.setError("Podaj adres.");
                    } else if (eventDate.getText().length() == 0){
                        eventDate.setError("W jaki dzień odbędzie się wydarzenie?");
                    } else if (meetingTime.getText().length() == 0){
                        meetingTime.setError("O której zacznie się wydarzenie?");
                    } else if (meetingTimeEnd.getText().length() == 0){
                        meetingTimeEnd.setError("O której skończy się wydarzenie?");
                    } else {

                        String meeting = meetingTime.getText().toString() + " "
                                + textHours.getText().toString() + " "
                                + meetingTimeEnd.getText().toString();

                        GenerateDocument.genrateDocument(getActivity(), building.getText().toString(),
                                leader.getCityChanged(), route.getText().toString(),
                                meeting, eventDate.getText().toString(),
                                startHour.getText().toString(), endHour.getText().toString(),
                                leader.getCollage(), leader.getPhone());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

//        eventDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog(v);
//            }
//        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getActivity().getFragmentManager().popBackStack();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }





}