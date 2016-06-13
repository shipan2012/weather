package com.xfshipan.weather.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/29.
 */
public class DefaultWeatherInfo implements WeatherInfo {

    /**
     * EpochTime : 1446092640
     * LocalObservationDateTime : 2015-10-29T12:24:00+08:00
     * Pressure : 1021.0
     * RealFeelTemperature : 13.9
     * RelativeHumidity : 92
     * UVIndex : 2
     * Visibility : 1.6
     * WindDirectionDegrees : 0
     * WindSpeed : 9.3
     */

    private AccuCcEntity accu_cc;
    /**
     * DailyForecasts : [{"Date":"2015-10-29T07:00:00+08:00","EpochDate":"1446073200","PrecipitationProbability":"60","Sun_EpochRise":"1446070200","Sun_EpochSet":"1446110100","Sun_Rise":"2015-10-29T06:10:00+08:00","Sun_Set":"2015-10-29T17:15:00+08:00"},{"Date":"2015-10-30T07:00:00+08:00","EpochDate":"1446159600","PrecipitationProbability":"40","Sun_EpochRise":"1446156660","Sun_EpochSet":"1446196440","Sun_Rise":"2015-10-30T06:11:00+08:00","Sun_Set":"2015-10-30T17:14:00+08:00"},{"Date":"2015-10-31T07:00:00+08:00","EpochDate":"1446246000","PrecipitationProbability":"55","Sun_EpochRise":"1446243120","Sun_EpochSet":"1446282780","Sun_Rise":"2015-10-31T06:12:00+08:00","Sun_Set":"2015-10-31T17:13:00+08:00"},{"Date":"2015-11-01T07:00:00+08:00","EpochDate":"1446332400","PrecipitationProbability":"15","Sun_EpochRise":"1446329580","Sun_EpochSet":"1446369120","Sun_Rise":"2015-11-01T06:13:00+08:00","Sun_Set":"2015-11-01T17:12:00+08:00"},{"Date":"2015-11-02T07:00:00+08:00","EpochDate":"1446418800","PrecipitationProbability":"0","Sun_EpochRise":"1446415980","Sun_EpochSet":"1446455460","Sun_Rise":"2015-11-02T06:13:00+08:00","Sun_Set":"2015-11-02T17:11:00+08:00"}]
     * EffectiveDate : 2015-10-29T13:00:00+08:00
     * EffectiveEpochDate : 1446094800
     */

    private AccuF5Entity accu_f5;
    /**
     * aqi : 69
     * city : 萧山
     * city_id : 101210102
     * no2 : 91
     * pm10 : 69
     * pm25 : 50
     * pub_time : 2015-10-29 11:00
     * so2 : 15
     * spot :
     * src : 中国环境监测总站
     */

    private AqiEntity aqi;
    /**
     * city : 萧山
     * city_en : xiaoshan
     * cityid : 101210102
     * date :
     * date_y : 2015年10月29日
     * fchh : 11
     * fl1 : 小于3级
     * fl2 : 小于3级
     * fl3 : 小于3级
     * fl4 : 小于3级
     * fl5 : 小于3级
     * fl6 : 微风
     * fx1 : 东北风
     * fx2 : 东北风
     * img1 :
     * img10 :
     * img11 :
     * img12 :
     * img2 :
     * img3 :
     * img4 :
     * img5 :
     * img6 :
     * img7 :
     * img8 :
     * img9 :
     * img_single :
     * img_title1 : 小雨
     * img_title10 : 晴
     * img_title11 : 晴
     * img_title12 : 晴
     * img_title2 : 小雨
     * img_title3 : 小雨
     * img_title4 : 小雨
     * img_title5 : 小雨
     * img_title6 : 小雨
     * img_title7 : 小雨
     * img_title8 : 阴
     * img_title9 : 多云
     * img_title_single :
     * index : 冷
     * index48 :
     * index48_d :
     * index48_uv :
     * index_ag : 易发
     * index_cl : 较不宜
     * index_co : 舒适
     * index_d :
     * index_ls : 不宜
     * index_tr : 适宜
     * index_uv : 最弱
     * index_xc : 不宜
     * st1 :
     * st2 :
     * st3 :
     * st4 :
     * st5 :
     * st6 :
     * temp1 : 17℃~15℃
     * temp2 : 17℃~13℃
     * temp3 : 18℃~12℃
     * temp4 : 16℃~10℃
     * temp5 : 18℃~10℃
     * temp6 : 0℃~0℃
     * tempF1 :
     * tempF2 :
     * tempF3 :
     * tempF4 :
     * tempF5 :
     * tempF6 :
     * weather1 : 小雨
     * weather2 : 小雨
     * weather3 : 小雨
     * weather4 : 小雨转阴
     * weather5 : 多云转晴
     * weather6 : 晴
     * week : 星期二
     * wind1 : 东北风
     * wind2 : 东北风
     * wind3 : 东北风
     * wind4 : 北风
     * wind5 : 东北风转东南风
     * wind6 : 微风
     */

    private ForecastEntity forecast;
    /**
     * SD : 92%
     * WD : 北风
     * WS : 2级
     * WSE :
     * city :
     * cityid : 101210102
     * isRadar : 1
     * radar : JC_RADAR_AZ9010_JB
     * temp : 15
     * time : 12:24
     * weather : 中雨
     */

    private RealtimeEntity realtime;
    /**
     * cityCode : 101210102
     * date : 2015-10-29
     * humidityMax : 93
     * humidityMin : 61
     * precipitationMax : 2.7
     * precipitationMin : 0
     * tempMax : 18
     * tempMin : 15
     * weatherEnd : 小雨
     * weatherStart : 多云
     * windDirectionEnd : 东北风
     * windDirectionStart : 东风
     * windMax : 3
     * windMin : 0
     */

    private TodayEntity today;
    /**
     * cityCode : 101210102
     * date : 2015-10-28
     * humidityMax : 95
     * humidityMin : 47
     * precipitationMax : 0.9
     * precipitationMin : 0.9
     * tempMax : 20
     * tempMin : 13
     * weatherEnd : 多云
     * weatherStart : 多云
     * windDirectionEnd : 北风
     * windDirectionStart : 南风
     * windMax : 3
     * windMin : 0
     */

    private YestodayEntity yestoday;
    /**
     * accu_cc : {"EpochTime":"1446092640","LocalObservationDateTime":"2015-10-29T12:24:00+08:00","Pressure":"1021.0","RealFeelTemperature":"13.9","RelativeHumidity":"92","UVIndex":"2","Visibility":"1.6","WindDirectionDegrees":"0","WindSpeed":"9.3"}
     * accu_f5 : {"DailyForecasts":[{"Date":"2015-10-29T07:00:00+08:00","EpochDate":"1446073200","PrecipitationProbability":"60","Sun_EpochRise":"1446070200","Sun_EpochSet":"1446110100","Sun_Rise":"2015-10-29T06:10:00+08:00","Sun_Set":"2015-10-29T17:15:00+08:00"},{"Date":"2015-10-30T07:00:00+08:00","EpochDate":"1446159600","PrecipitationProbability":"40","Sun_EpochRise":"1446156660","Sun_EpochSet":"1446196440","Sun_Rise":"2015-10-30T06:11:00+08:00","Sun_Set":"2015-10-30T17:14:00+08:00"},{"Date":"2015-10-31T07:00:00+08:00","EpochDate":"1446246000","PrecipitationProbability":"55","Sun_EpochRise":"1446243120","Sun_EpochSet":"1446282780","Sun_Rise":"2015-10-31T06:12:00+08:00","Sun_Set":"2015-10-31T17:13:00+08:00"},{"Date":"2015-11-01T07:00:00+08:00","EpochDate":"1446332400","PrecipitationProbability":"15","Sun_EpochRise":"1446329580","Sun_EpochSet":"1446369120","Sun_Rise":"2015-11-01T06:13:00+08:00","Sun_Set":"2015-11-01T17:12:00+08:00"},{"Date":"2015-11-02T07:00:00+08:00","EpochDate":"1446418800","PrecipitationProbability":"0","Sun_EpochRise":"1446415980","Sun_EpochSet":"1446455460","Sun_Rise":"2015-11-02T06:13:00+08:00","Sun_Set":"2015-11-02T17:11:00+08:00"}],"EffectiveDate":"2015-10-29T13:00:00+08:00","EffectiveEpochDate":"1446094800"}
     * alert : []
     * aqi : {"aqi":"69","city":"萧山","city_id":"101210102","no2":"91","pm10":"69","pm25":"50","pub_time":"2015-10-29 11:00","so2":"15","spot":"","src":"中国环境监测总站"}
     * forecast : {"city":"萧山","city_en":"xiaoshan","cityid":"101210102","date":"","date_y":"2015年10月29日","fchh":"11","fl1":"小于3级","fl2":"小于3级","fl3":"小于3级","fl4":"小于3级","fl5":"小于3级","fl6":"微风","fx1":"东北风","fx2":"东北风","img1":"","img10":"","img11":"","img12":"","img2":"","img3":"","img4":"","img5":"","img6":"","img7":"","img8":"","img9":"","img_single":"","img_title1":"小雨","img_title10":"晴","img_title11":"晴","img_title12":"晴","img_title2":"小雨","img_title3":"小雨","img_title4":"小雨","img_title5":"小雨","img_title6":"小雨","img_title7":"小雨","img_title8":"阴","img_title9":"多云","img_title_single":"","index":"冷","index48":"","index48_d":"","index48_uv":"","index_ag":"易发","index_cl":"较不宜","index_co":"舒适","index_d":"","index_ls":"不宜","index_tr":"适宜","index_uv":"最弱","index_xc":"不宜","st1":"","st2":"","st3":"","st4":"","st5":"","st6":"","temp1":"17℃~15℃","temp2":"17℃~13℃","temp3":"18℃~12℃","temp4":"16℃~10℃","temp5":"18℃~10℃","temp6":"0℃~0℃","tempF1":"","tempF2":"","tempF3":"","tempF4":"","tempF5":"","tempF6":"","weather1":"小雨","weather2":"小雨","weather3":"小雨","weather4":"小雨转阴","weather5":"多云转晴","weather6":"晴","week":"星期二","wind1":"东北风","wind2":"东北风","wind3":"东北风","wind4":"北风","wind5":"东北风转东南风","wind6":"微风"}
     * index : [{"code":"fs","details":"属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。","index":"弱","name":"防晒指数","otherName":""},{"code":"ct","details":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","index":"较冷","name":"穿衣指数","otherName":""},{"code":"yd","details":"有降水，推荐您在室内进行各种健身休闲运动，若坚持户外运动，须注意保暖并携带雨具。","index":"较不宜","name":"运动指数","otherName":""},{"code":"xc","details":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。","index":"不宜","name":"洗车指数","otherName":""},{"code":"ls","details":"有降水，不适宜晾晒。若需要晾晒，请在室内准备出充足的空间。","index":"不宜","name":"晾晒指数","otherName":""}]
     * realtime : {"SD":"92%","WD":"北风","WS":"2级","WSE":"","city":"","cityid":"101210102","isRadar":"1","radar":"JC_RADAR_AZ9010_JB","temp":"15","time":"12:24","weather":"中雨"}
     * today : {"cityCode":"101210102","date":"2015-10-29","humidityMax":93,"humidityMin":61,"precipitationMax":2.7,"precipitationMin":0,"tempMax":18,"tempMin":15,"weatherEnd":"小雨","weatherStart":"多云","windDirectionEnd":"东北风","windDirectionStart":"东风","windMax":3,"windMin":0}
     * yestoday : {"cityCode":"101210102","date":"2015-10-28","humidityMax":95,"humidityMin":47,"precipitationMax":0.9,"precipitationMin":0.9,"tempMax":20,"tempMin":13,"weatherEnd":"多云","weatherStart":"多云","windDirectionEnd":"北风","windDirectionStart":"南风","windMax":3,"windMin":0}
     */

    private List<?> alert;
    /**
     * code : fs
     * details : 属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
     * index : 弱
     * name : 防晒指数
     * otherName :
     */

    private List<IndexEntity> index;

    public void setAccu_cc(AccuCcEntity accu_cc) {
        this.accu_cc = accu_cc;
    }

    public void setAccu_f5(AccuF5Entity accu_f5) {
        this.accu_f5 = accu_f5;
    }

    public void setAqi(AqiEntity aqi) {
        this.aqi = aqi;
    }

    public void setForecast(ForecastEntity forecast) {
        this.forecast = forecast;
    }

    public void setRealtime(RealtimeEntity realtime) {
        this.realtime = realtime;
    }

    public void setToday(TodayEntity today) {
        this.today = today;
    }

    public void setYestoday(YestodayEntity yestoday) {
        this.yestoday = yestoday;
    }

    public void setAlert(List<?> alert) {
        this.alert = alert;
    }

    public void setIndex(List<IndexEntity> index) {
        this.index = index;
    }

    public AccuCcEntity getAccu_cc() {
        return accu_cc;
    }

    public AccuF5Entity getAccu_f5() {
        return accu_f5;
    }

    public AqiEntity getAqi() {
        return aqi;
    }

    public ForecastEntity getForecast() {
        return forecast;
    }

    public RealtimeEntity getRealtime() {
        return realtime;
    }

    public TodayEntity getToday() {
        return today;
    }

    public YestodayEntity getYestoday() {
        return yestoday;
    }

    public List<?> getAlert() {
        return alert;
    }

    public List<IndexEntity> getIndex() {
        return index;
    }

    public static class AccuCcEntity {
        private String EpochTime;
        private String LocalObservationDateTime;
        private String Pressure;
        private String RealFeelTemperature;
        private String RelativeHumidity;
        private String UVIndex;
        private String Visibility;
        private String WindDirectionDegrees;
        private String WindSpeed;

        public void setEpochTime(String EpochTime) {
            this.EpochTime = EpochTime;
        }

        public void setLocalObservationDateTime(String LocalObservationDateTime) {
            this.LocalObservationDateTime = LocalObservationDateTime;
        }

        public void setPressure(String Pressure) {
            this.Pressure = Pressure;
        }

        public void setRealFeelTemperature(String RealFeelTemperature) {
            this.RealFeelTemperature = RealFeelTemperature;
        }

        public void setRelativeHumidity(String RelativeHumidity) {
            this.RelativeHumidity = RelativeHumidity;
        }

        public void setUVIndex(String UVIndex) {
            this.UVIndex = UVIndex;
        }

        public void setVisibility(String Visibility) {
            this.Visibility = Visibility;
        }

        public void setWindDirectionDegrees(String WindDirectionDegrees) {
            this.WindDirectionDegrees = WindDirectionDegrees;
        }

        public void setWindSpeed(String WindSpeed) {
            this.WindSpeed = WindSpeed;
        }

        public String getEpochTime() {
            return EpochTime;
        }

        public String getLocalObservationDateTime() {
            return LocalObservationDateTime;
        }

        public String getPressure() {
            return Pressure;
        }

        public String getRealFeelTemperature() {
            return RealFeelTemperature;
        }

        public String getRelativeHumidity() {
            return RelativeHumidity;
        }

        public String getUVIndex() {
            return UVIndex;
        }

        public String getVisibility() {
            return Visibility;
        }

        public String getWindDirectionDegrees() {
            return WindDirectionDegrees;
        }

        public String getWindSpeed() {
            return WindSpeed;
        }
    }

    public static class AccuF5Entity {
        private String EffectiveDate;
        private String EffectiveEpochDate;
        /**
         * Date : 2015-10-29T07:00:00+08:00
         * EpochDate : 1446073200
         * PrecipitationProbability : 60
         * Sun_EpochRise : 1446070200
         * Sun_EpochSet : 1446110100
         * Sun_Rise : 2015-10-29T06:10:00+08:00
         * Sun_Set : 2015-10-29T17:15:00+08:00
         */

        private List<DailyForecastsEntity> DailyForecasts;

        public void setEffectiveDate(String EffectiveDate) {
            this.EffectiveDate = EffectiveDate;
        }

        public void setEffectiveEpochDate(String EffectiveEpochDate) {
            this.EffectiveEpochDate = EffectiveEpochDate;
        }

        public void setDailyForecasts(List<DailyForecastsEntity> DailyForecasts) {
            this.DailyForecasts = DailyForecasts;
        }

        public String getEffectiveDate() {
            return EffectiveDate;
        }

        public String getEffectiveEpochDate() {
            return EffectiveEpochDate;
        }

        public List<DailyForecastsEntity> getDailyForecasts() {
            return DailyForecasts;
        }

        public static class DailyForecastsEntity {
            private String Date;
            private String EpochDate;
            private String PrecipitationProbability;
            private String Sun_EpochRise;
            private String Sun_EpochSet;
            private String Sun_Rise;
            private String Sun_Set;

            public void setDate(String Date) {
                this.Date = Date;
            }

            public void setEpochDate(String EpochDate) {
                this.EpochDate = EpochDate;
            }

            public void setPrecipitationProbability(String PrecipitationProbability) {
                this.PrecipitationProbability = PrecipitationProbability;
            }

            public void setSun_EpochRise(String Sun_EpochRise) {
                this.Sun_EpochRise = Sun_EpochRise;
            }

            public void setSun_EpochSet(String Sun_EpochSet) {
                this.Sun_EpochSet = Sun_EpochSet;
            }

            public void setSun_Rise(String Sun_Rise) {
                this.Sun_Rise = Sun_Rise;
            }

            public void setSun_Set(String Sun_Set) {
                this.Sun_Set = Sun_Set;
            }

            public String getDate() {
                return Date;
            }

            public String getEpochDate() {
                return EpochDate;
            }

            public String getPrecipitationProbability() {
                return PrecipitationProbability;
            }

            public String getSun_EpochRise() {
                return Sun_EpochRise;
            }

            public String getSun_EpochSet() {
                return Sun_EpochSet;
            }

            public String getSun_Rise() {
                return Sun_Rise;
            }

            public String getSun_Set() {
                return Sun_Set;
            }
        }
    }

    public static class AqiEntity {
        private String aqi;
        private String city;
        private String city_id;
        private String no2;
        private String pm10;
        private String pm25;
        private String pub_time;
        private String so2;
        private String spot;
        private String src;

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public void setPub_time(String pub_time) {
            this.pub_time = pub_time;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public void setSpot(String spot) {
            this.spot = spot;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getAqi() {
            return aqi;
        }

        public String getCity() {
            return city;
        }

        public String getCity_id() {
            return city_id;
        }

        public String getNo2() {
            return no2;
        }

        public String getPm10() {
            return pm10;
        }

        public String getPm25() {
            return pm25;
        }

        public String getPub_time() {
            return pub_time;
        }

        public String getSo2() {
            return so2;
        }

        public String getSpot() {
            return spot;
        }

        public String getSrc() {
            return src;
        }
    }

    public static class ForecastEntity {
        private String city;
        private String city_en;
        private String cityid;
        private String date;
        private String date_y;
        private String fchh;
        private String fl1;
        private String fl2;
        private String fl3;
        private String fl4;
        private String fl5;
        private String fl6;
        private String fx1;
        private String fx2;
        private String img1;
        private String img10;
        private String img11;
        private String img12;
        private String img2;
        private String img3;
        private String img4;
        private String img5;
        private String img6;
        private String img7;
        private String img8;
        private String img9;
        private String img_single;
        private String img_title1;
        private String img_title10;
        private String img_title11;
        private String img_title12;
        private String img_title2;
        private String img_title3;
        private String img_title4;
        private String img_title5;
        private String img_title6;
        private String img_title7;
        private String img_title8;
        private String img_title9;
        private String img_title_single;
        private String index;
        private String index48;
        private String index48_d;
        private String index48_uv;
        private String index_ag;
        private String index_cl;
        private String index_co;
        private String index_d;
        private String index_ls;
        private String index_tr;
        private String index_uv;
        private String index_xc;
        private String st1;
        private String st2;
        private String st3;
        private String st4;
        private String st5;
        private String st6;
        private String temp1;
        private String temp2;
        private String temp3;
        private String temp4;
        private String temp5;
        private String temp6;
        private String tempF1;
        private String tempF2;
        private String tempF3;
        private String tempF4;
        private String tempF5;
        private String tempF6;
        private String weather1;
        private String weather2;
        private String weather3;
        private String weather4;
        private String weather5;
        private String weather6;
        private String week;
        private String wind1;
        private String wind2;
        private String wind3;
        private String wind4;
        private String wind5;
        private String wind6;

        public void setCity(String city) {
            this.city = city;
        }

        public void setCity_en(String city_en) {
            this.city_en = city_en;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setDate_y(String date_y) {
            this.date_y = date_y;
        }

        public void setFchh(String fchh) {
            this.fchh = fchh;
        }

        public void setFl1(String fl1) {
            this.fl1 = fl1;
        }

        public void setFl2(String fl2) {
            this.fl2 = fl2;
        }

        public void setFl3(String fl3) {
            this.fl3 = fl3;
        }

        public void setFl4(String fl4) {
            this.fl4 = fl4;
        }

        public void setFl5(String fl5) {
            this.fl5 = fl5;
        }

        public void setFl6(String fl6) {
            this.fl6 = fl6;
        }

        public void setFx1(String fx1) {
            this.fx1 = fx1;
        }

        public void setFx2(String fx2) {
            this.fx2 = fx2;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public void setImg10(String img10) {
            this.img10 = img10;
        }

        public void setImg11(String img11) {
            this.img11 = img11;
        }

        public void setImg12(String img12) {
            this.img12 = img12;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public void setImg4(String img4) {
            this.img4 = img4;
        }

        public void setImg5(String img5) {
            this.img5 = img5;
        }

        public void setImg6(String img6) {
            this.img6 = img6;
        }

        public void setImg7(String img7) {
            this.img7 = img7;
        }

        public void setImg8(String img8) {
            this.img8 = img8;
        }

        public void setImg9(String img9) {
            this.img9 = img9;
        }

        public void setImg_single(String img_single) {
            this.img_single = img_single;
        }

        public void setImg_title1(String img_title1) {
            this.img_title1 = img_title1;
        }

        public void setImg_title10(String img_title10) {
            this.img_title10 = img_title10;
        }

        public void setImg_title11(String img_title11) {
            this.img_title11 = img_title11;
        }

        public void setImg_title12(String img_title12) {
            this.img_title12 = img_title12;
        }

        public void setImg_title2(String img_title2) {
            this.img_title2 = img_title2;
        }

        public void setImg_title3(String img_title3) {
            this.img_title3 = img_title3;
        }

        public void setImg_title4(String img_title4) {
            this.img_title4 = img_title4;
        }

        public void setImg_title5(String img_title5) {
            this.img_title5 = img_title5;
        }

        public void setImg_title6(String img_title6) {
            this.img_title6 = img_title6;
        }

        public void setImg_title7(String img_title7) {
            this.img_title7 = img_title7;
        }

        public void setImg_title8(String img_title8) {
            this.img_title8 = img_title8;
        }

        public void setImg_title9(String img_title9) {
            this.img_title9 = img_title9;
        }

        public void setImg_title_single(String img_title_single) {
            this.img_title_single = img_title_single;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public void setIndex48(String index48) {
            this.index48 = index48;
        }

        public void setIndex48_d(String index48_d) {
            this.index48_d = index48_d;
        }

        public void setIndex48_uv(String index48_uv) {
            this.index48_uv = index48_uv;
        }

        public void setIndex_ag(String index_ag) {
            this.index_ag = index_ag;
        }

        public void setIndex_cl(String index_cl) {
            this.index_cl = index_cl;
        }

        public void setIndex_co(String index_co) {
            this.index_co = index_co;
        }

        public void setIndex_d(String index_d) {
            this.index_d = index_d;
        }

        public void setIndex_ls(String index_ls) {
            this.index_ls = index_ls;
        }

        public void setIndex_tr(String index_tr) {
            this.index_tr = index_tr;
        }

        public void setIndex_uv(String index_uv) {
            this.index_uv = index_uv;
        }

        public void setIndex_xc(String index_xc) {
            this.index_xc = index_xc;
        }

        public void setSt1(String st1) {
            this.st1 = st1;
        }

        public void setSt2(String st2) {
            this.st2 = st2;
        }

        public void setSt3(String st3) {
            this.st3 = st3;
        }

        public void setSt4(String st4) {
            this.st4 = st4;
        }

        public void setSt5(String st5) {
            this.st5 = st5;
        }

        public void setSt6(String st6) {
            this.st6 = st6;
        }

        public void setTemp1(String temp1) {
            this.temp1 = temp1;
        }

        public void setTemp2(String temp2) {
            this.temp2 = temp2;
        }

        public void setTemp3(String temp3) {
            this.temp3 = temp3;
        }

        public void setTemp4(String temp4) {
            this.temp4 = temp4;
        }

        public void setTemp5(String temp5) {
            this.temp5 = temp5;
        }

        public void setTemp6(String temp6) {
            this.temp6 = temp6;
        }

        public void setTempF1(String tempF1) {
            this.tempF1 = tempF1;
        }

        public void setTempF2(String tempF2) {
            this.tempF2 = tempF2;
        }

        public void setTempF3(String tempF3) {
            this.tempF3 = tempF3;
        }

        public void setTempF4(String tempF4) {
            this.tempF4 = tempF4;
        }

        public void setTempF5(String tempF5) {
            this.tempF5 = tempF5;
        }

        public void setTempF6(String tempF6) {
            this.tempF6 = tempF6;
        }

        public void setWeather1(String weather1) {
            this.weather1 = weather1;
        }

        public void setWeather2(String weather2) {
            this.weather2 = weather2;
        }

        public void setWeather3(String weather3) {
            this.weather3 = weather3;
        }

        public void setWeather4(String weather4) {
            this.weather4 = weather4;
        }

        public void setWeather5(String weather5) {
            this.weather5 = weather5;
        }

        public void setWeather6(String weather6) {
            this.weather6 = weather6;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public void setWind1(String wind1) {
            this.wind1 = wind1;
        }

        public void setWind2(String wind2) {
            this.wind2 = wind2;
        }

        public void setWind3(String wind3) {
            this.wind3 = wind3;
        }

        public void setWind4(String wind4) {
            this.wind4 = wind4;
        }

        public void setWind5(String wind5) {
            this.wind5 = wind5;
        }

        public void setWind6(String wind6) {
            this.wind6 = wind6;
        }

        public String getCity() {
            return city;
        }

        public String getCity_en() {
            return city_en;
        }

        public String getCityid() {
            return cityid;
        }

        public String getDate() {
            return date;
        }

        public String getDate_y() {
            return date_y;
        }

        public String getFchh() {
            return fchh;
        }

        public String getFl1() {
            return fl1;
        }

        public String getFl2() {
            return fl2;
        }

        public String getFl3() {
            return fl3;
        }

        public String getFl4() {
            return fl4;
        }

        public String getFl5() {
            return fl5;
        }

        public String getFl6() {
            return fl6;
        }

        public String getFx1() {
            return fx1;
        }

        public String getFx2() {
            return fx2;
        }

        public String getImg1() {
            return img1;
        }

        public String getImg10() {
            return img10;
        }

        public String getImg11() {
            return img11;
        }

        public String getImg12() {
            return img12;
        }

        public String getImg2() {
            return img2;
        }

        public String getImg3() {
            return img3;
        }

        public String getImg4() {
            return img4;
        }

        public String getImg5() {
            return img5;
        }

        public String getImg6() {
            return img6;
        }

        public String getImg7() {
            return img7;
        }

        public String getImg8() {
            return img8;
        }

        public String getImg9() {
            return img9;
        }

        public String getImg_single() {
            return img_single;
        }

        public String getImg_title1() {
            return img_title1;
        }

        public String getImg_title10() {
            return img_title10;
        }

        public String getImg_title11() {
            return img_title11;
        }

        public String getImg_title12() {
            return img_title12;
        }

        public String getImg_title2() {
            return img_title2;
        }

        public String getImg_title3() {
            return img_title3;
        }

        public String getImg_title4() {
            return img_title4;
        }

        public String getImg_title5() {
            return img_title5;
        }

        public String getImg_title6() {
            return img_title6;
        }

        public String getImg_title7() {
            return img_title7;
        }

        public String getImg_title8() {
            return img_title8;
        }

        public String getImg_title9() {
            return img_title9;
        }

        public String getImg_title_single() {
            return img_title_single;
        }

        public String getIndex() {
            return index;
        }

        public String getIndex48() {
            return index48;
        }

        public String getIndex48_d() {
            return index48_d;
        }

        public String getIndex48_uv() {
            return index48_uv;
        }

        public String getIndex_ag() {
            return index_ag;
        }

        public String getIndex_cl() {
            return index_cl;
        }

        public String getIndex_co() {
            return index_co;
        }

        public String getIndex_d() {
            return index_d;
        }

        public String getIndex_ls() {
            return index_ls;
        }

        public String getIndex_tr() {
            return index_tr;
        }

        public String getIndex_uv() {
            return index_uv;
        }

        public String getIndex_xc() {
            return index_xc;
        }

        public String getSt1() {
            return st1;
        }

        public String getSt2() {
            return st2;
        }

        public String getSt3() {
            return st3;
        }

        public String getSt4() {
            return st4;
        }

        public String getSt5() {
            return st5;
        }

        public String getSt6() {
            return st6;
        }

        public String getTemp1() {
            return temp1;
        }

        public String getTemp2() {
            return temp2;
        }

        public String getTemp3() {
            return temp3;
        }

        public String getTemp4() {
            return temp4;
        }

        public String getTemp5() {
            return temp5;
        }

        public String getTemp6() {
            return temp6;
        }

        public String getTempF1() {
            return tempF1;
        }

        public String getTempF2() {
            return tempF2;
        }

        public String getTempF3() {
            return tempF3;
        }

        public String getTempF4() {
            return tempF4;
        }

        public String getTempF5() {
            return tempF5;
        }

        public String getTempF6() {
            return tempF6;
        }

        public String getWeather1() {
            return weather1;
        }

        public String getWeather2() {
            return weather2;
        }

        public String getWeather3() {
            return weather3;
        }

        public String getWeather4() {
            return weather4;
        }

        public String getWeather5() {
            return weather5;
        }

        public String getWeather6() {
            return weather6;
        }

        public String getWeek() {
            return week;
        }

        public String getWind1() {
            return wind1;
        }

        public String getWind2() {
            return wind2;
        }

        public String getWind3() {
            return wind3;
        }

        public String getWind4() {
            return wind4;
        }

        public String getWind5() {
            return wind5;
        }

        public String getWind6() {
            return wind6;
        }
    }

    public static class RealtimeEntity {
        private String SD;
        private String WD;
        private String WS;
        private String WSE;
        private String city;
        private String cityid;
        private String isRadar;
        private String radar;
        private String temp;
        private String time;
        private String weather;

        public void setSD(String SD) {
            this.SD = SD;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public void setWS(String WS) {
            this.WS = WS;
        }

        public void setWSE(String WSE) {
            this.WSE = WSE;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setIsRadar(String isRadar) {
            this.isRadar = isRadar;
        }

        public void setRadar(String radar) {
            this.radar = radar;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getSD() {
            return SD;
        }

        public String getWD() {
            return WD;
        }

        public String getWS() {
            return WS;
        }

        public String getWSE() {
            return WSE;
        }

        public String getCity() {
            return city;
        }

        public String getCityid() {
            return cityid;
        }

        public String getIsRadar() {
            return isRadar;
        }

        public String getRadar() {
            return radar;
        }

        public String getTemp() {
            return temp;
        }

        public String getTime() {
            return time;
        }

        public String getWeather() {
            return weather;
        }
    }

    public static class TodayEntity {
        private String cityCode;
        private String date;
        private int humidityMax;
        private int humidityMin;
        private double precipitationMax;
        private int precipitationMin;
        private int tempMax;
        private int tempMin;
        private String weatherEnd;
        private String weatherStart;
        private String windDirectionEnd;
        private String windDirectionStart;
        private int windMax;
        private int windMin;

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setHumidityMax(int humidityMax) {
            this.humidityMax = humidityMax;
        }

        public void setHumidityMin(int humidityMin) {
            this.humidityMin = humidityMin;
        }

        public void setPrecipitationMax(double precipitationMax) {
            this.precipitationMax = precipitationMax;
        }

        public void setPrecipitationMin(int precipitationMin) {
            this.precipitationMin = precipitationMin;
        }

        public void setTempMax(int tempMax) {
            this.tempMax = tempMax;
        }

        public void setTempMin(int tempMin) {
            this.tempMin = tempMin;
        }

        public void setWeatherEnd(String weatherEnd) {
            this.weatherEnd = weatherEnd;
        }

        public void setWeatherStart(String weatherStart) {
            this.weatherStart = weatherStart;
        }

        public void setWindDirectionEnd(String windDirectionEnd) {
            this.windDirectionEnd = windDirectionEnd;
        }

        public void setWindDirectionStart(String windDirectionStart) {
            this.windDirectionStart = windDirectionStart;
        }

        public void setWindMax(int windMax) {
            this.windMax = windMax;
        }

        public void setWindMin(int windMin) {
            this.windMin = windMin;
        }

        public String getCityCode() {
            return cityCode;
        }

        public String getDate() {
            return date;
        }

        public int getHumidityMax() {
            return humidityMax;
        }

        public int getHumidityMin() {
            return humidityMin;
        }

        public double getPrecipitationMax() {
            return precipitationMax;
        }

        public int getPrecipitationMin() {
            return precipitationMin;
        }

        public int getTempMax() {
            return tempMax;
        }

        public int getTempMin() {
            return tempMin;
        }

        public String getWeatherEnd() {
            return weatherEnd;
        }

        public String getWeatherStart() {
            return weatherStart;
        }

        public String getWindDirectionEnd() {
            return windDirectionEnd;
        }

        public String getWindDirectionStart() {
            return windDirectionStart;
        }

        public int getWindMax() {
            return windMax;
        }

        public int getWindMin() {
            return windMin;
        }
    }

    public static class YestodayEntity {
        private String cityCode;
        private String date;
        private int humidityMax;
        private int humidityMin;
        private double precipitationMax;
        private double precipitationMin;
        private int tempMax;
        private int tempMin;
        private String weatherEnd;
        private String weatherStart;
        private String windDirectionEnd;
        private String windDirectionStart;
        private int windMax;
        private int windMin;

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setHumidityMax(int humidityMax) {
            this.humidityMax = humidityMax;
        }

        public void setHumidityMin(int humidityMin) {
            this.humidityMin = humidityMin;
        }

        public void setPrecipitationMax(double precipitationMax) {
            this.precipitationMax = precipitationMax;
        }

        public void setPrecipitationMin(double precipitationMin) {
            this.precipitationMin = precipitationMin;
        }

        public void setTempMax(int tempMax) {
            this.tempMax = tempMax;
        }

        public void setTempMin(int tempMin) {
            this.tempMin = tempMin;
        }

        public void setWeatherEnd(String weatherEnd) {
            this.weatherEnd = weatherEnd;
        }

        public void setWeatherStart(String weatherStart) {
            this.weatherStart = weatherStart;
        }

        public void setWindDirectionEnd(String windDirectionEnd) {
            this.windDirectionEnd = windDirectionEnd;
        }

        public void setWindDirectionStart(String windDirectionStart) {
            this.windDirectionStart = windDirectionStart;
        }

        public void setWindMax(int windMax) {
            this.windMax = windMax;
        }

        public void setWindMin(int windMin) {
            this.windMin = windMin;
        }

        public String getCityCode() {
            return cityCode;
        }

        public String getDate() {
            return date;
        }

        public int getHumidityMax() {
            return humidityMax;
        }

        public int getHumidityMin() {
            return humidityMin;
        }

        public double getPrecipitationMax() {
            return precipitationMax;
        }

        public double getPrecipitationMin() {
            return precipitationMin;
        }

        public int getTempMax() {
            return tempMax;
        }

        public int getTempMin() {
            return tempMin;
        }

        public String getWeatherEnd() {
            return weatherEnd;
        }

        public String getWeatherStart() {
            return weatherStart;
        }

        public String getWindDirectionEnd() {
            return windDirectionEnd;
        }

        public String getWindDirectionStart() {
            return windDirectionStart;
        }

        public int getWindMax() {
            return windMax;
        }

        public int getWindMin() {
            return windMin;
        }
    }

    public static class IndexEntity {
        private String code;
        private String details;
        private String index;
        private String name;
        private String otherName;

        public void setCode(String code) {
            this.code = code;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOtherName(String otherName) {
            this.otherName = otherName;
        }

        public String getCode() {
            return code;
        }

        public String getDetails() {
            return details;
        }

        public String getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public String getOtherName() {
            return otherName;
        }
    }
}