package com.apen.simple.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.apen.simple.service.TcpServerService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-12-18.
 * GitHub：https://github.com/cxydxpx
 */

public class SocketActivity extends BaseActivity {


    @BindView(R.id.et_content)
    EditText etContent;

    @BindView(R.id.tv)
    TextView tvMessage;

    @BindView(R.id.tv_send)
    TextView tvSend;

    @OnClick(R.id.tv_send)
    void send() {
        final String msg = etContent.getText().toString();
        if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
            mPrintWriter.println(msg);
            etContent.setText("");
            String time = formatDateTime(System.currentTimeMillis());
            final String shwedMsg = "self " + time + " : " + msg + "\n";
            tvMessage.setText(tvMessage.getText() + shwedMsg);
        }
    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_socket;
    }

    @Override
    protected void init() {
        super.init();

        Intent service = new Intent(this, TcpServerService.class);
        startService(service);

        new Thread() {
            @Override
            public void run() {
                socketService();
            }
        }.start();

    }

    private void socketService() {
        Socket socket = null;

        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;

                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(message_socket_connected);
                Log.v(tag, "connect server success");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                e.printStackTrace();
            }
        }

        // 接收服务器消息
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!SocketActivity.this.isFinishing()) {
                String msg = br.readLine();
                Log.v(tag, "reveive:" + msg);
                if (msg != null) {
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server" + time + ":" + msg + "\n";
                    mHandler.obtainMessage(message_reveiver_new_msg, showedMsg).sendToTarget();
                    Log.v(tag, "quit...");
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String formatDateTime(long l) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(l));
    }

    private Socket mClientSocket;
    private PrintWriter mPrintWriter;
    private static final int message_socket_connected = 2;
    private static final int message_reveiver_new_msg = 1;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case message_reveiver_new_msg:
                    tvMessage.setText(tvMessage.getText() + (String) msg.obj);
                    break;
                case message_socket_connected:
                    tvSend.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };


}
