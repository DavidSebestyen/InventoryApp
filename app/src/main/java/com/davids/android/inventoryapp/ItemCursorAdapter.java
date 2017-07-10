package com.davids.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.davids.android.inventoryapp.data.ItemContract;

/**
 * Created by krypt on 23/11/2016.
 */

public class ItemCursorAdapter extends CursorAdapter {

    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c, 0/* flags */);


    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        final TextView priceTextView = (TextView) view.findViewById(R.id.price);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        Button sellItemButton = (Button) view.findViewById(R.id.sell_button);


        int nameColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_PRICE);
        final int quantityColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY);

        String itemName = cursor.getString(nameColumnIndex);
        int itemPrice = cursor.getInt(priceColumnIndex);
        final int itemQuantity = cursor.getInt(quantityColumnIndex);
        final int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ItemContract.ItemEntry._ID));


        nameTextView.setText(itemName);
        priceTextView.setText(String.valueOf(itemPrice));
        quantityTextView.setText(String.valueOf(itemQuantity));


        sellItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                if (itemQuantity > 0) {
                    int mItemQty;
                    mItemQty = (itemQuantity - 1);
                    values.put(ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY, mItemQty);
                    Uri uri = ContentUris.withAppendedId(ItemContract.ItemEntry.CONTENT_URI, itemId);
                    context.getContentResolver().update(uri, values, null, null);
                }
                context.getContentResolver().notifyChange(ItemContract.ItemEntry.CONTENT_URI, null);


            }
        });
    }
}
