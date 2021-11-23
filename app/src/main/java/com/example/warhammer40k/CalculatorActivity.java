package com.example.warhammer40k;

import android.os.Bundle;
import android.text.TextUtils;
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


/*
* CALCULATORACTIVITY CLASS
* -------------------------
* DESCRIPTION:
* The CalculatorActivity class is responsible for the primary
* logic behind the Warhammer 40K probability calculations, as well as
* the integration with the calculator user interface.
*
* On creation (when a calculator button is clicked in the MainActivity class),
* the calculate initializes all UI elements. Afterwards, the user inputs all data before
* pressing the "calculate" button.
*
* Once the calculate button is pressed, all input fields are checked to be within the set
* parameters. This is done with a wrapper function called "ProcessUserInput" which returns
* an error code if something isn't right. This triggers a popup (Toast message) with the error,
* and how the user can correct it.
*
* If everything is entered properly, the CalculatorActivity class processes the data and generates
* the statistical average result. During processing, user data and results are temporarily stored in
* a "Session" class. This class is then passed to a HistoryActibity object, which stores data in
* a file called history.txt. This file is internal to the app.
* -------------------------
 */
public class CalculatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Text box variables
    EditText attacksInput;
    EditText skillInput;
    EditText hitModifierInput;
    EditText woundModifierInput;
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
    Button resetButton;

    // Spinner variables
    Spinner damageSpinner;
    Spinner damageModSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Text inputs & checkboxes initialization
        ConfigureInputs();
        ConfigureCheckBoxes();

        // Button Initializations & Listeners
        ConfigureCalculateButton();
        ConfigureBackButton();
        ConfigureResetButton();

        // Spinner initializations
        ConfigureSpinners();

        // Set UI object visibility depending on item selection
        ConfigureEdition();

        Log.i("onCreate", "initializations complete...");
    }

    private void ConfigureInputs() {
        // Text box initializations
        attacksInput = (EditText) findViewById(R.id.attacksInput);
        skillInput = (EditText) findViewById(R.id.skillInput);
        hitModifierInput = (EditText) findViewById(R.id.hitModifierInput);
        woundModifierInput = (EditText) findViewById(R.id.woundModifierInput);
        strengthInput = (EditText) findViewById(R.id.strengthInput);
        toughnessInput = (EditText) findViewById(R.id.toughnessInput);
        armPenInput = (EditText) findViewById(R.id.armPenInput);
        armSaveInput = (EditText) findViewById(R.id.armSaveInput);
        invulnSaveInput = (EditText) findViewById(R.id.invulnSaveInput);
        feelNoPainInput = (EditText) findViewById(R.id.feelNoPainInput);
        Log.i("configureInputs", "text boxes initialized...");
    }

    private void ConfigureCheckBoxes() {
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

    private void ConfigureSpinners() {
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
        damageModSpinner.setSelection(1); // sets default position to 0
        Log.i("configureSpinners", "damageModSpinner initialized...");

    }

    // If eighth edition is selected, hide modifier checkboxes and display text input instead.
    // If ninth edition is selected, do the opposite. Depends on bool set in MainActivity.
    private void ConfigureEdition() {
        if (MainActivity.eighthEdition) {
            toHitPlusOneCheckBox.setVisibility(View.INVISIBLE);
            toHitMinusOneCheckBox.setVisibility(View.INVISIBLE);
            toWoundPlusOneCheckBox.setVisibility(View.INVISIBLE);
            toWoundMinusOneCheckBox.setVisibility(View.INVISIBLE);

            hitModifierInput.setVisibility(View.VISIBLE);
            woundModifierInput.setVisibility(View.VISIBLE);
            Log.i("ConfigureEdition", "Eighth Edition Calculator Initialized...");
        } else {
            toHitPlusOneCheckBox.setVisibility(View.VISIBLE);
            toHitMinusOneCheckBox.setVisibility(View.VISIBLE);
            toWoundPlusOneCheckBox.setVisibility(View.VISIBLE);
            toWoundMinusOneCheckBox.setVisibility(View.VISIBLE);

            hitModifierInput.setVisibility(View.INVISIBLE);
            woundModifierInput.setVisibility(View.INVISIBLE);
            Log.i("ConfigureEdition", "Ninth Edition Calculator Initialized...");
        }
    }

    private void ConfigureBackButton() {
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.i("configureBackButton", "backButton initialized...");
    }

    // Resets all inputs to empty, checkboxes to unchecked, and spinners to defaults
    private void ConfigureResetButton() {
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clear edittexts
                attacksInput.getText().clear();
                skillInput.getText().clear();
                hitModifierInput.getText().clear();
                strengthInput.getText().clear();
                toughnessInput.getText().clear();
                woundModifierInput.getText().clear();
                armSaveInput.getText().clear();
                armPenInput.getText().clear();
                invulnSaveInput.getText().clear();
                feelNoPainInput.getText().clear();
                Log.i("configureResetButton", "Text inputs reset!");

                //Clear checkboxes
                if (toHitRerollOnesCheckBox.isChecked())
                    toHitRerollOnesCheckBox.toggle();
                if (toHitPlusOneCheckBox.isChecked())
                    toHitPlusOneCheckBox.toggle();
                if (toHitMinusOneCheckBox.isChecked())
                    toHitMinusOneCheckBox.toggle();
                if (toWoundRerollOnesCheckBox.isChecked())
                    toWoundRerollOnesCheckBox.toggle();
                if (toWoundPlusOneCheckBox.isChecked())
                    toWoundPlusOneCheckBox.toggle();
                if (toWoundMinusOneCheckBox.isChecked())
                    toWoundMinusOneCheckBox.toggle();
                if (feelNoPainCheckBox.isChecked())
                    feelNoPainCheckBox.toggle();
                if (invulnSaveCheckBox.isChecked())
                    invulnSaveCheckBox.toggle();
                Log.i("configureResetButton", "Checboxes reset!");

                //Spinner defaults:
                damageSpinner.setSelection(0);
                damageModSpinner.setSelection(1);
                Log.i("configureResetButton", "Spinners reset!");
            }
        });
        Log.i("configureResetButton", "resetButton initialized...");
    }

    // This button triggers all of the user input retrieval, input error handling, and calculator function calls
    private void ConfigureCalculateButton() {
        calculateButton = (Button) findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("configCalculateButton", "\"calculate\" button pressed...");

                //////////////////////////////////////////////////////////////////////////////
                //////////////////////////////MAIN FUNCTIONALITY//////////////////////////////
                //////////////////////////////////////////////////////////////////////////////

                // Declare new session object to hold user input and results
                Session session = new Session();

                // Read attacks & skill user inputs, and calculate number of hits.
                session.attacks = ProcessUserInput(attacksInput, "Attacks", 1, 500);
                if (session.attacks == -10)
                    return; // Ends calculation if input is out of bounds (-10 is an error code)
                session.skill = ProcessUserInput(skillInput, "Skills", 2, 6);
                if (session.skill == -10)
                    return; // Ends calculation if input is out of bounds (-10 is an error code)

                // Eighth vs. ninth edition processing
                if (MainActivity.eighthEdition) {
                    session.hitMod = ProcessUserInput(hitModifierInput, "Hit Modifier", -4, 4);
                    if (session.hitMod == -10)
                        return; // Ends calculation if input is out of bounds (-10 is an error code)
                    session.hits = ToHitEighthEdition(session.attacks, session.skill, session.hitMod);
                } else {
                    session.hits = ToHitNinthEdition(session.attacks, session.skill);
                }


                // Read stength & toughness user inputs, and calculate number of wounds
                session.strength = ProcessUserInput(strengthInput, "Strength", 1, 16);
                if (session.strength == -10)
                    return; // Ends calculation if input is out of bounds (-10 is an error code)
                session.toughness = ProcessUserInput(toughnessInput, "Toughness", 1, 16);
                if (session.toughness == -10)
                    return; // Ends calculation if input is out of bounds (-10 is an error code)

                // Eighth vs. ninth edition processing
                if (MainActivity.eighthEdition) {
                    session.woundMod = ProcessUserInput(woundModifierInput, "Wound Modifier", -4, 4);
                    if (session.hitMod == -10)
                        return; // Ends calculation if input is out of bounds (-10 is an error code)

                    session.wounds = ToWoundEighthEdition(session.strength, session.toughness, session.hits, session.woundMod);
                } else {
                    session.wounds = ToWoundNinthEdition(session.strength, session.toughness, session.hits);
                }

                // Read saves & damage user inputs, and calculate final damage
                session.armSave = ProcessUserInput(armSaveInput, "Armor Save", 2, 6);
                if (session.armSave == -10)
                    return; // Ends calculation if input is out of bounds (-10 is an error code)
                session.armPen = ProcessUserInput(armPenInput, "Armor Penetration", -5, 0);
                if (session.armPen == -10)
                    return; // Ends calculation if input is out of bounds (-10 is an error code)

                // Reads in indexes of damage spinners, and calculates initial damage
                session.damage = DamageInput();

                // Invulnerable save and feel no pain are optional. Checks inputs depending on user selection.
                if (invulnSaveCheckBox.isChecked()) {
                    session.invulnSave = ProcessUserInput(invulnSaveInput, "Invulnerable Save", 2, 6);
                    if (session.invulnSave == -10)
                        return; // Ends calculation if input is out of bounds (-10 is an error code)
                }
                if (feelNoPainCheckBox.isChecked()) {
                    session.feelNoPain = ProcessUserInput(feelNoPainInput, "Feel No Pain", 2, 6);
                    if (session.invulnSave == -10)
                        return; // Ends calculation if input is out of bounds (-10 is an error code)
                }

                // Calculates the final damage based on user inputs
                session.finalDamage = FinalDamage(session.damage, session.armPen, session.armSave,
                        session.invulnSave, (int) session.wounds, session.feelNoPain);


                // Create new entry for history.txt and append file (for history logging feature)
                HistoryActivity historyEntry = new HistoryActivity();
                historyEntry.entry = historyEntry.generateString(session);
                historyEntry.AppendHistory(getApplicationContext());

                //TODO: DISPLAY RESULT
                Toast.makeText(getApplicationContext(), "" + session.finalDamage, Toast.LENGTH_LONG).show();
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

    // (Ninth Edition) takes the number of attacks and skill (user input), and calculates the number of hits
    public double ToHitNinthEdition(int attacks, int skill) {
        double result = -1;

        // check if +1/-1 modifier radios are ticked
        if (toHitPlusOneCheckBox.isChecked() && toHitMinusOneCheckBox.isChecked()) {
            //no modification takes place
        } else if (toHitPlusOneCheckBox.isChecked()) {
            skill--;
        } else if (toHitMinusOneCheckBox.isChecked()) {
            skill++;
        }

        if (skill >= 6)
            result = 0.16;
        else
            result = ModifierConvert(skill);

        // check if reroll ones modifier radio is ticked
        if (toHitRerollOnesCheckBox.isChecked()) {
            result += 0.16;
        }

        result *= attacks;
        Log.i("ToHitNinthEdition", "ToHit calculates: hits = " + result);
        return result;
    }

    // (Eighth Edition) takes the number of attacks and skill (user input), and calculates the number of hits
    public double ToHitEighthEdition(int attacks, int skill, int hitMod) {
        double result = -1;
        hitMod = hitMod * -1;

        skill += hitMod;

        // check if +1/-1 modifier radios are ticked
        result = ModifierConvert(skill);

        // check if reroll ones modifier radio is ticked
        if (toHitRerollOnesCheckBox.isChecked()) {
            result += 0.16;
        }

        result *= attacks;
        Log.i("ToHitEighthEdition", "ToHit calculates: hits = " + result);
        return result;
    }

    // (Ninth Edition) takes the number of attacks and skill (user input), and calculates the number of wounds
    public double ToWoundNinthEdition(int strength, int toughness, double hits) {
        double result = StrvTgh(strength, toughness);

        if (toWoundPlusOneCheckBox.isChecked() && toWoundMinusOneCheckBox.isChecked()) {
            //no modification takes place
        } else if (toWoundPlusOneCheckBox.isChecked()) {
            result--;
        } else if (toWoundMinusOneCheckBox.isChecked()) {
            result++;
        }

        if (result >= 6)
            result = 0.16;
        else
            result = ModifierConvert((int) result);

        // check if reroll ones modifier radio is ticked
        if (toWoundRerollOnesCheckBox.isChecked()) {
            result += 0.16;
        }

        result *= hits;
        Log.i("ToWoundNinthEdition", "ToWound calculates: wounds = " + result);
        return result;
    }

    // (Eighth Edition) takes the number of attacks and skill (user input), and calculates the number of wounds
    public double ToWoundEighthEdition(int strength, int toughness, double hits, int woundMod) {
        double result = StrvTgh(strength, toughness);

        woundMod = woundMod * -1;

        result = ModifierConvert((int) result);

        // check if reroll ones modifier radio is ticked
        if (toWoundRerollOnesCheckBox.isChecked()) {
            result += 0.16;
        }

        result *= hits;
        Log.i("ToWoundEighthEdition", "ToWound calculates: wounds = " + result);
        return result;
    }

    // Takes user input on damage spinner and damage modifier spinner, and returns the average
    // damage based on those selections.
    public int DamageInput() {
        int result = -1;

        // Spinner selected array indexes
        int damageCase = damageSpinner.getSelectedItemPosition();
        int damageModCase = damageModSpinner.getSelectedItemPosition();
        Log.i("damage case = ", "" + damageCase);
        Log.i("damage mod case = ", "" + damageModCase);

        // Map damageSpinner index onto actual damage value
        if (damageCase > -1 && damageCase < 5) {
            result = damageCase + 1;
        } else if (damageCase == 5) {
            result = 2;
        } else if (damageCase == 6 || damageCase == 7) {
            result = 4;
        } else if (damageCase == 8) {
            result = 7;
        } else {
            Log.e("DamageInput", "damageCase out of range!" + damageCase);
        }
        Log.i("DamageInput", "damageCase returns: " + result);

        // Map damageModSpinner index onto actual damage value
        if (damageModCase == -1) {
            result += -1;
        } else if (damageModCase > -1 && damageModCase < 9) {
            result += damageModCase - 1;
        } else {
            Log.e("DamageInput", "damageModCase out of range!" + damageModCase);
        }
        Log.i("DamageInput", "damageModCase returns: " + damageModCase);

        Log.i("DamageInput", "DamageInput returns: " + result);
        return result;
    }

    // Calculates final damage statistic
    public double FinalDamage(int damage, int armPen, int armSave, int invulnSave, int wounds, int feelNoPain) {
        double result = -1;

        // damage cannot be 0, map to 1
        if (damage == 0) {
            damage = 1;
        }

        armPen *= -1;
        armSave += armPen;

        if (invulnSaveCheckBox.isChecked() && armSave >= invulnSave) {
            invulnSave = Integer.valueOf(invulnSaveInput.getText().toString());
            wounds *= 1 - ModifierConvert(invulnSave);
        } else {
            wounds *= 1 - ModifierConvert(armSave);
        }

        damage *= wounds;
        Log.i("FinalDamage", "damage: " + damage);

        if (feelNoPainCheckBox.isChecked()) {
            Log.i("FinalDamage", "FNP checked");
            feelNoPain = Integer.valueOf(feelNoPainInput.getText().toString());
            damage *= 1 - ModifierConvert(feelNoPain);
        }
        result = damage;
        Log.i("Damage", "Damage calculates: finalDamage = " + result);
        return result;
    }


    // compares strength vs. toughness. Returns the wounds value.
    public static int StrvTgh(int s, int t) {
        int result;

        if (s == t) {
            result = 4;
        } else if (s >= t * 2) {
            result = 2;
        } else if (s > t) {
            result = 3;
        } else if (s <= t / 2) {
            result = 6;
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

        if (mod >= 7)
            return 0;

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
                result = .16;
                break;
            default:
                Log.e("ModifierConvert", "'Mod' Out of range! " + mod);
                result = -1;
        }
        Log.i("ModifierConvert", "ModifierConvert " + mod + " returns: " + result);
        return result;
    }

    // Wrapper function that checks text input against null and min/max parameters. Returns -10 if null or out of range (-10 is never used in the input fields).
    // Otherwise the actual input is returned to the caller
    public int ProcessUserInput(EditText input, String msg, int min, int max) {
        if (TextUtils.isEmpty(input.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), msg + " input is empty!", Toast.LENGTH_LONG).show();
            Log.e("ProcessUserInput", msg + " input is empty!");
            return -10;
        }

        int result = Integer.valueOf(input.getText().toString().trim());
        if (result < min || result > max) {
            Toast.makeText(getApplicationContext(), msg + " input must be between " + min + " and " + max + "!", Toast.LENGTH_LONG).show();
            Log.e("ProcessUserInput", msg + " input must be between " + min + " and " + max + "!");
            return -10;
        }
        Log.i("ProcessUserInput", msg + " input processed. Result: " + result);
        return result;
    }
}



