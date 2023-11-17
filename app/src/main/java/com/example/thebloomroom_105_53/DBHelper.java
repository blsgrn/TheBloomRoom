package com.example.thebloomroom_105_53;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//import org.jetbrains.annotations.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "the_bloom_room_db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME1 = "users";

    public static final String TABLE_NAME2 = "flowers";




    private static final String CREATE_USERS_TABLE = "create table "+
            TABLE_NAME1+
            " (user_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT);";

    private static final String CREATE_FLOWERS_TABLE = "create table "+
            TABLE_NAME2+
            " (flower_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price REAL, category TEXT, image_filename TEXT);";






    private static final String CREATE_TESTING_TABLE = "CREATE TABLE test(" +
            "test_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "test_flowername TEXT" +
            ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user table when the database is created
        try {
            db.execSQL(CREATE_USERS_TABLE);
            db.execSQL(CREATE_FLOWERS_TABLE);
            db.execSQL(CREATE_TESTING_TABLE);
            // Add log statements to check if tables are created successfully
            Log.d("DBHelper", "Tables created successfully");
        } catch (Exception e) {
            Log.e("DBHelper", "Error creating tables", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
        // Drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS flowers");
        db.execSQL("DROP TABLE IF EXISTS test ");


        // Create a new table with the updated schema
        onCreate(db);

    }

    public List<Flower> getAllFlowers() {
        List<Flower> flowerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);

        while (cursor.moveToNext()) {

            int flowerId = cursor.getInt(cursor.getColumnIndex("flower_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String imageFilename = cursor.getString(cursor.getColumnIndex("image_filename"));

            Flower flower = new Flower(flowerId, name, description, price, category, imageFilename);
            flowerList.add(flower);


        }

        cursor.close();
        db.close();

        return flowerList;
    }
}
