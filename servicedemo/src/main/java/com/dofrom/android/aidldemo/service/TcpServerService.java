package com.dofrom.android.aidldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-17.
 * GitHub：https://github.com/cxydxpx
 */

public class TcpServerService extends Service {

    private boolean mIsServiceDestroy = false;

    private String[] mDefinedMessages = new String[]{
            "你好啊，哈哈",
            "请问你叫什么名字呀？",
            "今天杭州天气不错呀，shy",
            "你知道吗？我是可以喝多个人同时聊天的哦",
            "给你讲个笑话吧：据说爱笑的人运气不会太差"
    };

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroy = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;

            try {
                // 监听本地8688端口
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.err.println("establish tcp server fail ,port : 8688");
                e.printStackTrace();
            }

            while (!mIsServiceDestroy) {
                try {
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void responseClient(Socket client) throws IOException {
        // 用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // 用户向客户端发送消息
        PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(client.getOutputStream())
                ), true);
        out.print("欢迎来到聊天室！");

        while (!mIsServiceDestroy) {
            String str = in.readLine();
            System.out.println("msg from client:" + str);
            if (str == null) {
                // 客户端断开连接
                break;
            }
            ;
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.print(msg);
            System.out.println("send:" + msg);
        }

        System.out.println("client quit.");
        // 关闭流
        client.close();

    }


}
