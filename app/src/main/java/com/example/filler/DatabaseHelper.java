package com.example.filler;

/* DatabaseHelper program to utilize SQLite database to our purposes

    Our database is used in the Leaderboard Activity; it stores a 3-Character string as a NAME
    and that players Integer SCORE.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    String title;
    private static final String DB_NAME = "FillerDB"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database
    DatabaseHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Filler (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SCORE INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static void insertData(SQLiteDatabase db, String name, int score) {
        ContentValues FillerValues = new ContentValues();
        FillerValues.put("NAME", name);
        FillerValues.put("SCORE", score);

        db.insert("Filler", null, FillerValues);
    }

    public static void deleteData(SQLiteDatabase db, String name) {
        // not sure how to do this.
        String selection = "NAME" + " LIKE ?";
        String[] selectionArgs = { name };
        db.delete("Filler", selection, selectionArgs);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Filler";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public List<ScoreModel> getThreeLowestScores() {
        List<ScoreModel> scores = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Filler ORDER BY Score ASC LIMIT 3";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("NAME"));
                int score = cursor.getInt(cursor.getColumnIndex("SCORE"));


                ScoreModel scoreModel = new ScoreModel(name, score);
                scores.add(scoreModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return scores;
    }


}
