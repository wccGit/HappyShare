package com.example.zyh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
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

public class AboutTen extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutten);
    }

//    @Override
//    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        View view = getLayoutInflater().inflate(R.layout.aboutten,null);
//        return view ;
//    }
}
