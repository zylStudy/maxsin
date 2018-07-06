package com.renyi.maxsin.module.me;

import android.os.Bundle;
import android.webkit.WebView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.net.Api;

import butterknife.BindView;

public class UserProtocolOrIntroduceActivity extends BaseActivity {
    String url = "";
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_protocol_or_introduce;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("flage").equals("1")) {
            url = bundle.getString("url");
            showTitleAndBack("功能介绍");
        } else {
            url = bundle.getString("url");
            showTitleAndBack("用户协议");
        }

    }

    @Override
    protected void loadData() {
        webView.loadUrl(Api.URL + url);
    }

    @Override
    protected void setOnClickListeners() {

    }


}
