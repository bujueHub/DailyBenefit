package com.bujue.dailybenefit;

import android.app.Application;

import com.bujue.dailybenefit.biz.DataManager;

/**
 * @author bujue
 * @date 16/12/13
 */
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DataManager.getInstance().destroy();
    }
}
