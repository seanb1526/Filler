package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        playerBoard = player1.getBoard(); // create a player using model

        //initialize and create game board - change this later because players will share the game board
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
                //rBtn.setButtonDrawable(null);   // remove the circle on the button
                tv.setWidth(100);
                tv.setHeight(100);
                tv.setText("---");
                tv.setTextColor(color);
                //rBtn.setLayoutParams(new GridLayout.LayoutParams());
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
            //gridChild.setBackgroundColor(color);
            CheckPoints(x,y,color);
            System.out.println("after loop 2");
        }

            //set color of the above textView here

    }


    public void ColorChange(View v){
        Button clicked = (Button) v;
        int colorChanging =  clicked.getCurrentTextColor();
        SearchBoard(colorChanging);
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

                    if(row == 7 && col == 0){
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col+1, color);
                    }
                    else if(row == 7 && col == 7){
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else if(row == 0 && col == 0){
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row, col+1, color);
                    }
                    else if(row == 0 && col == 7){
                        //check win
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else if(row == 0){
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row, col+1, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else if(row == 7){
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col+1, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else if(col == 0){
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row, col+1, color);
                        SetCoordsColor(row-1, col, color);
                    }
                    else if(col == 7){
                        SetCoordsColor(row+1, col, color);
                        SetCoordsColor(row-1, col, color);
                        SetCoordsColor(row, col-1, color);
                    }
                    else{
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