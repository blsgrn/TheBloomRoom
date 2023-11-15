package com.example.thebloomroom_105_53;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_db";
    private static final int DATABASE_VERSION = 1;

    // Define your user table creation query here
    private static final String CREATE_USER_TABLE = "CREATE TABLE users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT," +
            "password TEXT," +
            "email TEXT" +
            ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user table when the database is created
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
        // Drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS users");

        // Create a new table with the updated schema
        onCreate(db);
    }
}
