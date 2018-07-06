package com.renyi.maxsin.module.me;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.view.DecoratorViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//com.renyi.maxsin.view.swipe.CstViewPager
public class MyMessageActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.tl_5)
    SlidingTabLayout tl5;

    int a = 0;
    @BindView(R.id.vp)
    DecoratorViewPager vp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "私信", "课程消息", "系统消息"

    };
    private List<String> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initView() {
        showTitleAndBack("收件箱");
        list.add("私信");
        list.add("课程消息");
        list.add("系统消息");

        mFragments.add(MessagePrivateLetterFragment.getInstance("1"));
        mFragments.add(MessageCoursesFragment.getInstance("2"));
        mFragments.add(MessageSystemFragment.getInstance("1"));
        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, list);

        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(this);
        vp.setOffscreenPageLimit(3);
        tl5.setViewPager(vp);
        tl5.setOnTabSelectListener(this);
        vp.setCurrentItem(0);

        tl5.showDot(0);
        tl5.setMsgMargin(0, 50, 3);
        tl5.showDot(1);
        tl5.setMsgMargin(1, 30, 3);
        tl5.showDot(2);
        tl5.setMsgMargin(2, 30, 3);
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
        tl5.hideMsg(position);
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
