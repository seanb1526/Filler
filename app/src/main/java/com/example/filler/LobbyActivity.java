package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
    }

    public void rules(View view) {
        Intent intent = new Intent(LobbyActivity.this, RulesActivity.class);
        startActivity(intent);
    }
    public void start(View view) {
        Intent intent = new Intent(LobbyActivity.this, MainActivity.class);
        startActivity(intent);
    }
}