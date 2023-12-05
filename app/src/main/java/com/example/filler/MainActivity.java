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

        if(x == 0 && y == 0){
            gridChild = (TextView) gameBoard.getChildAt(0);
        }
        else if(x == 7 && y == 7){
            int index = (8*7+7);
            gridChild = (TextView) gameBoard.getChildAt(index);
        }
        else{
            int index = (8*x+y); //weird math for index
            gridChild = (TextView) gameBoard.getChildAt(index);
        }
        ColorDrawable cd = (ColorDrawable) gridChild.getBackground();
        int colorCode = cd.getColor();
        System.out.println("here we go 2" + color + " +" + colorCode + " " + x + " +" + y);
        if(color == colorCode) {
            System.out.println("inside loop 2");
            player1.setBoardLoc(x,y,1);
            gridChild.setBackgroundColor(color);
            changeTextViewColor(gridChild, color);
            System.out.println("after loop 2");
        }
        //gridChild.setBackgroundColor(color);
            //set color of the above textView here

    }
    public void changeTextViewColor(TextView t, int color){
        t.setBackgroundColor(color);
    }

    public void ColorChange(View v){
//        int index = (8*3+4) -1;
//
//        TextView gridChild = (TextView) gameBoard.getChildAt(index);

        Button clicked = (Button) v;

        int colorChanging =  clicked.getCurrentTextColor();
        SearchBoard(colorChanging);
//        gridChild.setBackgroundColor(colorChanging);

    }

    public void SearchBoard(int color){
        playerBoard = player1.getBoard();
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(Objects.equals(player1.playerBoard[row][col], 1)){

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
//                    if(row-1>=0 && row+1 <=7){
//                       // System.out.println("NOT out of bounds1 " + (row+1) +""+ (row-1) + ""+col);
////                        player1.setBoardLoc(row+1,col, 1);
////                        player1.setBoardLoc(row-1,col, 1);
//                        SetCoordsColor(row+1, col, color);
//                      //  System.out.println("bruh1.2");
//
//                        SetCoordsColor(row-1,col,color);
//                       // System.out.println("bruh1");
//                    }
//                    else if(row-1 >=0 && row+1>7){
//                        //System.out.println("NOT out of bounds2" + (row-1)+""+col);
//
////                        player1.setBoardLoc(row-1,col, 1);
//                        SetCoordsColor(row-1, col, color);
//                       // System.out.println("bruh2");
//
//
//                    }
//                    else if(row+1 <=7 && row-1<0){
//                       // System.out.println("NOT out of bounds3" + (row+1)+""+col);
//
////                        player1.setBoardLoc(row+1,col,1);
//                        SetCoordsColor(row+1, col, color);
//                       // System.out.println("bruh3");
//
//                    }
////                    if(col+1>=7 || col-1 <0){
////                        System.out.println("out of bounds");
////                    }
//                    if(col-1 >= 0 && col + 1 <=7){
//                       // System.out.println("NOT out of bounds4"+ (col+1)+""+ (col-1) + "" + row);
//
////                        player1.playerBoard[row][col-1] = 1;
////                        player1.playerBoard[row][col+1] = 1;
//                        SetCoordsColor(row, col-1, color);
//                       // System.out.println("bruh4.5");
//                        SetCoordsColor(row,col+1,color);
//                        //System.out.println("bruh4");
//
//                    }
//                    else if(col-1 >=0 && col+1>7){
//                      //  System.out.println("NOT out of bounds5"+ (col-1));
//
////                        player1.playerBoard[row][col-1] = 1;
//                        SetCoordsColor(row, col-1, color);
//                       // System.out.println("bruh5");
//
//
//                    }
//                    else if(col+1 <=7 && col-1 < 0){
//                        //System.out.println("NOT out of bounds6"+ (col+1));
//
////                        player1.playerBoard[row][col+1] = 1;
//                        SetCoordsColor(row, col+1, color);
//                       // System.out.println("bruh6");
//
//                    }


                }
            }

        }

    }

}