package com.renyi.maxsin.module.me;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.view.Html5WebView;

import butterknife.BindView;

public class UserProtocolOrIntroduceActivity extends BaseActivity {
    String url = "";
    // @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.rel)
    RelativeLayout rel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_protocol_or_introduce;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("flage").equals("1")) {
            url = Api.URL + bundle.getString("url");
            showTitleAndBack("功能介绍");
        }
        if (bundle.getString("flage").equals("2")) {
            url = Api.URL + bundle.getString("url");
            showTitleAndBack("用户协议");
        }
        if (bundle.getString("flage").equals("3")) {
            url = Api.URL + bundle.getString("url");
            showTitleAndBack("作品集培训");
        }
        if (bundle.getString("flage").equals("4")) {
            url = bundle.getString("url");
            showTitleAndBack("网页详情");
        }
        webView = new Html5WebView(this);
        webView.setWebChromeClient(new Html5WebChromeClient());

        webView.loadUrl(url);
        rel.addView(webView);
    }

    // 继承 WebView 里面实现的基类
    class Html5WebChromeClient extends Html5WebView.BaseWebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // 顶部显示网页加载进度

        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {

    }


}
