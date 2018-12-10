package com.apen.simple.activity.rxjava_test;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-07-24.
 * GitHub：https://github.com/cxydxpx
 */

public interface ObserverTest<T> {

    // rxjava源码方法
    void onSubscribe();

    void onNext(T value);

    void onError(Throwable e);

    void onComplete();
}
