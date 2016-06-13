package com.xfshipan.weather.bean;

import android.text.TextUtils;

import com.xfshipan.weather.http.WeatherConstants;
import com.xfshipan.weather.utils.TimeUtils;
import com.xfshipan.weather.utils.WeatherIconUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class WeatherUtilities {
    /**
     * 将天气转化成可用的
     *
     * @param temp
     * @return
     */
    public static String[] convertTemp(String temp) {
        String[] temps = temp.split("~");
        String minTemp = (temps[0].split("℃"))[0];
        String maxTemp = (temps[1].split("℃", 0))[0];
        return new String[]{
                minTemp, maxTemp
        };
    }

    /**
     * 将json数据封装成可用的数据
     *
     * @param defaultInfos
     */
    public static List<ReadableWeekInfos> parseWeekInfos(DefaultWeatherInfo defaultInfos) {
        List<ReadableWeekInfos> weatherInfos = new ArrayList<ReadableWeekInfos>();
        DefaultWeatherInfo.ForecastEntity forecast = defaultInfos.getForecast();
        ReadableWeekInfos weatherInfo1 = new ReadableWeekInfos();
        ReadableWeekInfos weatherInfo2 = new ReadableWeekInfos();
        ReadableWeekInfos weatherInfo3 = new ReadableWeekInfos();
        ReadableWeekInfos weatherInfo4 = new ReadableWeekInfos();
        ReadableWeekInfos weatherInfo5 = new ReadableWeekInfos();

        int type1 = WeatherUtilities.getAnimationType(forecast.getWeather1());
        int type2 = WeatherUtilities.getAnimationType(forecast.getWeather2());
        int type3 = WeatherUtilities.getAnimationType(forecast.getWeather3());
        int type4 = WeatherUtilities.getAnimationType(forecast.getWeather4());
        int type5 = WeatherUtilities.getAnimationType(forecast.getWeather5());
        //图标
        weatherInfo1.setIcons(WeatherIconUtils.getWeatherIcon(type1));
        weatherInfo2.setIcons(WeatherIconUtils.getWeatherIcon(type2));
        weatherInfo3.setIcons(WeatherIconUtils.getWeatherIcon(type3));
        weatherInfo4.setIcons(WeatherIconUtils.getWeatherIcon(type4));
        weatherInfo5.setIcons(WeatherIconUtils.getWeatherIcon(type5));
        //温度
        weatherInfo1.setTemps(WeatherUtilities.convertTemp(forecast.getTemp1()));
        weatherInfo2.setTemps(WeatherUtilities.convertTemp(forecast.getTemp2()));
        weatherInfo3.setTemps(WeatherUtilities.convertTemp(forecast.getTemp3()));
        weatherInfo4.setTemps(WeatherUtilities.convertTemp(forecast.getTemp4()));
        weatherInfo5.setTemps(WeatherUtilities.convertTemp(forecast.getTemp5()));
        //星期
        weatherInfo1.setWeekInfos("今天");// 从今天开始
        weatherInfo2.setWeekInfos(TimeUtils.getWeek(1, TimeUtils.XING_QI));
        weatherInfo3.setWeekInfos(TimeUtils.getWeek(2, TimeUtils.XING_QI));
        weatherInfo4.setWeekInfos(TimeUtils.getWeek(3, TimeUtils.XING_QI));
        weatherInfo5.setWeekInfos(TimeUtils.getWeek(4, TimeUtils.XING_QI));
        //描述
        weatherInfo1.setDesc(forecast.getWeather1());
        weatherInfo2.setDesc(forecast.getWeather2());
        weatherInfo3.setDesc(forecast.getWeather3());
        weatherInfo4.setDesc(forecast.getWeather4());
        weatherInfo5.setDesc(forecast.getWeather5());

        weatherInfos.add(weatherInfo1);
        weatherInfos.add(weatherInfo2);
        weatherInfos.add(weatherInfo3);
        weatherInfos.add(weatherInfo4);
        weatherInfos.add(weatherInfo5);
        return weatherInfos;
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

    /**
     * realTime.getWeather()从realTime中获取Weather信息
     *
     * @param weatherStr
     * @return
     */
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

    public static String getAqiDesc(int aqiLevel, String language) {
//		String languageKey = checkLanguageSuport(language);
        String languageKey = Locale.CHINA.toString().toLowerCase();
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

    public static String getAqiLevel(int aqiLevel, String language) {
//		String languageKey = checkLanguageSuport(language);
        String languageKey = Locale.CHINA.toString().toLowerCase();
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

    public static String getWind(String windStr, String language) {
        String resultStr = "";
        String[] winds = windStr.split("转");
        String languageKey = checkLanguageSuport(language);
        if (winds.length > 1) {
            String windReal = WeatherConstants.CN_WIND_TYPE_MAP.get(winds[0]);
            resultStr = WeatherConstants.WIND_TYPE_LANGUAGE_MAP
                    .get(languageKey).get(windReal);
            if (resultStr == null)
                resultStr = (String) WeatherConstants.CN_WIND_TYPE_MAP
                        .get(winds[1]);
        } else {
            resultStr = WeatherConstants.WIND_TYPE_LANGUAGE_MAP
                    .get(languageKey).get(windStr);
            if (resultStr == null)
                resultStr = (String) WeatherConstants.CN_WIND_TYPE_MAP
                        .get(resultStr);
        }
        return resultStr;
    }

    /**
     * 获取风的信息
     *
     * @param fx
     * @param fl
     * @param language
     * @return
     */
    public static String getWind(String fx, String fl, String language) {
        String resultStr = "";
        String fxValue = WeatherConstants.CN_WIND_TYPE_MAP.get(fx);
        if (fxValue == null)
            fxValue = "0";
        String languageKey = checkLanguageSuport(language);
        String[] winds = fl.split("转");
        if (winds.length > 1) {
            String windBefore = (String) (WeatherConstants.WIND_LEVEL_LANGUAGE_MAP
                    .get(languageKey)).get(winds[0]);
            String windAfter = (String) (WeatherConstants.WIND_LEVEL_LANGUAGE_MAP
                    .get(languageKey)).get(winds[1]);
            String transferStr = (String) WeatherConstants.TRANSFER_LANGUAGE_MAP
                    .get(languageKey);
            resultStr = WeatherConstants.WIND_TYPE_LANGUAGE_MAP
                    .get(languageKey).get(fxValue)
                    + WeatherConstants.WIND_TYPE_CONNECTTOR_LANGUAGE_MAP
                    .get(languageKey)
                    + windBefore
                    + transferStr
                    + windAfter;
        } else {
            resultStr = (WeatherConstants.WIND_TYPE_LANGUAGE_MAP
                    .get(languageKey)).get(fxValue)
                    + WeatherConstants.WIND_TYPE_CONNECTTOR_LANGUAGE_MAP
                    .get(languageKey)
                    + WeatherConstants.WIND_LEVEL_LANGUAGE_MAP.get(languageKey)
                    .get(fl);
        }
        if ((!(TextUtils.isEmpty(language)))
                && (language.toLowerCase().contains("en")))
            resultStr = "Wind: " + resultStr;
        return resultStr;
    }

    public static String getWindIndexDetail(String windIndex, String language) {
        String languageKey = checkLanguageSuport(language);
        String[] strs = windIndex.split("转");
        if (strs.length > 1)
            return WeatherConstants.WIND_LEVEL_DETAIL_LANGUAGE_MAP.get(languageKey)
                    .get(strs[1]);
        return WeatherConstants.WIND_LEVEL_DETAIL_LANGUAGE_MAP.get(languageKey).get(
                windIndex);
    }

    public static String getFullDayTime(String jf) {
        String hour = jf.substring(8, 10);
        String min = jf.substring(10, 12);
        return hour + ":" + min;
    }

    /**
     * 将24小时天气json解析成可读的数据
     *
     * @param fullDayWeatherInfo
     * @return
     */
    public static List<ReadableFullInfos> parseFullDayInfos(FullDayWeatherInfo fullDayWeatherInfo) {
        if (fullDayWeatherInfo != null) {
            List<FullDayWeatherInfo.JhEntity> jh = fullDayWeatherInfo.getJh();
            List<ReadableFullInfos> readableFullInfoses = new ArrayList<ReadableFullInfos>();
            ReadableFullInfos fullInfo;
            for (FullDayWeatherInfo.JhEntity jhEntity : jh) {
                fullInfo = new ReadableFullInfos();
                String fullDayTime = WeatherUtilities.getFullDayTime(jhEntity.getJf());
                String subTime = WeatherUtilities.getFullDayTime(jhEntity.getJf()).substring(0, 2);
                fullInfo.setTime(fullDayTime);
                fullInfo.setIcon(WeatherIconUtils.getFullDayWeatherIcon(Integer.parseInt(jhEntity.getJa()), WeatherIconUtils.isNight(subTime)));
                fullInfo.setTemp(jhEntity.getJb());
                readableFullInfoses.add(fullInfo);
            }
            return readableFullInfoses;
        }
        return null;
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
