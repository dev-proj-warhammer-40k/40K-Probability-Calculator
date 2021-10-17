package com.example.a40k_probability_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // compares strength vs. toughness. Returns the WoundRoll value.
    public static int StrvTgh(int s, int t){
        if(s == t){
            return 4;
        }else if (s*2 > t){
            return 2;
        }else if(s > t){
            return 3;
        }else if(s < t*2){
            return 6;
        }else if(s < t){
            return 5;
        }else{
            //TODO
            //error handling goes here
            return -1;
        }
    }
}