package com.apen.simple.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-11.
 * @author apen
 * GitHub：https://github.com/cxydxpx
 */

public class User implements Parcelable{

//    private static final long serialVersionUID = 519067123721295773L;

    public int usrId;

    public String userName;

    public boolean isMale;


    public User(int i, String jake, boolean b) {
        this.usrId = i;
        this.userName = jake;
        this.isMale = b;
    }

    protected User(Parcel in) {
        usrId = in.readInt();
        userName = in.readString();
        isMale = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "User{" +
                "usrId=" + usrId +
                ", userName='" + userName + '\'' +
                ", isMale=" + isMale +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(usrId);
        dest.writeString(userName);
        dest.writeByte((byte) (isMale ? 1 : 0));
    }
}
