package com.dofrom.android.aidldemo.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-17.
 * GitHub：https://github.com/cxydxpx
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String db_name = "book_provider.db";
    public static final String book_table_name = "book";
    public static final String user_table_name = "user";

    private String create_book_table = "create table if not exists "
            + book_table_name + "(_id integer primary key," + "name text)";
    private String create_user_table = "create table if not exists "
            + user_table_name + "(_id integer primary key," + "name text," + "sex int)";

    public DbOpenHelper(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "
                + book_table_name + "(id integer primary key," + "name text)");
        db.execSQL("create table if not exists "
                + user_table_name + "(id integer primary key," + "name text," + "sex int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
