package com.renyi.maxsin.module.get;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.mob.MobSDK;
import com.renyi.maxsin.BuildConfig;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.ActivityBean;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ActivityDetailsActivity extends BaseActivity {

    @BindView(R.id.cover_image)
    ImageView coverImage;
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.first_rel)
    RelativeLayout firstRel;
    @BindView(R.id.two_rel)
    RelativeLayout twoRel;
    @BindView(R.id.three_rel)
    RelativeLayout threeRel;
    @BindView(R.id.first_image_hl)
    ImageView firstImageHl;
    @BindView(R.id.post_tv)
    TextView postTv;
    @BindView(R.id.tImage)
    ImageView tImage;
    @BindView(R.id.tname)
    TextView tname;
    @BindView(R.id.position)
    TextView position;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.image5)
    ImageView image5;
    private List<ImageView> imageViewList = new ArrayList<>();
    public final static String CSS_STYLE = "<style>* {font-size:14px;line-height:20px;}p {color:#666666;}</style>";
    ActivityBean activityBean;
    String id;

    @Override
    protected int getLayoutId() {
        id = getIntent().getExtras().getString("id");
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        showTitleAndBack("活动详情");
        imageViewList.add(image1);
        imageViewList.add(image2);
        imageViewList.add(image3);
        imageViewList.add(image4);
        imageViewList.add(image5);


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
                postLoveOrZan("2");
            }
        });
        postTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postJoin();
            }
        });
        twoRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showShare();
            }
        });
    }

    private void showShare() {

        MobSDK.init(ActivityDetailsActivity.this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(activityBean.getData().getTitle());
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://m.mxsyzen.com/news/+" + id + ".html");
        oks.setText(activityBean.getData().getDescription());
        // text是分享文本，所有平台都需要这个字段
        oks.setImageUrl(activityBean.getData().getThumb());
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
        map.put("user_id", (String) SPUtils.get("uid", ""));
        map.put("content_id", id);

        mHttpHelper.post(Api.URL + "activity_info", map, new BaseCallback<ActivityBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ActivityBean resultBean) {
                activityBean = resultBean;
                if (resultBean.getCode().equals("800")) {
                    title.setText(resultBean.getData().getTitle());

                    if (resultBean.getData().getSpeaker().isEmpty()) {
                        tname.setText("美行思远");
                    } else {
                        tname.setText(resultBean.getData().getSpeaker());
                    }
                    if (resultBean.getData().getAddress().isEmpty()) {
                        position.setText("线上");
                    } else {
                        position.setText(resultBean.getData().getAddress());
                    }
                    time.setText(resultBean.getData().getActstart() + "至" + resultBean.getData().getActend());

                    if (resultBean.getData().getSpeakerphoto().isEmpty()) {
                        Glide.with(ActivityDetailsActivity.this).load(resultBean.getData().getThumb()).into(tImage);
                    } else {
                        Glide.with(ActivityDetailsActivity.this).load(resultBean.getData().getSpeakerphoto()).into(tImage);

                    }

                    if (resultBean.getData().getShoucang_status().equals("1")) {

                        firstImageHl.setBackgroundResource(R.mipmap.ic_love_bg);
                    } else {

                        firstImageHl.setBackgroundResource(R.mipmap.ic_love_nor_bg);
                    }
                    if (resultBean.getData().getBaoming_status().equals("0")) {
                        postTv.setClickable(true);
                        postTv.setBackgroundResource(R.drawable.shape_bt_hl);
                        postTv.setText("立即报名");
                    } else if (resultBean.getData().getBaoming_status().equals("1")) {
                        postTv.setBackgroundResource(R.drawable.shape_bt_nor);
                        postTv.setClickable(false);
                        postTv.setText("已报名");
                    } else {
                        postTv.setBackgroundResource(R.drawable.shape_bt_nor);
                        postTv.setClickable(false);
                        postTv.setText("活动结束");
                    }
                    Glide.with(ActivityDetailsActivity.this).load(resultBean.getData().getThumb()).asBitmap().centerCrop().into(new BitmapImageViewTarget(coverImage) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularBitmapDrawable.setCornerRadius(12);
                            coverImage.setImageDrawable(circularBitmapDrawable);
                        }
                    });


                    for (int i = 0; i < resultBean.getData().getTouxiang_img().size(); i++) {
                        final ImageView imageView = imageViewList.get(i);

                        Glide.with(ActivityDetailsActivity.this).load(resultBean.getData().getTouxiang_img().get(i).getImg()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                imageView.setImageDrawable(circularBitmapDrawable);
                            }
                        });
                    }

                    webView.loadDataWithBaseURL(null, CSS_STYLE + resultBean.getData().getContent() + "", "text/html", "utf-8", null);
                    webView.setVerticalScrollBarEnabled(false);
                    webView.setHorizontalScrollBarEnabled(false);

                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void postJoin() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("user_id", (String) SPUtils.get("uid", ""));
        map.put("content_id", getIntent().getExtras().getString("id"));

        mHttpHelper.post(Api.URL + "baoming", map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    postTv.setText("已报名");
                    postTv.setBackgroundResource(R.drawable.shape_bt_nor);
                    postTv.setClickable(false);
                    Toast.makeText(ActivityDetailsActivity.this, "报名成功", Toast.LENGTH_SHORT).show();

                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });


    }


    private void postLoveOrZan(final String flag) {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("user_id", (String) SPUtils.get("uid", ""));
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

                    firstImageHl.setBackgroundResource(R.mipmap.ic_love_bg);


                } else {

                    firstImageHl.setBackgroundResource(R.mipmap.ic_love_nor_bg);

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