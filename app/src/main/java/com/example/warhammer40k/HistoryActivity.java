package com.example.warhammer40k;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryActivity extends AppCompatActivity {

    static String fileName = "history.txt";
    String entry = "";
    String file = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ConfigureBackButton();
        PopulateTable(getApplicationContext());
    }

    // Reads history.txt into a string for application processing
    public String ReadHistory(Context context){
        FileInputStream input = null;

        try {
            input = context.openFileInput(fileName);
            InputStreamReader fileReader = new InputStreamReader(input);
            BufferedReader fileBuffer = new BufferedReader(fileReader);
            StringBuilder fileBuilder = new StringBuilder();

            while((file = fileBuffer.readLine()) != null){
                fileBuilder.append(file).append("\n");
            }
            Log.i("ReadHistory", "File contents: \n" + fileBuilder.toString());
            return fileBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.e("ReadHistory", "ERROR: FILE NOT FOUND! " + e);
        } catch (IOException e) {
            Log.e("ReadHistory", "ERROR: IOException! " + e);
        }
        return "";
    }

    // When calculate button is pressed, this function is called to save the current values of
    // the calculation to the history.txt file
    public void AppendHistory(Context context) {
        FileOutputStream output = null;

        try {
            output = context.openFileOutput(fileName, context.MODE_APPEND);
            output.write(entry.getBytes());

            Log.i("AppendHistory", "Saved to: " + context.getFilesDir() + "/" + fileName);
        } catch (FileNotFoundException e) {
            Log.e("AppendHistory", "ERROR: FILE NOT FOUND! " + e);
        } catch (IOException e) {
            Log.e("AppendHistory", "ERROR: IOException " + e);
        } finally {
            if(output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    Log.e("AppendHistory", "ERROR: IOException " + e);
                }
            }
        }

        //TODO: remove, for troubleshooting only
        //ReadHistory(context);
    }

    private void PopulateTable(Context context){
        LinearLayout historyLayout = (LinearLayout) findViewById(R.id.historyLayout);

        int rows = 50; // total number of history entries possible in history linear layout
        TextView[] layoutRows = new TextView[rows];
        //for (int i = 1; i < rows; i++) {
            // create a new textview
            TextView rowTextView = new TextView(this);

            rowTextView.setText(ReadHistory(context));


            // add the textview to the linearlayout
            historyLayout.addView(rowTextView);

            // save a reference to the textview for later
            //layoutRows[i] = rowTextView;
    }

    // Generates a string for entry into the history.txt file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String generateString(Session session){
        String result = "";

        //Get current date time
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //Get edition
        String edition;
        if (MainActivity.eighthEdition)
            edition = " -- Eighth Edition";
        else
            edition = " -- Ninth Edition";


        result = format.format(now) + edition + "\n" +
                "----------------------------------------------------------\n" +
                "attacks:  "        +   session.attacks     + "\n" +
                "skill:  "          +   session.skill       + "\n" +
                "strength:  "       +   session.strength    + "\n" +
                "toughness:  "      +   session.toughness   + "\n" +
                "armPen:  "         +   session.armPen      + "\n" +
                "armSave:  "        +   session.armSave     + "\n" +
                "invulnSave:  "     +   session.invulnSave  + "\n" +
                "feelNoPain:  "     +   session.feelNoPain  + "\n" +
                "hits:  "           +   session.hits        + "\n" +
                "wounds:  "         +   session.wounds      + "\n" +
                "damage:  "         +   session.damage      + "\n" +
                "finalDamage:  "    +   session.finalDamage + "\n\n";

        return result;
    }

    private void ConfigureBackButton(){
        Button backButton = (Button) findViewById(R.id.historyExitButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
