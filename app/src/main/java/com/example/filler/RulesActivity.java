package com.example.filler;

/* Rules Activity to display the rules and goals of the game. Teach player how to play. */

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

    // ActionListener to move from Rules back to Lobby
    public void clicked(View view) {
        Intent intent = new Intent(RulesActivity.this, LobbyActivity.class);
        startActivity(intent);
    }
}