package com.example.filler;
/* LeaderBoard displays the 3 high scores (lowest scores) of previously played games, with their corresponding
   player names. We search our SQLite database and find the lowest scores to display.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Leaderboard extends AppCompatActivity {
    private TextView firstPlaceTextView;
    private TextView secondPlaceTextView;
    private TextView thirdPlaceTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        firstPlaceTextView = findViewById(R.id.firstDisplay);
        secondPlaceTextView = findViewById(R.id.secondDisplay);
        thirdPlaceTextView = findViewById(R.id.thirdDisplay);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<ScoreModel> lowestScores = dbHelper.getThreeLowestScores();

        if (lowestScores.size() >= 1) {
            firstPlaceTextView.setText(lowestScores.get(0).getName() + ": " + lowestScores.get(0).getScore());
        }

        if (lowestScores.size() >= 2) {
            secondPlaceTextView.setText(lowestScores.get(1).getName() + ": " + lowestScores.get(1).getScore());
        }

        if (lowestScores.size() >= 3) {
            thirdPlaceTextView.setText(lowestScores.get(2).getName() + ": " + lowestScores.get(2).getScore());
        }
    }

    // ActionListener for exit button to leave the activity and return to Lobby
    public void exit(View view) {
        Intent intent = new Intent(Leaderboard.this, LobbyActivity.class);
        startActivity(intent);
    }
}