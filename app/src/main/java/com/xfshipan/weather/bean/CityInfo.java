package com.xfshipan.weather.bean;

/**
 * Created by Administrator on 2015/10/22.
 */
public class CityInfo implements WeatherInfo {
    private String provinceName;
    private String cityName;
    private String areaName;
    private long weatherId;
    private int isLocal;//0表示不是本地，1表示本地

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(long weatherId) {
        this.weatherId = weatherId;
    }

    public int getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(int isLocal) {
        this.isLocal = isLocal;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", weatherId=" + weatherId +
                ", isLocal=" + isLocal +
                '}';
    }
}
