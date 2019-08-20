package com.example.okhttpsimple.interceptor;

import java.util.List;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-08-03.
 * GitHub：https://github.com/cxydxpx
 */
public class IRealInterceptorChain implements IChain {

    private List<IInterceptor> interceptors;

    private int index;

    private String request;

    public IRealInterceptorChain(List<IInterceptor> interceptors, int index, String request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @Override
    public String request() {
        return request;
    }

    @Override
    public String proceed(String request) {
        if (index >= interceptors.size()) {
            return null;
        }
        //获取下一个责任链
        IRealInterceptorChain next = new IRealInterceptorChain(interceptors, index + 1, request);

        // 执行当前的拦截器
        IInterceptor interceptor = interceptors.get(index);

        return interceptor.interceptor(next);
    }
}
