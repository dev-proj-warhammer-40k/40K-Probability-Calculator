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

    static String fileName = "historyLog";
    static String filePath = "historyLogDir";
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }

    public void AppendHistory(JSONObject entry){
        if(!ExternalStorageAvailable()){
            Log.e("appendHistory","external media not present");
            Log.i("appendHistory","skipping append to history log");
            return;
        }

        file = new File(getExternalFilesDir(filePath), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(entry.toString().getBytes());
            fos.close();
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

    public String generateJson(String entry){
     JSONObject = 
     return json;
    }
}
