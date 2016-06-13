package com.xfshipan.weather.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xfshipan.weather.bean.CityInfo;
import com.xfshipan.weather.utils.SharePrefUtils;
import com.xfshipan.weather.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class WeatherCityDao {

    private static List<CityInfo> cityInfos;

    /**
     * 添加城市
     *
     * @param str
     */
    public static void addCity(String str) {
        //第一步：取出本地已经存在的数据
        String configString = SharePrefUtils.getString(UiUtils.getContext(), SharePrefUtils.CITY_SELECTED, "");
        StringBuffer buffer = new StringBuffer(configString);
        //第二步：追加数据
        buffer.append(str);
        //第三步:设置数据
        SharePrefUtils.setString(UiUtils.getContext(), SharePrefUtils.CITY_SELECTED, buffer.toString());

    }

    /**
     * 删除城市
     *
     * @param oldStr
     */
    public static void deleteCity(String oldStr) {
        //第一步：取出本地已经存在的数据
        String configString = SharePrefUtils.getString(UiUtils.getContext(), SharePrefUtils.CITY_SELECTED, "");
        //第二步：删除数据
        String replace = configString.replace(oldStr, "");
        //第三步:设置数据
        SharePrefUtils.setString(UiUtils.getContext(), SharePrefUtils.CITY_SELECTED, replace);

    }

    /**
     * 判断SharePreferenc中是否已经存在选中城市
     *
     * @param citySelected
     * @param str
     * @return
     */
    public static boolean isExit(String citySelected, String str) {
        String citys = SharePrefUtils.getString(UiUtils.getContext(), citySelected, "");
        return citys.contains(str);
    }

    /**
     * 通过输入地名，获取位置代码
     *
     * @param cityName
     * @return
     */
    public static List<CityInfo> queryCityCode(String cityName) {
        cityInfos = new ArrayList<CityInfo>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                UiUtils.getContext().getFilesDir() + "/city.db", null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db
                .rawQuery(
                        "select province_name ,city_name, area_name,weather_id from weathers where province_name like ? or city_name like ? or area_name like ?",
                        new String[]{"%" + cityName + "%", "%" + cityName + "%", "%" + cityName + "%"});
        CityInfo cityInfo = null;
        if (cursor != null) {
            //more to the first row
            cursor.moveToFirst();

            //iterate over rows
            for (int i = 0; i < cursor.getCount(); i++) {

                //iterate over the columns
                for (int j = 0; j < cursor.getColumnNames().length; j++) {
                    cityInfo = new CityInfo();
                    String provinceName = cursor.getString(0);
                    String citysName = cursor.getString(1);
                    String areaName = cursor.getString(2);
                    long cityCode = cursor.getLong(3);
                    cityInfo.setProvinceName(provinceName);
                    cityInfo.setCityName(citysName);
                    cityInfo.setAreaName(areaName);
                    cityInfo.setWeatherId(cityCode);
                }
                cityInfos.add(cityInfo);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return cityInfos;
    }

    public static CityInfo queryInfoByCode(long cityCode) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                UiUtils.getContext().getFilesDir() + "/city.db", null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db
                .rawQuery(
                        "select province_name ,city_name, area_name,weather_id from weathers where weather_id = ?",
                        new String[]{cityCode + ""});
        CityInfo cityInfo = null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                cityInfo = new CityInfo();
                String provinceName = cursor.getString(0);
                String citysName = cursor.getString(1);
                String areaName = cursor.getString(2);
                long code = cursor.getLong(3);
                cityInfo.setProvinceName(provinceName);
                cityInfo.setCityName(citysName);
                cityInfo.setAreaName(areaName);
                cityInfo.setWeatherId(code);
            }
            cursor.close();
            db.close();
        }
        return cityInfo;
    }

    public static List<CityInfo> queryHotCitys() {
        cityInfos = new ArrayList<CityInfo>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                UiUtils.getContext().getFilesDir() + "/city.db", null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db
                .rawQuery(
                        "select  name ,postID from hotcity ", null);
        CityInfo cityInfo = null;
        if (cursor != null) {

            //more to the first row
            cursor.moveToFirst();

            //iterate over rows
            for (int i = 0; i < cursor.getCount(); i++) {

                //iterate over the columns
                for (int j = 0; j < cursor.getColumnNames().length; j++) {
                    cityInfo = new CityInfo();
                    String areaName = cursor.getString(0);
                    long cityCode = cursor.getLong(1);
                    cityInfo.setAreaName(areaName);
                    cityInfo.setWeatherId(cityCode);
                }
                cityInfos.add(cityInfo);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return cityInfos;
    }
}
