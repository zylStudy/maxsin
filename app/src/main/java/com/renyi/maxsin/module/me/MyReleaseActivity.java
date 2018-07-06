package com.renyi.maxsin.module.me;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.release.MyReleaseInfoAndWorksFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyReleaseActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.tl_5)
    SlidingTabLayout tl5;
    @BindView(R.id.vp)
    ViewPager vp;
    int a = 0;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "内容", "作品"

    };
    private List<String> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_activity;
    }

    @Override
    protected void initView() {
        showTitleAndBack("我的发布");
        list.add("内容");
        list.add("作品");


        mFragments.add(MyReleaseInfoAndWorksFragment.getInstance("1"));
        mFragments.add(MyReleaseInfoAndWorksFragment.getInstance("2"));


        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, list);

        vp.setAdapter(mAdapter);
   vp.addOnPageChangeListener(this);
        tl5.setViewPager(vp);
        tl5.setOnTabSelectListener(this);
        vp.setCurrentItem(0);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {

    }

    @Override
    public void onTabSelect(int position) {
        a = position;
        vp.setCurrentItem(position);

    }

    private void setTextViewInlarge() {

        for (int i = 0; i < list.size(); i++) {
            if (i == a) {
                tl5.getTitleView(i).setTextSize(17);
            } else {
                tl5.getTitleView(i).setTextSize(16);
            }

        }

    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        a = position;
        setTextViewInlarge();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
