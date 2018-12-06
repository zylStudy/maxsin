package com.renyi.maxsin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.renyi.maxsin.module.login.SplashActivity;
import com.renyi.maxsin.utils.SPUtils;

public class WelcomeActivity extends Activity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        //        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        SPUtils spUtils = new SPUtils(this);
        handler.postDelayed(new Runnable() {
            public void run() {
                getJumpWhere();
            }
        }, 2000);

    }

    private void getJumpWhere() {
        if (SPUtils.get("isFirst", true)) {
            //跳转到欢迎页
            Intent intent = new Intent(WelcomeActivity.this,
                    SplashActivity.class);
            startActivity(intent);
            SPUtils.putBoolean("isFirst", false);

        } else {

            if (SPUtils.get("isLogin", true)) {
                //如果没有登录跳转到登录页面，登录成功后把isLogin=flase
                Intent intent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            } else {
                //如果登录了跳转到Mainactivity，同时isLogin值不变，依然为true
                Intent intent = new Intent(WelcomeActivity.this,
                        MainActivity.class);
                startActivity(intent);
                //  overridePendingTransition(R.anim.alpha_activity_in, R.anim.alpha_activity_out);

            }
        }
        finish();

    }

}
