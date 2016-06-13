package com.xfshipan.weather.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/10/31.
 */
public class CustomRecycleView extends RecyclerView {
    public CustomRecycleView(Context context) {
        super(context);
    }

    public CustomRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 事件分发, 请求父控件及祖宗控件不拦截
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
