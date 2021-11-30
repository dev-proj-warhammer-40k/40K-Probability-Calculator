package com.example.warhammer40k;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity  {
    //Declaring variables for Dark/Light Mode
    private RadioGroup radioGroup;
    private TextView select;

    private boolean isCheckedValue;

    public static boolean eighthEdition = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        configureButtons();
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


    private void configureButtons(){
        Button ninthEditionButton = (Button) findViewById(R.id.ninthEditionButton);
        Button eighthEditionButton = (Button) findViewById(R.id.eighthEditionButton);
        Button termButton = (Button) findViewById(R.id.termButton);
        Button historyButton = (Button) findViewById(R.id.historyButton);

        ninthEditionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    eighthEdition = false;
                    startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
                    Log.i("configureButtons", "9th edition selected...");
            }
        });
        eighthEditionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eighthEdition = true;
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
                Log.i("configureButtons", "8th edition selected...");
            }
        });
        
        termButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TermsPage.class));
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });
        }


    public class SplashScreenActivity extends AppCompatActivity {

        ImageView imageView;
        TextView splashText;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.splashscreen);


            ImageView imageView = findViewById(R.id.splashImage);
            TextView splashText = findViewById(R.id.splashText);





            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(MainActivity.this, com.example.warhammer40k.SplashScreenActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }

    }

}