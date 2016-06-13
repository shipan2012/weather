package com.xfshipan.weather.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.DefaultWeatherInfo;
import com.xfshipan.weather.bean.FullDayWeatherInfo;
import com.xfshipan.weather.bean.WeatherInfo;
import com.xfshipan.weather.bean.WeatherUtilities;
import com.xfshipan.weather.utils.WeatherIconUtils;

import java.util.ArrayList;
import java.util.List;

public class WeatheFullDayView extends WeatherBaseView {
    // 24小时天气预报
    private DefaultWeatherInfo defaultWeatherInfo;
    private RecyclerView rv_fullday;
    private FullDayAdapter fullDayAdapter;
    private FullDayWeatherInfo fullDayInfo;
    private Context context;

    public WeatheFullDayView(Context c) {
        this(c, null);
    }

    public WeatheFullDayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatheFullDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
    }


    @Override
    public void setDefaultWeatherInfo(WeatherInfo weatherInfo) {
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        rv_fullday = (RecyclerView) findViewById(R.id.rv_fullday);

        //设置布局管理器
        rv_fullday.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //设置adapter
        fullDayAdapter = new FullDayAdapter(context);
        rv_fullday.setAdapter(fullDayAdapter);
        //设置Item增加、移除动画
        rv_fullday.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        rv_fullday.addItemDecoration(new DividerItemDecoration(
                context, DividerItemDecoration.HORIZONTAL_LIST));
    }

    public void setFullDayInfo(FullDayWeatherInfo fullDayInfo) {
        this.fullDayInfo = fullDayInfo;
        List<FullDayWeatherInfo.JhEntity> jh = fullDayInfo.getJh();
        fullDayAdapter.reFreshData(jh);
    }

    class FullDayAdapter extends RecyclerView.Adapter<FullDayHolder> {
        List<FullDayWeatherInfo.JhEntity> jhEntities;
        Context context;

        public FullDayAdapter(Context context) {
            this.context = context;
            jhEntities = new ArrayList<FullDayWeatherInfo.JhEntity>();
        }

        public void reFreshData(List<FullDayWeatherInfo.JhEntity> jh) {
            jhEntities.clear();
            jhEntities.addAll(jh);
        }

        @Override
        public FullDayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.item_fullday, null);
            return new FullDayHolder(view);
        }

        @Override
        public void onBindViewHolder(FullDayHolder holder, int position) {
            FullDayWeatherInfo.JhEntity jhEntity = jhEntities.get(position);
            String time = WeatherUtilities.getFullDayTime(jhEntity.getJf());//时间
            String subTime = time.substring(0, 2);

            int state = Integer.parseInt(jhEntity.getJa());//天气状态
            int weatherIcon = WeatherIconUtils.getWeatherIcon(state);//天气id
            int fullIcon = WeatherIconUtils.getFullDayWeatherIcon(state, WeatherIconUtils.isNight(subTime));
            String temp = jhEntity.getJb();//温度
            holder.tv_fullday_time.setText(time);
            holder.iv_fullday_weather.setImageResource(fullIcon);
            holder.tv_fullday_temp.setText(temp + "°");
        }

        @Override
        public int getItemCount() {
            return jhEntities.size();
        }
    }

    class FullDayHolder extends RecyclerView.ViewHolder {
        TextView tv_fullday_time;
        ImageView iv_fullday_weather;
        TextView tv_fullday_temp;

        public FullDayHolder(View itemView) {
            super(itemView);
            tv_fullday_time = (TextView) itemView.findViewById(R.id.tv_fullday_time);
            iv_fullday_weather = (ImageView) itemView.findViewById(R.id.iv_fullday_weather);
            tv_fullday_temp = (TextView) itemView.findViewById(R.id.tv_fullday_temp);
        }
    }


}
