package com.hjianfei.ticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.hjianfei.ticket.R;

public class SplashActivity extends AppCompatActivity {

    private RelativeLayout rlSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlSplash = (RelativeLayout) findViewById(R.id.rlSplash);
        //由于是使用了三种动画效果合在一起，所以要使用AnimationSet动画集
        AnimationSet set = new AnimationSet(false);
        RotateAnimation rtAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rtAnimation.setDuration(2000);
        rtAnimation.setFillAfter(true);

        ScaleAnimation scAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scAnimation.setDuration(2000);
        scAnimation.setFillAfter(true);

        AlphaAnimation alAnimation = new AlphaAnimation(0, 1);
        alAnimation.setDuration(2000);
        alAnimation.setFillAfter(true);

        set.addAnimation(rtAnimation);
        set.addAnimation(scAnimation);
        set.addAnimation(alAnimation);
        //执行动画
        rlSplash.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
