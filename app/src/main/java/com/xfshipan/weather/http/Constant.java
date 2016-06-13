package com.xfshipan.weather.http;

/**
 * Created by Administrator on 2015/10/22.
 */
public class Constant {
    public static final String DEFAULTWEATHER = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=";
    //实时天气
    public static final String REALTIEMWEATHER = "http://www.nmc.cn/service/data/real/";
    //24小时天气.扩展名为.html
    public static final String FULLDAYWEATHER = "http://m.weather.com.cn/mpub/hours/";
    //一周天气
    public static final String WEEKWEATHER = "http://www.nmc.cn/service/data/predict/";

    public static final String JSONKEY = ".json";
    public static final String HTMLKEY = ".html";

    //数字代码对应的意义
    public static final int NO_VALUE_FLAG = -999;//无
    public static final int SUNNY = 0;//晴
    public static final int CLOUDY = 1;//多云
    public static final int OVERCAST = 2;//阴
    public static final int FOGGY = 3;//雾
    public static final int SEVERE_STORM = 4;//飓风
    public static final int HEAVY_STORM = 5;//大暴风雨
    public static final int STORM = 6;//暴风雨
    public static final int THUNDERSHOWER = 7;//雷阵雨
    public static final int SHOWER = 8;//阵雨
    public static final int HEAVY_RAIN = 9;//大雨
    public static final int MODERATE_RAIN = 10;//中雨
    public static final int LIGHT_RAIN = 11;//小雨
    public static final int SLEET = 12;//雨夹雪
    public static final int SNOWSTORM = 13;//暴雪
    public static final int SNOW_SHOWER = 14;//阵雪
    public static final int HEAVY_SNOW = 15;//大雪
    public static final int MODERATE_SNOW = 16;//中雪
    public static final int LIGHT_SNOW = 17;//小雪
    public static final int STRONGSANDSTORM = 18;//强沙尘暴
    public static final int SANDSTORM = 19;//沙尘暴
    public static final int SAND = 20;//沙尘
    public static final int BLOWING_SAND = 21;//风沙
    public static final int ICE_RAIN = 22;//冻雨
    public static final int DUST = 23;//尘土
    public static final int HAZE = 24;//霾
}
