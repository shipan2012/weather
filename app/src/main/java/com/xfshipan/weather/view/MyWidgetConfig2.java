package com.xfshipan.weather.view;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.xfshipan.weather.R;
import com.xfshipan.weather.bean.CityInfo;
import com.xfshipan.weather.bean.DefaultWeatherInfo;
import com.xfshipan.weather.bean.WeatherUtilities;
import com.xfshipan.weather.db.CityInfoUtils;
import com.xfshipan.weather.protocal.DefaultWeatherProtocal;
import com.xfshipan.weather.utils.SharePrefUtils;
import com.xfshipan.weather.utils.ThreadManager;
import com.xfshipan.weather.utils.UiUtils;
import com.xfshipan.weather.utils.WeatherIconUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2015/12/3.
 */
public class MyWidgetConfig2 extends AppWidgetProvider {

    private static RemoteViews remoteViews;
    protected static Context context;
    public static AppWidgetManager appWidgetManager;
    private static final String ACTION = "com.xfshipan.weather.widgetupdate2";

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (appWidgetManager == null) {
            appWidgetManager = AppWidgetManager.getInstance(context);
        }
        if (ACTION.equals(intent.getAction())) {
            if (!isMyServiceRunning(WeatherService.class)) {
                context.startService(new Intent(context, WeatherService.class));
            }
        } else {
            super.onReceive(context, intent);
        }
    }


    // onUpdate
    @Override
    public void onUpdate(Context con, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        this.appWidgetManager = appWidgetManager;
        context = con;
        if (!isMyServiceRunning(WeatherService.class)) {
            context.startService(new Intent(context, WeatherService.class));
        }
    }


    /**
     * 获取RemoteViews
     *
     * @return
     */
    @NonNull
    protected static RemoteViews getRemoteViews() {
        if (remoteViews == null) {
            remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.example_appwidget2);
        }
        return remoteViews;
    }

    /**
     * 判断Service是否运行
     *
     * @param serviceClass
     * @return
     */
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 填充数据
     *
     * @param remoteViews
     * @param weatherInfo
     * @param finalLocalCity
     * @param widgetId
     */
    protected static void fillWeatherData(RemoteViews remoteViews, DefaultWeatherInfo weatherInfo, CityInfo finalLocalCity, int widgetId) {
        //刷新数据
        String weather = weatherInfo.getRealtime().getWeather();
        String temp = weatherInfo.getRealtime().getTemp() + "°";
        String type = WeatherIconUtils.getWeather(weatherInfo.getRealtime().getWeather());
        String aqiLevel = WeatherUtilities.getAqiLevel(WeatherIconUtils.getAqi(weatherInfo.getAqi().getAqi()), "");
        int weatherIcon = WeatherIconUtils.getWeatherIcon(Integer.parseInt(type));
        String pubTime = weatherInfo.getRealtime().getTime() + "发布";
        remoteViews.setImageViewResource(R.id.iv_realweather_icon, weatherIcon);
        remoteViews.setTextViewText(R.id.tv_realweather_temp_desc, temp + " " + weather);
        remoteViews.setTextViewText(R.id.tv_realweather_aqi, "空气质量" + aqiLevel);
        remoteViews.setTextViewText(R.id.tv_realweather_city, finalLocalCity.getAreaName());
        remoteViews.setTextViewText(R.id.tv_realweather_pubtime, pubTime);
        remoteViews.setOnClickPendingIntent(R.id.iv_realweather_icon, getPendingSelfIntent(context, "com.xfshipan.weather.widgetupdate1"));
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(widgetId, remoteViews);
    }

    //PendingIntent
    public static PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, MyWidgetConfig1.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }


    /**
     * 天气任务
     */
    public static class WeatherService extends Service {
        private Timer timer;
        private TimerTask weatherTask = new TimerTask() {
            public void run() {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt("taskId", 1);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        };

        @Override
        public void onStart(Intent intent, int startId) {
            timer = new Timer();
            timer.schedule(weatherTask, 0, 1000 * 60 * 10);
        }


        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            flags = START_STICKY;
            return super.onStartCommand(intent, flags, startId);
        }
    }

    // Handler
    private static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            int taskId = data.getInt("taskId");
            if (taskId == 0) {
            } else if (taskId == 1) {
                //天气任务
                if (remoteViews == null) {
                    remoteViews = getRemoteViews();
                }
                // Get all ids
                final ComponentName thisWidget = new ComponentName(context,
                        MyWidgetConfig2.class);
                int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                for (final int widgetId : allWidgetIds) {
                    //找出本地城市
                    CityInfo localCity = null;
                    localCity = CityInfoUtils.parStringToBean(SharePrefUtils.getString(UiUtils.getContext(), SharePrefUtils.CITY_LOCAL, ""));
                    if (localCity != null) {
                        final long weatherId = localCity.getWeatherId();
                        final CityInfo finalLocalCity = localCity;
                        ThreadManager.getInstance().createShortPool().execute(new Runnable() {
                            @Override
                            public void run() {

                                DefaultWeatherProtocal defaultWeatherProtocal = new DefaultWeatherProtocal();
                                final DefaultWeatherInfo weatherInfo = defaultWeatherProtocal.load(weatherId);
                                UiUtils.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        fillWeatherData(remoteViews, weatherInfo, finalLocalCity, widgetId);
                                    }
                                });
                            }
                        });
                    }
                }
            }
        }
    };

}
