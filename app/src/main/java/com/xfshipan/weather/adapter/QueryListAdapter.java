package com.xfshipan.weather.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.CityInfo;
import com.xfshipan.weather.db.CityInfoUtils;
import com.xfshipan.weather.utils.SharePrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/6.
 */
public class QueryListAdapter extends BaseAdapter {

    private final ArrayList<CityInfo> queryCityInfos;
    private Context context;
    private String citySelectedStr;
    private String areaSelectedStr;
    private List<CityInfo> citySelectedList;

    public QueryListAdapter(Context context) {
        queryCityInfos = new ArrayList<CityInfo>();
        this.context = context;
        areaSelectedStr = getSelectedStr();
    }

    public String getSelectedStr() {
        citySelectedStr = SharePrefUtils.getString(context, SharePrefUtils.CITY_SELECTED, "");
        citySelectedList = new ArrayList<>();
        citySelectedList.addAll(CityInfoUtils.transCityString(citySelectedStr));
        StringBuffer buffer = new StringBuffer();
        for (CityInfo cityInfo : citySelectedList) {
            buffer.append(cityInfo.getAreaName());
            buffer.append(",");
        }
        return buffer.toString();
    }

    public void refreshView(List<CityInfo> infos) {
        //刷新数据
        queryCityInfos.clear();
        queryCityInfos.addAll(infos);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return queryCityInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return queryCityInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder itemViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_cityquery, null);
            itemViewHolder = new ItemViewHolder();
            itemViewHolder.tv_cityname = (TextView) convertView.findViewById(R.id.tv_cityname);
            itemViewHolder.iv_checked = (ImageView) convertView.findViewById(R.id.iv_city_selected);
            convertView.setTag(itemViewHolder);
        } else {
            itemViewHolder = (ItemViewHolder) convertView.getTag();
        }
        CityInfo info = queryCityInfos.get(position);
        if (areaSelectedStr.contains(info.getAreaName())) {
            itemViewHolder.iv_checked.setVisibility(View.VISIBLE);
        } else {
            itemViewHolder.iv_checked.setVisibility(View.INVISIBLE);
        }
        itemViewHolder.tv_cityname.setText(info.getProvinceName() + "-" +
                info.getCityName() + "-" + info.getAreaName());
        return convertView;
    }

    class ItemViewHolder {
        TextView tv_cityname;
        ImageView iv_checked;
    }
}
