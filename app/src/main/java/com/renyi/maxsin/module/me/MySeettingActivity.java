package com.renyi.maxsin.module.me;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renyi.maxsin.LoginActivity;
import com.renyi.maxsin.MainActivity;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.utils.GlideCacheUtil;
import com.renyi.maxsin.utils.SPUtils;

import butterknife.BindView;

public class MySeettingActivity extends BaseActivity {

    @BindView(R.id.div_rel)
    RelativeLayout divRel;
    @BindView(R.id.clear_tv)
    TextView clearTv;
    @BindView(R.id.clear_rel)
    RelativeLayout clearRel;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.phone_rel)
    RelativeLayout phoneRel;
    @BindView(R.id.change_rel)
    RelativeLayout changeRel;
    @BindView(R.id.about_rel)
    RelativeLayout aboutRel;
    @BindView(R.id.out_rel)
    RelativeLayout outRel;
    private String phone;
    private Dialog dialog;

    @Override
    protected int getLayoutId() {
        phone = getIntent().getExtras().getString("phone");
        return R.layout.activity_my_seetting;
    }

    @Override
    protected void initView() {
        showTitleAndBack("设置");
        clearTv.setText(GlideCacheUtil.getInstance().getCacheSize(this));
        phoneTv.setText(phone);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        divRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySeettingActivity.this, SuggestionActivity.class);
                startActivity(intent);
            }
        });
        aboutRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySeettingActivity.this, AboutAppActivity.class);
                startActivity(intent);
            }
        });
        clearRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideCacheUtil.getInstance().clearImageAllCache(MySeettingActivity.this);
                Toast.makeText(MySeettingActivity.this, "清除缓存成功", Toast.LENGTH_SHORT).show();
                clearTv.setText(GlideCacheUtil.getInstance().getCacheSize(MySeettingActivity.this));

            }
        });
        phoneRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                Intent intent = new Intent(MySeettingActivity.this, MyBindingPhoneNumberActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        changeRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                Intent intent = new Intent(MySeettingActivity.this, MyChangePsdActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        outRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outApp();
            }
        });
    }

    private void outApp() {
        dialog = new Dialog(MySeettingActivity.this, R.style.MyDialogStyle);
        dialog.setContentView(R.layout.dialog_out_login_layout);
        TextView content = (TextView) dialog.findViewById(R.id.tvdialog);
        TextView sure = (TextView) dialog.findViewById(R.id.tvdel);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancle);
        content.setText("确定退出登录");
        content.setTextSize(17);
        content.setTextColor(ContextCompat.getColor(this, R.color.color3));
        sure.setTextColor(ContextCompat.getColor(this, R.color.colorOuteb4161));
        cancel.setTextColor(ContextCompat.getColor(this, R.color.colorOuteb4161));
        dialog.show();
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new SPNewUtils(SeettingActivity.this, "USER_SP_NAME").putString("pwd", "000");
                dialog.dismiss();
                MainActivity.mainActivity.finish();
                MainActivity.mainActivity = null;

                Intent intent = new Intent(MySeettingActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                SPUtils.put("isLogin", true);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        setResult(2, intent);
    }
}
