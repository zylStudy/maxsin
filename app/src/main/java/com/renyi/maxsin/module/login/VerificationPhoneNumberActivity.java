package com.renyi.maxsin.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renyi.maxsin.LoginActivity;
import com.renyi.maxsin.MainActivity;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.renyi.maxsin.view.loginview.Keyboard;
import com.renyi.maxsin.view.loginview.KeyboardWatcher;
import com.renyi.maxsin.view.loginview.PayEditText;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.renyi.maxsin.R.id.keyboardView_pay;


public class VerificationPhoneNumberActivity extends BaseActivity implements KeyboardWatcher.SoftKeyboardStateListener, Handler.Callback {

    @BindView(R.id.back)
    RelativeLayout backRel;
    @BindView(R.id.code_edit)
    PayEditText codeEdit;
    @BindView(R.id.code_time)
    TextView codeTime;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(keyboardView_pay)
    Keyboard keyboardViewPay;
    private Bundle extras;
    private KeyboardWatcher keyboardWatcher;
    private static final String[] KEY = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "<<", "0", "完成"
    };

    private TimeCount time;

    @Override
    protected int getLayoutId() {
        extras = getIntent().getExtras();
        return R.layout.activity_verification_phone_number;
    }

    @Override
    protected void initView() {
        time = new TimeCount(60000, 1000);
        time.start();

        hideTitleAndBack();
        keyboardWatcher = new KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
        keyboardViewPay.setKeyboardKeys(KEY);
        initEvent();
        tvPhone.setText("已发送短信验证码到+86" + extras.getString("phoneNumber"));
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        backRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        codeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.start();
                getCode();
            }
        });
    }


    private void initEvent() {
        keyboardViewPay.setOnClickKeyboardListener(new Keyboard.OnClickKeyboardListener() {
            @Override
            public void onKeyClick(int position, String value) {
                if (position < 11 && position != 9) {
                    codeEdit.add(value);
                } else if (position == 9) {
                    codeEdit.remove();
                } else if (position == 11) {
                    //当点击完成的时候，也可以通过payEditText.getText()获取密码，此时不应该注册OnInputFinishedListener接口
                    //                    Toast.makeText(getApplication(), "您的密码是：" + payEditText.getText(), Toast.LENGTH_SHORT).show();
                    //                    finish();
                }
            }
        });

        /**
         * 当密码输入完成时的回调
         */
        codeEdit.setOnInputFinishedListener(new PayEditText.OnInputFinishedListener() {
            @Override
            public void onInputFinished(String code) {
                if (code.length() == 6) {
                    codeLogin(code);
                }
            }
        });
    }

    private void codeLogin(String code) {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("login_flag", extras.getString("login_flag"));
        map.put("device_flag", "1");
        map.put("third_account", extras.getString("login_account"));
        map.put("key", Api.KEY);
        map.put("code", code);
        map.put("nickname", extras.getString("nickname"));
        map.put("head_url", extras.getString("head_url"));
        map.put("mobile", extras.getString("phoneNumber"));
        map.put("gender", extras.getString("gender"));

        mHttpHelper.post(Api.URL + "tow_login", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    Intent intent = new Intent(VerificationPhoneNumberActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    SPUtils.putBoolean("isLogin", false);
                    SPUtils.put("uid", resultBean.getData().getU_id());
                    BindingPhoneNumeberActivity.bindingPhoneNumeberActivity.finish();
                    LoginActivity.loginActivity.finish();
                    finish();
                } else {
                    Toast.makeText(VerificationPhoneNumberActivity.this, "绑定失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


    private void getCode() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("mobile", extras.getString("phoneNumber"));


        mHttpHelper.post(Api.URL + "send_code", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {


                } else {
                    Toast.makeText(VerificationPhoneNumberActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {

    }

    @Override
    public void onSoftKeyboardClosed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        time.cancel();
        keyboardWatcher.removeSoftKeyboardStateListener(this);
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            codeTime.setEnabled(false);
            codeTime.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
            codeTime.setTextColor(ContextCompat.getColor(VerificationPhoneNumberActivity.this, R.color.color8));

        }

        @Override
        public void onFinish() {
            codeTime.setText("重新获取验证码");
            codeTime.setEnabled(true);
            codeTime.setTextColor(ContextCompat.getColor(VerificationPhoneNumberActivity.this, R.color.colornor));

        }
    }

}
