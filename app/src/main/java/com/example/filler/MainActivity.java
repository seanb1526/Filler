package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    PlayerModel player1 = new PlayerModel();
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        player1.playerBoard = new CheckBox[8][8]; // create a player using model

        //initialize and create game board - change this later because players will share the game board
        GridLayout gameBoard = findViewById(R.id.gridLayout);
        int[] colors = {R.color.color1, R.color.color2, R.color.color3,R.color.color4,
                R.color.color5, R.color.color6};
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                rand = new Random();
                int color = colors[new Random().nextInt(6)];
                //
                CheckBox rBtn = new CheckBox(this);
                rBtn.setBackgroundColor(getResources().getColor(color));
                //rBtn.setButtonDrawable(null);   // remove the circle on the button
                rBtn.setMaxWidth(150);
                rBtn.setMaxHeight(150);
                rBtn.setText("---");
                //rBtn.setLayoutParams(new GridLayout.LayoutParams());
                gameBoard.addView(rBtn);
                rBtn.setId(Integer.parseInt(i+ "" + j));
                player1.playerBoard[i][j] = rBtn;
            }
        }
    }

}