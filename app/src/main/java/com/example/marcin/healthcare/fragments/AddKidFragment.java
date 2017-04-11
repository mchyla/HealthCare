package com.example.marcin.healthcare.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcin.healthcare.MainActivity;
import com.example.marcin.healthcare.R;
import com.example.marcin.healthcare.model.Kid;
import com.example.marcin.healthcare.repository.OrmLiteKidRepository;

import org.apache.commons.lang3.ObjectUtils;

import java.sql.SQLException;

import static com.example.marcin.healthcare.R.id.textCollege;
import static com.example.marcin.healthcare.R.id.textLastName;
import static com.example.marcin.healthcare.R.id.textName;

/**
 * Created by Marcin on 15.03.2017.
 */

public class AddKidFragment extends Fragment {

    Kid model = null;
    EditText txtName = null;
    EditText txtLastName = null;
    EditText txtCollege = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.add_kid_fragment, container, false);
        setHasOptionsMenu(true);
        Button addPlaceButton = (Button) rootView.findViewById(R.id.addKidButton);

        Bundle bundle = getArguments();
        //Kid division= (Kid) bundle.getSerializable("kidToEdit");

        try {
            model = (Kid) bundle.getSerializable("kidToEdit");
            Log.e("Kid TEST", "" + model.getName());
        }catch(NullPointerException e){
            model = null;
            Log.e("Kid TEST", "" + e.getMessage());
        }

        txtName = (EditText) rootView.findViewById(textName);
        txtLastName = (EditText) rootView.findViewById(textLastName);
        txtCollege = (EditText) rootView.findViewById(textCollege);

        if (model == null) {

        } else {
            txtName.setText(model.getName());
            txtLastName.setText(model.getLastName());
            txtCollege.setText(model.getCollege());

        }

        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    FragmentManager ft =  getActivity().getFragmentManager();
                    if (model != null) {
                        model.setName(txtName.getText().toString());
                        model.setLastName(txtLastName.getText().toString());
                        model.setCollege(txtCollege.getText().toString());
                        OrmLiteKidRepository.updateKid(getContext(), model);
                        ft.beginTransaction().replace(R.id.content_frame, new KidManagementFragment()).addToBackStack("fragBack").commit();
                    } else {
                        Kid kid = new Kid();
                        kid.setName(txtName.getText().toString());
                        kid.setLastName(txtLastName.getText().toString());
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

        return rootView;
    }


}
