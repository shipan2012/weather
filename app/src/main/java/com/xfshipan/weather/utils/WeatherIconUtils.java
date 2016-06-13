package com.xfshipan.weather.utils;

import android.text.TextUtils;

import com.xfshipan.weather.R;
import com.xfshipan.weather.http.Constant;
import com.xfshipan.weather.http.WeatherConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherIconUtils {

    private static String languageKey;

    private WeatherIconUtils() {
    }

    /**
     * 获取Api的描述信息
     *
     * @param aqiLevel
     * @param language
     * @return
     */
    public static String getAqiDesc(int aqiLevel, String language) {
//		String languageKey = checkLanguageSuport(language);
        if (aqiLevel == 0)
            return WeatherConstants.AQI_DESC_LANGUAGE_MAP.get(languageKey).get(
                    Integer.valueOf(0));
        else if ((aqiLevel > 0) && (aqiLevel <= 50))
            return WeatherConstants.AQI_DESC_LANGUAGE_MAP.get(languageKey).get(
                    Integer.valueOf(50));
        else if ((aqiLevel > 50) && (aqiLevel <= 100))
            return WeatherConstants.AQI_DESC_LANGUAGE_MAP.get(languageKey).get(
                    Integer.valueOf(100));
        else if ((aqiLevel > 100) && (aqiLevel <= 150))
            return WeatherConstants.AQI_DESC_LANGUAGE_MAP.get(languageKey).get(
                    Integer.valueOf(150));
        else if ((aqiLevel > 150) && (aqiLevel <= 200))
            return WeatherConstants.AQI_DESC_LANGUAGE_MAP.get(languageKey).get(
                    Integer.valueOf(200));
        else if ((aqiLevel > 200) && (aqiLevel <= 300))
            return WeatherConstants.AQI_DESC_LANGUAGE_MAP.get(languageKey).get(
                    Integer.valueOf(300));
        else if ((aqiLevel > 300) && (aqiLevel < 500))
            return WeatherConstants.AQI_DESC_LANGUAGE_MAP.get(languageKey).get(
                    Integer.valueOf(500));
        return WeatherConstants.AQI_DESC_LANGUAGE_MAP.get(languageKey).get(
                Integer.valueOf(500));
    }

    /**
     * 获取Aqi的水平
     *
     * @param aqiLevel
     * @param language
     * @return
     */
    public static String getAqiLevel(int aqiLevel, String language) {
        languageKey = checkLanguageSuport(language);
        if (aqiLevel == 0)
            return WeatherConstants.AQI_LEVEL_LANGUAGE_MAP.get(languageKey)
                    .get(Integer.valueOf(0));
        else if ((aqiLevel > 0) && (aqiLevel <= 50))
            return WeatherConstants.AQI_LEVEL_LANGUAGE_MAP.get(languageKey)
                    .get(Integer.valueOf(50));
        else if ((aqiLevel > 50) && (aqiLevel <= 100))
            return WeatherConstants.AQI_LEVEL_LANGUAGE_MAP.get(languageKey)
                    .get(Integer.valueOf(100));
        else if ((aqiLevel > 100) && (aqiLevel <= 150))
            return WeatherConstants.AQI_LEVEL_LANGUAGE_MAP.get(languageKey)
                    .get(Integer.valueOf(150));
        else if ((aqiLevel > 150) && (aqiLevel <= 200))
            return WeatherConstants.AQI_LEVEL_LANGUAGE_MAP.get(languageKey)
                    .get(Integer.valueOf(200));
        else if ((aqiLevel > 200) && (aqiLevel <= 300))
            return WeatherConstants.AQI_LEVEL_LANGUAGE_MAP.get(languageKey)
                    .get(Integer.valueOf(300));
        else if ((aqiLevel > 300) && (aqiLevel < 500))
            return WeatherConstants.AQI_LEVEL_LANGUAGE_MAP.get(languageKey)
                    .get(Integer.valueOf(500));
        return WeatherConstants.AQI_LEVEL_LANGUAGE_MAP.get(languageKey).get(
                Integer.valueOf(500));
    }

    /**
     * 获取天气图标
     *
     * @param type
     * @return
     */
    public static int getWeatherIcon(int type) {
        return getFullDayWeatherIcon(type, isNight(System.currentTimeMillis()));
    }

    public static int getFullDayWeatherIcon(int type, boolean isNight) {
        // 如果是晚上
        if (isNight) {
            switch (type) {
                case Constant.SUNNY:
                    return R.mipmap.ic_nightsunny_big;
                case Constant.CLOUDY:
                    return R.mipmap.ic_nightcloudy_big;
                case Constant.HEAVY_RAIN:
                case Constant.LIGHT_RAIN:
                case Constant.MODERATE_RAIN:
                case Constant.SHOWER:
                case Constant.STORM:
                    return R.mipmap.ic_nightrain_big;
                case Constant.SNOWSTORM:
                case Constant.LIGHT_SNOW:
                case Constant.MODERATE_SNOW:
                case Constant.HEAVY_SNOW:
                case Constant.SNOW_SHOWER:
                    return R.mipmap.ic_nightsnow_big;
                default:
                    break;
            }
        }
        // 如果是白天
        switch (type) {
            case Constant.SUNNY:
                return R.mipmap.ic_sunny_big;
            case Constant.CLOUDY:
                return R.mipmap.ic_cloudy_big;
            case Constant.OVERCAST:
                return R.mipmap.ic_overcast_big;
            case Constant.FOGGY:
                return R.mipmap.tornado_day_night;
            case Constant.SEVERE_STORM:
                return R.mipmap.hurricane_day_night;
            case Constant.HEAVY_STORM:
                return R.mipmap.ic_heavyrain_big;
            case Constant.STORM:
                return R.mipmap.ic_heavyrain_big;
            case Constant.THUNDERSHOWER:
                return R.mipmap.ic_thundeshower_big;
            case Constant.SHOWER:
                return R.mipmap.ic_shower_big;
            case Constant.HEAVY_RAIN:
                return R.mipmap.ic_heavyrain_big;
            case Constant.MODERATE_RAIN:
                return R.mipmap.ic_moderraterain_big;
            case Constant.LIGHT_RAIN:
                return R.mipmap.ic_lightrain_big;
            case Constant.SLEET:
                return R.mipmap.ic_sleet_big;
            case Constant.SNOWSTORM:
                return R.mipmap.ic_snow_big;
            case Constant.SNOW_SHOWER:
                return R.mipmap.ic_snow_big;
            case Constant.HEAVY_SNOW:
                return R.mipmap.ic_heavysnow_big;
            case Constant.MODERATE_SNOW:
                return R.mipmap.ic_snow_big;
            case Constant.LIGHT_SNOW:
                return R.mipmap.ic_snow_big;
            case Constant.STRONGSANDSTORM:
                return R.mipmap.ic_sandstorm_big;
            case Constant.SANDSTORM:
                return R.mipmap.ic_sandstorm_big;
            case Constant.SAND:
                return R.mipmap.ic_sandstorm_big;
            case Constant.BLOWING_SAND:
                return R.mipmap.ic_sandstorm_big;
            case Constant.ICE_RAIN:
                return R.mipmap.freezing_rain_day_night;
            case Constant.DUST:
                return R.mipmap.ic_dust_big;
            case Constant.HAZE:
                return R.mipmap.ic_haze_big;
            default:
                return R.mipmap.ic_default_big;
        }
    }
    public static boolean isNight(long time) {
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String timeStr = df.format(new Date(System.currentTimeMillis()));
        // LogUtils.i("liweiping", "timeStr = " + timeStr);
        try {
            int timeHour = Integer.parseInt(timeStr);
            return (timeHour >= 18 || timeHour <= 6);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是白天还是夜晚
     *
     * @param timeStr
     * @return
     */
    public static boolean isNight(String timeStr) {
        try {
            int timeHour = Integer.parseInt(timeStr);
            return (timeHour >= 18 || timeHour <= 6);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String checkLanguageSuport(String language) {
        if (WeatherConstants.SURPORTTED_LANGUAGE_LIST.contains(language
                .toLowerCase()))
            return language.toLowerCase();
        return Locale.US.toString().toLowerCase();
    }

    public static String getAQISource(String paramString) {
        String str = checkLanguageSuport(paramString);
        return ((String) WeatherConstants.AQI_SOURCE_LANGUAGE_MAP.get(str));
    }

    public static int getAnimationType(String weatherStr) {
        int type = -1;
        String[] strs = weatherStr.split("转");
        if (strs.length > 1) {
            String[] arrayOfString3 = strs[0].split("到");
            if (arrayOfString3.length > 1)
                type = WeatherConstants.WEATHER_ANIMATION_MAP
                        .get(arrayOfString3[1]);
            else {
                type = WeatherConstants.WEATHER_ANIMATION_MAP
                        .get(strs[0]);
            }
        } else {
            String[] arrayOfString2 = weatherStr.split("到");
            if (arrayOfString2.length > 1)
                type = WeatherConstants.WEATHER_ANIMATION_MAP
                        .get(arrayOfString2[1]);
            else
                type = WeatherConstants.WEATHER_ANIMATION_MAP
                        .get(weatherStr);
        }
        return type;
    }

    public static int getAqi(String aqiStr) {
        int aqi = WeatherConstants.NO_VALUE_FLAG;
        try {
            aqi = Integer.parseInt(aqiStr);
        } catch (NumberFormatException e) {
        }
        return aqi;
    }

    public static String getChina(String paramString) {
        String str = checkLanguageSuport(paramString);
        return ((String) WeatherConstants.CHINA_LANGUAGE_MAP.get(str));
    }

    public static int getHumidity(String humidity) {
        return Integer.parseInt(humidity.split("%")[0]);
    }

    public static String getIndexDesc(String indexTitle, String indexType,
                                      String language) {
        String languageKey = checkLanguageSuport(language);
        return WeatherConstants.INDEX_DESC_LANGUAGE_MAP.get(languageKey)
                .get(indexTitle).get(indexType);
    }

    public static String getIndexDetail(String paramString1,
                                        String paramString2, String language) {
        String languageKey = checkLanguageSuport(language);
        return WeatherConstants.INDEX_DETAIL_LANGUAGE_MAP.get(languageKey)
                .get(paramString1).get(paramString2);
    }

    public static String getIndexTitle(String indexTitleKey, String language) {
        String languageKey = checkLanguageSuport(language);
        return WeatherConstants.INDEX_LANGUAGE_MAP.get(languageKey).get(
                indexTitleKey);
    }

    public static Integer getIndexType(String indexTypeKey) {
        return WeatherConstants.INDEX_TYPE.get(indexTypeKey);
    }

    public static int getIntFromJSON(JSONObject jsonObject, String paramString) {
        int result = WeatherConstants.NO_VALUE_FLAG;
        try {
            result = jsonObject.getInt(paramString);
        } catch (JSONException e) {
        }
        return result;
    }

    public static String getWeather(String weatherStr) {
        String[] arrayOfString = weatherStr.split("转");
        if (arrayOfString.length > 1) {
            String str1 = (String) WeatherConstants.CN_WEATHER_TYPE_MAP
                    .get(arrayOfString[0]);
            String str2 = (String) WeatherConstants.CN_WEATHER_TYPE_MAP
                    .get(arrayOfString[1]);
            return str1 + "-" + str2;
        }
        return ((String) WeatherConstants.CN_WEATHER_TYPE_MAP.get(weatherStr));
    }

    public static WeatherName getWeatherName(String weatherStr, String language) {
        WeatherName weatherName = new WeatherName();
        String languageKey = checkLanguageSuport(language);
        String[] strs = weatherStr.split("转");
        if (strs.length > 1) {
            String str4 = WeatherConstants.CN_WEATHER_TYPE_MAP.get(strs[0]);
            String str5 = WeatherConstants.CN_WEATHER_TYPE_MAP.get(strs[1]);
            String str6 = WeatherConstants.WEATHER_TYPE_LANGUAGE_MAP.get(
                    languageKey).get(str4);
            String str7 = WeatherConstants.WEATHER_TYPE_LANGUAGE_MAP.get(
                    languageKey).get(str5);
            String str8 = WeatherConstants.TRANSFER_LANGUAGE_MAP
                    .get(languageKey);
            weatherName.setName(str6 + str8 + str7);
            weatherName.setFrom(str6);
            weatherName.setTo(str7);
            return weatherName;
        }
        String key = WeatherConstants.CN_WEATHER_TYPE_MAP.get(weatherStr);
        String name = WeatherConstants.WEATHER_TYPE_LANGUAGE_MAP.get(
                languageKey).get(key);
        weatherName.setName(name);
        weatherName.setFrom(name);
        weatherName.setTo(name);
        return weatherName;
    }

    public static class WeatherName {
        String from;
        String name;
        String to;

        public String getFrom() {
            return this.from;
        }

        public String getName() {
            return this.name;
        }

        public String getTo() {
            return this.to;
        }

        public void setFrom(String paramString) {
            this.from = paramString;
        }

        public void setName(String paramString) {
            this.name = paramString;
        }

        public void setTo(String paramString) {
            this.to = paramString;
        }
    }
}
