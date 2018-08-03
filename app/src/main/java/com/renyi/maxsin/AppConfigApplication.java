package com.renyi.maxsin;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * Created by zhangyuliang on 2018/8/3.
 */

public class AppConfigApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTextSize();
    }

    //系统字体改变  应用字体不受影响
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
