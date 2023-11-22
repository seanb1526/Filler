package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// this is the first activity. It will be the default first screen when app is run.
public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }
}