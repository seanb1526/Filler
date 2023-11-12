package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView[][] matrix;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrix = new TextView[8][8];
        GridLayout gameBoard = findViewById(R.id.gridLayout);

        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                rand = new Random();
                int color = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

                // these should be buttons not text views
                TextView tv = new TextView(this);
                tv.setBackgroundColor(color);
                tv.setMaxWidth(150);
                tv.setMaxHeight(150);
                tv.setText("---");
                tv.setLayoutParams(new GridLayout.LayoutParams());
                gameBoard.addView(tv);
                tv.setId(Integer.parseInt(i+ "" + j));
                matrix[i][j] = tv;
            }
        }
    }

}