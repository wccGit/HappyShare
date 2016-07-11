package com.example.zyh.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by 小军 on 2016/7/9.
 */
/*
 *自定义的竖向ScrollView，用来解决事件冲突
 */
public class MyScrollView extends ScrollView {
//    private GestureDetector gestureDetector;
//    private View.OnTouchListener onTouchListener;
    private LinearLayout mLinearLayout;
    private ViewGroup mMenu, mContent;//菜单 ，内容区域
    private int mScreenwidth, mpaddingRight, mMenuWidth;//屏幕宽度，菜单距离屏幕右侧边距
    private boolean once, isOpen;//是否第一次加载，是否是打开状态


    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        //添加一个手势选择器
//        gestureDetector=new GestureDetector(new Yscroll() );
//        setFadingEdgeLength(0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;

    }
    /*
    * 屏幕高宽
    */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            mLinearLayout = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mLinearLayout.getChildAt(0);
            mContent = (ViewGroup) mLinearLayout.getChildAt(1);
            mMenuWidth = mMenu.getLayoutParams().width = mScreenwidth - mpaddingRight;//将屏幕的宽度减去距离右侧的宽赋值给mMenuWidth
            mContent.getLayoutParams().width = mScreenwidth;
            once = true;
        }


    }

//    private class Yscroll extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
//               //控制手指滑动的距离
//                if (Math.abs(distanceY)>=Math.abs(distanceX)) {
//                    return true;
//                }
//                return false;
//            }
//
//        }
    }
