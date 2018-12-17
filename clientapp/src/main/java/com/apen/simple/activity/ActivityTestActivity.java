package com.apen.simple.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.apen.simple.tool.UserManager;
import com.dofrom.android.aidldemo.IBookManager;

import java.util.List;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-11.
 * GitHub：https://github.com/cxydxpx
 */

public class ActivityTestActivity extends BaseActivity {

    private Messenger mService;

    @Override
    protected int layoutResId() {
        return R.layout.activity_test_activity;
    }

    @Override
    protected void init() {
        super.init();
        Log.v(TAG, "  ActivityTestActivity  :  " + UserManager.sUserId);
    }

    Binder mBinder;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.v(TAG, "activityTestActivity onNewIntent执行");
    }

    public void actOnclick(View view) {
        startActivity(new Intent(this, ActivityTestActivity.class));
    }

    public void toNext(View view) {
//        startActivity(new Intent(this, GlideActivity.class).putExtra("", "666"));

        Intent intent = new Intent();
        intent.setPackage("com.dofrom.android.aidldemo");
        intent.setAction("com.dofrom.android.aidldemo.action");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    public void addBook(View view) {

        try {
            mIBookManager.addBook("添加一本书");

            List<String> newlist = mIBookManager.getBooks();
            Log.v("TAG", "query list ,list type :" + newlist.getClass().getCanonicalName());
            Log.v("TAG", "query list :" + newlist.toString());


        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    private IBookManager mIBookManager;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mIBookManager = IBookManager.Stub.asInterface(service);
            try {
                List<String> list = mIBookManager.getBooks();
                Log.v("TAG", "query list ,list type :" + list.getClass().getCanonicalName());
                Log.v("TAG", "query list :" + list.toString());

                mIBookManager.addBook("《人家不值得》");


                List<String> newlist = mIBookManager.getBooks();
                Log.v("TAG", "query list ,list type :" + newlist.getClass().getCanonicalName());
                Log.v("TAG", "query list :" + newlist.toString());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection mMessengerConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = Message.obtain(null, 1);
            Bundle date = new Bundle();
            date.putString("msg", "hello ,this client , are you serice ");
            msg.setData(date);

            msg.replyTo = mMessenger;

            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 2:
                    Log.v("TAG", "receive msg from service : " + msg.getData().get("repor"));
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
