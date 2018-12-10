package com.apen.simple.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apen.simple.R;
import com.apen.simple.base.BaseFragment;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-01.
 * GitHub：https://github.com/cxydxpx
 */

public class ProguardFragment extends BaseFragment {

    private String toastTip = "toast in ProguardFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proguard, container, false);
        methodWithGlobalVariable();
        methodWithLocalVariable();
        return view;
    }

    private void methodWithLocalVariable() {
        String logMessage = "log in MyFragment";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }

    private void methodWithGlobalVariable() {
        Toast.makeText(getActivity(), toastTip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
