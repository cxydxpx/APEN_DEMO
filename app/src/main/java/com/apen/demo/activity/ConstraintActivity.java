package com.apen.demo.activity;

import com.apen.demo.R;
import com.apen.demo.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.Random;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-10-17.
 * GitHub：https://github.com/cxydxpx
 *
 * @author Administrator
 */

public class ConstraintActivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_constraint;
    }

    static class TempClass {
        public TempClass() {
            Logger.v("TempClass: new instance");
        }
    }

    static class StaticMemberClass {
        static TempClass tempClass = new TempClass();
    }

    private String str = new String("hello");

    private Integer mInt = new Integer(20);

    @Override
    protected void init() {
        super.init();

        Exception m;

        try {
            Logger.v("->run start");

            int sleepSeconds = new Random().nextInt(5) + 1;

            Logger.v("sleeping: " + sleepSeconds);

            Thread.sleep(sleepSeconds);

            TempClass tempClass = StaticMemberClass.tempClass;

            Logger.v("<-run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
