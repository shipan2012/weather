package com.xfshipan.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/10/24.
 * SharePreferences工具类
 */
public class SharePrefUtils {

    public static final String WEATHER_CONFIG = "weather_config";
    public static final String CITY_SELECTED = "city_selected";
    public static final String CITY_LOCAL = "city_local";
    public static final String THEME_SELECTED = "theme_selected";
    public static final String THEME_MODIFIED = "theme_modified";

    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(WEATHER_CONFIG,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(WEATHER_CONFIG,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(WEATHER_CONFIG,
                Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void setString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences(WEATHER_CONFIG,
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static int getInt(Context ctx, String key, int defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(WEATHER_CONFIG,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static void setInt(Context ctx, String key, int value) {
        SharedPreferences sp = ctx.getSharedPreferences(WEATHER_CONFIG,
                Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }
}
