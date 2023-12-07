package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    PlayerModel player1 = new PlayerModel();
    boolean check = true;
    Random rand;
    GridLayout gameBoard;
    LinearLayout buttonsLay;
    int [][] playerBoard;
    Button b;
    int turnCounter = 0;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create database and databaseHelper
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        setContentView(R.layout.activity_main);
        playerBoard = player1.getBoard(); // create a player using model

        //initialize and create game board - change this later because players will share the game board
        b = findViewById(R.id.button);
        gameBoard = findViewById(R.id.gridLayout);
        buttonsLay = findViewById(R.id.buttonslay);
        int[] colors = {R.color.color1, R.color.color2, R.color.color3,R.color.color4,
                R.color.color5, R.color.color6};
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                rand = new Random();
                int color = colors[new Random().nextInt(6)];
                //
                TextView tv = new TextView(this);
                tv.setBackgroundColor(getResources().getColor(color));

                tv.setWidth(100);
                tv.setHeight(100);
                tv.setMinWidth(100);
                tv.setMinHeight(100);
                tv.setText("---");
                tv.setTextColor(getResources().getColor(color));

                gameBoard.addView(tv);
                tv.setId(Integer.parseInt(i+ "" + j));


            }
        }
        player1.intitializeBoard();
        player1.setBoardLoc(7, 0, 1);
        playerBoard = player1.getBoard();

    }
    public void SetCoordsColor(int x, int y, int color){
        TextView gridChild;
        int index = 0;
        if(x == 0 && y == 0){
            gridChild = (TextView) gameBoard.getChildAt(0);
        }
        else if(x == 7 && y == 7){
            index = (8*7+7);
            gridChild = (TextView) gameBoard.getChildAt(index);
        }
        else{
            index = (8*x+y); //weird math for index
            gridChild = (TextView) gameBoard.getChildAt(index);
        }
        ColorDrawable cd = (ColorDrawable) gridChild.getBackground();
        int colorCode = cd.getColor();
        System.out.println("here we go 2" + color + " +" + colorCode + " " + x + " +" + y);
        if(color == colorCode) {
            System.out.println("inside loop 2");
            player1.setBoardLoc(x,y,1);

            CheckPoints(x,y,color);
            System.out.println("after loop 2");
        }

            //set color of the above textView here

    }


    public void ColorChange(View v){
        b.setEnabled(true);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.success16297);
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();
        Button clicked = (Button) v;
        int colorChanging =  clicked.getCurrentTextColor();
        SearchBoard(colorChanging);
        if(player1.checkWin()){
            System.out.println("Winner!");
            /*  START LEADERBOARD DATA COLLECTION ACTIVITY
                Send the score to the LeaderboardActivity.java
             */
            Intent intent = new Intent(MainActivity.this, GameEnd.class);
            intent.putExtra("score", turnCounter);

            // Start the GameEnd activity

            startActivity(intent);
        }

        b = clicked;
        b.setEnabled(false);
        iterateCount();
    }
    public void iterateCount(){
        turnCounter++;
        TextView t = findViewById(R.id.textView);
        String textToInput = ("Turn Count: " + turnCounter);
        t.setText(textToInput);
        //set the text view holding the number of turn to new number
    }
    public void CheckPoints(int x, int y, int color){
        playerBoard = player1.getBoard();
        TextView gridChild;
        int index = 0;
        if(x != 0){
            index = (8*(x-1)+y);
            gridChild = (TextView) gameBoard.getChildAt(index);

            ColorDrawable cd = (ColorDrawable) gridChild.getBackground();
            int colorCode = cd.getColor();
            if(color == colorCode){
                player1.setBoardLoc(x-1,y,1);
                CheckPoints(x-1,y, color);

            }

        }
        if(y!=0){
            index = (8*x+(y-1));
            gridChild = (TextView) gameBoard.getChildAt(index);

            ColorDrawable cd = (ColorDrawable) gridChild.getBackground();
            int colorCode = cd.getColor();
            if(color == colorCode){
                player1.setBoardLoc(x,y-1,1);
                CheckPoints(x,y-1, color);

            }
        }
        //SearchBoard(color);


    }

    public void SearchBoard(int color){
        playerBoard = player1.getBoard();

        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(Objects.equals(player1.playerBoard[row][col], 1)){
                    int index = (8*row+col);
                    TextView gridChild = (TextView) gameBoard.getChildAt(index);
                    gridChild.setBackgroundColor(color);
                    gridChild.setTextColor(color);

                    if(row == 7 && col == 0){
                        if(player1.playerBoard[row-1][col] == 1 && player1.playerBoard[row][col+1] == 1){
                            continue;
                        }
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col+1, color);
                    }
                    else if(row == 7 && col == 7){
                        if(player1.playerBoard[row-1][col] == 1 && player1.playerBoard[row][col-1] == 1){
                            continue;
                        }
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else if(row == 0 && col == 0){
                        if(player1.playerBoard[row+1][col] == 1 && player1.playerBoard[row][col+1] == 1){
                            continue;
                        }
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row, col+1, color);
                    }
                    else if(row == 0 && col == 7){
                        if(player1.playerBoard[row+1][col] == 1 && player1.playerBoard[row][col-1] == 1){
                            continue;
                        }
                        //check win
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else if(row == 0){
                        if(player1.playerBoard[row+1][col] == 1 && player1.playerBoard[row][col+1] == 1 && player1.playerBoard[row][col-1] == 1){
                            continue;
                        }
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row, col+1, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else if(row == 7){
                        if(player1.playerBoard[row-1][col] == 1 && player1.playerBoard[row][col+1] == 1 && player1.playerBoard[row][col-1] == 1){
                            continue;
                        }
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col+1, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else if(col == 0){
                        if(player1.playerBoard[row+1][col] == 1 && player1.playerBoard[row][col+1] == 1 && player1.playerBoard[row-1][col] == 1){
                            continue;
                        }
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row, col+1, color);
                        SetCoordsColor(row-1, col, color);
                    }
                    else if(col == 7){
                        if(player1.playerBoard[row+1][col] == 1 && player1.playerBoard[row][col-1] == 1 && player1.playerBoard[row-1][col] == 1){
                            continue;
                        }
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else{
                        if(player1.playerBoard[row+1][col] == 1 && player1.playerBoard[row][col+1] == 1 && player1.playerBoard[row-1][col] == 1 && player1.playerBoard[row][col-1] == 1){
                            continue;
                        }
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col-1, color);
                        SetCoordsColor(row, col+1, color);
                    }


                }
            }

        }

    }

}