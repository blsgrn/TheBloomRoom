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
    public static final String TABLE_NAME4 = "payment_records";




    private static final String CREATE_USERS_TABLE = "create table "+
            TABLE_NAME1+
            " (user_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT);";

    private static final String CREATE_FLOWERS_TABLE = "create table "+
            TABLE_NAME2+
            " (flower_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price REAL, category TEXT, image_filename TEXT);";



    private static final String CREATE_CART_TABLE = "create table "+
            TABLE_NAME3+
            " (cart_id INTEGER PRIMARY KEY AUTOINCREMENT, item_id INT, item_name TEXT, item_price REAL, item_category TEXT);";


    private static final String CREATE_PAYMENT_RECORD_TABLE = "create table " +
            TABLE_NAME4 +
            "(receipt_no INTEGER PRIMARY KEY AUTOINCREMENT, customer TEXT, total REAL, method TEXT);";

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
            db.execSQL(CREATE_PAYMENT_RECORD_TABLE);

            //inserting default data into tables
            insertDefaultFlowers(db);
            insertDefaultUsers(db);
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
        db.execSQL("DROP TABLE IF EXISTS payment_records");

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

    //delete from cart
    public void deleteCartItem(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME3, "item_id = ?", new String[]{String.valueOf(itemId)});
        db.close();
    }

    //calculate total of cart items
    // Method to get the total price of all items in the cart
    public double getTotalCartPrice() {
        double total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(item_price) FROM " + TABLE_NAME3, null);
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return total;


    }

    //methods for payment_records table
    public List<PaymentRecord> getAllPaymentRecords() {
        List<PaymentRecord> paymentRecordList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME4, null);

        while (cursor.moveToNext()) {
            int receiptNo = cursor.getInt(cursor.getColumnIndex("receipt_no"));
            String customer = cursor.getString(cursor.getColumnIndex("customer"));
            double total = cursor.getDouble(cursor.getColumnIndex("total"));
            String method = cursor.getString(cursor.getColumnIndex("method"));

            PaymentRecord paymentRecord = new PaymentRecord(receiptNo, customer, total, method);
            paymentRecordList.add(paymentRecord);
        }

        cursor.close();
        db.close();

        return paymentRecordList;
    }

    public long insertPaymentRecord(String customer, double total, String method) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("customer", customer);
        values.put("total", total);
        values.put("method", method);

        // Insert data into the payment_records database
        long newRowId = db.insert(TABLE_NAME4, null, values);

        // Close the database connection
        db.close();

        return newRowId;
    }
/*NOTE: I am inserting default records (for users table and flowers table) for demonstration purpose only.
 Data must be inserted into tables using the app. */


    private void insertDefaultUsers(SQLiteDatabase db) {
        // Insert user: john
        db.execSQL("INSERT INTO " + TABLE_NAME1 + " (username, password, email) VALUES ('john', 'john123', 'john@example.com');");

        // Insert user: admin
        db.execSQL("INSERT INTO " + TABLE_NAME1 + " (username, password, email) VALUES ('admin', 'admin123', 'admin@example.com');");
    }

    private void insertDefaultFlowers(SQLiteDatabase db) {
    String[] flowerNames = {
            "Red Roses", "Pink Delight Roses",
            "White Calla Lily", "Stargazer Lily",
            "Phalaenopsis Orchid", "Dendrobium Orchid",
            "Spring Tulips", "Autumn Sunflowers",
            "Rainbow Bouquet", "Pastel Harmony Mix",
            "Blue Mystique Rose", "Golden Anniversary Orchid"
    };

    String[] descriptions = {
            "Red roses symbolize love and passion. Their vibrant color and enchanting fragrance make them a perfect expression of romantic feelings.",
            "Pink roses convey admiration and gratitude. This delightful shade adds a touch of elegance to any occasion, expressing appreciation and sweetness.",
            "White calla lilies represent purity and innocence. Their simple yet sophisticated beauty makes them a classic choice for weddings and celebrations.",
            "Stargazer lilies symbolize prosperity and success. With their bold, upward-facing blooms and captivating fragrance, they make a statement in any arrangement.",
            "Phalaenopsis orchids exude grace and beauty. Known for their long-lasting flowers, they add a touch of luxury to any space.",
            "Dendrobium orchids symbolize strength and endurance. Their vibrant colors and unique shape create an exotic and captivating display.",
            "Spring tulips herald the arrival of a new season with their vibrant colors. They bring joy and freshness to any springtime celebration.",
            "Autumn sunflowers capture the warmth of the season. Their golden petals evoke the beauty of fall, making them a perfect choice for autumn bouquets.",
            "A rainbow bouquet features an assortment of vibrant blooms, symbolizing diversity and celebration. It's a colorful expression of joy and positivity.",
            "The pastel harmony mix combines soft hues for a serene and elegant arrangement. This blend of colors creates a soothing and delightful visual experience.",
            "The blue mystique rose is a rare and enchanting flower. Its deep blue petals symbolize mystery and the unattainable, making it a truly special gift.",
            "The golden anniversary orchid represents 50 years of love and commitment. With its golden blooms, it's a stunning choice to celebrate a milestone anniversary."
    };

    double[] prices = {
            12.99, 14.99,
            18.99, 22.99,
            25.99, 21.99,
            15.99, 17.99,
            29.99, 26.99,
            35.99, 42.99
    };

    String[] categories = {
            "Rose", "Rose",
            "Lily", "Lily",
            "Orchid", "Orchid",
            "Seasonal", "Seasonal",
            "Mixed", "Mixed",
            "Special", "Special"
    };

    String[] imageFileNames = {
            "red_roses", "pink_delight_roses",
            "white_calla_lily", "stargazer_lily",
            "phalaenopsis_orchid", "dendrobium_orchid",
            "spring_tulips", "autumn_sunflowers",
            "rainbow_bouquet", "pastel_harmony_mix",
            "blue_mystique_rose", "golden_anniversary_orchid"
    };
    for (int i = 0; i < flowerNames.length; i++) {
        ContentValues values = new ContentValues();
        values.put("name", flowerNames[i]);
        values.put("description", descriptions[i]);
        values.put("price", prices[i]);
        values.put("category", categories[i]);
        values.put("image_filename", imageFileNames[i]);

        // Insert data into the flowers table
        db.insert(TABLE_NAME2, null, values);
    }
}


}
