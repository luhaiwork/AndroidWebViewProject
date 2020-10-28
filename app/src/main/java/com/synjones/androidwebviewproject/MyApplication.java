package com.synjones.androidwebviewproject;

import com.kingja.loadsir.core.LoadSir;
import com.synjones.base.BaseApplication;
import com.synjones.base.loadsir.*;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
