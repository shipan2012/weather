package com.xfshipan.weather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xfshipan.weather.bean.WeatherInfo;

public abstract class WeatherBaseView extends RelativeLayout{
    public WeatherBaseView(Context c) {
        this(c, null);
    }

    public WeatherBaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setDefaultWeatherInfo(WeatherInfo weatherInfo);
}
