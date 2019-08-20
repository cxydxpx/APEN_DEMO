package com.example.okhttpsimple.interceptor;

import android.util.Log;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-08-03.
 * GitHub：https://github.com/cxydxpx
 */
public class IBridgeInterceptor implements IInterceptor {
    @Override
    public String interceptor(IChain chain) {

        Log.v("TAG", "1 执行 IBridgeInterceptor 拦截器之前代码");

        String proceed = chain.proceed(chain.request());

        Log.v("TAG", "5 执行 BridgeInterceptor 拦截器之后代码 得到最终数据：" + proceed);

        return "IBridgeInterceptor " +  proceed;
    }
}
