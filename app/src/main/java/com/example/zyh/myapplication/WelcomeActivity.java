package com.example.zyh.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

/**
 * Created by zyh on 2016/7/7.
 */

public class WelcomeActivity extends AppCompatActivity {
    private RelativeLayout startReLayout, startReLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        startReLayout = (RelativeLayout) findViewById(R.id.relative_start);
        startReLayout2 = (RelativeLayout) findViewById(R.id.start02_relative);
        AnimatorSet set = new AnimatorSet();
        AnimatorSet set1 = new AnimatorSet();
        ObjectAnimator scale = ObjectAnimator.ofFloat(startReLayout, "scaleX",
                1.5f);

        ObjectAnimator scale2 = ObjectAnimator.ofFloat(startReLayout, "scaleY",
                1.5f);

        set1.play(scale).with(scale2);
        set1.start();

        ObjectAnimator translate = ObjectAnimator.ofFloat(startReLayout,
                "translationX", -90);
        ObjectAnimator translate02 = ObjectAnimator.ofFloat(startReLayout2,
                "translationX", 90);
        set.setDuration(3000);
        set.play(translate).with(translate02);
        set.start();

        translate.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                // TODO Auto-generated method stub
                WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();

            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
}

