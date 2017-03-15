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
import com.example.marcin.healthcare.model.Place;

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
                Place place = new Place();
                EditText editText = (EditText) rootView.findViewById(textName);
                EditText editAddress = (EditText) rootView.findViewById(textAddress);
                place.setName(editText.getText().toString());
                place.setAddress(editAddress.getText().toString());

                Toast.makeText(getActivity(), place.getName()+" "+place.getAddress(),
                        Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }


}
