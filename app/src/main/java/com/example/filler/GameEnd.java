package com.example.filler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameEnd extends AppCompatActivity {

    private EditText char1, char2, char3;
    private Button enterBtn;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        char1 = findViewById(R.id.char1);
        char2 = findViewById(R.id.char2);
        char3 = findViewById(R.id.char3);
        enterBtn = findViewById(R.id.insertToDB);

        // get Score from intent
        Intent intent = getIntent();
        if (intent != null) {
            finalScore = intent.getIntExtra("score", -1);
        }


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Enable the button if all three EditTexts have one character, otherwise disable it
                enterBtn.setEnabled(
                        char1.getText().length() == 1 &&
                                char2.getText().length() == 1 &&
                                char3.getText().length() == 1
                );
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };

        char1.addTextChangedListener(textWatcher);
        char2.addTextChangedListener(textWatcher);
        char3.addTextChangedListener(textWatcher);



    }   // end OnCreate

    public void buttonClick(View view) {
        // Handle button click action here

        // Retrieve the characters from the EditTexts
        String s1 = char1.getText().toString();
        String s2 = char2.getText().toString();
        String s3 = char3.getText().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s1);
        stringBuilder.append(s2);
        stringBuilder.append(s3);

        String name = stringBuilder.toString();

        // INSERT INTO DATABASE along with SCORE
        dbHelper.insertData(db, name, finalScore);
        enterBtn.setEnabled(false);
    }
}