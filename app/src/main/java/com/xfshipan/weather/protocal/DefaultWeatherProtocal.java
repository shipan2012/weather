package com.xfshipan.weather.protocal;

import com.xfshipan.weather.bean.DefaultWeatherInfo;
import com.xfshipan.weather.http.Constant;

/**
 * Created by Administrator on 2015/10/29.
 */
public class DefaultWeatherProtocal extends BaseProtocal<DefaultWeatherInfo> {

    @Override
    protected String getKey() {
        return "default";
    }

    @Override
    protected DefaultWeatherInfo parseJson(String json) {
        return gson.fromJson(json, DefaultWeatherInfo.class);
    }

    @Override
    protected String getUrl() {
        return Constant.DEFAULTWEATHER + getCityCode();
    }
}
