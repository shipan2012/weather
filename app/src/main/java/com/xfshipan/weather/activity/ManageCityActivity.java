package com.xfshipan.weather.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.CityInfo;
import com.xfshipan.weather.bean.DefaultWeatherInfo;
import com.xfshipan.weather.bean.WeatherUtilities;
import com.xfshipan.weather.db.CityInfoUtils;
import com.xfshipan.weather.db.dao.WeatherCityDao;
import com.xfshipan.weather.fragment.FragmentFactory;
import com.xfshipan.weather.protocal.DefaultWeatherProtocal;
import com.xfshipan.weather.utils.SharePrefUtils;
import com.xfshipan.weather.utils.ThreadManager;
import com.xfshipan.weather.utils.UiUtils;
import com.xfshipan.weather.utils.WeatherIconUtils;
import com.xfshipan.weather.view.DragSortGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/25.
 */
public class ManageCityActivity extends BaseActivity implements View.OnClickListener {
    public static final int MAX_CITY_NUM = 9;
    private ImageView mBackBtn; //销毁当前进程
    private ImageView mRefreshCityBtn; //刷新
    private ImageView mDividerLine; //分割线
    private ImageView mEditCityBtn; //编辑
    private ImageView mConfirmCityBtn; //
    private ProgressBar mRefreshProgressBar;
    private static boolean isRefreshMode; //是否是刷新状态
    private DragSortGridView mGridView; //GirdView
    private CityGridAdapter mAdapter; //GirdView Adapter
    private List<DefaultWeatherInfo> allWeatherInfos;

    private boolean isLoadingAll = false;
    private List<CityInfo> cityInfoList;

    @Override
    protected void initToolBar() {
        super.initToolBar();
    }

    @Override
    protected void initView() {
        super.initView();
        View mRootView = View.inflate(ManageCityActivity.this, R.layout.city_manager_layout, null);
        setContentView(mRootView);
        mGridView = (DragSortGridView) mRootView.findViewById(R.id.my_city);
        mAdapter = new CityGridAdapter();
        mGridView.setAdapter(mAdapter);
//        mGridView.setOnReorderingListener(dragSortListener);

        mBackBtn = (ImageView) mRootView.findViewById(R.id.back_image);
        mRefreshCityBtn = (ImageView) mRootView.findViewById(R.id.refresh_city);
        mDividerLine = (ImageView) mRootView.findViewById(R.id.divider_line);
        mEditCityBtn = (ImageView) mRootView.findViewById(R.id.edit_city);
        mConfirmCityBtn = (ImageView) mRootView.findViewById(R.id.confirm_city);
        mRefreshProgressBar = (ProgressBar) mRootView.findViewById(R.id.refresh_progress);

        //设置监听
        mBackBtn.setOnClickListener(this);
        mRefreshCityBtn.setOnClickListener(this);
        mEditCityBtn.setOnClickListener(this);
        mConfirmCityBtn.setOnClickListener(this);
        mRefreshProgressBar.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * 初始化已选择城市数据
     */
    public void initCityDatas() {
        //城市数据
        String cityString = SharePrefUtils.getString(UiUtils.getContext(), SharePrefUtils.CITY_SELECTED, "");
        cityInfoList = CityInfoUtils.transCityString(cityString);
        //更新ActionBar状态信息
        updateBtnStates(cityInfoList);
        //刷新城市数据
        mAdapter.refreshCityData(cityInfoList);
        refreshAllWeathers(cityInfoList);
    }

    /**
     * 刷新所有天气信息
     *
     * @param cityInfoList
     */
    private void refreshAllWeathers(final List<CityInfo> cityInfoList) {
        //获取所有城市天气数据
        allWeatherInfos = new ArrayList<DefaultWeatherInfo>();
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                isLoadingAll = true;
                DefaultWeatherProtocal defaultWeatherProtocal = new DefaultWeatherProtocal();
                for (int i = 0; i < cityInfoList.size(); i++) {
                    DefaultWeatherInfo weatherInfo = defaultWeatherProtocal.load(cityInfoList.get(i).getWeatherId());
                    allWeatherInfos.add(weatherInfo);
                }
                isLoadingAll = false;
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //刷新所有天气信息
                        mAdapter.refreshWeatherData(allWeatherInfos, isLoadingAll);
                    }
                });
            }
        });
    }

    /**
     * 更新ActionBar按钮状态
     *
     * @param cityInfoList
     */
    private void updateBtnStates(List<CityInfo> cityInfoList) {
        mEditCityBtn.setEnabled(cityInfoList.size() > 1);
        mRefreshCityBtn.setEnabled(cityInfoList.size() > 1);
        mRefreshProgressBar.setEnabled(cityInfoList.size() > 1);
    }


    /**
     * 点击删除之后，改变状态和修改信息
     *
     * @param info
     */
    private void changeState(CityInfo info) {
        String selectedInfos = CityInfoUtils.parseBeanToString(info);
        String cityInfos = SharePrefUtils.getString(UiUtils.getContext(), SharePrefUtils.CITY_SELECTED, "");
        String modifiedInfos = cityInfos.replace(selectedInfos, "");
        SharePrefUtils.setString(UiUtils.getContext(), SharePrefUtils.CITY_SELECTED, modifiedInfos);
        //从Fragment集合中移除
        FragmentFactory.destoryFragment(info.getWeatherId());
        //刷新数据
        ManageCityActivity.this.initCityDatas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_image:
                //结束当前
                finish();
                break;
            case R.id.refresh_city:// 开始刷新
//                updateRefreshMode(true);
                break;
            case R.id.refresh_progress:// 取消刷新
//                updateRefreshMode(false);
                break;
            case R.id.edit_city:
            case R.id.confirm_city:
                changeEditMode();
                break;
            default:
                break;
        }
    }
/*

    */
    /**
     * 切换刷新模式
     *
     * @param isRefresh
     */
/*
    private void updateRefreshMode(boolean isRefresh) {
        //没有网络，不刷新
        if (isRefresh && HttpUtils.getNetworkState(this) == HttpUtils.NETWORN_NONE) {
            Toast.makeText(this, R.string.net_error, Toast.LENGTH_SHORT).show();
            return;
        }
        isRefreshMode = isRefresh;
        mRefreshProgressBar.setVisibility(isRefresh ? View.VISIBLE
                : View.INVISIBLE);
        mRefreshCityBtn
                .setVisibility(isRefresh ? View.INVISIBLE : View.VISIBLE);
        mEditCityBtn.setEnabled(!isRefresh && (mTmpCitys.size() > 1));
        mGridView.setEnabled(!isRefresh);
        mGridView.setOnReorderingListener(isRefresh ? null : dragSortListener);
        // 开一个异步线程去更新天气或者取消更新
        if (isRefresh) {
            getAllWeather();
        } else {
            mIndex = -1;
            mAdapter.setRefreshingIndex(mIndex);
            App.getVolleyRequestQueue().cancelAll("All");
        }

    }*/

   /* private DragSortGridView.OnReorderingListener dragSortListener = new DragSortGridView.OnReorderingListener() {

        @Override
        public void onReordering(int fromPosition, int toPosition) {
            L.d("liweiping", "onReordering fromPosition:" + fromPosition
                    + ",toPosition:" + toPosition);
            mAdapter.reorder(fromPosition, toPosition);
            changeSortIndex();
        }

        @Override
        public void beginRecordering(AdapterView<?> parent, View view,
                                     int position, long id) {
            if (mAdapter.isEditMode)
                return;
            changeEditMode();
        }

    };*/

    /**
     * 切换编辑状态
     */

    private void changeEditMode() {
        mAdapter.changeEditMode();
        if (mAdapter.isEditMode) {
            mConfirmCityBtn.setVisibility(View.VISIBLE);
            mRefreshCityBtn.setVisibility(View.INVISIBLE);
            mDividerLine.setVisibility(View.INVISIBLE);
            mEditCityBtn.setVisibility(View.INVISIBLE);
        } else {
            mConfirmCityBtn.setVisibility(View.INVISIBLE);
            if (mRefreshProgressBar.getVisibility() != View.VISIBLE)
                mRefreshCityBtn.setVisibility(View.VISIBLE);
            mDividerLine.setVisibility(View.VISIBLE);
            mEditCityBtn.setVisibility(View.VISIBLE);
        }
        updateBtnStates(cityInfoList);
    }

    /**
     * 更新ActionBar按钮状态
     */
    private void updateBtnStates() {
        mEditCityBtn.setEnabled(cityInfoList.size() > 1);
        mRefreshCityBtn.setEnabled(cityInfoList.size() > 1);
        mRefreshProgressBar.setEnabled(cityInfoList.size() > 1);
    }

    /**
     * 两种类型
     */
    private class CityGridAdapter extends BaseAdapter {
        public static final int NORMAL_CITY_TYPE = 0;
        public static final int ADD_CITY_TYPE = 1;
        private boolean isEditMode = false; //编辑的模式
        private boolean isLoadingAll = false;//是否正在刷新所有的

        private List<CityInfo> cityInfos;
        private List<DefaultWeatherInfo> defaultWeatherInfos;

        public CityGridAdapter() {
            cityInfos = new ArrayList<CityInfo>();
            defaultWeatherInfos = new ArrayList<DefaultWeatherInfo>();
        }

        /**
         * 刷新数据
         *
         * @param cityInfoList
         */
        public void refreshCityData(List<CityInfo> cityInfoList) {
            cityInfos.clear();
            cityInfos.addAll(cityInfoList);
            //在最后添加一个编辑状态的item
            if (cityInfos.size() < MAX_CITY_NUM)
                cityInfos.add(null);
            notifyDataSetChanged();
        }

        //刷新天气信息
        public void refreshWeatherData(List<DefaultWeatherInfo> allWeatherInfos, boolean isLoadingAll) {
            this.defaultWeatherInfos = allWeatherInfos;
            this.isLoadingAll = isLoadingAll;
            notifyDataSetChanged();
        }


        @Override
        public int getCount() {
            return cityInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return cityInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (getItem(position) == null)
                return ADD_CITY_TYPE; //最后一个添加城市item
            return NORMAL_CITY_TYPE;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            int type = getItemViewType(position);
            if (convertView == null
                    || convertView.getTag(R.mipmap.ic_launcher + type) == null) {
                switch (type) {
                    case NORMAL_CITY_TYPE:
                        convertView = View.inflate(ManageCityActivity.this, R.layout.city_manger_grid_item_normal, null);
                        break;
                    case ADD_CITY_TYPE:
                        convertView = View.inflate(ManageCityActivity.this, R.layout.city_manger_grid_item_add, null);
                        convertView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ManageCityActivity.this, QueryCityActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    default:
                        break;
                }
                viewHolder = buildHolder(convertView);
                // 因为类型不同，所以给viewHolder设置一个标识,标识必须是资源id，不然会挂掉
                // 我这里为了区分不同的type，所以加上类型
                convertView.setTag(R.mipmap.ic_launcher + type, viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView
                        .getTag(R.mipmap.ic_launcher + type);
            }
            /**
             * 绑定数据
             */
            bindViewData(viewHolder, position);
            return convertView;
        }

        /**
         * 刷新界面之前，在编辑状态和非编辑状态两种状态下，对集合进行编辑
         * 编辑状态下：
         * 如果是编辑状态下，如果集合不为空，并且最后一个为null，则移除最后一个
         * 如果是非编辑状态下，如果集合为空或者（最后一个不为null并且总数不超过最大数目），增加一个
         */
        @Override
        public void notifyDataSetChanged() {
            //获取最后一个item位置
            int lastPosition = ((getCount() - 1) < 0) ? 0 : (getCount() - 1);
            /**
             * 编辑模式下的两种方式切换
             */
            if (isEditMode) {
                // 在编辑模式下，集合中不为空，移除最后一个
                if (!cityInfos.isEmpty() && cityInfos.get(lastPosition) == null)
                    cityInfos.remove(lastPosition);
            } else {

                if (cityInfos.isEmpty()
                        || ((cityInfos.get(lastPosition) != null) && (getCount() < MAX_CITY_NUM)))// 如果最后一个不为空，并且数量小于9个，则添加一个
                    cityInfos.add(null);
            }

            super.notifyDataSetChanged();
        }

        /**
         * 根据传入的view，生成相关的holder
         *
         * @param convertView
         * @return
         */
        private ViewHolder buildHolder(View convertView) {
            ViewHolder holder = new ViewHolder();
            holder.cityTV = (TextView) convertView
                    .findViewById(R.id.city_manager_name_tv);
            holder.tempTV = (TextView) convertView
                    .findViewById(R.id.city_manager_temp_tv);
            holder.weatherIV = (ImageView) convertView
                    .findViewById(R.id.city_manager_icon_iv);
            holder.deleteIV = (ImageView) convertView
                    .findViewById(R.id.city_delete_btn);
            holder.loadingBar = (ProgressBar) convertView
                    .findViewById(R.id.city_manager_progressbar);
            holder.addView = convertView;
            return holder;
        }

        /**
         * 给View绑定数据
         *
         * @param holder
         * @param position
         */
        private void bindViewData(ViewHolder holder, final int position) {
            DefaultWeatherInfo defaultWeatherInfo;
            switch (getItemViewType(position)) {
                case NORMAL_CITY_TYPE:
                    if (!defaultWeatherInfos.isEmpty()) {
                        defaultWeatherInfo = defaultWeatherInfos.get(position);
                        if (isLoadingAll) {
                            holder.loadingBar.setVisibility(View.VISIBLE);
                            holder.weatherIV.setVisibility(View.GONE);
                            holder.tempTV.setText("加载中...");
                        } else {
                            holder.loadingBar.setVisibility(View.GONE);
                            holder.weatherIV.setVisibility(View.VISIBLE);
                            holder.loadingBar.setVisibility(View.GONE);
                            holder.weatherIV.setVisibility(View.VISIBLE);
                            if (defaultWeatherInfo != null) {
                                String[] temps = WeatherUtilities.convertTemp(defaultWeatherInfo.getForecast().getTemp1());
                                holder.tempTV
                                        .setText(temps[0]
                                                + "~"
                                                + temps[1] + "°");
                                DefaultWeatherInfo.RealtimeEntity realtime = defaultWeatherInfo.getRealtime();
                                int animationType = WeatherUtilities.getAnimationType(realtime.getWeather());
                                int weatherIcon = WeatherIconUtils.getWeatherIcon(animationType);
                                holder.weatherIV.setImageResource(weatherIcon);
                            } else {
                                holder.tempTV.setText("--~--°");
                                holder.weatherIV
                                        .setImageResource(R.mipmap.xy_weather_ic_default);
                            }
                        }
                        final CityInfo cityInfo = cityInfos.get(position);
                        holder.cityTV.setText(cityInfo.getAreaName());
                        if (cityInfo.getIsLocal() == 1) {
                            holder.cityTV.setCompoundDrawablesWithIntrinsicBounds(
                                    R.mipmap.current_loc_active_26x26, 0, 0, 0);
                        } else {
                            holder.cityTV.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                    0, 0);
                        }
                        if (isEditMode && cityInfo.getIsLocal() != 1)
                            holder.deleteIV.setVisibility(View.VISIBLE);
                        else
                            holder.deleteIV.setVisibility(View.GONE);
                        holder.deleteIV.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 从数据库中删除城市
                                String deleteCity = CityInfoUtils.parseBeanToString(cityInfo);
                                WeatherCityDao.deleteCity(deleteCity);
                                //删除临时数据，刷新数据
                                cityInfos.remove(cityInfo);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    }
                case ADD_CITY_TYPE:
                   /* holder.addView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (isRefreshMode) {
                                return;
                            }
                            ManagerCityActivity.this.startActivityForResult(
                                    (new Intent(ManagerCityActivity.this,
                                            QueryCityActivity.class)), 0);
                        }
                    });*/

                    break;

                default:
                    break;
            }
        }

        //切换编辑状态
        public void changeEditMode() {
            isEditMode = !isEditMode;

            notifyDataSetChanged();
        }


    }

    private static class ViewHolder {
        TextView cityTV;
        TextView tempTV;
        ImageView weatherIV;
        ProgressBar loadingBar;
        ImageView deleteIV;
        View addView;
    }

    /**
     * 当再次可见时，重新加载数据
     */
    @Override
    protected void onResume() {
        super.onResume();
        initCityDatas();
    }
}
