package com.example.ncpmap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.ncpmap.activity.MainActivity;
import com.example.ncpmap.activity.WebActivity;

public class StepNavigator {

    public static void goToMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void goToWebActivity(Context context , String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    public static void goToOSBroswer(Context context , String url){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
