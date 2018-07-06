package com.renyi.maxsin.module.me;

import android.content.Intent;
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
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MyVerificationPhoneActivity extends BaseActivity {

    @BindView(R.id.phone)
    TextView phoneTv;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView codeTime;
    @BindView(R.id.bt_change)
    TextView btChange;
    private String phone;
    private TimeCount time;
    public static MyVerificationPhoneActivity myVerificationPhoneActivity = null;

    @Override
    protected int getLayoutId() {
        myVerificationPhoneActivity = this;
        phone = getIntent().getExtras().getString("phone");
        return R.layout.activity_my_verification_phone;
    }

    @Override
    protected void initView() {
        showTitleAndBack("验证手机号");
        phoneTv.setText(phone);
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
                getCode();
            }
        });
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etCode.getText().toString().trim().length() != 6) {
                    Toast.makeText(MyVerificationPhoneActivity.this, "验证码不合法", Toast.LENGTH_SHORT).show();
                } else {
                    verificationCode();
                }


            }
        });


    }

    private void getCode() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("mobile", phone);


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
                    Toast.makeText(MyVerificationPhoneActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                } else if (resultBean.getCode().equals("901")) {
                    Toast.makeText(MyVerificationPhoneActivity.this, "参数缺失", Toast.LENGTH_SHORT).show();
                } else if (resultBean.getCode().equals("903")) {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void verificationCode() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("mobile", phone);
        map.put("code", etCode.getText().toString().trim());


        mHttpHelper.post(Api.URL + "check_code", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    Intent intent = new Intent(MyVerificationPhoneActivity.this, MyBindingNewPhonePostActivity.class);
                    startActivity(intent);

                } else if (resultBean.getCode().equals("902")) {
                    Toast.makeText(MyVerificationPhoneActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                } else if (resultBean.getCode().equals("901")) {
                    Toast.makeText(MyVerificationPhoneActivity.this, "参数缺失", Toast.LENGTH_SHORT).show();
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
            codeTime.setTextColor(ContextCompat.getColor(MyVerificationPhoneActivity.this, R.color.color8));

        }

        @Override
        public void onFinish() {
            codeTime.setText("重新获取验证码");
            codeTime.setEnabled(true);
            codeTime.setTextColor(ContextCompat.getColor(MyVerificationPhoneActivity.this, R.color.colorOuteb4161));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        time.cancel();
    }
}
