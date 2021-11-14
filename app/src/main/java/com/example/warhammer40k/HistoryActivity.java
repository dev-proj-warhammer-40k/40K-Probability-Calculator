package com.example.warhammer40k;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class HistoryActivity extends AppCompatActivity {

    static String fileName = "history.txt";
    String entry = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }

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

    }

    // Generates a string for entry into the history.txt file
    public String generateString(Session session){

        String result = "";

        result = "\n{attacks:"  +   session.attacks     + "\n" +
                "skill:"        +   session.skill       + "\n" +
                "strength:"     +   session.strength    + "\n" +
                "toughness:"    +   session.toughness   + "\n" +
                "armPen:"       +   session.armPen      + "\n" +
                "armSave:"      +   session.armSave     + "\n" +
                "invulnSave:"   +   session.invulnSave  + "\n" +
                "feelNoPain:"   +   session.feelNoPain  + "\n" +
                "hits:"         +   session.hits        + "\n" +
                "wounds:"       +   session.wounds      + "\n" +
                "damage:"       +   session.damage      + "\n" +
                "finalDamage:"  +   session.finalDamage + "}\n";


        return result;
    }
}
