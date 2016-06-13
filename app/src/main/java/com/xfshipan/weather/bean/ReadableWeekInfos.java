package com.xfshipan.weather.bean;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/10/24.
 * 一周天气
 */
public class ReadableWeekInfos implements WeatherInfo {
    private String[] temps; //温度，最高温最低温
    private int icons;//天气图标
    private String weekInfos;//星期
    private String desc;//天气描述

    public String[] getTemps() {
        return temps;
    }

    public void setTemps(String[] temps) {
        this.temps = temps;
    }

    public int getIcons() {
        return icons;
    }

    public void setIcons(int icons) {
        this.icons = icons;
    }

    public String getWeekInfos() {
        return weekInfos;
    }

    public void setWeekInfos(String weekInfos) {
        this.weekInfos = weekInfos;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ReadableWeekInfos{" +
                "temps=" + Arrays.toString(temps) +
                ", icons=" + icons +
                ", weekInfos='" + weekInfos + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
