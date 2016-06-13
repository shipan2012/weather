package com.xfshipan.weather.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.DefaultWeatherInfo;
import com.xfshipan.weather.bean.FullDayWeatherInfo;
import com.xfshipan.weather.bean.WeatherInfo;
import com.xfshipan.weather.view.WeatheFullDayView;
import com.xfshipan.weather.view.WeatherBaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherListAdapter extends BaseAdapter {
    private final HashMap<Integer, WeatherBaseView> mWeatherBaseViews = new HashMap<Integer, WeatherBaseView>();
    public static final int FORECAST_TYPE = 0;
    public static final int WEATHER_DETAILS_TYPE = 1;
    public static final int AQI_TYPE = 2;
    public static final int INDEX_TYPE = 3;
    public static final int FULLDAY_TYPE = 4;

    private LayoutInflater mLayoutInflater;
    private List<Integer> mTypes;
    private DefaultWeatherInfo defaultWeatherInfo;
    private View weather_fullday;
    private FullDayWeatherInfo fullDayWeatherInfo;

    public WeatherListAdapter(Context context) {
        mTypes = new ArrayList<Integer>();
        mLayoutInflater = LayoutInflater.from(context);
    }

/*    public void initViews() {
        if (!mWeatherBaseViews.isEmpty())
            return;
        WeatherBaseView convertView = (WeatherBaseView) mLayoutInflater
                .inflate(R.layout.weather_forecast, null);
        mWeatherBaseViews.put(FORECAST_TYPE, convertView);
        convertView = (WeatherBaseView) mLayoutInflater.inflate(
                R.layout.weather_details, null);
        mWeatherBaseViews.put(WEATHER_DETAILS_TYPE, convertView);
        convertView = (WeatherBaseView) mLayoutInflater.inflate(
                R.layout.weather_aqi, null);
        mWeatherBaseViews.put(AQI_TYPE, convertView);
        convertView = (WeatherBaseView) mLayoutInflater.inflate(
                R.layout.weather_index, null);
        mWeatherBaseViews.put(INDEX_TYPE, convertView);

        //24小时天气
        weather_fullday = mLayoutInflater.inflate(R.layout.weather_fullday, null);
        mWeatherBaseViews.put(INDEX_TYPE, convertView);

    }*/

    public void setDefaultWeather(WeatherInfo weatherInfo) {
        if (weatherInfo == null)
            return;
        defaultWeatherInfo = (DefaultWeatherInfo) weatherInfo;
        mTypes.clear();
        mTypes.add(FULLDAY_TYPE);
        mTypes.add(FORECAST_TYPE);
        mTypes.add(WEATHER_DETAILS_TYPE);
        mTypes.add(AQI_TYPE);
        mTypes.add(INDEX_TYPE);
        notifyDataSetChanged();
    }

    public void setFullDayWeatherInfo(FullDayWeatherInfo fullDayWeatherInfo) {
        this.fullDayWeatherInfo = fullDayWeatherInfo;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return mTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (mTypes.size() < 1)
            return 1;
        return mTypes.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mTypes.size())
            return mTypes.get(position);
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemType = getItemViewType(position);
        if (convertView == null
                || !convertView.getTag().equals(
                R.mipmap.ic_launcher + itemType)) {
            final WeatherBaseView weakFragment = mWeatherBaseViews
                    .get(itemType);
            if (weakFragment != null) {
                Log.i("liweiping", "getView..." + "weakFragment = " + weakFragment);
                convertView = weakFragment;
            } else {
                switch (itemType) {
                    case FORECAST_TYPE:
                        convertView = (WeatherBaseView) mLayoutInflater.inflate(
                                R.layout.weather_forecast, parent, false);
                        break;
                    case WEATHER_DETAILS_TYPE:
                        convertView = (WeatherBaseView) mLayoutInflater.inflate(
                                R.layout.weather_details, parent, false);
                        break;
                    case AQI_TYPE:
                        convertView = (WeatherBaseView) mLayoutInflater.inflate(
                                R.layout.weather_aqi, parent, false);
                        break;
                    case INDEX_TYPE:
                        convertView = (WeatherBaseView) mLayoutInflater.inflate(
                                R.layout.weather_index, parent, false);
                        break;
                    case FULLDAY_TYPE:
                        convertView = (WeatherBaseView) mLayoutInflater.inflate(
                                R.layout.weather_fullday, parent, false);
                    default:
                        break;
                }
                mWeatherBaseViews.put(itemType,

                        (WeatherBaseView) convertView);
            }
            convertView.setTag(R.mipmap.ic_launcher + itemType);
        }

        if (convertView instanceof WeatheFullDayView && fullDayWeatherInfo != null) {
            WeatheFullDayView weatheFullDayView = (WeatheFullDayView) convertView;
            weatheFullDayView.setFullDayInfo(fullDayWeatherInfo);
            return weatheFullDayView;

        } else if (convertView instanceof WeatherBaseView
                && defaultWeatherInfo != null) {
            WeatherBaseView baseView = (WeatherBaseView) convertView;
            baseView.setDefaultWeatherInfo(defaultWeatherInfo);
            return baseView;
        }
        return convertView;
    }
}
