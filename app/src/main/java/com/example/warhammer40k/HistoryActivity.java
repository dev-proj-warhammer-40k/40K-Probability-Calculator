package com.example.warhammer40k;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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

public class HistoryActivity extends AppCompatActivity {

    static String fileName = "history.txt";
    String entry = "";
    String file = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }

    // Reads history.txt into a string for application processing
    public void ReadHistory(Context context){
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
        } catch (FileNotFoundException e) {
            Log.e("ReadHistory", "ERROR: FILE NOT FOUND! " + e);
        } catch (IOException e) {
            Log.e("ReadHistory", "ERROR: IOException! " + e);
        }
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

        //TODO: remove, for testing only
        ReadHistory(context);

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
