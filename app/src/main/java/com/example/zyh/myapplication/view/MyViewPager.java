package com.example.zyh.myapplication.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by 小军 on 2016/7/9.
 */
/*
 *自定义的ViewPager
 */
public class MyViewPager extends ViewPager {


    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
    private PointF sp = new PointF();
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                sp = new PointF(ev.getX(), ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                PointF ep = new PointF(ev.getX(), ev.getY());
                float move_x = sp.x - ep.x;
                if(!(move_x < 0 && getCurrentItem() == 0) && getParent() != null
                        && !(move_x > 0 && getCurrentItem() == getAdapter().getCount() - 1)
                        && sp.x > 50){
                    Log.i("DEBUG", "intercept move event");
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

        }

        return super.onTouchEvent(ev);
    }
}
