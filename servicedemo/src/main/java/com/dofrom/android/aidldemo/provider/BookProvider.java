package com.dofrom.android.aidldemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-17.
 * GitHub：https://github.com/cxydxpx
 */

public class BookProvider extends ContentProvider {

    private static final String TAG = "TAG";

    public static final String authority = "com.apen.simple.bookprovider";

    public static final Uri book_content_uri = Uri.parse("content://" + authority + "/book");
    public static final Uri user_content_uri = Uri.parse("content://" + authority + "/user");

    public static final int book_uri_code = 0;
    public static final int user_uri_code = 1;


    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI(authority, "book", book_uri_code);
        mUriMatcher.addURI(authority, "user", user_uri_code);
    }


    private Context mContext;


    /**
     * ContentProvider 的创建
     *
     * @return
     */
    @Override
    public boolean onCreate() {

        Log.v(TAG, "onCreate current thread : " + Thread.currentThread().getName());

        mContext = getContext();

        initProvider();

        return true;
    }

    private SQLiteDatabase mDbOpenHelper;

    private void initProvider() {

        mDbOpenHelper = new DbOpenHelper(mContext).getWritableDatabase();

        mDbOpenHelper.execSQL("delete from " + DbOpenHelper.book_table_name);
        mDbOpenHelper.execSQL("delete from " + DbOpenHelper.user_table_name);

        mDbOpenHelper.execSQL("insert into book values(3,'android');");
        mDbOpenHelper.execSQL("insert into book values(4,'Ios');");
        mDbOpenHelper.execSQL("insert into book values(5,'Html5');");

        mDbOpenHelper.execSQL("insert into user values(1,'jake');");
        mDbOpenHelper.execSQL("insert into user values(2,'jasm');");

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Log.v(TAG, "query current thread : " + Thread.currentThread().getName());

        String tabal = getTableName(uri);
        if (tabal == null) {
            throw new IllegalArgumentException("Unsupport URI :" + uri);
        }
        return mDbOpenHelper.query(tabal, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    /**
     * 获取媒体的类型
     *
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        Log.v(TAG, "getType current thread : " + Thread.currentThread().getName());

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Log.v(TAG, "insert");
        String tabal = getTableName(uri);
        if (tabal == null) {
            throw new IllegalArgumentException("Unsupport URI :" + uri);
        }

        mDbOpenHelper.insert(tabal, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.v(TAG, "delete ");
        String tabal = getTableName(uri);
        if (tabal == null) {
            throw new IllegalArgumentException("Unsupport URI :" + uri);
        }
        int count = mDbOpenHelper.delete(tabal, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.v(TAG, "update ");

        String tabal = getTableName(uri);
        if (tabal == null) {
            throw new IllegalArgumentException("Unsupport URI :" + uri);
        }

        int row = mDbOpenHelper.update(tabal, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return row;
    }

    private String getTableName(Uri uri) {
        String tableName = null;

        switch (mUriMatcher.match(uri)) {
            case book_uri_code:
                tableName = DbOpenHelper.book_table_name;
                break;
            case user_uri_code:
                tableName = DbOpenHelper.user_table_name;
                break;
            default:
                break;
        }

        return tableName;
    }

}
