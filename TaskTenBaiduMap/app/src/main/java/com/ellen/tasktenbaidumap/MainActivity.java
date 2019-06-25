package com.ellen.tasktenbaidumap;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends Activity {

    private MapView mapView;
    private BaiduMap baiduMap;
    private Context context;

    private LocationClient locationClient;
    private BitmapDescriptor locationIcon;
    private MyLocationListener mylocationLister;

    private boolean isFirstLocate = true; //Whether is the first located or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        this.context = this;

        initView();

        initFirstLocation();



    }

    private void initFirstLocation() {



        locationClient = new LocationClient(this);
        mylocationLister = new MyLocationListener();
        locationClient.registerLocationListener(mylocationLister);

        final LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setIsNeedAddress(true);
        locationClientOption.setOpenGps(true);
        locationClientOption.setScanSpan(1000);

        locationClient.setLocOption(locationClientOption);

        locationIcon = BitmapDescriptorFactory.fromResource(R.drawable.maker);


    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomBy(15.0f);
        baiduMap.setMapStatus(mapStatusUpdate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();

        //open location
        baiduMap.setMyLocationEnabled(true);
        if (!locationClient.isStarted()) {
            locationClient.start();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        //close location
        baiduMap.setMyLocationEnabled(false);
        locationClient.stop();
    }

    private class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData myLocationData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();

            baiduMap.setMyLocationData(myLocationData);


            MyLocationConfiguration configuration = new MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.COMPASS,true,locationIcon
            );
            baiduMap.setMyLocationConfigeration(configuration);

            if(isFirstLocate){
                LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                baiduMap.animateMapStatus(msu);
                isFirstLocate = false;

                Toast.makeText(context,bdLocation.getAddrStr(),Toast.LENGTH_LONG).show();
            }

        }
    }
}
