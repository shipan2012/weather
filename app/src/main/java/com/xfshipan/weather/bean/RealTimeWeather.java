package com.xfshipan.weather.bean;

/**
 * Created by Administrator on 2015/10/24.
 * 实时天气
 */
public class RealTimeWeather implements WeatherInfo {

    /**
     * city_code : 101200201
     * city_name : 襄阳
     * data_time : 2015-10-24 10:30
     * jie_qi : 霜降
     * moon : 九月十二
     * warn : {"alert":"9999","city":"9999","county":"9999","pic":"9999","province":"9999","url":"9999"}
     * weather : {"airpressure":"1001","feelst":"17","humidity":"79","icomfort":"-1","img":"1","info":"多云","rain":"0.0","rcomfort":"59","temperature":"17.9"}
     * week : 6
     * wind : {"direct":"西北风","power":"微风","speed":"1.5m/s"}
     */

    private String city_code;
    private String city_name;
    private String data_time;
    private String jie_qi;
    private String moon;
    /**
     * alert : 9999
     * city : 9999
     * county : 9999
     * pic : 9999
     * province : 9999
     * url : 9999
     */

    private WarnEntity warn;
    /**
     * airpressure : 1001
     * feelst : 17
     * humidity : 79
     * icomfort : -1
     * img : 1
     * info : 多云
     * rain : 0.0
     * rcomfort : 59
     * temperature : 17.9
     */

    private WeatherEntity weather;
    private String week;
    /**
     * direct : 西北风
     * power : 微风
     * speed : 1.5m/s
     */

    private WindEntity wind;

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setData_time(String data_time) {
        this.data_time = data_time;
    }

    public void setJie_qi(String jie_qi) {
        this.jie_qi = jie_qi;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    public void setWarn(WarnEntity warn) {
        this.warn = warn;
    }

    public void setWeather(WeatherEntity weather) {
        this.weather = weather;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setWind(WindEntity wind) {
        this.wind = wind;
    }

    public String getCity_code() {
        return city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getData_time() {
        return data_time;
    }

    public String getJie_qi() {
        return jie_qi;
    }

    public String getMoon() {
        return moon;
    }

    public WarnEntity getWarn() {
        return warn;
    }

    public WeatherEntity getWeather() {
        return weather;
    }

    public String getWeek() {
        return week;
    }

    public WindEntity getWind() {
        return wind;
    }

    public static class WarnEntity {
        private String alert;
        private String city;
        private String county;
        private String pic;
        private String province;
        private String url;

        public void setAlert(String alert) {
            this.alert = alert;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlert() {
            return alert;
        }

        public String getCity() {
            return city;
        }

        public String getCounty() {
            return county;
        }

        public String getPic() {
            return pic;
        }

        public String getProvince() {
            return province;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class WeatherEntity implements WeatherInfo {
        private String airpressure;
        private String feelst;
        private String humidity;
        private String icomfort;
        private String img;
        private String info;
        private String rain;
        private String rcomfort;
        private String temperature;

        public void setAirpressure(String airpressure) {
            this.airpressure = airpressure;
        }

        public void setFeelst(String feelst) {
            this.feelst = feelst;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public void setIcomfort(String icomfort) {
            this.icomfort = icomfort;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setRain(String rain) {
            this.rain = rain;
        }

        public void setRcomfort(String rcomfort) {
            this.rcomfort = rcomfort;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getAirpressure() {
            return airpressure;
        }

        public String getFeelst() {
            return feelst;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getIcomfort() {
            return icomfort;
        }

        public String getImg() {
            return img;
        }

        public String getInfo() {
            return info;
        }

        public String getRain() {
            return rain;
        }

        public String getRcomfort() {
            return rcomfort;
        }

        public String getTemperature() {
            return temperature;
        }

        @Override
        public String toString() {
            return "WeatherEntity{" +
                    "airpressure='" + airpressure + '\'' +
                    ", feelst='" + feelst + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", icomfort='" + icomfort + '\'' +
                    ", img='" + img + '\'' +
                    ", info='" + info + '\'' +
                    ", rain='" + rain + '\'' +
                    ", rcomfort='" + rcomfort + '\'' +
                    ", temperature='" + temperature + '\'' +
                    '}';
        }
    }

    public static class WindEntity {
        private String direct;
        private String power;
        private String speed;

        public void setDirect(String direct) {
            this.direct = direct;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDirect() {
            return direct;
        }

        public String getPower() {
            return power;
        }

        public String getSpeed() {
            return speed;
        }

    }
}
