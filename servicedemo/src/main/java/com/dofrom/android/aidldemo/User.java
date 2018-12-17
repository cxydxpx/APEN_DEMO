package com.dofrom.android.aidldemo;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-17.
 * GitHub：https://github.com/cxydxpx
 */

public class User {

    public int userId;
    public String userName;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
