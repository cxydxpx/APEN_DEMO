package com.apen.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.apen.demo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-08.
 * GitHub：https://github.com/cxydxpx
 */

public class ScanActivity extends AppCompatActivity implements QRCodeView.Delegate {

    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    private static final String TAG = "tag";

    private QRCodeView mQRCodeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        ButterKnife.bind(this);

        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);

        mQRCodeView.showScanRect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mQRCodeView.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.stopSpotAndHiddenRect();
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
    
    private static final String EXTRA_SELECTED_IMAGES = "EXTRA_SELECTED_IMAGES";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mQRCodeView.showScanRect();

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
            final String picturePath = data.getStringArrayListExtra(EXTRA_SELECTED_IMAGES).get(0);

            /*
            这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
            请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
             */
            Observable.just(QRCodeDecoder.syncDecodeQRCode(picturePath))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                                   @Override
                                   public void onSubscribe(Disposable disposable) {

                                   }

                                   @Override
                                   public void onNext(String result) {
                                       if (TextUtils.isEmpty(result)) {
                                           Log.d(TAG, "onNext: " + result);
                                           Toast.makeText(ScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
                                       } else {
                                           Log.d(TAG, "onNext: " + result);
                                           Toast.makeText(ScanActivity.this, result, Toast.LENGTH_SHORT).show();
                                       }
                                   }

                                   @Override
                                   public void onError(Throwable throwable) {

                                   }

                                   @Override
                                   public void onComplete() {

                                   }
                               }
                    );
        }
    }

    @OnClick({R.id.start_spot, R.id.stop_spot})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.start_spot:
                mQRCodeView.startSpotAndShowRect();
                Toast.makeText(this, "开始", Toast.LENGTH_SHORT).show();
                break;
            case R.id.stop_spot:
                mQRCodeView.stopSpot();
                Toast.makeText(this, "停止", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
