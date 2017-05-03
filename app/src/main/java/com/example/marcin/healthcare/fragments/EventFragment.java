package com.example.marcin.healthcare.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcin.healthcare.R;
import com.example.marcin.healthcare.model.Leader;
import com.example.marcin.healthcare.repository.LeaderRepository;
import com.example.marcin.healthcare.utils.GenerateDocument;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.marcin.healthcare.R.id.text;
import static com.example.marcin.healthcare.R.id.textBuilding;
import static com.example.marcin.healthcare.R.id.textLastName;

/**
 * Created by mchyl on 02/05/2017.
 */

public class EventFragment extends Fragment {

    EditText textHours = null;
    EditText building = null;
    EditText route = null;
    EditText eventDate = null;
    EditText startHour = null;
    EditText endHour = null;
    Leader leader = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.generate_doc_layout, container, false);
        setHasOptionsMenu(true);
        Button enerateEvent = (Button) rootView.findViewById(R.id.generateEvent);
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

        enerateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (building.getText().length() == 0) {
                        building.setError("Wydarzenie musi się gdzieś odbyć.");
                    } else if (route.getText().length() == 0) {
                        route.setError("Podaj adres.");
                    } else if (textHours.getText().length() == 0) {
                        textHours.setError("W jakim czasie odbędzie się wydarzenie?");
                    } else if (eventDate.getText().length() == 0){
                        eventDate.setError("W jaki dzień odbędzie się wydarzenie?");
                    } else {
                        GenerateDocument.genrateDocument(getContext(), building.getText().toString(),
                                leader.getCityChanged(), route.getText().toString(),
                                textHours.getText().toString(), eventDate.getText().toString(),
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

        return rootView;
    }


}