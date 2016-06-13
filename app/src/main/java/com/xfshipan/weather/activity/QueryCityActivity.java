package com.xfshipan.weather.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xfshipan.weather.R;
import com.xfshipan.weather.adapter.HotCityAdapter;
import com.xfshipan.weather.adapter.QueryListAdapter;
import com.xfshipan.weather.bean.CityInfo;
import com.xfshipan.weather.db.CityInfoUtils;
import com.xfshipan.weather.db.dao.WeatherCityDao;
import com.xfshipan.weather.utils.SharePrefUtils;
import com.xfshipan.weather.utils.UiUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/10/24.
 */
public class QueryCityActivity extends BaseActivity {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private QueryListAdapter queryListAdapter;
    private EditText et_queryCity; //城市输入框
    private TextView tv_location;  //点击开始定位
    private ListView lv_citys;  //查询后城市结果显示列表
    private GridView gv_host_city; //热门城市列表
    private ImageButton ib_delete_city; //删除输入框里面的内容
    private HotCityAdapter hotCityAdapter;
    private ImageView back_image; //退出按钮
    private String selectedStr;

    @Override
    protected void initToolBar() {
        super.initToolBar();
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_city_query);
        et_queryCity = (EditText) findViewById(R.id.et_query_city);
        tv_location = (TextView) findViewById(R.id.tv_location);
        lv_citys = (ListView) findViewById(R.id.lv_citys);
        gv_host_city = (GridView) findViewById(R.id.gv_hot_city);
        ib_delete_city = (ImageButton) findViewById(R.id.ib_delete_city);
        back_image = (ImageView) findViewById(R.id.back_image);
        queryListAdapter = new QueryListAdapter(QueryCityActivity.this);
        selectedStr = queryListAdapter.getSelectedStr();
        //监听输入框的变化
        et_queryCity.addTextChangedListener(new TextWatcher() {
            //当文本变化的时候调用的方法
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            //当文本变化之前调用的方法
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            //当文本变化之后调用的方法
            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = s.toString();
                if (!keyWord.isEmpty()) {
                    List<CityInfo> cityInfos = WeatherCityDao.queryCityCode(keyWord);
                    queryListAdapter.refreshView(cityInfos);
                    gv_host_city.setVisibility(View.INVISIBLE);
                    lv_citys.setVisibility(View.VISIBLE);
                    ib_delete_city.setVisibility(View.VISIBLE);
                } else {
                    //当没有任何文本输入时，listView隐藏
                    lv_citys.setVisibility(View.GONE);
                    //删除按钮消失
                    ib_delete_city.setVisibility(View.INVISIBLE);
                    List<CityInfo> cityInfos = WeatherCityDao.queryCityCode(keyWord);
                    queryListAdapter.refreshView(cityInfos);
                    gv_host_city.setVisibility(View.VISIBLE);
                }
            }
        });

        /**
         * 点击之后将数据存入本地，销毁此界面，同时跳转到已经选择的界面
         */
        lv_citys.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityInfo cityInfo = (CityInfo) queryListAdapter.getItem(position);
                if (selectedStr.contains(cityInfo.getAreaName())) {
                    return;
                } else {
                    String citySting = CityInfoUtils.parseBeanToString(cityInfo);
                    addCity(citySting);
                }
            }
        });


        ib_delete_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空输入框的内容
                et_queryCity.setText("");
            }
        });
        /**
         * 点击自动定位
         */
        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.start();//开始定位
            }
        });

        /**
         * 点击热门城市后开始跳转
         */
        gv_host_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityInfo cityInfo = (CityInfo) hotCityAdapter.getItem(position);
                String str = CityInfoUtils.parseBeanToString(cityInfo);
                addCity(str);
            }
        });
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_citys.setAdapter(queryListAdapter);
        hotCityAdapter = new HotCityAdapter(this);
        gv_host_city.setVisibility(View.VISIBLE);
        gv_host_city.setAdapter(hotCityAdapter);
    }

    /**
     * 添加新的城市
     *
     * @param str
     */
    private void addCity(String str) {
        Intent intent = new Intent(QueryCityActivity.this, MainActivity.class);
        if (!WeatherCityDao.isExit(SharePrefUtils.CITY_SELECTED, str)) {
            WeatherCityDao.addCity(str);
            intent.putExtra("newSelected", true);
            //销毁当前
            finish();
            startActivity(intent);
        } else {
            intent.putExtra("newSelected", false);//没有新添加
            return;
        }
    }


    @Override
    protected void initData() {
        super.initData();
        //百度地图
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();//初始化定位信息
        List<CityInfo> hotCities = WeatherCityDao.queryHotCitys();
        hotCityAdapter.refreshHotCity(hotCities);
        String localCity = "";
        //如果城市列表为空,自动开启定位服务
        if (CityInfoUtils.isEmpty()) {
            //开启自动定位
            mLocationClient.start();
        } else if (!(localCity = SharePrefUtils.getString(UiUtils.getContext(), SharePrefUtils.CITY_LOCAL, "")).equals("")) {
            //刷新数据
            final TextAppearanceSpan notificationSubjectSpan = new TextAppearanceSpan(
                    this, R.style.NotificationPrimaryText);
            String afterStr = "(点击重新定位)";
            String areaName = CityInfoUtils.parStringToBean(localCity).getAreaName();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(
                    areaName);
            if (!TextUtils.isEmpty(afterStr)) {
                spannableStringBuilder.append(afterStr);
                spannableStringBuilder.setSpan(notificationSubjectSpan,
                        areaName.length(), areaName.length() + afterStr.length(), 0);
            }

            tv_location.setText(spannableStringBuilder);
        }
    }

    /**
     * 定位回调接口
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            String city = null;//城市
            String district = null;//区域
            int locType = location.getLocType();
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation
                    || location.getLocType() == BDLocation.TypeOffLineLocation) {
                //定位成功
                Address address = location.getAddress();
                city = address.city.substring(0, address.district.length() - 1);
                district = address.district.substring(0, address.district.length() - 1);
                Toast.makeText(QueryCityActivity.this, "定位地址:" + district, Toast.LENGTH_SHORT).show();
            } else {
                //定位失败
                if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
            }
            if (city != null && district != null) {
                CityInfo cityInfo = CityInfoUtils.transLocationInfos(city, district);
                if (cityInfo != null) {
                    //停止定位
                    mLocationClient.stop();
                    //判断是否为空
                    if (CityInfoUtils.isEmpty()) {
                        //存入定位
                        SharePrefUtils.setString(UiUtils.getContext(), SharePrefUtils.CITY_LOCAL,
                                CityInfoUtils.parseBeanToString(cityInfo));
                        WeatherCityDao.addCity(CityInfoUtils.parseBeanToString(cityInfo));
                        Intent intent = new Intent(QueryCityActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            } else {
                //定位失败
                Toast.makeText(QueryCityActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 初始化定位配置相关信息
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("默认gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
}
