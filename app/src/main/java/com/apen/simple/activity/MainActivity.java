package com.apen.simple.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.View;

import com.apen.simple.R;
import com.apen.simple.activity.customview.CustomViewActivity;
import com.apen.simple.base.BaseActivity;
import com.apen.simple.bean.SimpleBean;

import java.net.URISyntaxException;
import java.net.URLEncoder;

import butterknife.OnClick;

/**
 * @author apen
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }

    @OnClick({
            R.id.btn_constraintLayout,
            R.id.btn_mpandroidchart,
            R.id.btn_map,
            R.id.btn_proguard,
            R.id.btn_glide,
            R.id.btn_zxing,
            R.id.btn_custom,
            R.id.btn_gesture,
            R.id.btn_service,
            R.id.btn_test,
            R.id.btn_swicthtext,
            R.id.btn_rxjava,
            R.id.btn_viewpager,
            R.id.btn_eventBus

    })
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_eventBus:
                startActivity(new Intent(this,EventBusActivity.class));
                break;
            case R.id.btn_viewpager:
//                Uri uri = Uri.parse("https://mobile.alipay.com/index.htm?cid=wap_dc");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//                startActivity(new Intent(this,ViewPagerActivity.class));
                startAlipay(this, "");
                break;
            case R.id.btn_rxjava:
                startActivity(new Intent(this, TestRxjavaActivity.class));
                break;
            case R.id.btn_swicthtext:
                startActivity(new Intent(this, TextSwitcherActivity.class));
                break;
            case R.id.btn_test:
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.btn_constraintLayout:
                startActivity(new Intent(this, ConstraintActivity.class));
                break;
            case R.id.btn_mpandroidchart:
                startActivity(new Intent(this, MpAndroidChartActivity.class));
                break;
            case R.id.btn_map:
                startActivity(new Intent(this, MapActivity.class));
                break;
            case R.id.btn_proguard:
                startActivity(new Intent(this, ProguardActivity.class));
                break;
            case R.id.btn_glide:
                startActivity(new Intent(this, GlideActivity.class));
                break;
            case R.id.btn_zxing:
                startActivity(new Intent(this, ZxingActivity.class));
                break;
            case R.id.btn_custom:
                startActivity(new Intent(this, CustomViewActivity.class));
                break;
            case R.id.btn_gesture:
                startActivity(new Intent(this, GestureActivity.class));
                break;
            case R.id.btn_service:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            default:
                break;
        }
    }

    public void startIntentUrl(Activity activity, String intentUrl) {
        try {
            Intent intent = Intent.parseUri(intentUrl, Intent.URI_INTENT_SCHEME);
            activity.startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

//    https://qr.alipay.com/bax04947wlhf7pxi0nnq60af

    public void startAlipay(Activity activity, String urlCode) {
         startIntentUrl(activity, url.replace("{urlCode}", URLEncoder.encode(urlCode)));
    }

    private String url = "intent://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718" +
            "&qrcode={urlCode}%3F_s%3Dweb-other" +
            "&_t=1472443966571#Intent;scheme=alipayqr;package=com.eg.android.AlipayGphone;end";
    //alipayqr://platformapi/startapp?saId=10000007&qrcode=xxxxx?_s

    public static void methodTest(SimpleBean bean) {

        Spiciness mBean = Spiciness.HOT;

    }

    enum Spiciness {
        NOT, MILD, MEDIUM, HOT
    }


}
