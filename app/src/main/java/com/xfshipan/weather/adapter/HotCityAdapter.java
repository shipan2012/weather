package com.xfshipan.weather.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.CityInfo;
import com.xfshipan.weather.db.dao.WeatherCityDao;
import com.xfshipan.weather.utils.SharePrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/3.
 */

public class HotCityAdapter extends BaseAdapter {
    private List<CityInfo> mHotCitys;
    private Context context;

    public HotCityAdapter(Context context) {
        this.context = context;
        mHotCitys = new ArrayList<CityInfo>();
    }

    /**
     * 刷新列表
     *
     * @param hotCitys
     */
    public void refreshHotCity(List<CityInfo> hotCitys) {
        mHotCitys.clear();
        //刷新列表
        mHotCitys.addAll(hotCitys);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mHotCitys.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotCitys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityInfo hotCity = (CityInfo) getItem(position);
        ViewHoler viewHoler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.city_query_hotcity_grid_item, null);
            viewHoler = new ViewHoler();
            viewHoler.hotCityTV = (TextView) convertView
                    .findViewById(R.id.grid_city_name);
            viewHoler.selectedIV = (ImageView) convertView
                    .findViewById(R.id.grid_city_selected_iv);
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (ViewHoler) convertView.getTag();
        }
        viewHoler.hotCityTV.setText(hotCity.getAreaName());
        boolean isExit = WeatherCityDao.isExit(SharePrefUtils.CITY_SELECTED, hotCity.getAreaName());
        if (isExit) {
            viewHoler.selectedIV.setVisibility(View.VISIBLE);
            convertView.setClickable(false);
        } else {
            viewHoler.selectedIV.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHoler {
        TextView hotCityTV;
        ImageView selectedIV;
    }
}
