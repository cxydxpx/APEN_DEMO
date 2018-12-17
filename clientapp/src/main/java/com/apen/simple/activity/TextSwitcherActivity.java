package com.apen.simple.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.apen.simple.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-07-17.
 * GitHub：https://github.com/cxydxpx
 */

public class TextSwitcherActivity extends AppCompatActivity {

    @BindView(R.id.switcher)
    com.apen.simple.custom.TextSwitcherView mViewSwitcher;

    private Button mBtn;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            return false;
        }
    }
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switcher);
        ButterKnife.bind(this);


        mBtn = (Button) findViewById(R.id.btn);

        mBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewSwitcher.setTextContent("hello world" + new Random().nextInt());
                    }
                }
        );
    }

}
