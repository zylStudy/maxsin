package com.renyi.maxsin.module.get;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.utils.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class GetFragment extends Basefragment implements OnTabSelectListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tl_5)
    SlidingTabLayout tl5;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.statu_layout)
    RelativeLayout statuLayout;
    int a = 0;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "推荐", "活动", "资讯"

    };
    private List<String> list = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_get;
    }

    @Override
    protected void initView() {
        StatusBarCompat.getStatusBarHeight(getContext());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) statuLayout.getLayoutParams();
        layoutParams.height =  StatusBarCompat.getStatusBarHeight(getActivity());
        statuLayout.setLayoutParams(layoutParams);

        list.add("推荐");
        list.add("资讯");
        list.add("活动");


        mFragments.add(RecommendFragment.getInstance("1"));
        mFragments.add(NewsFragment.getInstance("2"));
        mFragments.add(ActivityFragment.getInstance("3"));
        FragmentAdapter mAdapter = new FragmentAdapter(getFragmentManager(), mFragments, list);

        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(this);
        tl5.setViewPager(vp);
        tl5.setOnTabSelectListener(this);
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(3);
        setTextViewInlarge(0);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnclickListeners() {
        //  mTitles[mTitles.length]="你好";

    }


    @Override
    public void onTabSelect(int position) {
        a = position;

    }

    private void setTextViewInlarge(int a) {

        for (int i = 0; i < list.size(); i++) {
            if (i == a) {
                tl5.getTitleView(i).setTextSize(16);
            } else {
                tl5.getTitleView(i).setTextSize(13);
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
        setTextViewInlarge(a);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
