package com.odin.push.demo;

import android.app.Application;

import com.odin.odincommon.OdinSDK;

public class OdinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //OdinPushSDK初始化
        OdinSDK.init(this);
    }
}
