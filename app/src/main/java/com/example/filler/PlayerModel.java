package com.example.filler;

import android.graphics.Color;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

//Model for MVC
public class PlayerModel {
    public String[][] playerBoard;  // personal player board
    int colorSelected;
    int turn;
    public void StartGame(){
        playerBoard[0][7] = "2";
        playerBoard[7][0] = "1";
        GameLoop();
    }
    public void GameLoop()
    {

    }

    public String[] SearchBoard(){

        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(Objects.equals(playerBoard[row][col], "1") || Objects.equals(playerBoard[row][col], "2")){
                    if(row == 0 && col == 0){
                        //check/set the color to the right and down to new color
                    }
                    //might take the two starting ones out we'll see
                    else if(row == 0 && col == 7){
                        //check/set color down and left
                    }
                    else if(row == 7 && col == 0){
                        //check/set color up and right
                    }
                    else if(row == 7 && col == 7){
                        //check/set color to the left and up one
                    }
                    else if(row == 0){
                        //check/set color to the right left and down
                    }
                    else if(col == 0){
                        //check/set color to the right and up
                    }
                    else if(row == 7){
                        //check/set color to the up and right
                    }
                    else if(col == 7){
                        //check/set color to the left and down
                    }
                }
            }
        }
    }
}
