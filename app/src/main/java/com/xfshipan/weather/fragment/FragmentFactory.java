package com.xfshipan.weather.fragment;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {
    /**
     * 记录所有的fragment，防止重复创建
     */
    private static Map<Long, BaseFragment> mFragmentMap = new HashMap<Long, BaseFragment>();

    /**
     * 采用工厂类进行创建Fragment，便于扩展，已经创建的Fragment不再创建
     */
    public static BaseFragment createFragment(long cityCode) {
        BaseFragment fragment = mFragmentMap.get(cityCode);
        if (fragment == null) {
            fragment = new WeatherFragment();
            Bundle bundle = new Bundle();
            bundle.putLong("cityCode", cityCode);
            fragment.setArguments(bundle);
            mFragmentMap.put(cityCode, fragment);
        }
        return fragment;
    }

    /**
     * 根据id销毁Fragment
     *
     * @param cityCode
     */
    public static void destoryFragment(long cityCode) {
        if (mFragmentMap.containsKey(cityCode)) {
            mFragmentMap.remove(cityCode);
        }
    }
}
