package com.xfshipan.weather.protocal;

import com.xfshipan.weather.bean.ReadableWeekInfos;
import com.xfshipan.weather.http.Constant;

/**
 * Created by Administrator on 2015/10/24.
 */
public class WeekProtocal extends BaseProtocal<ReadableWeekInfos> {
    @Override
    protected String getKey() {
        return "week";
    }

    @Override
    protected ReadableWeekInfos parseJson(String json) {
        return gson.fromJson(json, ReadableWeekInfos.class);
    }

    @Override
    protected String getUrl() {
        return Constant.REALTIEMWEATHER + getCityCode() + Constant.JSONKEY;
    }
}
