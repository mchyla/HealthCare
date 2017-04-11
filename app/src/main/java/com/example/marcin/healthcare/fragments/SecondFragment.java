package com.example.marcin.healthcare.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcin.healthcare.R;
import com.example.marcin.healthcare.model.Kid;
import com.example.marcin.healthcare.repository.OrmLiteKidRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.marcin.healthcare.R.id.textAddress;
import static com.example.marcin.healthcare.R.id.textName;

/**
 * Created by Marcin on 15.03.2017.
 */

public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.second_fragment, container, false);

        Button addPlaceButton = (Button) rootView.findViewById(R.id.addPlaceButton);
        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
               // Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
                Kid kid = new Kid();
                EditText editText = (EditText) rootView.findViewById(textName);
                EditText editAddress = (EditText) rootView.findViewById(textAddress);
                kid.setName(editText.getText().toString());
                kid.setLastName(editAddress.getText().toString());

                try {
                    //OrmLiteKidRepository.addKid(getActivity().getApplicationContext(), kid);
                    OrmLiteKidRepository.addKid(getActivity(), kid);

                    List<Kid> kidArrayList = OrmLiteKidRepository.findAll(getActivity());

                    Toast.makeText(getActivity(), kidArrayList.get(0).getLastName(),
                            Toast.LENGTH_LONG).show();
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
