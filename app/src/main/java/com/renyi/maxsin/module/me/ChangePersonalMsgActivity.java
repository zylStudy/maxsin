package com.renyi.maxsin.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.renyi.maxsin.R.id.bt_clear_phone;

public class ChangePersonalMsgActivity extends BaseActivity {
    Bundle bundle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(bt_clear_phone)
    ImageView btClearPhone;
    @BindView(R.id.layout_name)
    RelativeLayout layoutName;
    @BindView(R.id.image01)
    ImageView image01;
    @BindView(R.id.layout_sex1)
    RelativeLayout layoutSex1;
    @BindView(R.id.image02)
    ImageView image02;
    @BindView(R.id.layout_sex2)
    RelativeLayout layoutSex2;
    @BindView(R.id.image03)
    ImageView image03;
    @BindView(R.id.layout_sex3)
    RelativeLayout layoutSex3;
    @BindView(R.id.layout_sex)
    LinearLayout layoutSex;
    String url = "", gender = "", genderStr;
    int flage = 0;


    @Override
    protected int getLayoutId() {

        bundle = getIntent().getExtras();
        return R.layout.activity_change_personal_msg;
    }

    @Override
    protected void initView() {
        layoutSex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image01.setVisibility(image01.VISIBLE);
                image02.setVisibility(image02.GONE);
                image03.setVisibility(image03.GONE);
                gender = "1";
                genderStr = "男";
            }
        });
        layoutSex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image01.setVisibility(image01.GONE);
                image02.setVisibility(image02.VISIBLE);
                image03.setVisibility(image03.GONE);
                gender = "0";
                genderStr = "女";
            }
        });
        layoutSex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image01.setVisibility(image01.GONE);
                image02.setVisibility(image02.GONE);
                image03.setVisibility(image03.VISIBLE);
                gender = "2";
                genderStr = "保密";
            }
        });


        showOrHideSearchBt(true, "完成");
        if (bundle.getString("nameOrSex").equals("1")) {
            showTitleAndBack("昵称");
            layoutSex.setVisibility(layoutSex.GONE);
            layoutName.setVisibility(layoutName.VISIBLE);
            etName.setText(bundle.getString("name"));
            etName.setSelection(etName.getText().length());
            url = "save_nickname";
            flage = 1;
        } else {
            flage = 2;
            showTitleAndBack("性别");
            layoutSex.setVisibility(layoutSex.VISIBLE);
            layoutName.setVisibility(layoutName.GONE);
            url = "save_gender";
            String sex = bundle.getString("sex");
            if (sex.equals("男")) {
                image01.setVisibility(image01.VISIBLE);
                image02.setVisibility(image02.GONE);
                image03.setVisibility(image03.GONE);
                gender = "1";
            }
            if (sex.equals("女")) {
                image01.setVisibility(image01.GONE);
                image02.setVisibility(image02.VISIBLE);
                image03.setVisibility(image03.GONE);
                gender = "0";
            }
            if (sex.equals("保密")) {
                image01.setVisibility(image01.GONE);
                image02.setVisibility(image02.GONE);
                image03.setVisibility(image03.VISIBLE);
                gender = "2";
            }


        }

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    btClearPhone.setVisibility(View.VISIBLE);
                } else {
                    btClearPhone.setVisibility(View.GONE);

                }
            }
        });

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {


        btClearPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
            }
        });

        setclickListener(new ClickListener() {
            @Override
            public void getClickListener() {
                if (flage == 1) {
                    if (etName.getText().toString().trim().length() == 0) {
                        Toast.makeText(ChangePersonalMsgActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();

                    } else {
                        postDate();
                    }
                }
                if (flage == 2) {
                    postDate();
                }


            }
        });

    }

    private void postDate() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("uid", (String) SPUtils.get("uid",""));
        if (flage == 1) {
            map.put("nickname", etName.getText().toString().trim());
        }
        if (flage == 2) {
            map.put("gender", gender);
        }
        map.put("key", Api.KEY);


        mHttpHelper.post(Api.URL + url, map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    Intent intent = new Intent();
                    //                    startActivity(intent);
                    Bundle bundle = new Bundle();
                    if (flage == 1) {
                        bundle.putString("name", etName.getText().toString());
                    } else {
                        bundle.putString("sex", genderStr);
                    }
                    intent.putExtras(bundle);
                    setResult(flage + 10, intent);
                    finish();

                } else {
                    Toast.makeText(ChangePersonalMsgActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

}
