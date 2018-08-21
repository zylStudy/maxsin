package com.renyi.maxsin.module.maxsin;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.module.maxsin.bean.StudentExampleDetailsBeans;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.DensityUtil;
import com.renyi.maxsin.utils.ShadowDrawable;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentExampleDetailsactivity extends AppCompatActivity implements OnTabSelectListener {
    @BindView(R.id.image_bg)
    ImageView imageBg;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.school)
    TextView school;
    @BindView(R.id.eschool)
    TextView eschool;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.nature)
    TextView nature;
    @BindView(R.id.difficulty)
    TextView difficulty;
    @BindView(R.id.school_tv)
    TextView school_tv;
    @BindView(R.id.education)
    TextView education;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.graduation)
    TextView graduation;
    @BindView(R.id.info_rel)
    RelativeLayout infoRel;
    @BindView(R.id.back_rel)
    RelativeLayout backRel;
    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.vp_contlayout)
    ViewPager vpContlayout;
    String case_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_student_example_detailsactivity);

        case_id = getIntent().getExtras().getString("id");
        ButterKnife.bind(this);
        initView();
        loadData();
        setOnClickListeners();
    }

    private void setViewBindData(StudentExampleDetailsBeans resultBean) {
        if (resultBean != null) {
            StudentExampleDetailsBeans.DataBean dataBean = resultBean.getData();
            setFragmentViewBindData(dataBean);
            school.setText(dataBean.getTitle());
            eschool.setText(dataBean.getKeywords());
            money.setText(dataBean.getJiangxuejin());
            nature.setText(dataBean.getKeywords());
            difficulty.setText("零基础");
            school_tv.setText(dataBean.getLuquyuanxiao());
            address.setText("北京");
            graduation.setText(dataBean.getShenqingxuewei());
            education.setText(dataBean.getShenqingxuewei());


            Glide.with(this).load(dataBean.getThumb()).asBitmap().centerCrop().into(new BitmapImageViewTarget(headImage) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                    circularBitmapDrawable.setCornerRadius(10);
                    headImage.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

    }

    private void setFragmentViewBindData(StudentExampleDetailsBeans.DataBean dataBean) {

        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        if (dataBean.getCaseDetail() != null && !dataBean.getCaseDetail().equals("")) {
            titles.add("Offer展示");
            fragments.add(UniversityDetailsFragment.getInstance(dataBean.getCaseDetail()));
        }
        if (dataBean.getCaseDetail() != null && !dataBean.getCaseDetail().equals("")) {
            titles.add("作品集展示");
            fragments.add(UniversityDetailsFragment.getInstance(dataBean.getCaseDetail()));
        }
        if (dataBean.getCaseDetail() != null && !dataBean.getCaseDetail().equals("")) {
            titles.add("学员故事");
            fragments.add(UniversityDetailsFragment.getInstance(dataBean.getCaseDetail()));
        }

        if (titles.size() != 0) {
            FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
            vpContlayout.setAdapter(adatper);
            tab.setViewPager(vpContlayout);
            tab.setOnTabSelectListener(this);
            vpContlayout.setCurrentItem(0);
            vpContlayout.setOffscreenPageLimit(3);
        }

    }


    private void setOnClickListeners() {
        backRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        loadDataFromSer();
    }

    private void initView() {

        ShadowDrawable shadowDrawable = new ShadowDrawable();
        shadowDrawable.setColor(ContextCompat.getColor(this, R.color.colora))    //shadowcolor
                .setOffsetY(DensityUtil.dip2px(this, 5))    //阴影下偏移--offset of the shadow
                .setRadius(DensityUtil.dip2px(this, 8))     //四角半径--concern of the rectangle
                .setEdgeShadowWidth(DensityUtil.dip2px(this, 8))   //四周阴影半径-- the shadow of each edge of the rectangle
                .setFilterColor(0x56ffffff)                 //中间值，越大阴影越接近设置的值-- the slot to said how close to the shadowcolor
                .setTopMargin(DensityUtil.dip2px(this, 3))  //上间距--top margin
                .setParentHeight(DensityUtil.dip2px(this, 280))  //设置要依附的View的高度 -- the height of parent view
                .attach(infoRel)                                 //要在哪个View上面加阴影-- the shadow parent.※
                .build();

    }

    private void loadDataFromSer() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);

        map.put("case_id", case_id);
        mHttpHelper.post(Api.URL + "case_info", map, new BaseCallback<StudentExampleDetailsBeans>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, StudentExampleDetailsBeans resultBean) {

                if (resultBean.getCode().equals("800")) {
                    setViewBindData(resultBean);

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
        ButterKnife.bind(this).unbind();
    }

    @Override
    public void onTabSelect(int position) {
        vpContlayout.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }
}