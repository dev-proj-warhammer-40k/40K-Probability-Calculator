package com.example.warhammer40k;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class TermsPage extends AppCompatActivity {

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_page);

        configureBackButton();

        listView = (ListView) findViewById(R.id.List);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.definitions, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);



    }

    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.backButtonTerms);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}

