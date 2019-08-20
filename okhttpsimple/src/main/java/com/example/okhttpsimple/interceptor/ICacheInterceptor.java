package com.example.okhttpsimple.interceptor;

import android.util.Log;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-08-03.
 * GitHub：https://github.com/cxydxpx
 */
public class ICacheInterceptor implements IInterceptor {
    @Override
    public String interceptor(IChain chain) {

        Log.v("TAG", "3 执行 ICacheInterceptor 最后一个拦截器 返回最终数据");
        return "ICacheInterceptor success + " + chain.request();
    }
}
