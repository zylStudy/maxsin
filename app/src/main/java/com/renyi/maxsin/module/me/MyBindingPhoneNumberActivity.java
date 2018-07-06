package com.renyi.maxsin.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;

import butterknife.BindView;

public class MyBindingPhoneNumberActivity extends BaseActivity {
    @BindView(R.id.phone)
    TextView phoneTv;
    @BindView(R.id.bt_change)
    TextView btChange;
    private String phone;
    public static MyBindingPhoneNumberActivity myBindingPhoneNumberActivity = null;

    @Override
    protected int getLayoutId() {
        myBindingPhoneNumberActivity = this;
        phone = getIntent().getExtras().getString("phone");
        return R.layout.activity_my_binding_phone_number;
    }

    @Override
    protected void initView() {
        showTitleAndBack("绑定手机号");
        phoneTv.setText(phone);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                Intent intent = new Intent(MyBindingPhoneNumberActivity.this, MyVerificationPhoneActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }


}
