package com.renyi.maxsin.module.me;

import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mob.MobSDK;
import com.renyi.maxsin.BuildConfig;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.NewsBean;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ReleaseDetailsActivity extends BaseActivity {

    @BindView(R.id.cover_image)
    ImageView coverImage;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.close_rel)
    RelativeLayout closeRel;
    @BindView(R.id.first_rel)
    RelativeLayout firstRel;
    @BindView(R.id.two_rel)
    RelativeLayout twoRel;
    @BindView(R.id.three_rel)
    RelativeLayout threeRel;
    @BindView(R.id.first_image_hl)
    ImageView firstImageHl;
    @BindView(R.id.two_image_hl)
    ImageView twoImageHl;
    @BindView(R.id.three_image_hl)
    ImageView threeImageHl;
    public final static String CSS_STYLE = "<style>* {font-size:14px;line-height:20px;}p {color:#666666;}</style>";
    NewsBean newsBean;
    String id;

    @Override
    protected int getLayoutId() {
        id = getIntent().getExtras().getString("id");
        return R.layout.activity_release_details;
    }

    @Override
    protected void initView() {
        hideTitleAndBack();
    }

    @Override
    protected void loadData() {
        loadDataFromSer();
    }

    @Override
    protected void setOnClickListeners() {
        firstRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postLoveOrZan("1");
            }
        });
        twoRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postLoveOrZan("3");
            }
        });
        threeRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        closeRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showShare() {

        MobSDK.init(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(newsBean.getData().getTitle());
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://m.mxsyzen.com/news/+" + id + ".html");
        oks.setText(newsBean.getData().getDescription());
        // text是分享文本，所有平台都需要这个字段
        oks.setImageUrl(newsBean.getData().getThumb());
        oks.setUrl("http://m.mxsyzen.com/news/" + id + ".html");
        oks.setSite(" ");
        oks.setSiteUrl("http://m.mxsyzen.com/news/+" + id + ".html");

        // 启动分享GUI
        oks.show(this);
    }

    private void loadDataFromSer() {


        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("content_id", id);

        mHttpHelper.post(Api.URL + "work_info", map, new BaseCallback<NewsBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, NewsBean resultBean) {
                newsBean = resultBean;
                if (resultBean.getCode().equals("800")) {
                    title.setText(resultBean.getData().getTitle());
                    type.setText(resultBean.getData().getCatname());

                    if (resultBean.getData().getZan_status().equals("1")) {
                        firstRel.setClickable(false);
                        firstImageHl.setBackgroundResource(R.mipmap.ic_zan_bg);
                    } else {
                        firstRel.setClickable(true);
                        firstImageHl.setBackgroundResource(R.mipmap.ic_zan_nor_bg);
                    }


                    if (resultBean.getData().getSc_status().equals("1")) {

                        twoImageHl.setBackgroundResource(R.mipmap.ic_love_bg);
                    } else {

                        twoImageHl.setBackgroundResource(R.mipmap.ic_love_nor_bg);
                    }


                    Glide.with(ReleaseDetailsActivity.this).load(resultBean.getData().getThumb()).asBitmap().centerCrop().into(coverImage);

                    webView.loadDataWithBaseURL(null, CSS_STYLE + resultBean.getData().getContent() + "", "text/html", "utf-8", null);
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webView.setVerticalScrollBarEnabled(false);
                    webView.setHorizontalScrollBarEnabled(false);
                     webView.setWebViewClient(new MyWebViewClient());

                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });


    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            // html加载完成之后，添加监听图片的点击js函数

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }

    private void postLoveOrZan(final String flag) {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("user_id", "1");
        map.put("content_id", getIntent().getExtras().getString("id"));
        map.put("action", flag);

        mHttpHelper.post(Api.URL + "user_action", map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    if (flag.equals("1")) {
                        firstRel.setClickable(false);
                        firstImageHl.setBackgroundResource(R.mipmap.ic_zan_bg);


                    } else {
                        twoImageHl.setBackgroundResource(R.mipmap.ic_love_bg);
                    }


                } else {
                    if (flag.equals("3")) {
                        twoImageHl.setBackgroundResource(R.mipmap.ic_love_nor_bg);
                    } else {

                    }
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
        //System.exit(0);
        if (webView != null) {
            webView.setVisibility(View.GONE);
            webView.removeAllViews();
            webView.destroy();
            releaseAllWebViewCallback();
        }
    }

    /**
     * 防止内存泄露
     */
    public void releaseAllWebViewCallback() {
        if (Build.VERSION.SDK_INT < 16) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }
}
