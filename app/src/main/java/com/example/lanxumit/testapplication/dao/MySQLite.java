package com.example.lanxumit.testapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySQLite extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table book ( id integer primary key autoincrement," +
            "author text,price real,pages Integer,name text) ";
    private Context mContext;

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     */

    public MySQLite(@androidx.annotation.Nullable Context context, @androidx.annotation.Nullable String name,
                    @androidx.annotation.Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext, "create table book success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
