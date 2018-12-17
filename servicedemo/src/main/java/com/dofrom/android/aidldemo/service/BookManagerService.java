package com.dofrom.android.aidldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.dofrom.android.aidldemo.IBookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-17.
 * GitHub：https://github.com/cxydxpx
 */

public class BookManagerService extends Service {


    private List<String> mDatas = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        mDatas.add("低欲望社会");
        mDatas.add("睡眠革命");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MiBinder();
    }

    class MiBinder extends IBookManager.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<String> getBooks() throws RemoteException {
            return mDatas;
        }

        @Override
        public void addBook(String book) throws RemoteException {
            mDatas.add(book);
        }
    }

}
