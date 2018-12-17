package com.dofrom.android.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-11.
 * GitHub：https://github.com/cxydxpx
 */

public class Book implements Parcelable{

    public int bookId;
    public String bookName;

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "userId=" + bookId +
                ", userName='" + bookName + '\'' +
                '}';
    }

    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
}
