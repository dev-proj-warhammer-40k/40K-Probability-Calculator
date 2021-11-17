package com.example.warhammer40k;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity  {



    //Declaring variables for Dark/Light Mode
    private RadioGroup radioGroup;
    private TextView select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNextButton();

        radioGroup = findViewById(R.id.darkLightMode);
        select = findViewById(R.id.textView9);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // on radio button check change
                switch (checkedId) {
                    case R.id.lightMode:
                        // checking the radio button with id.
                        // setting the text to text view as light mode.
                        select.setText("Light Theme");
                        // changing the theme to light mode.
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case R.id.darkMode:
                        // dark radio button is selected
                        // setting dark theme text to our text view.
                        select.setText("Dark Theme");
                        // changing the theme to dark mode.
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                }
            }
        });


    }


    private void configureNextButton(){
        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
                    Log.i("configureNextButton", "9th edition selected...");
            }
        });

        Button termButton = (Button) findViewById(R.id.termButton);
        termButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TermsPage.class));
            }
        });
        }



}

