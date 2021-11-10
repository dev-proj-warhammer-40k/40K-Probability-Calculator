package com.example.warhammer40k;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class historyActivity extends AppCompatActivity {

    static String fileName = "historyLog.txt";
    static String filePath = "historyLogDir";
    File file;
    int entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }

    public void AppendHistory(double entry){
        if(!ExternalStorageAvailable()){
            Log.e("appendHistory","external media not present");
            Log.i("appendHistory","skipping append to history log");
            return;
        }

        //file = new File(getExternalFilesDir(filePath), fileName);
        try {
            FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/historyLog.txt");
            //FileOutputStream out = new FileOutputStream(fileName, true);
            out.write(String.valueOf(entry).getBytes());
            out.close();
        }catch (IOException e){
            Log.e("appendHistory","unable to append history file");
        }

    }

    private boolean ExternalStorageAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        } else {
            return false;
        }
    }

    public String generateString(int attacks, int skill, boolean arr1,
                                 boolean ap1, boolean am1, int strength,
                                 int toughness,  boolean wrr1, boolean wp1,
                                 boolean wm1, int armSave, int armPen,
                                 int invulnSave, int feelNoPain, boolean is,
                                 boolean fnp, int finalDamage){

        String result = "";
        result = "" + attacks + ',' + skill + ',' + arr1 + ',' + ap1 + ',' +
                 am1 + ',' + strength + ',' + toughness + ',' + wrr1 + ',' +
                 wp1 + ',' + wm1 + ',' + armSave + ',' + armPen + ',' +
                 invulnSave + ',' + feelNoPain + ',' + is + ',' + fnp + ',' +
                 finalDamage;

        return result;
    }
}
