package com.example.filler;

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
