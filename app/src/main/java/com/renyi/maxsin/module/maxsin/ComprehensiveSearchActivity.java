package com.renyi.maxsin.module.maxsin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.maxsin.bean.KeyWords;
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

public class ComprehensiveSearchActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.tl_5)
    SlidingTabLayout tl5;
    @BindView(R.id.vp)
    ViewPager vp;
    int flag = 0;
    @BindView(R.id.search_rellayout)
    RelativeLayout search;
    @BindView(R.id.cancle_tv)
    TextView closeRel;

    @BindView(R.id.edit_info)
    EditText editInfo;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<String> list = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_comprehensive_search;
    }

    @Override
    protected void initView() {
        editInfo.setHint("请输入相关内容");
        hideTitleAndBack();


    }

    @Override
    protected void loadData() {
        loadDataFromSer();
    }

    @Override
    protected void setOnClickListeners() {
        closeRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editInfo.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ComprehensiveSearchActivity.this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent("broadcast.search");
                    Bundle bundle = new Bundle();
                    bundle.putInt("flag", flag);
                    bundle.putString("keywords", editInfo.getText().toString().trim());
                    intent.putExtras(bundle);
                    sendBroadcast(intent);
                }


            }
        });
    }

    @Override
    public void onTabSelect(int position) {
        flag = position;

    }

    private void setTextViewInlarge() {

        for (int i = 0; i < list.size(); i++) {
            if (i == flag) {
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
        flag = position;
        setTextViewInlarge();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void loadDataFromSer() {


        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);

        mHttpHelper.post(Api.URL + "showWords", map, new BaseCallback<KeyWords>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, KeyWords resultBean) {

                if (resultBean.getCode().equals("800")) {
                    initTabView(resultBean);

                } else {

                }

            }


            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void initTabView(KeyWords resultBean) {
        list.add("导师");
        list.add("作品");
        list.add("资讯");
        list.add("活动");

        mFragments.add(ComprehensiveSearchTeacherListFragment.getInstance((ArrayList) resultBean.getData().getDaoshilist()));
        mFragments.add(ComprehensiveSearchProductListFragment.getInstance((ArrayList) resultBean.getData().getZuopinlist()));
        mFragments.add(ComprehensiveSearchNewsListFragment.getInstance((ArrayList) resultBean.getData().getZixunlist()));
        mFragments.add(ComprehensiveSearchActivityListFragment.getInstance((ArrayList) resultBean.getData().getHuodonglist()));


        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, list);

        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(this);
        tl5.setViewPager(vp);
        tl5.setOnTabSelectListener(this);
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(4);
    }

}
