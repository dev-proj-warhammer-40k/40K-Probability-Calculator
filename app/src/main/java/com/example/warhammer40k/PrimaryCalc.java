package com.example.warhammer40k;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PrimaryCalc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Global variables
    int attacks;
    double skill;
    double hits;
    int strength;
    int toughness;
    double damage;
    int armPen;
    int armSave;
    int invulnSave = 100;
    int feelNoPain;
    double wounds;
    double finalDamage;

    // Text box variables
    EditText attacksInput;
    EditText skillInput;
    EditText strengthInput;
    EditText toughnessInput;
    EditText armPenInput;
    EditText armSaveInput;
    EditText invulnSaveInput;
    EditText feelNoPainInput;

    // Button variables
    CheckBox toHitPlusOneCheckBox;
    CheckBox toHitMinusOneCheckBox;
    CheckBox toHitRerollOnesCheckBox;
    CheckBox toWoundPlusOneCheckBox;
    CheckBox toWoundMinusOneCheckBox;
    CheckBox toWoundRerollOnesCheckBox;
    CheckBox invulnSaveCheckBox;
    CheckBox feelNoPainCheckBox;
    Button calculateButton;
    Button backButton;

    // Spinner variables
    Spinner damageSpinner;
    Spinner damageModSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_calc);

        // Text inputs & checkboxes initialization
        configureInputs();
        configureCheckBoxes();

        // Button Initializations & Listeners
        configureCalculateButton();
        configureBackButton();

        // Spinner initializations
        configureSpinners();

        Log.i("onCreate", "initializations complete...");
    }

    private void configureInputs(){
        // Text box initializations
        attacksInput = (EditText) findViewById(R.id.attacksInput);
        skillInput = (EditText) findViewById(R.id.skillInput);
        strengthInput = (EditText) findViewById(R.id.strengthInput);
        toughnessInput = (EditText) findViewById(R.id.toughnessInput);
        armPenInput = (EditText) findViewById(R.id.armPenInput);
        armSaveInput = (EditText) findViewById(R.id.armSaveInput);
        invulnSaveInput = (EditText) findViewById(R.id.invulnSaveInput);
        feelNoPainInput = (EditText) findViewById(R.id.feelNoPainInput);
        Log.i("configureInputs", "text boxes initialized...");
    }

    private void configureCheckBoxes(){
        toHitPlusOneCheckBox = (CheckBox) findViewById(R.id.toHitPlusOneCheckBox);
        toHitMinusOneCheckBox = (CheckBox) findViewById(R.id.toHitMinusOneCheckBox);
        toHitRerollOnesCheckBox = (CheckBox) findViewById(R.id.toHitRerollOnesCheckBox);
        toWoundPlusOneCheckBox = (CheckBox) findViewById(R.id.toWoundPlusOneCheckBox);
        toWoundMinusOneCheckBox = (CheckBox) findViewById(R.id.toWoundMinusOneCheckBox);
        toWoundRerollOnesCheckBox = (CheckBox) findViewById(R.id.toWoundRerollOnesCheckBox);
        invulnSaveCheckBox = (CheckBox) findViewById(R.id.invulnSaveCheckBox);
        feelNoPainCheckBox = (CheckBox) findViewById(R.id.feelNoPainCheckBox);
        Log.i("configureCheckBoxes", "checkboxes initialized...");
    }

    private void configureSpinners(){
        //Spinner for damage
        damageSpinner = findViewById(R.id.damageSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.damageNum, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        damageSpinner.setAdapter(adapter);
        damageSpinner.setOnItemSelectedListener(this);
        Log.i("configureSpinners", "damageSpinner initialized...");


        //Spinner for damageMod
        damageModSpinner = findViewById(R.id.damageModSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.damageModNum, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        damageModSpinner.setAdapter(adapter2);
        damageModSpinner.setOnItemSelectedListener(this);
        Log.i("configureSpinners", "damageModSpinner initialized...");

    }

    private void configureBackButton(){
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.i("configureBackButton", "backButton initialized...");
        }

    private void configureCalculateButton(){
        calculateButton = (Button) findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("configCalculateButton", "\"calculate\" button pressed...");

                attacks = Integer.valueOf(attacksInput.getText().toString());
                skill = Integer.valueOf(skillInput.getText().toString());
                strength = Integer.valueOf(strengthInput.getText().toString());
                toughness = Integer.valueOf(toughnessInput.getText().toString());
                armPen = Integer.valueOf(armPenInput.getText().toString());
                armSave = Integer.valueOf(armSaveInput.getText().toString());
                //damage = Integer.valueOf(damageInput.getText().toString());

                ToHit();
                ToWound();
                Damage();
                //TODO: DISPLAY RESULT
            }
        });
        Log.i("configCalculateButton", "calculateButton initialized...");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }

    // takes the number of attacks and skill (user input), and calculates the number of hits
    public void ToHit() {
        // make sure skill is in correct range
        if (skill < 2 || skill > 6) {
            Log.e("ToHit","SKILL NOT IN RANGE");
        }

        // check if +1/-1 modifier radios are ticked
        if (toHitPlusOneCheckBox.isChecked() && toHitMinusOneCheckBox.isChecked()) {
            //no modification takes place
        } else if (toHitPlusOneCheckBox.isChecked()) {
            skill--;
        } else if (toHitMinusOneCheckBox.isChecked()) {
            skill++;
        }
        skill = ModifierConvert((int)skill);

        // check if reroll ones modifier radio is ticked
        if (toHitRerollOnesCheckBox.isChecked()) {
            skill += 0.16;
        }

        hits = skill * attacks;
        Log.i("ToHit","ToHit calculates: hits = " + hits);
    }

    public void ToWound(){
        wounds = StrvTgh(strength, toughness);

        if(toWoundPlusOneCheckBox.isChecked() && toWoundMinusOneCheckBox.isChecked()){
            //no modification takes place
        } else if(toWoundPlusOneCheckBox.isChecked()){
            wounds--;
        } else if(toWoundMinusOneCheckBox.isChecked()){
            wounds++;
        }
        wounds = ModifierConvert((int)wounds);

        // check if reroll ones modifier radio is ticked
        if(toWoundRerollOnesCheckBox.isChecked()) {
            wounds += 0.16;
        }

        wounds *= hits;
        Log.i("ToWound","ToWound calculates: wounds = " + wounds);
    }

    // Calculates final damage statistic
    public void Damage() {
        // Spinner selected array indexes
        int damageCase = damageSpinner.getSelectedItemPosition();
        int damageModCase = damageModSpinner.getSelectedItemPosition();
        Log.i("damage case = ","" + damageCase);
        Log.i("damage mod case = ","" + damageModCase);

        // Map damageSpinner index onto actual damage value
        if(damageCase > -1 && damageCase < 5){
            damage = damageCase + 1;
        }else if (damageCase == 5){
            damage = 2;
        }else if (damageCase == 6 || damageCase == 7){
            damage = 4;
        }else if (damageCase == 8){
            damage = 7;
        }else{
            Log.e("Damage","damageCase out of range!" + damageCase);
        }
        Log.i("Damage","damageCase returns: " + damage);

        // Map damageModSpinner index onto actual damage value
        if(damageModCase == -1){
            damage += -1;
        }else if(damageModCase > -1 && damageModCase < 9){
            damage += damageModCase - 1;
        }else{
            Log.e("Damage","damageModCase out of range!" + damageModCase);
        }
        Log.i("Damage","damageModCase returns: " + damage);

        // damage cannot be 0, map to 1
        if(damage == 0){
            damage = 1;
        }

        if(invulnSaveCheckBox.isChecked()) {
            invulnSave = Integer.valueOf(invulnSaveInput.getText().toString());
        }

        armPen *= -1;
        armSave += armPen;

        if (armSave < invulnSave) {
            Log.i("Damage", "armSave < invulnSave");
            wounds *= 1- ModifierConvert(armSave);
        } else {
            Log.i("Damage","armSave >= invulnSave");
            wounds *= 1 - ModifierConvert(invulnSave);
        }

        damage *= wounds;

        if(feelNoPainCheckBox.isChecked()){
            Log.i("Damage","FNP checked");
            feelNoPain = Integer.valueOf(feelNoPainInput.getText().toString());
            damage *= 1 - ModifierConvert(feelNoPain);
        }
        finalDamage = damage;
        Log.i("Damage", "Damage calculates: finalDamage = " + finalDamage);

        //TODO: REMOVE
        Toast.makeText(this,"" + finalDamage,Toast.LENGTH_LONG).show();
    }


    // compares strength vs. toughness. Returns the wounds value.
    public static int StrvTgh(int s, int t) {
        int result;

        if (s == t) {
            result = 4;
        } else if (s >= t * 2) {
            result =  2;
        } else if (s > t) {
            result = 3;
        } else if (s <= t / 2) {
            result =  6;
        } else if (s < t) {
            result = 5;
        } else {
            Log.e("StrvTgh", "COMPARISON FAILURE: s = " + s + "t = " + t);
            return -1;
        }

        Log.i("StrvTgh", "StrvTgh returns: " + result);
        return result;
    }

    //ModifierConvert
    public double ModifierConvert(int mod) {
        double result;

        switch (mod) {
            case 1:
            case 2:
                result = .83;
                break;
            case 3:
                result = .66;
                break;
            case 4:
                result = .5;
                break;
            case 5:
                result = .33;
                break;
            case 6:
            case 7:
                result = .16;
                break;
            default:
                Log.e("ModifierConvert", "FAILURE: mod = " + mod);
                return -1;
        }
        Log.i("ModifierConvert", "ModifierConvert returns: " + result);
        return result;
    }
}



