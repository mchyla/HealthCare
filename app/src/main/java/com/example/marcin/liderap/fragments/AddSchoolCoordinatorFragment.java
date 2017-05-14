package com.example.marcin.liderap.fragments;

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

import com.example.marcin.liderap.R;
import com.example.marcin.liderap.model.SchoolCoordinator;
import com.example.marcin.liderap.repository.SchoolCoordinatorRepository;

import java.sql.SQLException;

import static com.example.marcin.liderap.R.id.textCollege;
import static com.example.marcin.liderap.R.id.textEmail;
import static com.example.marcin.liderap.R.id.textLastName;
import static com.example.marcin.liderap.R.id.textHours;

/**
 * Created by mchyl on 11/04/2017.
 */

public class AddSchoolCoordinatorFragment extends Fragment {

    SchoolCoordinator model = null;
    EditText txtName = null;
    EditText txtLastName = null;
    EditText txtEmail = null;
    EditText txtCollege = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.add_school_coordinator_fragment, container, false);
        Button addSCButton = (Button) rootView.findViewById(R.id.addSchoolCoordinatorButton);

        txtName = (EditText) rootView.findViewById(textHours);
        txtLastName = (EditText) rootView.findViewById(textLastName);
        txtEmail = (EditText) rootView.findViewById(textEmail);
        txtCollege = (EditText) rootView.findViewById(textCollege);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Dodaj KS'a");

        Bundle bundle = getArguments();
        //Kid division= (Kid) bundle.getSerializable("kidToEdit");

        try {
            model = (SchoolCoordinator) bundle.getSerializable("schoolCoordinatorToEdit");
            Log.e("Kid TEST", "" + model.getName());
        } catch (NullPointerException e) {
            model = null;
            Log.e("Kid TEST", "" + e.getMessage());
        }

        if (model == null) {

        } else {
            txtName.setText(model.getName());
            txtLastName.setText(model.getLastName());
            txtEmail.setText(model.getMail());
            txtCollege.setText(model.getCollege());

        }

        addSCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    FragmentManager ft = getActivity().getFragmentManager();
                    if (model != null) {

                        model.setName(txtName.getText().toString());
                        model.setLastName(txtLastName.getText().toString());
                        model.setMail(txtCollege.getText().toString());
                        model.setCollege(txtCollege.getText().toString());
                        SchoolCoordinatorRepository.updateSchoolCoordinator(getActivity(), model);
                        ft.beginTransaction().replace(R.id.content_frame, new SCManagementFragment()).addToBackStack("fragBack").commit();
                    } else {

                        final String textName = txtName.getText().toString();
                        final String textLastName = txtLastName.getText().toString();
                        final String textEmail = txtEmail.getText().toString();
                        final String textCollege = txtCollege.getText().toString();

                        if (textName.equals("") ||
                                textLastName.equals("") ||
                                textEmail.equals("") ||
                                textCollege.equals("")) {

                            Toast.makeText(getActivity(), R.string.fillAllField,
                                    Toast.LENGTH_LONG).show();

                        } else {
                            SchoolCoordinator schoolCoordinator = new SchoolCoordinator(textName, textLastName, textEmail, textCollege);

                            SchoolCoordinatorRepository.addSchoolCoordinator(getActivity(), schoolCoordinator);
                            ft.beginTransaction().replace(R.id.content_frame, new SCManagementFragment()).addToBackStack("fragBack").commit();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }
}
