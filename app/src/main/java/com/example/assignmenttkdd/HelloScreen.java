package com.example.assignmenttkdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class HelloScreen extends AppCompatActivity {
    private ImageView img_center;
    private TextView tv_left;
    private ImageView coin_1,coin_2,coin_3,coin_4,coin_5;
    private Handler handler = new Handler();
    private int a = 0;
    private int widthsc;
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_screen);
        img_center = findViewById(R.id.img_center);
        tv_left = findViewById(R.id.tv_left);
        coin_1 = findViewById(R.id.coin_1);
        coin_2 = findViewById(R.id.coin_2);
        coin_3 = findViewById(R.id.coin_3);
        coin_4 = findViewById(R.id.coin_4);
        coin_5 = findViewById(R.id.coin_5);
        widthsc = getScreenWidth();

        runImg();
        Animation animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(800);
        animation.setStartOffset(800);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        Animation animation2 = new AlphaAnimation(0.0f,1.0f);
        animation2.setDuration(1000);
        animation2.setStartOffset(1000);
        animation2.setRepeatMode(Animation.REVERSE);
        animation2.setRepeatCount(Animation.INFINITE);
        RotateAnimation rotate = new RotateAnimation(0, 350, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());


        coin_1.startAnimation(animation);
        coin_2.startAnimation(animation);
        coin_3.startAnimation(animation);
        coin_4.startAnimation(animation2);
        coin_5.startAnimation(animation2);

        img_center.startAnimation(rotate);

    }
    public void runImg() {
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(runnable, 250);
                a += 50;
                if(a>=widthsc){
                    stopImg();
                    coin_1.clearAnimation();
                    coin_2.clearAnimation();
                    coin_3.clearAnimation();
                    coin_4.clearAnimation();
                    coin_5.clearAnimation();

                    img_center.clearAnimation();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    HelloScreen.this.finish();
                }else {
                    ViewGroup.LayoutParams params = tv_left.getLayoutParams();
                    params.width = a;
                    tv_left.setLayoutParams(params);
                }
            }
        },250);
    }
    public void stopImg(){
        handler.removeCallbacks(runnable);
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}