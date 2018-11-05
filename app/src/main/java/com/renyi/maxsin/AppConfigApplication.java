package com.renyi.maxsin;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.tencent.bugly.Bugly;

import io.rong.imkit.RongIM;

/**
 * Created by zhangyuliang on 2018/8/3.
 */

public class AppConfigApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTextSize();
        Bugly.init(getApplicationContext(), "ba9fc1bbcd", false);


        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */

            RongIM.init(this);
            // RongCloudEvent.init(this);
        }
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

    //系统字体改变  应用字体不受影响
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
