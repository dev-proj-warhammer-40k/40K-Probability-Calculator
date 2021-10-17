package com.example.a40k_probability_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.assist.AssistStructure;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Global variables
    int attacks;
    double skill;
    double hits;
    int strength;
    int toughness;

    // Global text boxes
    EditText attacksInput;
    EditText skillInput;
    EditText strengthInput;
    EditText toughnessInput;

    // Global buttons
    CheckBox plusOneCheckBox;
    CheckBox minusOneCheckBox;
    CheckBox rerollOnesCheckBox;
    Button strvTghTestButton;   //TEST BUTTON FOR StrvTgh FUNCTION TODO: REMOVE
    Button toHitTestButton;     //TEST BUTTON FOR ToHit FUNCTION TODO: REMOVE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Text box initializations
        attacksInput = (EditText) findViewById(R.id.attacksInput);
        skillInput = (EditText) findViewById(R.id.skillInput);
        strengthInput = (EditText) findViewById(R.id.strengthInput);
        toughnessInput = (EditText) findViewById(R.id.toughnessInput);

        // Buttons Initializations
        plusOneCheckBox = (CheckBox) findViewById(R.id.plusOneCheckBox);
        minusOneCheckBox = (CheckBox) findViewById(R.id.minusOneCheckBox);
        rerollOnesCheckBox = (CheckBox) findViewById(R.id.rerollOnesCheckBox);

        // Button Events

        //////////////////////////////////////////////////
        //TEST BUTTON FOR ToHit FUNCTION TODO: REMOVE
        toHitTestButton = (Button) findViewById(R.id.toHitTestButton);
        toHitTestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attacks = Integer.valueOf(attacksInput.getText().toString());
                skill = Integer.valueOf(skillInput.getText().toString());
                hits = ToHit();

                Toast.makeText(MainActivity.this, String.valueOf(hits), Toast.LENGTH_LONG).show();
            }
        });
        //////////////////////////////////////////////////

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
        //////////////////////////////////////////////////

    }

    // takes the number of attacks and skill (user input), and calculates the number of hits
    public double ToHit(){

        // make sure skill is in correct range
        if(skill < 2 || skill > 6){
            //TODO: ADD ERROR HANDLING
            //error handling goes here
            return -1;
        }

        // check if +1/-1 modifier radios are ticked
        if(plusOneCheckBox.isChecked() && minusOneCheckBox.isChecked()){
            //no modification takes place
        } else if(plusOneCheckBox.isChecked()){
            skill--;
        } else if(minusOneCheckBox.isChecked()){
            skill++;
        }

        //ModifierConvert(skill); TODO: uncomment when ModifierConvert is implemented

        // check if reroll ones modifier radio is ticked
        if(rerollOnesCheckBox.isChecked()){
            skill += 0.16;
        }

        return skill * attacks;
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