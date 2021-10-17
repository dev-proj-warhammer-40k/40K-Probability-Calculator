package com.example.a40k_probability_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Global variables
    int strength;
    int toughness;

    // Global text boxes
    EditText strengthInput;
    EditText toughnessInput;

    // Global buttons
    Button strvTghTestButton;   //TEST BUTTON FOR StrvTgh FUNCTION TODO: REMOVE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Input fields
        strengthInput = (EditText) findViewById(R.id.strengthInput);
        toughnessInput = (EditText) findViewById(R.id.toughnessInput);

        // Buttons

        //////////////////////////////////////////////////
        //TEST BUTTON FOR StrvTgh FUNCTION TODO: REMOVE
        strvTghTestButton = (Button) findViewById(R.id.strvTghTestButton);
        strvTghTestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                strength = Integer.valueOf(strengthInput.getText().toString());
                toughness = Integer.valueOf(toughnessInput.getText().toString());

                Toast.makeText(MainActivity.this, String.valueOf(StrvTgh(strength, toughness)), Toast.LENGTH_LONG).show();
            }
        });
        //////////////////////////////////////////////

    }

    // compares strength vs. toughness. Returns the WoundRoll value.
    public static int StrvTgh(int s, int t){
        if(s == t){
            return 4;
        }else if (s >= t*2){
            return 2;
        }else if(s > t){
            return 3;
        }else if(s <= t/2){
            return 6;
        }else if(s < t){
            return 5;
        }else{
            //TODO: ADD ERROR HANDLING
            //error handling goes here
            return -1;
        }
    }
}