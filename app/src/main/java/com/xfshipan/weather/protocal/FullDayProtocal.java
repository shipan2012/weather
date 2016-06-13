package com.xfshipan.weather.protocal;

import com.xfshipan.weather.bean.FullDayWeatherInfo;
import com.xfshipan.weather.http.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FullDayProtocal extends BaseProtocal<FullDayWeatherInfo> {
    @Override
    protected String getKey() {
        return "fullday";
    }

    @Override
    protected FullDayWeatherInfo parseJson(String json) {
        FullDayWeatherInfo weatherInfos = new FullDayWeatherInfo();
        JSONObject jsonObject = null;
        List<FullDayWeatherInfo.JhEntity> fullDayWeatherInfos = new ArrayList<FullDayWeatherInfo.JhEntity>();
        FullDayWeatherInfo.JhEntity jhEntity = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray jh = jsonObject.getJSONArray("jh");
            for (int i = 0; i < jh.length(); i++) {
                jhEntity = new FullDayWeatherInfo.JhEntity();
                JSONObject obj = jh.getJSONObject(i);
                jhEntity.setJa(obj.getString("ja"));
                jhEntity.setJb(obj.getString("jb"));
                jhEntity.setJc(obj.getString("jc"));
                jhEntity.setJd(obj.getString("jd"));
                jhEntity.setJe(obj.getString("je"));
                jhEntity.setJf(obj.getString("jf"));
                fullDayWeatherInfos.add(jhEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        weatherInfos.setJh(fullDayWeatherInfos);
        return weatherInfos;
    }

    @Override
    protected String getUrl() {
        return Constant.FULLDAYWEATHER + getCityCode() + Constant.HTMLKEY;
    }
}
