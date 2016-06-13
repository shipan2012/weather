package com.xfshipan.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.CityInfo;
import com.xfshipan.weather.db.CityInfoUtils;
import com.xfshipan.weather.fragment.BaseFragment;
import com.xfshipan.weather.fragment.FragmentFactory;
import com.xfshipan.weather.utils.SharePrefUtils;
import com.xfshipan.weather.utils.UiUtils;
import com.xfshipan.weather.view.SkinSettingManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    //已经选择城市信息的字符串
    private String citySelecteds;
    private MainAdapter mainAdapter;
    private ViewPager vp_content;
    private List<BaseFragment> fragments;//Fragment集合
    private static Toolbar toolbar;
    private List<CityInfo> cityInfoList;
    private int themes;
    private boolean isModified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 显示主界面
     */
    @Override
    protected void initView() {
        super.initView();
        citySelecteds = SharePrefUtils.getString(this, SharePrefUtils.CITY_SELECTED, "");
        if (citySelecteds.equals("")) {
            initSelectCityView();
        } else {
            initMainView();
            reloadFregments();
            getSupportActionBar().setTitle(cityInfoList.get(0).getAreaName());
        }
    }

    /**
     * 初始化城市选择界面
     */
    private void initSelectCityView() {
        Intent intent = new Intent(MainActivity.this, QueryCityActivity.class);
        startActivity(intent);
        //销毁当前界面
        finish();
    }

    /**
     * 初始化主界面
     */
    private void initMainView() {
        View mainView = View.inflate(MainActivity.this, R.layout.activity_main, null);
        setContentView(mainView);
        toolbar = (Toolbar) mainView.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //侧滑抽屉
        DrawerLayout drawer = (DrawerLayout) mainView.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //侧边栏菜单
        NavigationView navigationView = (NavigationView) mainView.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Content ViewPager
        vp_content = (ViewPager) mainView.findViewById(R.id.vp_content);
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        vp_content.setAdapter(mainAdapter);
        vp_content.addOnPageChangeListener(this);
    }

    /**
     * 刷新数据
     */

    protected void initData() {

    }

    /**
     * 根据列表生成Fragment集合
     *
     * @param cityInfoList
     * @return
     */
    private List<BaseFragment> createFragments(List<CityInfo> cityInfoList) {
        List<BaseFragment> fragmentList = new ArrayList<BaseFragment>();
        for (CityInfo info : cityInfoList) {
            BaseFragment fragment = FragmentFactory.createFragment(info.getWeatherId());
            fragmentList.add(fragment);
        }
        return fragmentList;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    //每当可见时刷新数据
    @Override
    protected void onResume() {
        super.onResume();
        //如果有新的添加，直接显示最后一个新添加的
        if (getIntent().getBooleanExtra("newSelected", false)) {
            reloadFregments();
            vp_content.setCurrentItem(fragments.size());
        }
    }

    /**
     * 从文件中重新加载数据，刷新Fragments
     */
    private void reloadFregments() {
        //从文件中读取数据
        String cityInfos = SharePrefUtils.getString(this, SharePrefUtils.CITY_SELECTED, "");
        cityInfoList = CityInfoUtils.transCityString(cityInfos);
        //生成相应的Fragment集合
        fragments = createFragments(cityInfoList);
        if (fragments != null) {
            mainAdapter.refreshFragments(fragments);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (cityInfoList == null) {
            String cityInfos = SharePrefUtils.getString(this, SharePrefUtils.CITY_SELECTED, "");
            cityInfoList = CityInfoUtils.transCityString(cityInfos);
        }
        CityInfo cityInfo = cityInfoList.get(position);
        toolbar.setTitle(cityInfo.getAreaName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    class MainAdapter extends FragmentStatePagerAdapter {
        List<BaseFragment> selectedFragments;

        /**
         * 刷新Fragment
         *
         * @param fragmentList
         */
        public void refreshFragments(List<BaseFragment> fragmentList) {
            selectedFragments.clear();
            selectedFragments.addAll(fragmentList);
            notifyDataSetChanged();
        }

        public MainAdapter(FragmentManager fm) {
            super(fm);
            selectedFragments = new ArrayList<BaseFragment>();
        }

        @Override
        public Fragment getItem(int position) {
            return selectedFragments.get(position);
        }

        @Override
        public int getCount() {
            return selectedFragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public void onBackPressed() {
        if (SharePrefUtils.getString(this, SharePrefUtils.CITY_SELECTED, "").equals("")) {
            super.onBackPressed();
        } else {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (SharePrefUtils.getString(this, SharePrefUtils.CITY_SELECTED, "").equals("")) {
        } else {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            SharePrefUtils.setBoolean(UiUtils.getContext(), SharePrefUtils.THEME_MODIFIED, false);
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            MainActivity.this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_managecity) {
            //进入选择城市管理界面
            Intent intent = new Intent(this, ManageCityActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_change) {
            //换肤
            themes = new SkinSettingManager().toggleSkins();
            this.setTheme(themes);
            this.recreate();//重新创建界面
            isModified = true;

            Intent mIntent = new Intent(this, MainActivity.class);
            boolean isModified = SharePrefUtils.getBoolean(UiUtils.getContext(), SharePrefUtils.THEME_MODIFIED, true);
            if (isModified){
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            startActivity(mIntent);
//            finish();
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_help) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
