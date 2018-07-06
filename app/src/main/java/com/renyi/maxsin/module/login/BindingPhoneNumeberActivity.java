package com.renyi.maxsin.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.renyi.maxsin.R.id.bt_clear_phone;
import static com.renyi.maxsin.R.id.bt_next;
import static com.renyi.maxsin.R.id.et_phone;

public class BindingPhoneNumeberActivity extends BaseActivity {

    @BindView(et_phone)
    EditText etPhone;
    @BindView(bt_clear_phone)
    ImageView btClearPhone;
    @BindView(bt_next)
    ImageView btNext;
    @BindView(R.id.back)
    RelativeLayout backRel;
    private Bundle bundle;
    public static BindingPhoneNumeberActivity bindingPhoneNumeberActivity;

    @Override
    protected int getLayoutId() {
        bindingPhoneNumeberActivity = this;
        bundle = getIntent().getExtras();
        return R.layout.activity_binding_phone_numeber;
    }

    @Override
    protected void initView() {
        hideTitleAndBack();
    }

    @Override
    protected void loadData() {
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPhone = etPhone.getText().toString().trim();
                if (strPhone.length() == 11 && strPhone.charAt(0) == '1' && strPhone.charAt(1) != '1' && strPhone.charAt(1) != '0' && strPhone.charAt(1) != '2') {
                    codeLogin();

                } else {
                    Toast.makeText(getApplication(), "手机号不合法", Toast.LENGTH_SHORT).show();
                }
            }
        });


        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && btClearPhone.getVisibility() == View.GONE) {
                    btClearPhone.setVisibility(View.VISIBLE);
                    btNext.setBackgroundResource(R.mipmap.ic_login_next_bg);
                } else if (TextUtils.isEmpty(s)) {
                    btClearPhone.setVisibility(View.GONE);
                    btNext.setBackgroundResource(R.mipmap.ic_login_next_bg);
                } else if (s.toString().length() == 11 && s.toString().charAt(0) == '1' && s.toString().charAt(1) != '1' && s.toString().charAt(1) != '0' && s.toString().charAt(1) != '2') {
                    btClearPhone.setVisibility(View.VISIBLE);
                    btNext.setBackgroundResource(R.mipmap.ic_login_next_sel_bg);
                } else if (s.toString().length() != 11) {
                    btNext.setBackgroundResource(R.mipmap.ic_login_next_bg);
                }
            }
        });
    }

    @Override
    protected void setOnClickListeners() {
        backRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingPhoneNumeberActivity = null;
                finish();
            }
        });
        btClearPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPhone.setText("");
            }
        });
    }

    private void codeLogin() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        //        map.put("login_flag", extras.getString("login_flag"));
        //        map.put("device_flag", "1");
        //        map.put("third_account", extras.getString("login_account"));
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

                    bundle.putString("phoneNumber", etPhone.getText().toString().trim());
                    Intent intent = new Intent(BindingPhoneNumeberActivity.this,
                            VerificationPhoneNumberActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    // TODO: 2018/4/28 缓存uid 
                } else {
                    Toast.makeText(BindingPhoneNumeberActivity.this, "绑定失败", Toast.LENGTH_SHORT).show();
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
        bindingPhoneNumeberActivity = null;
    }
}
