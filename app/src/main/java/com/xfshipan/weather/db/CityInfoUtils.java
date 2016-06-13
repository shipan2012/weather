package com.xfshipan.weather.db;

import com.xfshipan.weather.BaseApplication;
import com.xfshipan.weather.bean.CityInfo;
import com.xfshipan.weather.db.dao.WeatherCityDao;
import com.xfshipan.weather.utils.SharePrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class CityInfoUtils {

    /**
     * 将一个cityBean对象转成String
     *
     * @param item
     * @return
     */
    public static String parseBeanToString(CityInfo item) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append(item.getWeatherId());
        buffer.append(",");
        buffer.append(item.getProvinceName());
        buffer.append(",");
        buffer.append(item.getCityName());
        buffer.append(",");
        buffer.append(item.getAreaName());
        buffer.append(",");
        buffer.append(item.getIsLocal());
        buffer.append("}");
        buffer.append(";");
        return buffer.toString();
    }

    /**
     * 将String转成Bean
     *
     * @param str
     * @return
     */
    public static CityInfo parStringToBean(String str) {
        if (!str.equals("")) {
            CityInfo info = new CityInfo();
            String replace = str.replace("{", "").replace("}", "").replace(";", "");
            String[] infos = replace.split(",");
            info.setWeatherId(Long.parseLong(infos[0]));
            info.setProvinceName(infos[1]);
            info.setCityName(infos[2]);
            info.setAreaName(infos[3]);
            info.setIsLocal(Integer.parseInt(infos[4]));
            return info;
        }
        return null;
    }


    /**
     * 将String字符串抓化成List集合
     *
     * @param cityString
     * @return
     */
    public static List<CityInfo> transCityString(String cityString) {
        List<CityInfo> cityInfos = new ArrayList<CityInfo>();
        String[] infos = cityString.split(";");
        for (String str : infos) {
            //str不为空
            if (!str.equals("")) {
                CityInfo info = parStringToBean(str);
                cityInfos.add(info);
            }
        }
        return cityInfos;
    }

    /**
     * 将百度定位得到得数据解析成天气城市信息
     *
     * @param city
     * @param district
     * @return
     */
    public static CityInfo transLocationInfos(String city, String district) {
        List<CityInfo> cityInfos = WeatherCityDao.queryCityCode(district);
        CityInfo cityInfo = new CityInfo();
        if (cityInfos.size() > 0) {
            if (cityInfos.size() == 1) {
                //判断城市是不是一样,一样直接返回
                if (cityInfos.get(0).getCityName().contains(city)) {
                    cityInfo = cityInfos.get(0);
                    cityInfo.setIsLocal(1);
                    return cityInfo;
                } else {
                    //不一样，获取当前市区的信息,没有相同的市
                    cityInfo = WeatherCityDao.queryCityCode(city).get(0);
                    cityInfo.setIsLocal(1);
                    return cityInfo;
                }
            } else if (cityInfos.size() > 1) {
                //如果获得的城市不止一个，再根据城市进行筛选
                for (CityInfo info : cityInfos) {
                    if (info.getCityName().contains(city)) {
                        info.setIsLocal(1);
                        return info;
                    }
                }
                return null;
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * 判断选中的城市是否存在传入的城市
     *
     * @param cityInfo
     * @return
     */
    public static boolean isExitCity(CityInfo cityInfo) {
        //将Bean对象转换成字符串
        String cityString = parseBeanToString(cityInfo);
        boolean exit = WeatherCityDao.isExit(SharePrefUtils.CITY_SELECTED, cityString);
        return exit;
    }

    /**
     * 判断已经选择的城市是否为空
     *
     * @return
     */
    public static boolean isEmpty() {
        String cityInfos = SharePrefUtils.getString(BaseApplication.getApplication(), SharePrefUtils.CITY_SELECTED, "");
        List<CityInfo> infos = transCityString(cityInfos);
        return infos.isEmpty();
    }
}
