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
    public int[][] playerBoard;  // personal player board
    int colorSelected;
    int turn;
    public PlayerModel(){
        playerBoard = new int[8][8];
    }
    public void StartGame(){
       // playerBoard[0][7] = "2";
        playerBoard[7][0] = 1;

    }
    public void setBoardLoc(int x, int y, int change){
        playerBoard[x][y] = change;
    }
    public int[][] getBoard(){
//        for(int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//
//                System.out.print(playerBoard[i][j]);
//            }
//            System.out.println();
//        }
        return playerBoard;
    }
    public void intitializeBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                playerBoard[i][j] = 0;
                System.out.print(playerBoard[i][j]);
            }
            System.out.println();
        }
    }
    public boolean checkWin()
    {
        int count = 0;
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(playerBoard[i][j] == 1){
                    count++;
                }

            }
        }
        return count == 64;
    }


}
