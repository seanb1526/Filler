package com.example.filler;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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




}
