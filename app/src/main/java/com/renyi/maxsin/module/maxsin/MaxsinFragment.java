package com.renyi.maxsin.module.maxsin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.module.me.MeCenterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class MaxsinFragment extends Fragment implements OnTabSelectListener {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.search_image)
    ImageView searchImage;
    @BindView(R.id.vp_contlayout)
    ViewPager vpContlayout;
    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.case_rel)
    RelativeLayout caseRel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maxsin, null);

        setHasOptionsMenu(true);

        ButterKnife.bind(this, view);
        initView();
        loadData();
        setOnclickListeners();
        return view;
    }



    protected void initView() {
        tv.setText("首页");

        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        titles.add("他的发布");
        titles.add("他的作品");

        fragments.add(MaxsinListFragment.getInstance("1", "1"));
        fragments.add(MaxsinListFragment.getInstance("2", "1"));
        FragmentAdapter adatper = new FragmentAdapter(getChildFragmentManager(), fragments, titles);

        vpContlayout.setAdapter(adatper);
        tab.setViewPager(vpContlayout);
        tab.setOnTabSelectListener(this);
        vpContlayout.setCurrentItem(0);
    }


    protected void loadData() {

    }


    protected void setOnclickListeners() {
        caseRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(MeCenterActivity.class, "");
            }
        });
    }

    protected void readyGo(Class<?> clazz, String id) {
        Intent intent = new Intent(getActivity(), clazz);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtras(bundle);
        startActivity(intent);

    }
    @Override
    public void onTabSelect(int position) {
        vpContlayout.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(getActivity()).unbind();
    }

}
