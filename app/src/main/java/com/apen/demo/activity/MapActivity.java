package com.apen.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.apen.demo.IApplication;
import com.apen.demo.R;
import com.apen.demo.helper.HelpeLocation;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-10-23.
 * GitHub：https://github.com/cxydxpx
 *
 * @author apen
 */

public class MapActivity extends AppCompatActivity {

    MapView mMapView = null;

    private BaiduMap mBaiduMap;

    /**
     * 初始化LocationClient 类
     */
    public LocationClient mLocationClient = null;
    private HelpeLocation mHepleLocation;
    private BDAbstractLocationListener mListener;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        //        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

//        普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        mListener = new MyLocationListener();


        mButton = (Button) findViewById(R.id.btn_location);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHepleLocation.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
// -----------location config ------------
        mHepleLocation = ((IApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        mHepleLocation.registerListener(mListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    /**
     * 位置监听器
     * BDLocationListener接口有1个方法需要实现： 1.接收异步返回的定位结果，参数是BDLocation类型参数。
     */
    class MyLocationListener extends BDAbstractLocationListener {
        /**
         * 接收位置的信息回调方法
         *
         * @param location
         */
        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
            location.getTime();    //获取定位时间
            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息
            location.getRadius();    //获取定位精准度
            location.getAddrStr();    //获取地址信息
            location.getCountry();    //获取国家信息
            location.getCountryCode();    //获取国家码
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息

            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息

            if (location == null) {
                return;
            }
            // 开启定位图层
            mBaiduMap.setMyLocationEnabled(true);
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
            mCurrentMarker = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_marka);
            mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
            MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
            mBaiduMap.setMyLocationConfiguration(config);
            // 当不需要定位图层时关闭定位图层
            mBaiduMap.setMyLocationEnabled(false);

            mHepleLocation.start();
        }

    }

    private MyLocationConfiguration.LocationMode mCurrentMode;
    private BitmapDescriptor mCurrentMarker;

}
