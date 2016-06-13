package com.xfshipan.weather.protocal;

import com.xfshipan.weather.bean.RealTimeWeather;
import com.xfshipan.weather.http.Constant;

/**
 * Created by Administrator on 2015/10/24.
 */
public class RealTimeProtocal extends BaseProtocal<RealTimeWeather> {
    @Override
    protected String getKey() {
        return "realtime";
    }

    @Override
    protected RealTimeWeather parseJson(String json) {
        return gson.fromJson(json, RealTimeWeather.class);
    }

    @Override
    protected String getUrl() {
        return Constant.REALTIEMWEATHER + getCityCode() + Constant.JSONKEY;
    }
}
