package com.example.yuchi.coming.common.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yuchi.coming.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by choes_000 on 2015/4/6.
 */
public class TimerDbHelper extends SQLiteOpenHelper {

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

    //Delete Query
    public void removeFav(int id) {
        String countQuery = "DELETE FROM " + TABLE_NAME + " where " + COLUMN_NAME_ENTRY_ID + "= " + id ;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(countQuery);
    }

    public boolean hasEvent(){
        Cursor c = fetchEvents();
        return c.moveToFirst();
    }

    // 讀取所有記事資料
    private List<Map<String, Object>> getData()
    {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        if (cursor.moveToFirst()) {
            do {
                map = new HashMap<String, Object>();
                map.put("title", "跆拳道");
                map.put("info", "快乐源于生活...");
                list.add(map);
            } while (cursor.moveToNext());
        }
        return list;
    }


    public Cursor fetchEvents(){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.query(
                TABLE_NAME, //Database name.
                null,           //Column name.
                null,
                null,
                null,
                null,
                null);
    }

    //Get FavList
    public List<FavoriteList> getFavList(){

        List<FavoriteList> FavList = new ArrayList<FavoriteList>();
        if (cursor.moveToFirst()) {
            do {
                FavoriteList list = new FavoriteList();
                list.setId(Integer.parseInt(cursor.getString(0)));
                list.setName(cursor.getString(1));
                list.setAge(cursor.getString(2));
                FavList.add(list);
            } while (cursor.moveToNext());
        }
        return FavList;
    }

    public Cursor fetchNote(long rowId) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor mCursor =
                db.query(true,
                        TABLE_NAME,
                        new String[] {COLUMN_NAME_ENTRY_ID, COLUMN_EVENT_CONTENT, COLUMN_TIMER_CHANGETOSECOND},
                        COLUMN_NAME_ENTRY_ID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
}