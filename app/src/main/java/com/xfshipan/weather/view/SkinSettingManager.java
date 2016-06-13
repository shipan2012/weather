package com.xfshipan.weather.view;

import com.xfshipan.weather.R;
import com.xfshipan.weather.utils.SharePrefUtils;
import com.xfshipan.weather.utils.UiUtils;

public class SkinSettingManager {

    private int[] skinResources = {R.style.AppTheme_Blue1Theme, R.style.AppTheme_RedTheme};

    public SkinSettingManager() {
    }

    /**
     * 获取当前程序的皮肤序号
     *
     * @return
     */
    public int getSkinType() {
        return SharePrefUtils.getInt(UiUtils.getContext(), SharePrefUtils.THEME_SELECTED, 0);
    }

    /**
     * 把皮肤序号写到全局设置里去
     *
     * @param type
     */
    public void setSkinType(int type) {
        SharePrefUtils.setInt(UiUtils.getContext(), SharePrefUtils.THEME_SELECTED, type);
    }

    /**
     * 获取当前皮肤的style
     *
     * @return
     */
    public int getCurrentSkinRes() {

        int getSkinLen = getSkinType();
        return skinResources[getSkinLen];
    }

    /**
     * 用于切换皮肤
     */
    public int toggleSkins() {

        int skinType = getSkinType();

        if (skinType == 0) {
            skinType = 1;
            SharePrefUtils.setBoolean(UiUtils.getContext(), SharePrefUtils.THEME_MODIFIED, true);
        } else {
            skinType = 0;
            SharePrefUtils.setBoolean(UiUtils.getContext(), SharePrefUtils.THEME_MODIFIED, true);
        }
        setSkinType(skinType);
        return getCurrentSkinRes();
    }

}