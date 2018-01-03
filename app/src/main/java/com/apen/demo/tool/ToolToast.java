package com.apen.demo.tool;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apen.demo.R;


/**
 * Created by 002 on 2017/4/11.
 */

public class ToolToast {

    private static Toast toast;

    public static void showToast(Context context, String text){

        View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast, null);
        Toast toast = new Toast(context);
        toast.setView(toastRoot);
        TextView tv = (TextView) toastRoot.findViewById(R.id.tv_toast);
        if (toast == null){
            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }
        tv.setText(text);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    public static void showToast(String text){
        showToast(ToolApp.getContext(),text);
    }

}
