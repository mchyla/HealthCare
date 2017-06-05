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
 * Created by mchyl on 27/04/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        TextView headTextView = (TextView)findViewById(R.id.headTextView);
        headTextView.setText(R.string.toRegister);

        Button buttonRegister = (Button) findViewById(R.id.addSchoolCoordinatorButton);

        final TextView nameTextView = (TextView) findViewById(R.id.textLeaderName);
        final TextView lastNameTextView = (TextView) findViewById(R.id.textLeaderLastName);
        final TextView emailTextView = (TextView) findViewById(R.id.textLeaderEmail);
        final TextView pinTextView = (TextView) findViewById(R.id.textLeaderPin);
        final TextView confirmPinTextView = (TextView) findViewById(R.id.textLeaderConfirmPin);
        final TextView phoneTextView = (TextView) findViewById(R.id.textLeaderTelephone);
        final TextView cityTextView = (TextView) findViewById(R.id.textLeaderCity);
        final TextView cityChangedTextView = (TextView) findViewById(R.id.textLeaderCityChanged) ;
        final TextView collageTextView = (TextView) findViewById(R.id.textLeaderCollege);
        final TextView securityQuestionTextView = (TextView) findViewById(R.id.textSecurityQuestion);
        final TextView securityAnswerTextView = (TextView) findViewById(R.id.textSecurityAnswer);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameTextView.getText().toString();
                String lastName = lastNameTextView.getText().toString();
                String email = emailTextView.getText().toString();
                String pin = pinTextView.getText().toString();
                String confirmPin = confirmPinTextView.getText().toString();
                String phone = phoneTextView.getText().toString();
                String city = cityTextView.getText().toString();
                String cityChanged = cityChangedTextView.getText().toString();
                String collage = collageTextView.getText().toString();
                String securityQuestion = securityQuestionTextView.getText().toString();
                String securityAnswer = securityAnswerTextView.getText().toString();


                if(name.length() == 0){
                    nameTextView.setError("Imie jest wymagane.");
                } else if (lastName.length() == 0) {
                    lastNameTextView.setError("Nazwisko jest wymagane.");
                } else if (email.length() == 0) {
                    emailTextView.setError("Email jest wymagany.");
                } else if (phone.length() == 0) {
                    phoneTextView.setError("Telefon jest wymagany.");
                } else if (city.length() == 0) {
                    cityTextView.setError("Miasto jest wymagane.");
                } else if (cityChanged.length() == 0) {
                    cityChangedTextView.setError("Odmiana nazwy miasta jest wymagana.");
                } else if (collage.length() == 0) {
                    collageTextView.setError("Numer kolegium jest wymagany.");
                } else if (pin.length() == 0) {
                    pinTextView.setError("Pin jest wymagany.");
                } else if (confirmPin.length() == 0) {
                    confirmPinTextView.setError("Potwierdzenie pinu jest wymagane.");
                } else if (securityQuestion.length() == 0) {
                    securityQuestionTextView.setError("To pole jest wymagane.");
                } else if (securityQuestion.length() == 0) {
                    securityAnswerTextView.setError("To pole jest wymagane.");
                } else {
                    if (email.contains("@") && email.contains(".")) {
                        if (pin.equals(confirmPin)) {

                            Leader leader = new Leader();
                            leader.setName(name);
                            leader.setLastName(lastName);
                            leader.setEmail(email);
                            leader.setPin(Integer.valueOf(pin));
                            leader.setPhone(phone);
                            leader.setCity(city);
                            leader.setCityChanged(cityChanged);
                            leader.setCollage(collage);
                            leader.setSecurityQuestion(securityQuestion);
                            leader.setSecurityAnswer(securityAnswer);

                            try {
                                LeaderRepository.addLeader(getApplicationContext(), leader);
                                Toast.makeText(getApplicationContext(), R.string.success,
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, StartActivity.class));
                            } catch (SQLException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            pinTextView.setError("Pin niepoprawnie potwierdzony.");
                            confirmPinTextView.setError("Pin niepoprawnie potwierdzony.");
                        }
                    } else {
                        emailTextView.setError("Proszę podać poprawny email.");
                    }

                }

               // startActivity(new Intent(RegisterActivity.this, StartActivity.class));
            }
        });


    }



}
