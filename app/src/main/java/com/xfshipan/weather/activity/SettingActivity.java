package com.xfshipan.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xfshipan.weather.R;
import com.xfshipan.weather.utils.SharePrefUtils;
import com.xfshipan.weather.utils.UiUtils;
import com.xfshipan.weather.view.SkinSettingManager;

public class SettingActivity extends BaseActivity {
    private static boolean isModified = false;
    private int themes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void back(View view) {
        SettingActivity.this.finish();
    }

    public void toggle(View view) {
        themes = new SkinSettingManager().toggleSkins();
        this.setTheme(themes);
        this.recreate();//重新创建界面
        isModified = true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mIntent = new Intent(this, MainActivity.class);
        boolean isModified = SharePrefUtils.getBoolean(UiUtils.getContext(), SharePrefUtils.THEME_MODIFIED, true);
        if (isModified){
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        startActivity(mIntent);
        finish();
    }
}
