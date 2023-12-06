package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// Activity to display rules/How to play  -> HomeScreen -> Rules -> Main
public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }

    public void clicked(View view) {
        Intent intent = new Intent(RulesActivity.this, LobbyActivity.class);
        startActivity(intent);
    }
}