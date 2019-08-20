package com.apen.simple.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-12.
 * GitHub：https://github.com/cxydxpx
 */

public class MessengerService extends Service {

    private static final int from_client = 1;

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case from_client:
                    Log.v("tag", "receive msg from client : " + msg.getData().get("msg"));

                    Messenger client = msg.replyTo;

                    Message relpyMsg = Message.obtain(null, 2);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "嗯，你的消息我已经收到了，稍后回复你。。。");
                    relpyMsg.setData(bundle);

                    try {
                        client.send(relpyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
