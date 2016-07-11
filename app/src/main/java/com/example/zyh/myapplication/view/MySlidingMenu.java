package com.example.zyh.myapplication.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.zyh.myapplication.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by zyh on 2016/7/6.
 */

public class MySlidingMenu extends HorizontalScrollView {
    private LinearLayout mLinearLayout;
    private ViewGroup mMenu, mContent;//菜单，内容区域
    private int mScreenwidth, mpaddingRight, mMenuWidth;//屏幕宽度，菜单距离屏幕右侧边距
    private boolean once, isOpen;//是否第一次加载，是否是打开状态

    public MySlidingMenu(Context context) {
        this(context, null);
    }

    /**
     * 当设置自定义属性的时候调用
     * 获得WindouManager,会将屏幕信息放到这里面来，我们只需要从里面获取就可以了
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MySlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //怎么获取自定义属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MySlidingMenu, defStyleAttr, 0);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.MySlidingMenu_paddingRight:
                    //默认50dp转成像素赋值给mpaddingRight
                    mpaddingRight = a.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();//用完一定要释放
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);

        mScreenwidth = displayMetrics.widthPixels;//获取屏幕宽度
    }

    public MySlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 测量宽高度，可能多次调用
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            scrollTo(mMenuWidth, 0);
            isOpen = false;
        }

    }

    /**
     * 手势监听
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();//获取当前Scrollview左上角坐标
                if (scrollX >= mMenuWidth / 2) {
                    smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                } else {
                    smoothScrollTo(0, 0);//缓慢滑动到目标位置
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滑动状态变化后执行
     * 滑动多少偏移多少
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //滑动比例从1到0
        float scale = 1.0f * l / mMenuWidth;
        ViewHelper.setTranslationX(mMenu, (float) (l * 0.8f));
        /**
         * 区别：
         * 1.内容区域越来越小，比例从1.0f到0.7f  0.7f+0.3f*scale;
         * 2.菜单区域越来越大，比例从0.7f到1.0f  1.0f-0.3f*scale;
         *  透明度越来越不透明，从0.6f到1.0f 1.0f-0.4f*scale;
         */
        float contentScale = 0.7f + 0.3f * scale;
        float menuScale = 1.0f - 0.3f * scale;
        float menuAlpha = 1.0f - 0.4f * scale;
        //菜单
        ViewHelper.setScaleX(mMenu, menuScale);
        ViewHelper.setScaleY(mMenu, menuScale);
        ViewHelper.setAlpha(mMenu, menuAlpha);
        //内容需要设置缩放中心点
        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, contentScale);
        ViewHelper.setScaleY(mContent, contentScale);
    }

    /**
     * 打开关闭菜单栏
     */
    public void open() {
        if (isOpen) return;
        else {
            smoothScrollTo(0, 0);
            isOpen = true;
        }
    }

    public void close() {
        if (!isOpen) return;
        else {
            smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }

    public void toggle() {
        if (isOpen) close();
        else {
            open();
        }
    }
}
