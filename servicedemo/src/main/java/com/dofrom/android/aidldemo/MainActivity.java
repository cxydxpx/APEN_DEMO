package com.dofrom.android.aidldemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ListView mListView;

    public void startProvider(View view) {



        Intent intent = new Intent();

        Uri bookUri = Uri.parse("content://com.apen.simple.bookprovider/book");

        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);
        Cursor bookCusor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);

        while (bookCusor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCusor.getInt(0);
            book.bookName = bookCusor.getString(1);
            Log.v(TAG, "query book:" + book.toString());
        }
        bookCusor.close();

        Uri userUri = Uri.parse("content://com.apen.simple.bookprovider/user");

        Cursor userCusor = getContentResolver().query(userUri, new String[]{"_id", "name"}, null, null, null);

        while (userCusor.moveToNext()) {
            User user = new User();
            user.userId = bookCusor.getInt(0);
            user.userName = bookCusor.getString(1);
            Log.v(TAG,"query user:" + user.toString());
        }
        userCusor.close();


    }

    private static final String TAG = "TAG";

}
