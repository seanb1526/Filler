package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class LobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.musicpack_008_coral);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();
    }

    public void rules(View view) {
        Intent intent = new Intent(LobbyActivity.this, RulesActivity.class);
        startActivity(intent);
    }
    public void start(View view) {
        Intent intent = new Intent(LobbyActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void leaderboard(View view) {
        Intent intent = new Intent(LobbyActivity.this, Leaderboard.class);
        startActivity(intent);
    }
}