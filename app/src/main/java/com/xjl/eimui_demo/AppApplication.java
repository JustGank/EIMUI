package com.xjl.eimui_demo;

import android.app.Application;

import com.xjl.eimui.EIMUI;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EIMUI.INSTANCE.init(getApplicationContext(),"EIMUI");
    }
}
