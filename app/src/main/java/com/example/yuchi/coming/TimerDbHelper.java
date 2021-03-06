package com.example.yuchi.coming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by choes_000 on 2015/4/6.
 */
public class TimerDbHelper extends SQLiteOpenHelper {

    public static final String TAG = "TimerDbHelper";

    public static final String TABLE_NAME = "entry";
    public static final String COLUMN_NAME_NULLABLE = "null";

    public static final String COLUMN_NAME_ENTRY_ID = "_id";
    public static final String COLUMN_EVENT_CONTENT = "event";
    public static final String COLUMN_TIMER_CHANGETOSECOND = "second";

    /* Database name*/
    public static final String DATABASE_NAME = "data.db";
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_ENTRIES =
              "CREATE TABLE " + TABLE_NAME + " (" +
              COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
              COLUMN_EVENT_CONTENT + " TEXT NOT NULL" + COMMA_SEP +
              COLUMN_TIMER_CHANGETOSECOND + " INTEGER NOT NULL" +");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public TimerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    //Get Row Count
    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        return count;
    }

    //Insert data
    public long add(TimerPack timerPack){
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_CONTENT, timerPack.getEventStr());
        values.put(COLUMN_TIMER_CHANGETOSECOND, timerPack.getTotalSecond());
        return db.insert(
                TABLE_NAME,
                COLUMN_NAME_NULLABLE,
                values);
    }

    public int updateFav(TimerPack timerPack){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String where = COLUMN_NAME_ENTRY_ID + "=" + timerPack.getId();
        values.put(COLUMN_TIMER_CHANGETOSECOND, timerPack.getTotalSecond());
        values.put(COLUMN_EVENT_CONTENT, timerPack.getEventStr());
        return db.update(TABLE_NAME, values, where, null);
    }
    //Delete data
    public void removeFav(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i(TAG, "Delete ID: " + id);
        String removeQuery = "DELETE FROM " + TABLE_NAME + " where " + COLUMN_NAME_ENTRY_ID + "= " + id ;
        db.execSQL(removeQuery);
    }

    public Cursor fetchTheEvent(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        String where =  COLUMN_NAME_ENTRY_ID + "= " + id;
        return db.query(TABLE_NAME, null, where, null, null, null, null, null);
    }


    public Cursor fetchEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_NAME, //Database name.
                null,           //Column name.
                null,
                null,
                null,
                null,
                null);
    }

    // 讀取所有記事資料
    public List<HashMap<String, Object>> getData()
    {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_NAME_ENTRY_ID, COLUMN_EVENT_CONTENT, COLUMN_TIMER_CHANGETOSECOND
        };
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map;
        if (cursor.moveToFirst()) {
            do {
                map = new HashMap<String, Object>();
                map.put(COLUMN_NAME_ENTRY_ID, cursor.getInt(0));
                map.put(COLUMN_EVENT_CONTENT, cursor.getString(1));
                map.put(COLUMN_TIMER_CHANGETOSECOND, cursor.getInt(2));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}