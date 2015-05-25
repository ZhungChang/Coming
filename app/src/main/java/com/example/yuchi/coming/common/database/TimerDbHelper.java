package com.example.yuchi.coming.common.database;

import static com.example.yuchi.coming.common.database.TimerContract.FeedEntry.*;
import static com.example.yuchi.coming.common.database.TimerContract.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by choes_000 on 2015/4/6.
 */
public class TimerDbHelper extends SQLiteOpenHelper {
    /* Database name*/
    public static final String DATABASE_NAME = "data.db";
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
              "CREATE TABLE " + TABLE_NAME + " (" +
              COLUMN_NAME_ENTRY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
              COLUMN_EVENT_CONTENT + "CHAR" + COMMA_SEP +
              COLUMN_TIMER_CHANGETOSECOND + "INTEGER NOT NULL" + COMMA_SEP  + ")";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public TimerDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void add(SQLiteDatabase db,TimerDbHelper mDbHelper, String content, String sec){
        // Gets the data repository in write mode
        SQLiteDatabase DB = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(FeedEntry.COLUMN_EVENT_CONTENT, content);
        values.put(FeedEntry.COLUMN_TIMER_CHANGETOSECOND, sec);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedEntry.TABLE_NAME,
                FeedEntry.COLUMN_NAME_NULLABLE,
                values);
    }
}
