package com.apen.simple.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.apen.simple.IApplication;
import com.apen.simple.R;
import com.apen.simple.adapter.MapAdapter;
import com.apen.simple.helper.HelpeLocation;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

public class MapActivity extends AppCompatActivity implements com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener, BaiduMap.OnMapStatusChangeListener, com.baidu.mapapi.search.sug.OnGetSuggestionResultListener, OnGetPoiSearchResultListener {

    private MapView mMapView;

    private BaiduMap mBaiduMap;


    /**
     * 初始化LocationClient 定位端
     */
    public com.baidu.location.LocationClient mLocationClient = null;
    /**
     * 定位坐标
     */
    private com.baidu.mapapi.model.LatLng locationLatLng;
    /**
     * 定位城市
     */
    private String city;
    /**
     * 反地理编码
     */
    private com.baidu.mapapi.search.geocode.GeoCoder mSearch;

    private HelpeLocation mHepleLocation;
    private com.baidu.location.BDAbstractLocationListener mListener;

    @BindView(R.id.main_pois)
    ListView mListView;

    private boolean mIsFirstLoc = true;

    private SuggestionSearch mSuggestionSearch = null;
    private PoiSearch mPoiSearch = null;
    /**
     * 定位模式
     */
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private MapAdapter mPoiAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ButterKnife.bind(this);

        initMapView();
        initMapstatus();


        //地图状态改变相关监听
        mBaiduMap.setOnMapStatusChangeListener(this);

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, null);
        mBaiduMap.setMyLocationConfiguration(config);

        initLocationListener();
        initGeoCoder();
        initPoiSearch();
        initSuggestionSearch();


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

    private ArrayAdapter<String> sugAdapter = null;

    private void initSuggestionSearch() {
        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

        sugAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        etContent.setAdapter(sugAdapter);
        etContent.setThreshold(1);

         /* 当输入关键字变化时，动态更新建议列表 */
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    return;
                }

                /* 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新 */
                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
                        .keyword(cs.toString())
                        .city(city));
            }
        });

    }

    private void initPoiSearch() {
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
    }

    private void initGeoCoder() {
        //创建地理编码检索实例
        mSearch = GeoCoder.newInstance();
        //设置查询结果监听者  地理编码检索监听者
        mSearch.setOnGetGeoCodeResultListener(MapActivity.this);
    }

    private void initLocationListener() {
        // 定位 监听接口
        mListener = new MyLocationListener();
        mHepleLocation = ((IApplication) getApplication()).locationService;
        // /获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        mHepleLocation.registerListener(mListener);
    }

    private void initMapstatus() {
        // 定义地图状态 改变地图缩放等级
        MapStatus mMapStatus = new MapStatus.Builder().zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);

//        直接设置地图状态  设置地图显示范围
//        mBaiduMap.setMapStatus(mMapStatusUpdate);
//        采用动画的方式设置地图状态   设置地图显示范围
        mBaiduMap.animateMapStatus(mMapStatusUpdate);
    }

    private void initMapView() {
        // 获取地图控件引用
        mMapView = (MapView) findViewById(R.id.main_bdmap);
        mBaiduMap = mMapView.getMap();
        // 普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
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
     * 用地址检索坐标
     *
     * @param result
     */
    @Override
    public void onGetGeoCodeResult(com.baidu.mapapi.search.geocode.GeoCodeResult result) {

        if (result == null || result.error != com.baidu.mapapi.search.core.SearchResult.ERRORNO.NO_ERROR) {
            //没有检索到结果
        }
        //获取地理编码结果
    }

    /**
     * 反地理编码查询结果回调函数
     * 用坐标检索地址
     *
     * @param result
     */
    @Override
    public void onGetReverseGeoCodeResult(com.baidu.mapapi.search.geocode.ReverseGeoCodeResult result) {

        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有检索到结果
        } else {
            final List<PoiInfo> poiInfos = result.getPoiList() == null ? new ArrayList<PoiInfo>() : result.getPoiList();
            mPoiAdapter = new MapAdapter(MapActivity.this, poiInfos);
            mListView.setAdapter(mPoiAdapter);

            mListView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            PoiInfo info = poiInfos.get(position);
                            LatLng location = info.getLocation();
                            LatLng cenpt = new LatLng(location.latitude, location.longitude);
                            //定义地图状态
                            MapStatus mMapStatus = new MapStatus.Builder()
                                    .target(cenpt)
                                    .build();
                            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                            //改变地图状态
                            mBaiduMap.setMapStatus(mMapStatusUpdate);
                        }
                    }
            );

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
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

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


                //获取坐标，待会用于POI信息点与定位的距离
                locationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                //获取城市，待会用于POISearch
                city = location.getCity();

                // 当不需要定位图层时关闭定位图层

                //发起反地理编码请求(经纬度->地址信息)
                ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
                //设置反地理编码位置坐标
                reverseGeoCodeOption.location(new LatLng(location.getLatitude(), location.getLongitude()));
//            发起地理编码检索
                mSearch.reverseGeoCode(reverseGeoCodeOption);

            }

        }

    }

    @BindView(R.id.et_content)
    AutoCompleteTextView etContent;

    @OnClick(R.id.btn_search)
    void click() {

        String trim = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return;
        }
        mPoiSearch.searchInCity((new PoiCitySearchOption())
                .city(city)
                .keyword(trim)
                .pageNum(0)
                .scope(1));


    }

    @Override
    public void onGetSuggestionResult(SuggestionResult result) {

        if (result == null || result.getAllSuggestions() == null) {
            return;
        }

        List<String> suggest = new ArrayList<>();
        for (SuggestionResult.SuggestionInfo info : result.getAllSuggestions()) {
            if (info.key != null) {
                suggest.add(info.key);
            }
        }

        sugAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                suggest);
        etContent.setAdapter(sugAdapter);

        sugAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetPoiResult(PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            Toast.makeText(this, "未找到结果", Toast.LENGTH_LONG).show();
            return;
        }

        final List<PoiInfo> poiInfos = result.getAllPoi() == null ? new ArrayList<PoiInfo>() : result.getAllPoi();

        if (poiInfos.size() > 0) {
            PoiInfo info = poiInfos.get(0);
            LatLng location = info.getLocation();
            LatLng cenpt = new LatLng(location.latitude, location.longitude);
            //定义地图状态
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(cenpt)
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            mBaiduMap.setMapStatus(mMapStatusUpdate);

        }
        mPoiAdapter.notifyDataSetChangedData(poiInfos);

        mListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        PoiInfo info = poiInfos.get(position);
                        LatLng location = info.getLocation();
                        LatLng cenpt = new LatLng(location.latitude, location.longitude);
                        //定义地图状态
                        MapStatus mMapStatus = new MapStatus.Builder()
                                .target(cenpt)
                                .build();
                        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                        //改变地图状态
                        mBaiduMap.setMapStatus(mMapStatusUpdate);
                    }
                }
        );

    }


    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult result) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult result) {

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
