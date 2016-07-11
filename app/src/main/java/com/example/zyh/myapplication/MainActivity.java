package com.example.zyh.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyh.myapplication.fragment.ArticleFragment;
import com.example.zyh.myapplication.fragment.MovieFragment;
import com.example.zyh.myapplication.fragment.PictureFragment;
import com.example.zyh.myapplication.fragment.UserFragment;
import com.example.zyh.myapplication.view.MySlidingMenu;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {
    private RadioGroup group;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private ArticleFragment articleFrag;
    private MovieFragment movieFrag;
    private PictureFragment pictureFrag;
    private UserFragment userFrag;
    private MySlidingMenu mySlidingMenu;
    private LinearLayout linear;
    private ImageView img_2, img_3, img_4, img_5, img_6;
    private TextView tv_2, tv_3, tv_4, tv_5, tv_6;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化控件
        init();
        //设置TextView获得焦点和可点击
        for (int i = 0; i < linear.getChildCount(); i++) {
            linear.getChildAt(i).setFocusable(true);
            linear.getChildAt(i).setClickable(true);
            final int j = i;
            linear.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "点击了" + linear.getChildAt(j).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        group = (RadioGroup) findViewById(R.id.main_rg);
        group.setOnCheckedChangeListener(this);

        articleFrag = new ArticleFragment();
        movieFrag = new MovieFragment();
        pictureFrag = new PictureFragment();
        userFrag = new UserFragment();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction.add(R.id.ll_container_id, movieFrag);
        transaction.add(R.id.ll_container_id, articleFrag);
        transaction.add(R.id.ll_container_id, pictureFrag);
        transaction.add(R.id.ll_container_id, userFrag);

        transaction.hide(articleFrag);
        transaction.hide(pictureFrag);
        transaction.hide(userFrag);
        transaction.commit();

    }

    //初始化
    private void init() {
        mySlidingMenu = (MySlidingMenu) findViewById(R.id.slid_id);
        linear = (LinearLayout) findViewById(R.id.Linear_menu_id);
//        img_1 = (ImageView) findViewById(R.id.img_1);
        img_2 = (ImageView) findViewById(R.id.img_2);
        img_3 = (ImageView) findViewById(R.id.img_3);
        img_4 = (ImageView) findViewById(R.id.img_4);
        img_5 = (ImageView) findViewById(R.id.img_5);
        img_6 = (ImageView) findViewById(R.id.img_6);
//        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
        tv_5 = (TextView) findViewById(R.id.tv_5);
        tv_6 = (TextView) findViewById(R.id.tv_6);
    }

    public void onClick(View view) {
        mySlidingMenu.toggle();
        if (flag) {
//            img_1.setVisibility(View.GONE);
            img_2.setVisibility(View.GONE);
            img_3.setVisibility(View.GONE);
            img_4.setVisibility(View.GONE);
            img_5.setVisibility(View.GONE);
            img_6.setVisibility(View.GONE);
            flag = false;
        } else {
//            img_1.setVisibility(View.VISIBLE);
            img_2.setVisibility(View.VISIBLE);
            img_3.setVisibility(View.VISIBLE);
            img_4.setVisibility(View.VISIBLE);
            img_5.setVisibility(View.VISIBLE);
            img_6.setVisibility(View.VISIBLE);
            flag = !flag;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.get_movices:
                transaction.show(movieFrag);
                transaction.hide(articleFrag);
                transaction.hide(pictureFrag);
                transaction.hide(userFrag);
                break;
            case R.id.get_texts:
                transaction.show(articleFrag);
                transaction.hide(movieFrag);
                transaction.hide(pictureFrag);
                transaction.hide(userFrag);
                break;
            case R.id.get_pictures:
                transaction.show(pictureFrag);
                transaction.hide(articleFrag);
                transaction.hide(movieFrag);
                transaction.hide(userFrag);
                break;
            case R.id.get_users:
                transaction.show(userFrag);
                transaction.hide(articleFrag);
                transaction.hide(pictureFrag);
                transaction.hide(movieFrag);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {

            this.finish();
        }
    }
}
