package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RadioButton[][] matrix;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrix = new RadioButton[8][8];
        GridLayout gameBoard = findViewById(R.id.gridLayout);

        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                rand = new Random();
                int color = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

                // these should be buttons not text views
                RadioButton rBtn = new RadioButton(this);
                rBtn.setBackgroundColor(color);
                rBtn.setButtonDrawable(null);   // remove the circle on the button
                rBtn.setMaxWidth(150);
                rBtn.setMaxHeight(150);
                rBtn.setText("---");
                rBtn.setLayoutParams(new GridLayout.LayoutParams());
                gameBoard.addView(rBtn);
                rBtn.setId(Integer.parseInt(i+ "" + j));
                matrix[i][j] = rBtn;
            }
        }
    }

}