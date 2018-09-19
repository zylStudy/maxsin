package com.renyi.maxsin.module.Study;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.login.ResultBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class StudyActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {
    List<Fragment> fragments = new ArrayList<>();

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.study_rel)
    RelativeLayout studyRel;
    @BindView(R.id.empty_rel)
    RelativeLayout emptyRel;
    List<String> titles = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initView() {
        showTitleAndBack("学情管理");

        if (getIntent().getExtras().getString("flag").equals("2")) {
            getRoal();
            emptyRel.setVisibility(emptyRel.GONE);
            studyRel.setVisibility(emptyRel.VISIBLE);
        } else {
            emptyRel.setVisibility(emptyRel.VISIBLE);
            studyRel.setVisibility(emptyRel.GONE);
        }


    }

    private void setRoleLayout() {


//        titles.add("课程表");
        titles.add("简报");
        titles.add("课程");
        titles.add("我的学习");

//        fragments.add(new ScheduleFragment());
        fragments.add(BriefingFragment.getInstance());
        fragments.add(CourseFragment.getInstance());
        fragments.add(MyStudyFragment.getInstance());


        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);

        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        viewPager.addOnPageChangeListener(this);
        tab.setViewPager(viewPager);
        tab.setOnTabSelectListener(this);
        viewPager.setCurrentItem(0);
        setTextViewInlarge(0);

    }

    private void setTextViewInlarge(int position) {

        for (int i = 0; i < titles.size(); i++) {
            if (i == position) {
                tab.getTitleView(i).setTextSize(16);
            } else {
                tab.getTitleView(i).setTextSize(14);
            }

        }

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {

    }


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

    @Override
    public void onTabSelect(int position) {
        setTextViewInlarge(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    private void getRoal() {
        Map<String, String> map = new HashMap<>();
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        map.put("u_id", getIntent().getExtras().getString("sid"));
        map.put("key", Api.KEY);


        mHttpHelper.post(Api.URL + "is_stu", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    if (resultBean.getData().getIs_stu().equals("2")) {
                        setRoleLayout();
                        emptyRel.setVisibility(emptyRel.GONE);
                        studyRel.setVisibility(emptyRel.VISIBLE);
                    } else {
                        emptyRel.setVisibility(emptyRel.VISIBLE);
                        studyRel.setVisibility(emptyRel.GONE);
                    }

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }
}
