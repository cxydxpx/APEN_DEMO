package com.apen.simple.activity;

import android.view.View;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-07-24.
 * GitHub：https://github.com/cxydxpx
 *
 * @author Administrator
 */

public class TestRxjavaActivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_rxjava;
    }

    Integer i = 100;

    public void click(View view) {

        Logger.v(System.currentTimeMillis() + "   -------   时间段");


        Observable<Integer> observable = Observable.just(1)
                .delay(5, TimeUnit.SECONDS);
//                Observable.empty();
//                Observable.range(2, 5);
//                Observable.intervalRange(2, 5, 2, 1, TimeUnit.SECONDS);
//                Observable.interval(4, TimeUnit.SECONDS);
//                Observable.timer(2, TimeUnit.SECONDS);
//                Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
//            @Override
//            public ObservableSource<? extends Integer> call() throws Exception {
//                return Observable.timer(2, TimeUnit.SECONDS);
//            }
//        });
//
//        i = 200;

//                Observable.fromIterable(new ArrayList<Integer>());  集合
//                Observable.fromArray(1, 2, 3, 4);  数组
//                = Observable.just() 数组
//                Observable.create( 普通创建
//                new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        Logger.v("======= currentThread Name :  " + Thread.currentThread().getName());
//
//                        Logger.v("emitter发送第一个onNext");
//                        emitter.onNext(1);
//                        Logger.v("emitter发送第二个onNext");
//                        emitter.onNext(2);
//                        Logger.v("emitter发送onComplete");
//                        emitter.onComplete();
//                    }
//                }
//        );

        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
//                Logger.v("======= onSubscribe  " + d);
            }

            @Override
            public void onNext(Integer value) {

                Logger.v(System.currentTimeMillis() + "   -------   时间段");

                Logger.v("Observer接收到onNext，value = " + value);
            }

            @Override
            public void onError(Throwable e) {
                Logger.v("Observer接收到onError，errorMsg = " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Logger.v("Observer接收到onComplete");
            }
        };

        observable.subscribe(observer);

    }


}
