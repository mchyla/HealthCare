package com.example.marcin.liderap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcin.liderap.model.Leader;
import com.example.marcin.liderap.repository.LeaderRepository;

import java.sql.SQLException;

/**
 * Created by mchyl on 27/04/2017.
 */

public class AuthenticationActivity extends AppCompatActivity {

    public boolean isAuthenticated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.authentication);

        Button buttonConfirm = (Button) findViewById(R.id.buttonPin);
        final EditText pinText = (EditText) findViewById(R.id.pin);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Leader leader = LeaderRepository.findAll(getApplicationContext()).get(0);
                    //Pin pin = PinRepository.findAll(getApplicationContext()).get(0);
                    //if (leader.getPin() != null) {
                    if(pinText.getText().length() != 0) {
                        if (Integer.valueOf(pinText.getText().toString()) == leader.getPin()) {
                            isAuthenticated = true;
                            //TODO: go to main activity
                            startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
                        } else {
                            pinText.setError("Proszę podać poprawny pin.");
                            //  }
                        }
                    } else {
                        pinText.setError("Pin jest wymagany do autentykacji.");
                    }
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "SQLException "+e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Exception "+ex.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //TODO: activity to remind pin to email of user
        //FragmentManager fm = getFragmentManager();
        //fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).addToBackStack("main").commit();
    }
}
