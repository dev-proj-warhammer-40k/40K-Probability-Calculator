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
    int skill;
    double hits;
    int strength;
    int toughness;
    int damage;
    int armPen;
    int armSave;
    int invulnSave = 100;
    int finSave;

    // Global text boxes
    EditText attacksInput;
    EditText skillInput;
    EditText strengthInput;
    EditText toughnessInput;
    EditText damageInput;
    EditText armPenInput;
    EditText armSaveInput;
    EditText invulnSaveInput;

    // Global buttons
    CheckBox plusOneCheckBox;
    CheckBox minusOneCheckBox;
    CheckBox rerollOnesCheckBox;
    CheckBox rndmDmgCheckBox;
    CheckBox invulnSaveCheckBox;
    CheckBox feelNoPainCheckBox;
    Button strvTghTestButton;   //TEST BUTTON FOR StrvTgh FUNCTION TODO: REMOVE
    Button toHitTestButton;     //TEST BUTTON FOR ToHit FUNCTION TODO: REMOVE
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Text box initializations
        attacksInput = (EditText) findViewById(R.id.attacksInput);
        skillInput = (EditText) findViewById(R.id.skillInput);
        strengthInput = (EditText) findViewById(R.id.strengthInput);
        toughnessInput = (EditText) findViewById(R.id.toughnessInput);
        damageInput = (EditText) findViewById(R.id.damageInput);
        armPenInput = (EditText) findViewById(R.id.armPenInput);
        armSaveInput = (EditText) findViewById(R.id.armSaveInput);
        invulnSaveInput = (EditText) findViewById(R.id.invulnSaveInput);

        // Checkbox Initializations
        plusOneCheckBox = (CheckBox) findViewById(R.id.plusOneCheckBox);
        minusOneCheckBox = (CheckBox) findViewById(R.id.minusOneCheckBox);
        rerollOnesCheckBox = (CheckBox) findViewById(R.id.rerollOnesCheckBox);
        rndmDmgCheckBox = (CheckBox) findViewById(R.id.rndmDmgCheckBox);
        invulnSaveCheckBox = (CheckBox) findViewById(R.id.invulnSaveCheckBox);
        feelNoPainCheckBox = (CheckBox) findViewById(R.id.feelNoPainCheckBox);

        // Button Initializations & Events
        //CALCULATE BUTTON
        calculateButton = (Button) findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damage = Integer.valueOf(damageInput.getText().toString());
                armPen = Integer.valueOf(armPenInput.getText().toString());
                armSave = Integer.valueOf(armSaveInput.getText().toString());
                invulnSave = Integer.valueOf(invulnSaveInput.getText().toString());

                //TODO: CALCULATE ALL OF THE THINGS
            }
        });

        //////////////////////////////////////////////////
        //TEST BUTTON FOR ToHit FUNCTION TODO: REMOVE
        toHitTestButton = (Button) findViewById(R.id.toHitTestButton);
        toHitTestButton.setOnClickListener(new View.OnClickListener() {
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
        strvTghTestButton.setOnClickListener(new View.OnClickListener() {
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
    public double ToHit() {

        // make sure skill is in correct range
        if (skill < 2 || skill > 6) {
            //TODO: ADD ERROR HANDLING
            //error handling goes here
            return -1;
        }

        // check if +1/-1 modifier radios are ticked
        if (plusOneCheckBox.isChecked() && minusOneCheckBox.isChecked()) {
            //no modification takes place
        } else if (plusOneCheckBox.isChecked()) {
            skill--;
        } else if (minusOneCheckBox.isChecked()) {
            skill++;
        }
        double modSkill;
        modSkill = ModifierConvert(skill); //TODO: uncomment when ModifierConvert is implemented

        // check if reroll ones modifier radio is ticked
        if(rerollOnesCheckBox.isChecked()){
            modSkill += 0.16;
        }

        return modSkill * attacks;
    }

    // compares strength vs. toughness. Returns the WoundRoll value.
    public static int StrvTgh(int s, int t) {
        if (s == t) {
            return 4;
        } else if (s >= t * 2) {
            return 2;
        } else if (s > t) {
            return 3;
        } else if (s <= t / 2) {
            return 6;
        } else if (s < t) {
            return 5;
        } else {
            //TODO: ADD ERROR HANDLING
            //error handling goes here
            return -1;
        }
    }

    //ModiferConvert
    public static double ModifierConvert (int Mod) {

        switch(Mod) {
            case 1:

            case 2:
                return .83;
            case 3:
                return .66;
            case 4:
                return .5;
            case 5:
                return .33;
            case 6:

            case 7:
                return .16;
        }

    // Calculates final damage statistic
    public double Damage(){
        if(rndmDmgCheckBox.isChecked()){
            //TODO: RANDOMDAMAGE FUNCTION
        }

        if(invulnSaveCheckBox.isChecked()){
            //TODO: INVULN SAVE HANDLING
        }

        armPen *= -1;
        armSave += armPen;

        if(armSave < invulnSave){
            //TODO: ModiferConvert(armSave);
        }else{
            //TODO: ModifierConvert(invulnSave);
            finSave = invulnSave;
            //NOT DONE HERE
        }

        return 0;
    }
}