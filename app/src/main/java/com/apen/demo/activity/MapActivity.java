package com.apen.demo.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.apen.demo.IApplication;
import com.apen.demo.R;
import com.apen.demo.adapter.MapAdapter;
import com.apen.demo.helper.HelpeLocation;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.f_ms.runtimepermission.simple.PermissionRefuseResultHelper;
import cn.f_ms.runtimepermission.simple.SimpleRuntimePermission;
import cn.f_ms.runtimepermission.simple.SimpleRuntimePermissionHelper;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-10-23.
 * GitHub：https://github.com/cxydxpx
 *
 * @author apen
 */

public class MapActivity extends AppCompatActivity implements OnGetGeoCoderResultListener, BaiduMap.OnMapStatusChangeListener {

    private TextureMapView mMapView;

    private BaiduMap mBaiduMap;

    /**
     * 初始化LocationClient 定位端
     */
    public LocationClient mLocationClient = null;
    /**
     * 定位坐标
     */
    private LatLng locationLatLng;
    /**
     * 定位城市
     */
    private String city;
    /**
     * 反地理编码
     */
    private GeoCoder mSearch;

    private HelpeLocation mHepleLocation;
    private BDAbstractLocationListener mListener;

    @BindView(R.id.main_pois)
    ListView mListView;

    private boolean mIsFirstLoc = true;

    /**
     * 定位模式
     */
    private MyLocationConfiguration.LocationMode mCurrentMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);

        ButterKnife.bind(this);

        //        //获取地图控件引用
        mMapView = (TextureMapView) findViewById(R.id.main_bdmap);
        mBaiduMap = mMapView.getMap();
//        普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //定义地图状态 改变地图缩放等级
        MapStatus mMapStatus = new MapStatus.Builder().zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);

//        直接设置地图状态  设置地图显示范围
//        mBaiduMap.setMapStatus(mMapStatusUpdate);
//        采用动画的方式设置地图状态   设置地图显示范围
        mBaiduMap.animateMapStatus(mMapStatusUpdate);

        //地图状态改变相关监听
        mBaiduMap.setOnMapStatusChangeListener(this);

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, null);
        mBaiduMap.setMyLocationConfiguration(config);

        mListener = new MyLocationListener();
        mHepleLocation = ((IApplication) getApplication()).locationService;

        // /获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        mHepleLocation.registerListener(mListener);

        SimpleRuntimePermissionHelper.with(new SimpleRuntimePermission(MapActivity.this))
                .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                .execute(new SimpleRuntimePermission.PermissionListener() {
                    @Override
                    public void onAllPermissionGranted() {
                        mHepleLocation.start();
                    }

                    @Override
                    public void onPermissionRefuse(PermissionRefuseResultHelper resultHelper) {

                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
// -----------location config ------------

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
     * 地理编码查询结果回调函数
     *
     * @param result
     */
    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {

        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有检索到结果
        }
        //获取地理编码结果
    }

    /**
     * 反地理编码查询结果回调函数
     *
     * @param result
     */
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有检索到结果
        } else {
            final List<PoiInfo> poiInfos = result.getPoiList();
            MapAdapter poiAdapter = new MapAdapter(MapActivity.this, poiInfos);
            mListView.setAdapter(poiAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    PoiInfo info = poiInfos.get(position);
                    Toast.makeText(MapActivity.this, info.name, Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }
    }

    /**
     * 手势操作地图，设置地图状态等操作导致地图状态开始改变。
     *
     * @param status 地图状态改变开始时的地图状态
     */
    @Override
    public void onMapStatusChangeStart(MapStatus status) {

    }

    /**
     * 因某种操作导致地图状态开始改变。
     *
     * @param status 地图状态改变开始时的地图状态
     * @param i      reason表示地图状态改变的原因，取值有：
     *               1：用户手势触发导致的地图状态改变,比如双击、拖拽、滑动底图
     *               2：SDK导致的地图状态改变, 比如点击缩放控件、指南针图标
     *               3：开发者调用,导致的地图状态改变
     */
    @Override
    public void onMapStatusChangeStart(MapStatus status, int i) {

    }

    /**
     * 地图状态变化中
     *
     * @param status 当前地图状态
     */
    @Override
    public void onMapStatusChange(MapStatus status) {

    }

    /**
     * 地图状态改变结束
     *
     * @param status 地图状态改变结束后的地图状态
     */
    @Override
    public void onMapStatusChangeFinish(MapStatus status) {
        //地图操作的中心点
        LatLng cenpt = status.target;
//      发起地理编码检索
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(cenpt));
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
        public void onReceiveLocation(BDLocation location) { // de

            if (location == null) {
                return;
            }

            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    //定位精度bdLocation.getRadius()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection())
                    //经度
                    .latitude(location.getLatitude())
                    //纬度
                    .longitude(location.getLongitude())
                    .build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);

            //是否是第一次定位
            if (mIsFirstLoc) {
                mIsFirstLoc = false;

                LatLng cenpt = new LatLng(location.getLatitude(), location.getLongitude());
                //定义地图状态
                MapStatus mMapStatus = new MapStatus.Builder()
                        .target(cenpt)
                        .build();
                //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                //改变地图状态
                mBaiduMap.setMapStatus(mMapStatusUpdate);

            }

            //获取坐标，待会用于POI信息点与定位的距离
            locationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            //获取城市，待会用于POISearch
            city = location.getCity();

            //创建地理编码检索实例
            mSearch = GeoCoder.newInstance();
            //发起反地理编码请求(经纬度->地址信息)
            ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
            //设置反地理编码位置坐标
            reverseGeoCodeOption.location(new LatLng(location.getLatitude(), location.getLongitude()));
//            发起地理编码检索
            mSearch.reverseGeoCode(reverseGeoCodeOption);

            //设置查询结果监听者  地理编码检索监听者
            mSearch.setOnGetGeoCodeResultListener(MapActivity.this);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();

        //退出时停止定位
        mHepleLocation.stop();
        //退出时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);

        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();

        //释放资源
        if (mSearch != null) {
            mSearch.destroy();
        }

        mMapView = null;
    }

}
