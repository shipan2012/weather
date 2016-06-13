package com.xfshipan.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.DefaultWeatherInfo;
import com.xfshipan.weather.bean.ReadableWeekInfos;
import com.xfshipan.weather.bean.WeatherUtilities;
import com.xfshipan.weather.view.WeatherTypefacedTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */

public class WeekWeatherAdapter extends RecyclerView.Adapter<WeekWeatherHolder> {
    private DefaultWeatherInfo.ForecastEntity forecastEntity;
    private List<ReadableWeekInfos> readableWeekInfoses;
    private Context context;

    public WeekWeatherAdapter(Context context) {
        this.context = context;
        readableWeekInfoses = new ArrayList<ReadableWeekInfos>();
    }

    /**
     * 刷新数据
     */
    public void refreshData(DefaultWeatherInfo defaultInfos) {
        readableWeekInfoses.clear();
        readableWeekInfoses.addAll(WeatherUtilities.parseWeekInfos(defaultInfos));
        this.notifyDataSetChanged();
    }

    @Override
    public WeekWeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_week_weather, null);
        return new WeekWeatherHolder(view);
    }

    @Override
    public int getItemCount() {
        return readableWeekInfoses.size();
    }

    @Override
    public void onBindViewHolder(WeekWeatherHolder holder, int position) {
        ReadableWeekInfos weatherInfo = readableWeekInfoses.get(position);
        holder.tv_week_time.setText(weatherInfo.getWeekInfos());
        holder.tv_week_temp.setText(weatherInfo.getTemps()[1] + "°" + "~" + weatherInfo.getTemps()[0]);
        holder.iv_week_weathericon.setImageResource(weatherInfo.getIcons());
        holder.tv_week_weatherdesc.setText(weatherInfo.getDesc());
    }
}

class WeekWeatherHolder extends RecyclerView.ViewHolder {
    WeatherTypefacedTextView tv_week_time;//时间
    ImageView iv_week_weathericon;//图标
    WeatherTypefacedTextView tv_week_temp;//温度
    WeatherTypefacedTextView tv_week_weatherdesc;//描述

    public WeekWeatherHolder(View itemView) {
        super(itemView);
        tv_week_time = (WeatherTypefacedTextView) itemView.findViewById(R.id.tv_week_time);
        iv_week_weathericon = (ImageView) itemView.findViewById(R.id.iv_week_weathericon);
        tv_week_temp = (WeatherTypefacedTextView) itemView.findViewById(R.id.tv_week_temp);
        tv_week_weatherdesc = (WeatherTypefacedTextView) itemView.findViewById(R.id.tv_week_weatherdesc);
    }
}


