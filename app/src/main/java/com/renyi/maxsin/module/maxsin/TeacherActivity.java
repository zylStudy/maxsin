package com.renyi.maxsin.module.maxsin;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeacherActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.tl_5)
    SlidingTabLayout tl5;
    @BindView(R.id.vp)
    ViewPager vp;
    int a = 0;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.close_rel)
    RelativeLayout closeRel;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher;
    }

    @Override
    protected void initView() {
        hideTitleAndBack();
        list.add("视觉科系");
        list.add("服装时尚科系");
        list.add("工业科系");
        list.add("空间管理科系");
        list.add("纯艺术科系");
        list.add("数字媒体科系");

        for (int i = 1; i <= list.size(); i++) {
            mFragments.add(TeacherListFragment.getInstance(i + ""));
        }

        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, list);

        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(this);
        tl5.setViewPager(vp);
        tl5.setOnTabSelectListener(this);
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(6);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        closeRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onTabSelect(int position) {
        a = position;

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
