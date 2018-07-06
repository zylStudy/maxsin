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
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MyChangePsdActivity extends BaseActivity {

    @BindView(R.id.et_psdf)
    EditText etPsdf;
    @BindView(R.id.et_psdt)
    EditText etPsdt;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView codeTime;
    @BindView(R.id.bt_change)
    TextView btChange;
    private String phone;
    private TimeCount time;

    @Override
    protected int getLayoutId() {
        phone = getIntent().getExtras().getString("phone");
        return R.layout.activity_my_change_psd;
    }

    @Override
    protected void initView() {
        showTitleAndBack("修改密码");
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
                if (etPsdf.getText().toString().trim().length() < 6 || etPsdt.getText().toString().trim().length() < 6) {
                    Toast.makeText(MyChangePsdActivity.this, " 密码长度少于6位", Toast.LENGTH_SHORT).show();
                } else {
                    if (!etPsdf.getText().toString().trim().equals(etPsdt.getText().toString().trim())) {
                        Toast.makeText(MyChangePsdActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    } else {

                        if (etCode.getText().toString().trim().length() == 0) {
                            Toast.makeText(MyChangePsdActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();

                        } else {
                            verificationCode();
                        }

                    }
                }


            }
        });
    }

    private void verificationCode() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("mobile", phone);
        map.put("uid", "1");
        map.put("code", etCode.getText().toString().trim());
        map.put("passwd", etPsdf.getText().toString().trim());


        mHttpHelper.post(Api.URL + "save_paswd", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    Toast.makeText(MyChangePsdActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                    // TODO: 2018/5/16 修改密码之后写入密码

                } else if (resultBean.getCode().equals("903")) {
                    Toast.makeText(MyChangePsdActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                } else if (resultBean.getCode().equals("901")) {
                    Toast.makeText(MyChangePsdActivity.this, "参数缺失", Toast.LENGTH_SHORT).show();
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
            codeTime.setTextColor(ContextCompat.getColor(MyChangePsdActivity.this, R.color.color8));

        }

        @Override
        public void onFinish() {
            codeTime.setText("重新获取验证码");
            codeTime.setEnabled(true);
            codeTime.setTextColor(ContextCompat.getColor(MyChangePsdActivity.this, R.color.colorOuteb4161));

        }
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

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        time.cancel();
    }
}
