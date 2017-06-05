package com.example.marcin.liderap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcin.liderap.model.Leader;
import com.example.marcin.liderap.repository.LeaderRepository;

import java.sql.SQLException;

/**
 * Created by mchyl on 04/06/2017.
 */

public class SetNewPin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_question);
        //TextView headTextView = (TextView)findViewById(R.id.headTextView);
        // headTextView.setText(R.string.toRegister);

        Button cancelSetNewPinButton = (Button) findViewById(R.id.cancelSetNewPinButton);
        Button setNewPinButton = (Button) findViewById(R.id.confirmSetNewPinButton);

        final TextView confirmSetNewPin = (TextView) findViewById(R.id.textConfirmSetNewPin);
        final TextView setNewPin = (TextView) findViewById(R.id.textSetNewPin);

        setNewPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newConfirmPin = confirmSetNewPin.getText().toString();
                String newPin = setNewPin.getText().toString();

                Leader leader = null;
                try {
                    leader = LeaderRepository.findAll(getApplicationContext()).get(0);
                    if (newConfirmPin.length() == 0) {
                        confirmSetNewPin.setError("Pol jest wymagane.");
                    } else if (newPin.length() == 0) {
                        setNewPin.setError("Pol jest wymagane.");
                    } else {
                        if (newConfirmPin.equals(newPin)) {
                            leader.setPin(Integer.valueOf(newConfirmPin));
                        } else {
                            Toast.makeText(getApplicationContext(), "Pin i potwierdzenie różnią się od siebie.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "Exception "+e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        cancelSetNewPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
