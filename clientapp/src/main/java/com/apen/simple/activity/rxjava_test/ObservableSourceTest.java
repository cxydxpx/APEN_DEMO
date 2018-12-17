package com.apen.simple.activity.rxjava_test;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-07-24.
 * GitHub：https://github.com/cxydxpx
 */

public interface ObservableSourceTest<T> {

    void subscibe(ObserverTest<? super T> observerTest);

}
