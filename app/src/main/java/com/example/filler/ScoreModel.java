package com.example.filler;

/* Score Model to help with Score data using the database */
public class ScoreModel {
    private String name;
    private int score;

    public ScoreModel(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
