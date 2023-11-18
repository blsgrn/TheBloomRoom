package com.example.thebloomroom_105_53;

import android.content.ContentValues;
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

    public static final String TABLE_NAME3 = "cart";




    private static final String CREATE_USERS_TABLE = "create table "+
            TABLE_NAME1+
            " (user_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT);";

    private static final String CREATE_FLOWERS_TABLE = "create table "+
            TABLE_NAME2+
            " (flower_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price REAL, category TEXT, image_filename TEXT);";



    private static final String CREATE_CART_TABLE = "create table "+
            TABLE_NAME3+
            " (cart_id INTEGER PRIMARY KEY AUTOINCREMENT, item_id INT, item_name TEXT, item_price REAL, item_category TEXT);";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user table when the database is created
        try {
            db.execSQL(CREATE_USERS_TABLE);
            db.execSQL(CREATE_FLOWERS_TABLE);
            db.execSQL(CREATE_CART_TABLE);
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
        db.execSQL("DROP TABLE IF EXISTS cart ");


        // Create a new table with the updated schema
        onCreate(db);

    }
//Methods to read/delete from flowers table
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

    public void deleteFlower(int flowerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, "flower_id = ?", new String[]{String.valueOf(flowerId)});
        db.close();
    }

    //add to cart
    public long addToCart(int itemId, String itemName, double itemPrice, String itemCategory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("item_id", itemId);
        values.put("item_name", itemName);
        values.put("item_price", itemPrice);
        values.put("item_category", itemCategory);


        // Insert data into the cart database
        long newRowId = db.insert(TABLE_NAME3, null, values);

        // Close the database connection
        db.close();

        return newRowId;
    }

    //get items from cart
    public List<CartItem> getAllCartItems() {
        List<CartItem> cartItemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME3, null);

        while (cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndex("item_id"));
            String itemName = cursor.getString(cursor.getColumnIndex("item_name"));
            double itemPrice = cursor.getDouble(cursor.getColumnIndex("item_price"));
            String itemCategory = cursor.getString(cursor.getColumnIndex("item_category"));

            CartItem cartItem = new CartItem(itemId, itemName, itemPrice, itemCategory);
            cartItemList.add(cartItem);
        }

        cursor.close();
        db.close();

        return cartItemList;
    }

}
