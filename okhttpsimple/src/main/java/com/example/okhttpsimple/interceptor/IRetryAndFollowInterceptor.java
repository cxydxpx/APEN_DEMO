package com.example.okhttpsimple.interceptor;

import android.util.Log;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-08-03.
 * GitHub：https://github.com/cxydxpx
 */
public class IRetryAndFollowInterceptor implements IInterceptor {
    @Override
    public String interceptor(IChain chain) {

        Log.v("TAG", "2 执行 IRetryAndFollowInterceptor 拦截器之前代码");

        String proceed = chain.proceed(chain.request());

        Log.v("TAG", "4 执行 IRetryAndFollowInterceptor 拦截器之后代码 得到最终数据：" + proceed);

        return "IRetryAndFollowInterceptor" + proceed;
    }
}
