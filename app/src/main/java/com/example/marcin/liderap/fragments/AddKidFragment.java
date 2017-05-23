package com.example.marcin.liderap.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
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
import com.example.marcin.liderap.model.Kid;
import com.example.marcin.liderap.repository.OrmLiteKidRepository;

import java.sql.SQLException;

import static com.example.marcin.liderap.R.id.textCollege;
import static com.example.marcin.liderap.R.id.textLastName;

/**
 * Created by Marcin on 15.03.2017.
 */

public class AddKidFragment extends Fragment {

    Kid model = null;
    EditText txtName = null;
    EditText txtLastName = null;
    EditText txtCollege = null;
    EditText txtNameChanged = null;
    EditText txtLastNameChanged = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.add_kid_fragment, container, false);
        setHasOptionsMenu(true);
        Button addPlaceButton = (Button) rootView.findViewById(R.id.addKidButton);
        Button cancelButton = (Button) rootView.findViewById(R.id.cancelAddKidButton);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dodaj/edytuj dziecko");
        //FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Add kid", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        Bundle bundle = getArguments();
        //Kid division= (Kid) bundle.getSerializable("kidToEdit");

        try {
            model = (Kid) bundle.getSerializable("kidToEdit");
        } catch (NullPointerException e) {
            model = null;
        }

        txtName = (EditText) rootView.findViewById(R.id.textName);
        txtNameChanged = (EditText) rootView.findViewById((R.id.textNameChanged));
        txtLastName = (EditText) rootView.findViewById(textLastName);
        txtLastNameChanged = (EditText) rootView.findViewById((R.id.textLastNameChanged));
        txtCollege = (EditText) rootView.findViewById(textCollege);

        if (model == null) {

        } else {
            txtName.setText(model.getName());
            txtNameChanged.setText(model.getNameChanged());
            txtLastName.setText(model.getLastName());
            txtLastNameChanged.setText(model.getLastNameChanged());
            txtCollege.setText(model.getCollege());

        }

        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    FragmentManager ft = getActivity().getFragmentManager();
                    if (model != null) {
                        model.setName(txtName.getText().toString());
                        model.setNameChanged(txtNameChanged.getText().toString());
                        model.setLastName(txtLastName.getText().toString());
                        model.setLastNameChanged(txtLastNameChanged.getText().toString());
                        model.setCollege(txtCollege.getText().toString());
                        OrmLiteKidRepository.updateKid(getActivity(), model);
                        ft.beginTransaction().replace(R.id.content_frame, new KidManagementFragment()).addToBackStack("fragBack").commit();
                    } else {
                        Kid kid = new Kid();
                        kid.setName(txtName.getText().toString());
                        kid.setNameChanged(txtNameChanged.getText().toString());
                        kid.setLastName(txtLastName.getText().toString());
                        kid.setLastNameChanged(txtLastNameChanged.getText().toString());
                        kid.setCollege(txtCollege.getText().toString());
                        OrmLiteKidRepository.addKid(getActivity(), kid);
                        ft.beginTransaction().replace(R.id.content_frame, new KidManagementFragment()).addToBackStack("fragBack").commit();
                    }
                } catch (SQLException e) {
                    Toast.makeText(getActivity(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

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
