package com.linuxh2o.clickui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies.db";
    public static final String TABLE_NAME = "movies_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "YEAR";
//    public static final String COL_4 = "GENRE";
//    public static final String COL_5 = "DIRECTOR";
//    public static final String COL_6 = "REVIEW";
//    public static final String COL_7 = "RATINGBAR";


    public DatabaseHelper(@Nullable Context context/*, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, YEAR TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertMovieData(String name, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, year);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getTheData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }
}
