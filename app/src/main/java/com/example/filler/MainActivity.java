package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    PlayerModel player1 = new PlayerModel();
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        player1.playerBoard = new TextView[8][8]; // create a player using model

        //initialize and create game board - change this later because players will share the game board
        GridLayout gameBoard = findViewById(R.id.gridLayout);
        int[] colors = {R.color.color1, R.color.color2, R.color.color3,R.color.color4,
                R.color.color5, R.color.color6};
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                rand = new Random();
                int color = colors[new Random().nextInt(6)];
                //
                TextView tv = new TextView(this);
                tv.setBackgroundColor(getResources().getColor(color));
                //rBtn.setButtonDrawable(null);   // remove the circle on the button
                tv.setWidth(100);
                tv.setHeight(100);
                tv.setText("---");
                //rBtn.setLayoutParams(new GridLayout.LayoutParams());
                gameBoard.addView(tv);
                tv.setId(Integer.parseInt(i+ "" + j));
                player1.playerBoard[i][j] = tv;
            }
        }
    }

}