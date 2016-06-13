package com.xfshipan.weather.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.xfshipan.weather.R;
import com.xfshipan.weather.adapter.FulldayWeatherAdapter;
import com.xfshipan.weather.adapter.WeekWeatherAdapter;
import com.xfshipan.weather.bean.DefaultWeatherInfo;
import com.xfshipan.weather.bean.FullDayWeatherInfo;
import com.xfshipan.weather.bean.WeatherUtilities;
import com.xfshipan.weather.protocal.DefaultWeatherProtocal;
import com.xfshipan.weather.protocal.FullDayProtocal;
import com.xfshipan.weather.utils.ThreadManager;
import com.xfshipan.weather.utils.UiUtils;
import com.xfshipan.weather.utils.WeatherIconUtils;
import com.xfshipan.weather.view.CustomRecycleView;
import com.xfshipan.weather.view.CustomSwipeToRefresh;
import com.xfshipan.weather.view.DividerItemDecoration;
import com.xfshipan.weather.view.WeatherTypefacedTextView;

/**
 * Created by Administrator on 2015/10/26.
 */
public class WeatherFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private long cityCode;//城市代码
    private View mRootView;
    private CustomSwipeToRefresh mSwipeRefreshLayout;
    private boolean isRefreshing = false;
    //实时天气
    private ImageView iv_real_icon; //实时天气图片
    private WeatherTypefacedTextView tv_realtime_desc; //实时天气描述
    private WeatherTypefacedTextView tv_realtime_temp; //实时温度
    private WeatherTypefacedTextView tv_publishtime; //实时天气发布时间
    //空气质量
    private WeatherTypefacedTextView tv_realtime_aqiquality;
    private WeatherTypefacedTextView tv_realtime_pm25;
    private WeatherTypefacedTextView tv_realtime_wd;
    //今明两天天气
    //今天
    private ImageView iv_today_icon;//icon
    private WeatherTypefacedTextView tv_today_desc;//天气
    private WeatherTypefacedTextView tv_today_temp;//温度

    //明天
    private ImageView iv_tomorrow_icon;
    private WeatherTypefacedTextView tv_tomorrow_desc;
    private WeatherTypefacedTextView tv_tomorrow_temp;
    //一周天气
    private CustomRecycleView rv_week_weather;
    private WeekWeatherAdapter weekWeatherAdapter;

    //24小时天气
    private CustomRecycleView rv_fullday_weather;
    private FulldayWeatherAdapter fullDayWeatherAdapter;
    private WeatherTypefacedTextView tv_fullday_title;
    private WeatherTypefacedTextView tv_week_title;
    private boolean isShowWeek = false;

    public WeatherFragment() {

    }

    @Override
    protected void init() {
        super.init();
        Bundle bundle = getArguments();
        cityCode = bundle.getLong("cityCode");
    }

    @Override
    protected View initView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_main_weather, null);
        mSwipeRefreshLayout = (CustomSwipeToRefresh) mRootView
                .findViewById(R.id.rl_main_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //实时天气
        iv_real_icon = (ImageView) mRootView.findViewById(R.id.iv_realtime_icon);
        tv_realtime_desc = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_realtime_desc);
        tv_realtime_temp = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_realtime_temp);
        tv_publishtime = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_publishtime);
        //空气质量
        tv_realtime_aqiquality = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_realtime_aqiquality);
        tv_realtime_pm25 = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_realtime_pm25);
        tv_realtime_wd = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_realtime_wd);

        //今天和明天的天气预报
        //今天
        iv_today_icon = (ImageView) mRootView.findViewById(R.id.iv_today_icon); //图标
        tv_today_desc = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_today_desc);//天气
        tv_today_temp = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_today_temp);//风速
        //明天
        iv_tomorrow_icon = (ImageView) mRootView.findViewById(R.id.iv_tomorrow_icon); //图标
        tv_tomorrow_desc = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_tomorrow_desc);//描述
        tv_tomorrow_temp = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_tomorrow_temp);//今天温度

        //一周天气
        rv_week_weather = (CustomRecycleView) mRootView.findViewById(R.id.rv_week_weather);
        //设置布局管理器
        rv_week_weather.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //设置adapter
        weekWeatherAdapter = new WeekWeatherAdapter(getActivity());
        rv_week_weather.setAdapter(weekWeatherAdapter);
        //设置Item增加、移除动画
        rv_week_weather.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        rv_week_weather.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        //24小时天气
        rv_fullday_weather = (CustomRecycleView) mRootView.findViewById(R.id.rv_fullday_weather);
        //设置布局管理器
        rv_fullday_weather.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //设置adapter
        fullDayWeatherAdapter = new FulldayWeatherAdapter(getActivity());
        rv_fullday_weather.setAdapter(fullDayWeatherAdapter);
        //设置Item增加、移除动画
        rv_fullday_weather.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        rv_fullday_weather.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        rv_week_weather.setVisibility(View.GONE);
        //点击切换按钮
        tv_fullday_title = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_fullday_title);
        tv_week_title = (WeatherTypefacedTextView) mRootView.findViewById(R.id.tv_week_title);
        //添加监听
        tv_fullday_title.setOnClickListener(this);
        tv_week_title.setOnClickListener(this);
        return mRootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fullday_title:
                if (isShowWeek) {
                    isShowWeek = false;
                    rv_fullday_weather.setVisibility(View.VISIBLE);
                    rv_week_weather.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.tv_week_title:
                if (!isShowWeek) {
                    isShowWeek = true;
                    rv_week_weather.setVisibility(View.VISIBLE);
                    rv_fullday_weather.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void initData() {
        super.initData();
        reFreshView(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        //每次可见时
    }

    private void reFreshView(final boolean isRefresh) {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                final DefaultWeatherInfo defaultWeatherInfo;
                final FullDayWeatherInfo fullDayWeatherInfo;
                DefaultWeatherProtocal protocal = new DefaultWeatherProtocal();
                final FullDayProtocal fullDayProtocal = new FullDayProtocal();
                defaultWeatherInfo = protocal.load(cityCode);
                fullDayWeatherInfo = fullDayProtocal.load(cityCode);
                if (isRefresh) {
                    //是否是下拉刷新，下拉刷新，延迟2秒
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (defaultWeatherInfo != null && fullDayWeatherInfo != null) {
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isRefreshing = false;
                            mSwipeRefreshLayout.setRefreshing(isRefreshing);
                        }
                    });
                }
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fillDefaultData(defaultWeatherInfo);
                        fillFullDayData(fullDayWeatherInfo);
                    }
                });
            }
        });
    }

    /**
     * 填充24小时天气
     *
     * @param fullDayWeatherInfo
     */
    private void fillFullDayData(FullDayWeatherInfo fullDayWeatherInfo) {
        fullDayWeatherAdapter.refreshData(fullDayWeatherInfo);
    }

    /**
     * 填充数据
     *
     * @param defaultWeatherInfo
     */
    private void fillDefaultData(DefaultWeatherInfo defaultWeatherInfo) {
        DefaultWeatherInfo.RealtimeEntity realtime = defaultWeatherInfo.getRealtime();
        DefaultWeatherInfo.ForecastEntity forecast = defaultWeatherInfo.getForecast();
        int animationType = WeatherUtilities.getAnimationType(realtime.getWeather());
        //实时天气
        tv_realtime_desc.setText(realtime.getWeather());
        tv_realtime_temp.setText(realtime.getTemp() + "°");
        tv_publishtime.setText(realtime.getTime() + "发布");
        //空气质量
        tv_realtime_aqiquality.setText("空气质量: " + WeatherUtilities.getAqiLevel(Integer.valueOf(defaultWeatherInfo.getAqi().getAqi()), ""));
        tv_realtime_pm25.setText("PM2.5: " + defaultWeatherInfo.getAqi().getPm25());
        tv_realtime_wd.setText(realtime.getWD() + realtime.getWS());
        //今天明天最低气温最高气温
        String[] temps1 = WeatherUtilities.convertTemp(forecast.getTemp1());
        String[] temps2 = WeatherUtilities.convertTemp(forecast.getTemp2());
        tv_today_desc.setText(forecast.getWeather1());
        tv_tomorrow_desc.setText(forecast.getWeather2());
        tv_today_temp.setText(temps1[1] + "°/" + temps1[0] + "°");
        tv_tomorrow_temp.setText(temps2[1] + "°/" + temps2[0] + "°");

        //天气图标
        int animationType1 = WeatherIconUtils.getAnimationType(forecast.getWeather1());
        int animationType2 = WeatherIconUtils.getAnimationType(forecast.getWeather2());
        int realWeatherIcon = WeatherIconUtils.getWeatherIcon(animationType);
        int weatherIcon1 = WeatherIconUtils.getWeatherIcon(animationType1);
        int weatherIcon2 = WeatherIconUtils.getWeatherIcon(animationType2);
        iv_real_icon.setImageResource(realWeatherIcon);
        iv_today_icon.setImageResource(weatherIcon1);
        iv_tomorrow_icon.setImageResource(weatherIcon2);
        //一周天气
        weekWeatherAdapter.refreshData(defaultWeatherInfo);
    }

    //刷新数据
    @Override
    public void onRefresh() {
        isRefreshing = true;
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
        reFreshView(true);
    }
}
