package com.renyi.maxsin.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.utils.ToAppStoreUtils;

import butterknife.BindView;

public class AboutAppActivity extends BaseActivity {

    @BindView(R.id.function_rel)
    RelativeLayout functionRel;
    @BindView(R.id.user_rel)
    RelativeLayout userRel;
    @BindView(R.id.comment_rel)
    RelativeLayout commentRel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_app;
    }

    @Override
    protected void initView() {
        showTitleAndBack("关于任意连接");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        commentRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToAppStoreUtils.judge(AboutAppActivity.this, ToAppStoreUtils.getIntent(AboutAppActivity.this))) {
                    ToAppStoreUtils.start(AboutAppActivity.this);
                } else {
                    Toast.makeText(AboutAppActivity.this, "您的手机中无应用市场", Toast.LENGTH_SHORT).show();
                }
            }
        });
        functionRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flage", "1");
                bundle.putString("url", "app_introduce");
                Intent intent = new Intent(AboutAppActivity.this, UserProtocolOrIntroduceActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        userRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flage", "2");
                bundle.putString("url", "user_agree");
                Intent intent = new Intent(AboutAppActivity.this, UserProtocolOrIntroduceActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


}
