package com.example.ncpmap.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ncpmap.MyApplication;
import com.example.ncpmap.R;
import com.example.ncpmap.StepNavigator;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    private ImageView mIcon;
    private TextView mLaunchTitle;
    private TextView mVersionView;

    public static final int TIME = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mIcon = findViewById(R.id.app_icon);
        mLaunchTitle = findViewById(R.id.launch_title);
        mVersionView = findViewById(R.id.version);

        mVersionView.setText("当前版本："+ MyApplication.VERSION);

        //设置动画

        //定时跳转
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StepNavigator.goToMainActivity(SplashActivity.this);
                finish();
            }
        },TIME);
    }
}
