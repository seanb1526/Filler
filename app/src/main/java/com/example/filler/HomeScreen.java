package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

// this is the first activity. It will be the default first screen when app is run.
public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);


    }

// Action Listener for start button that moves to Lobby
    public void clicked(View view) {
        Intent intent = new Intent(HomeScreen.this, LobbyActivity.class);
        startActivity(intent);
    }
}