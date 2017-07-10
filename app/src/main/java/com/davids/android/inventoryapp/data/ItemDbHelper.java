package com.davids.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by krypt on 23/11/2016.
 */

public class ItemDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "warehouse.db";

    private static final int DATABASE_VERSION = 2;

    public ItemDbHelper (Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ITEMS_TABLE = "CREATE TABLE " + ItemContract.ItemEntry.TABLE_NAME + " ("
                + ItemContract.ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemContract.ItemEntry.COLUMN_ITEM_NAME + " TEXT NOT NULL, "
                + ItemContract.ItemEntry.COLUMN_ITEM_PRICE + " INTEGER NOT NULL, "
                + ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY + " INTEGER NOT NULL, "
                + ItemContract.ItemEntry.COLUMN_ITEM_IMAGE + " TEXT);";

        db.execSQL(SQL_CREATE_ITEMS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ItemContract.ItemEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
