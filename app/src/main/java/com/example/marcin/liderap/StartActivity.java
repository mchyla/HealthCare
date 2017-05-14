package com.example.marcin.liderap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.marcin.liderap.repository.LeaderRepository;

import java.sql.SQLException;

/**
 * Created by mchyl on 27/04/2017.
 */

public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        Button  registerButton = (Button) findViewById(R.id.buttonRegister);
        Button  loginButton = (Button) findViewById(R.id.buttonLogin);

        registerButton.setAlpha(1.0f);
        loginButton.setAlpha(1.0f);

        try {
            //Leader leader = null;
           // boolean isNoOneInDatabase = false;
           // try {
                int size = LeaderRepository.findAll(getApplicationContext()).size();
           //     isNoOneInDatabase = false;
           // } catch(IndexOutOfBoundsException indexException){
           //    isNoOneInDatabase = true;
           // }
            if(size <= 0){
                registerButton.setEnabled(true);
                loginButton.setEnabled(false);
            } else {
                registerButton.setEnabled(false);
                loginButton.setEnabled(true);
            }



            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(StartActivity.this, RegisterActivity.class));
                }
            });

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(StartActivity.this, AuthenticationActivity.class));
                }
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}
