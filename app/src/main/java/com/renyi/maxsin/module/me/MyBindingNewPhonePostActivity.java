package com.renyi.maxsin.module.me;

import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.login.ResultBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MyBindingNewPhonePostActivity extends BaseActivity {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView codeTime;
    @BindView(R.id.bt_change)
    TextView btChange;
    private TimeCount time;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_binding_new_phone_post;
    }

    @Override
    protected void initView() {
        showTitleAndBack("验证手机号");

        time = new TimeCount(60000, 1000);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        codeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhone.getText().toString().trim().length() != 11) {
                    Toast.makeText(MyBindingNewPhonePostActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                } else {
                    getCode();
                }


            }
        });
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etPhone.getText().toString().trim().length() != 11) {
                    Toast.makeText(MyBindingNewPhonePostActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                } else {
                    if (etCode.getText().toString().trim().length() != 6) {
                        Toast.makeText(MyBindingNewPhonePostActivity.this, "验证码不合法", Toast.LENGTH_SHORT).show();
                    } else {
                        verificationCode();
                    }
                }


            }
        });
    }

    private void verificationCode() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("new_mobile", etPhone.getText().toString().trim());
        map.put("uid", (String) SPUtils.get("uid",""));
        map.put("code", etCode.getText().toString().trim());


        mHttpHelper.post(Api.URL + "save_mobile", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    MyVerificationPhoneActivity.myVerificationPhoneActivity.finish();
                    MyBindingPhoneNumberActivity.myBindingPhoneNumberActivity.finish();

                    MyVerificationPhoneActivity.myVerificationPhoneActivity = null;
                    MyBindingPhoneNumberActivity.myBindingPhoneNumberActivity = null;
                    Toast.makeText(MyBindingNewPhonePostActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();

                    finish();

                } else if (resultBean.getCode().equals("902")) {
                    Toast.makeText(MyBindingNewPhonePostActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                } else if (resultBean.getCode().equals("901")) {
                    Toast.makeText(MyBindingNewPhonePostActivity.this, "参数缺失", Toast.LENGTH_SHORT).show();
                } else if (resultBean.getCode().equals("904")) {
                    Toast.makeText(MyBindingNewPhonePostActivity.this, "该手机号已被绑定", Toast.LENGTH_SHORT).show();
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
        map.put("mobile", etPhone.getText().toString().trim());


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
                    time.start();

                } else if (resultBean.getCode().equals("902")) {
                    Toast.makeText(MyBindingNewPhonePostActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                } else if (resultBean.getCode().equals("901")) {
                    Toast.makeText(MyBindingNewPhonePostActivity.this, "参数缺失", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            codeTime.setEnabled(false);
            codeTime.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
            codeTime.setTextColor(ContextCompat.getColor(MyBindingNewPhonePostActivity.this, R.color.color8));

        }

        @Override
        public void onFinish() {
            codeTime.setText("重新获取验证码");
            codeTime.setEnabled(true);
            codeTime.setTextColor(ContextCompat.getColor(MyBindingNewPhonePostActivity.this, R.color.colorOuteb4161));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        time.cancel();
    }
}
