package com.xfshipan.weather.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FullDayWeatherInfo implements WeatherInfo {

    /**
     * ja : 01
     * jb : 8
     * jc : 0
     * jd : 0
     * je : 76
     * jf : 201511301200
     */

    private List<JhEntity> jh;

    public void setJh(List<JhEntity> jh) {
        this.jh = jh;
    }

    public List<JhEntity> getJh() {
        return jh;
    }

    public static class JhEntity {
        private String ja;
        private String jb;
        private String jc;
        private String jd;
        private String je;
        private String jf;

        public JhEntity() {
        }

        public void setJa(String ja) {
            this.ja = ja;
        }

        public void setJb(String jb) {
            this.jb = jb;
        }

        public void setJc(String jc) {
            this.jc = jc;
        }

        public void setJd(String jd) {
            this.jd = jd;
        }

        public void setJe(String je) {
            this.je = je;
        }

        public void setJf(String jf) {
            this.jf = jf;
        }

        public String getJa() {
            return ja;
        }

        public String getJb() {
            return jb;
        }

        public String getJc() {
            return jc;
        }

        public String getJd() {
            return jd;
        }

        public String getJe() {
            return je;
        }

        public String getJf() {
            return jf;
        }
    }
}
