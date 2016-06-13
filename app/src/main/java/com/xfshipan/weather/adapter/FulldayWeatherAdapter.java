package com.xfshipan.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.DefaultWeatherInfo;
import com.xfshipan.weather.bean.FullDayWeatherInfo;
import com.xfshipan.weather.bean.ReadableFullInfos;
import com.xfshipan.weather.bean.WeatherUtilities;
import com.xfshipan.weather.view.WeatherTypefacedTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 */
public class FulldayWeatherAdapter extends RecyclerView.Adapter<FulldayWeatherHolder> {
    private DefaultWeatherInfo.ForecastEntity forecastEntity;
    private List<ReadableFullInfos> fullDayWeatherInfos;
    private Context context;

    public FulldayWeatherAdapter(Context context) {
        this.context = context;
        fullDayWeatherInfos = new ArrayList<ReadableFullInfos>();
    }

    /**
     * 刷新数据
     */
    public void refreshData(FullDayWeatherInfo fullDayWeatherInfo) {
        fullDayWeatherInfos.clear();
        fullDayWeatherInfos.addAll(WeatherUtilities.parseFullDayInfos(fullDayWeatherInfo));
        this.notifyDataSetChanged();
    }

    @Override
    public FulldayWeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_fullday_weather, null);
        return new FulldayWeatherHolder(view);
    }

    @Override
    public int getItemCount() {
        return fullDayWeatherInfos.size();
    }

    @Override
    public void onBindViewHolder(FulldayWeatherHolder holder, int position) {
        ReadableFullInfos fullInfo = fullDayWeatherInfos.get(position);
        holder.tv_fullday_time.setText(fullInfo.getTime());
        holder.iv_fullday_weathericon.setImageResource(fullInfo.getIcon());
        holder.tv_fullday_temp.setText(fullInfo.getTemp() + "°");
    }
}

class FulldayWeatherHolder extends RecyclerView.ViewHolder {
    WeatherTypefacedTextView tv_fullday_time;//时间
    ImageView iv_fullday_weathericon;//图标
    WeatherTypefacedTextView tv_fullday_temp;//温度

    public FulldayWeatherHolder(View itemView) {
        super(itemView);
        tv_fullday_time = (WeatherTypefacedTextView) itemView.findViewById(R.id.tv_fullday_time);
        iv_fullday_weathericon = (ImageView) itemView.findViewById(R.id.iv_fullday_weathericon);
        tv_fullday_temp = (WeatherTypefacedTextView) itemView.findViewById(R.id.tv_fullday_temp);
    }
}


