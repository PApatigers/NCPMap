package com.example.ncpmap;

import android.app.Application;

public class MyApplication extends Application {

    public static final String VERSION = BuildConfig.VERSION_NAME;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
