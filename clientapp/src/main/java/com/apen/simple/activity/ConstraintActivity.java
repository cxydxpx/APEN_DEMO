package com.apen.simple.activity;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-10-17.
 * GitHub：https://github.com/cxydxpx
 *
 * @author Administrator
 */

public class ConstraintActivity extends BaseActivity {


    /**
     * 学习
     * https://developer.android.google.cn/reference/android/support/constraint/ConstraintLayout
     * https://developer.android.google.cn/studio/write/layout-editor
     * https://developer.android.google.cn/training/constraint-layout/
     * <p>
     * 参考线
     * https://developer.android.google.cn/reference/android/support/constraint/Guideline
     * 分组控制隐藏/展示
     * https://developer.android.google.cn/reference/android/support/constraint/Group
     * 组视图边缘线
     * https://developer.android.google.cn/reference/android/support/constraint/Barrier
     *
     * @return
     */

    @Override
    protected int layoutResId() {
        return R.layout.activity_constraint;
    }

//    static class TempClass {
//        public TempClass() {
//            Logger.v("TempClass: new instance");
//        }
//    }
//
//    static class StaticMemberClass {
//        static TempClass tempClass = new TempClass();
//    }

    @Override
    protected void init() {
        super.init();

//        Logger.v("ConstraintActivity this -- " + this + " -      " + this.isFinishing());
//        mTextView2.setText(ToolMobileInfo.getIMEI(this) + " -- " + ToolMobileInfo.getMacAddress());
//        what();
//        eventI();
    }

    private void eventI() {
//        EventBus.getDefault().register(this);
    }

//    private void what() {
//        Exception m;
//
//        try {
//            Logger.v("->run start");
//
//            int sleepSeconds = new Random().nextInt(5) + 1;
//
//            Logger.v("sleeping: " + sleepSeconds);
//
//            Thread.sleep(sleepSeconds);
//
//            TempClass tempClass = StaticMemberClass.tempClass;
//
//            Logger.v("<-run end");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
