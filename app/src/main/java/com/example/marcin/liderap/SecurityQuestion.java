package com.example.marcin.liderap;

import android.content.Intent;
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

public class SecurityQuestion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_question);
        //TextView headTextView = (TextView)findViewById(R.id.headTextView);
        // headTextView.setText(R.string.toRegister);

        Button cancelSetNewPinButton = (Button) findViewById(R.id.cancelChangePinButton);
        Button setNewPinButton = (Button) findViewById(R.id.changePinButton);

        final TextView securityAnswerText = (TextView) findViewById(R.id.securityAnswerText);
        final TextView securityQuestionText = (TextView) findViewById(R.id.securityQuestionText);

        try {
            Leader leader = LeaderRepository.findAll(getApplicationContext()).get(0);
            securityQuestionText.setText(leader.getSecurityQuestion());
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Exception " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        setNewPinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String securityAnswer = securityAnswerText.getText().toString();
                String securityQuestion = securityQuestionText.getText().toString();

                Leader leader = null;
                try {
                    leader = LeaderRepository.findAll(getApplicationContext()).get(0);
                    securityQuestionText.setText(leader.getSecurityQuestion());
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "Exception " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                if (securityAnswer.length() == 0) {
                    securityAnswerText.setError("Pol jest wymagane.");
                } else {
                    if (securityAnswer.equals(leader.getSecurityAnswer())) {
                        startActivity(new Intent(SecurityQuestion.this, SetNewPin.class));
                    } else {
                        securityAnswerText.setError("Odpowiedź nieprawidłowa!");
                    }
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
