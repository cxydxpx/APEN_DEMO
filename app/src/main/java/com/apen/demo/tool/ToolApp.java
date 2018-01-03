package com.apen.demo.tool;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.apen.demo.IApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ToolApp {

    /**
     * 判断是否为纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    public static boolean isNONONO(String str){
        String str1 = "[0-9a-zA-Z]*";
        return !str.matches(str1);
    }


    /**
     * 判断是否为纯字母
     *
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        char[] chars = str.toCharArray();
        boolean isPhontic = false;
        for (int i = 0; i < chars.length; i++) {
            isPhontic = (chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z');
            if (!isPhontic) {
                return false;
            }
        }
        return true;
    }

    public static final String TAG = "DOFROM_LOG";

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Context getContext() {
        return IApplication.getInstance();
    }

    public static int getVersionCode(Context mContext) {
        if (mContext != null) {
            try {
                return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }
        return 0;
    }

    public static boolean isContextValid(Context context) {
        return context instanceof Activity && !((Activity) context).isFinishing();
    }

    public static Drawable resourcesTodrawable(Context mContext, int drawable) {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), drawable);
        return new BitmapDrawable(mContext.getResources(), bitmap);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
