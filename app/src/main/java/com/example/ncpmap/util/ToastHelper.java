package com.example.ncpmap.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class ToastHelper {

    private Context context;
    private static ToastHelper instance;
    private Toast toast;
    private static Handler handler;


    private ToastHelper(Context context) {
        this.context = context;
        handler = new Handler(Looper.getMainLooper());
    }

    public static ToastHelper getInstance(Context context) {
        if (instance == null)
            synchronized (ToastHelper.class) {
                if (instance == null)
                    instance = new ToastHelper(context);
            }
        return instance;
    }

    public void showToast(final String msg) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            initToast();
            toast.setText(msg);
            toast.show();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    initToast();
                    toast.setText(msg);
                    toast.show();
                }
            });
        }
    }

    private void initToast() {
        toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
    }
}
