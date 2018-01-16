package com.example.musfiqrahman.waitlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ShengXiao on 2018-01-15.
 */
public class GuestDBAdapter {
    private final Context context;

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    public GuestDBAdapter(Context context) {
        this.context = context;
        myDBHelper=new DatabaseHelper(this.context);
    }
    public GuestDBAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    public long insertRow(String guestName, int guestNum) {
        // [TO_DO_A8]
        // Update data in the row with new fields.
        // Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(GuestDBContractor.DB_GUEST_NAME, guestName);
        initialValues.put(String.valueOf(GuestDBContractor.DB_GUEST_NUMBER),guestNum);

        // Insert it into the database.
        return db.insert(GuestDBContractor.DB_TABLE_NAME, null, initialValues);
    }
    public Cursor getAllRows() {
        String where = null;
        Cursor c = 	db.query(true, GuestDBContractor.DB_TABLE_NAME, GuestDBContractor.ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    private static class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context) {
            super(context, GuestDBContractor.DB_NAME, null, GuestDBContractor.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(GuestDBContractor.DB_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
