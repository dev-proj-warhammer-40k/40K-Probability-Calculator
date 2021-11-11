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

public class historyActivity extends AppCompatActivity {

    static String fileName = "history.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }

    public void AppendHistory(Context context, String entry) {
        FileOutputStream output = null;

        try {
            output = context.openFileOutput(fileName, context.MODE_APPEND);
            output.write(entry.getBytes());

            Log.i("AppendHistory", "Saved to: " + context.getFilesDir() + "/" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String generateString(int attacks, int skill, boolean arr1,
                                 boolean ap1, boolean am1, int strength,
                                 int toughness,  boolean wrr1, boolean wp1,
                                 boolean wm1, int armSave, int armPen,
                                 int invulnSave, int feelNoPain, boolean is,
                                 boolean fnp, double finalDamage){

        String result = "";
        result = "{attacks: " + attacks + "\n" + "}";

        return result;
    }
}
