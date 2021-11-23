package com.example.warhammer40k;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    public static boolean eighthEdition = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureButtons();
    }


    private void configureButtons(){
        Button ninthEditionButton = (Button) findViewById(R.id.ninthEditionButton);
        Button eighthEditionButton = (Button) findViewById(R.id.eighthEditionButton);

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
        }


}
