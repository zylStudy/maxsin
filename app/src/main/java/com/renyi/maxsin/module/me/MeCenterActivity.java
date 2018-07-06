package com.renyi.maxsin.module.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.module.me.bean.AppBarStateChangeListener;
import com.renyi.maxsin.module.release.ReleaseInfoAndWorksFragment;
import com.renyi.maxsin.module.release.bean.MeCenterBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.StatusBarCompat;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MeCenterActivity extends AppCompatActivity implements OnTabSelectListener {


    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.vp_contlayout)
    ViewPager vpContlayout;
    @BindView(R.id.headImageView)
    ImageView headImageView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.follow)
    TextView follow;

    @BindView(R.id.tv01)
    TextView popularity;
    @BindView(R.id.tv02)
    TextView follower;
    @BindView(R.id.tv03)
    TextView fans;
    @BindView(R.id.letter)
    TextView letter;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.collaps)
    CollapsingToolbarLayout collaps;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    String is_fans = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_center);
        StatusBarCompat.translucentStatusBar(this);
        ButterKnife.bind(this);
        setRoleLayout();
        loadDataString();

        tbToolbar.setContentInsetsRelative(R.styleable.Toolbar_contentInsetStart, R.styleable.Toolbar_contentInsetEnd);
        tbToolbar.setNavigationIcon(R.mipmap.ic_back_bg);
        setSupportActionBar(tbToolbar);
        collaps.setCollapsedTitleGravity(Gravity.CENTER);//设置收缩后标题的位置
        collaps.setTitle("_");
        tbToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        appbarlayout
                .addOnOffsetChangedListener(new AppBarStateChangeListener() {
                    @Override
                    public void onStateChanged(AppBarLayout appBarLayout, State state) {
                        if (state == State.EXPANDED) {

                            //展开状态
                            collaps.setTitle("");
                        } else if (state == State.COLLAPSED) {
                            //折叠状态
                            collaps.setTitle("个人中心  ");
                            // toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
                        } else {
                            //中间状态
                            collaps.setTitle("");
                            //toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);

                        }
                    }
                });
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_fans.equals("1")) {
                    postDate("1");
                } else {
                    postDate("0");
                }
            }
        });

    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private void setRoleLayout() {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        titles.add("他的发布");
        titles.add("他的作品");

        fragments.add(ReleaseInfoAndWorksFragment.getInstance("1"));
        fragments.add(ReleaseInfoAndWorksFragment.getInstance("2"));


        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);

        vpContlayout.setAdapter(adatper);
        //将TabLayout和ViewPage r关联起来。
        vpContlayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTextViewInlarge(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab.setViewPager(vpContlayout);
        tab.setOnTabSelectListener(this);
        vpContlayout.setCurrentItem(0);
        setTextViewInlarge(0);


    }


    private void setTextViewInlarge(int position) {

        for (int i = 0; i < 2; i++) {
            if (i == position) {
                tab.getTitleView(i).setTextSize(16);
            } else {
                tab.getTitleView(i).setTextSize(14);
            }

        }

    }

    private void loadDataString() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("id", "1");
        map.put("my_id", "1");

        mHttpHelper.post(Api.URL + "other_view", map, new BaseCallback<MeCenterBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, MeCenterBean resultBean) {

                if (resultBean.getCode().equals("800")) {

                    MeCenterBean.DataBean resultBeanData = resultBean.getData();
                    tvName.setText(resultBeanData.getUser_name());
                    popularity.setText(resultBeanData.getRenqi());
                    fans.setText(resultBeanData.getFans_num());
                    follower.setText(resultBeanData.getFocus_num());
                    info.setText("不忘初心，感恩常在");
                    is_fans = resultBeanData.getIs_fans();
                    setFollowBut(resultBeanData.getIs_fans());

                    Glide.with(MeCenterActivity.this).load(resultBeanData.getHead_url()).asBitmap().centerCrop().into(new BitmapImageViewTarget(headImageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            //    circularBitmapDrawable.setCornerRadius(5);设置图片圆角
                            headImageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //通过inflater对象将自己写的资源文件转换成menu对象
        //参数1代表需要创建的菜单，参数2代表将菜单设置到对应的menu上
        getMenuInflater().inflate(R.menu.menu_tab_layout, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tab_add:
                startActivity(new Intent(MeCenterActivity.this, MyMessageActivity.class));
                return true;
        }

        return false;
    }

    private void postDate(final String flage) {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("my_id", "1");
        map.put("key", Api.KEY);
        map.put("other_id", "1");
        String url = "";
        if (flage.equals("1")) {
            url = "un_focus";
        } else {
            url = "focus_other";
        }
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
                    if (flage.equals("1")) {
                        is_fans="0";
                    }else{
                        is_fans="1";
                    }
                    setFollowBut(is_fans);
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void setFollowBut(String s) {
        if (s.equals("1")) {
            follow.setBackgroundResource(R.drawable.shape_bt_hl);
            follow.setTextColor(ContextCompat.getColor(MeCenterActivity.this, R.color.white));
        } else {
            follow.setBackgroundResource(R.drawable.shap_null_center_layout);
            follow.setTextColor(ContextCompat.getColor(MeCenterActivity.this, R.color.colorOuteb4161));
        }
    }
}
