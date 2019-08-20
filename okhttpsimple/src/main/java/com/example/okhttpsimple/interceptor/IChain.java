package com.example.okhttpsimple.interceptor;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-08-05.
 * GitHub：https://github.com/cxydxpx
 */
public interface IChain {

    String request();

    String proceed(String request);
}
