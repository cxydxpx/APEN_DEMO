package com.example.okhttpsimple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.okhttpsimple.interceptor.IBridgeInterceptor;
import com.example.okhttpsimple.interceptor.ICacheInterceptor;
import com.example.okhttpsimple.interceptor.IInterceptor;
import com.example.okhttpsimple.interceptor.IRealInterceptorChain;
import com.example.okhttpsimple.interceptor.IRetryAndFollowInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<IInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new IBridgeInterceptor());
        interceptors.add(new IRetryAndFollowInterceptor());
        interceptors.add(new ICacheInterceptor());

        IRealInterceptorChain request = new IRealInterceptorChain(interceptors, 0, "request");
        String request_2 = request.proceed("main request.procesed()");

        Log.v("TAG", "6 这里应该是最后 : " + request_2);


        findViewById(R.id.tv)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    initData();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                    }
                });
    }


    private final String url = "https://wanandroid.com/wxarticle/chapters/json";

    private void initData() throws IOException {

        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.v("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.v("TAG", "成功" + string);
            }
        });


    }
}
