package com.xfshipan.weather.bean;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ReadableFullInfos {
    private String time; //时间
    private int icon;// 图标
    private String temp; //温度

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "ReadableFullInfos{" +
                "time='" + time + '\'' +
                ", icon=" + icon +
                ", temp='" + temp + '\'' +
                '}';
    }
}
