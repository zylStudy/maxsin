package com.renyi.maxsin.module.mvp;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentVPagerAdapter;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.get.ActivityFragment;
import com.renyi.maxsin.module.mvp.bean.MvpRecommendBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.view.DecoratorViewPager;
import com.renyi.maxsin.view.galleryview.CardScaleHelper;
import com.renyi.maxsin.view.galleryview.SpeedRecyclerView;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

;

/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class MvpFragment extends Basefragment implements ViewPager.OnPageChangeListener {

    List<MvpRecommendBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.blurView)
    ImageView mBlurView;
    @BindView(R.id.recyclerView)
    SpeedRecyclerView recyclerView;
    @BindView(R.id.newrecyclerView)
    RecyclerView newrecyclerView;

    @BindView(R.id.tab_line01)
    View tabLine01;
    @BindView(R.id.tab_line02)
    View tabLine02;
    @BindView(R.id.vp)
    DecoratorViewPager vp;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;


    private CardScaleHelper mCardScaleHelper = null;
    CardsAdapter adapter;
    CommonAdapter commonAdapter;
    private List<String> titleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mvp;
    }

    @Override
    protected void initView() {
        titleList.add("人气最旺");
        titleList.add("粉丝最多");

        mFragments.add(ActivityFragment.getInstance("3"));
        mFragments.add(FansNumFragment.getInstance("2"));
        FragmentVPagerAdapter mAdapter = new FragmentVPagerAdapter(getFragmentManager(), mFragments);

        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(this);
        vp.setCurrentItem(0);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        Glide.with(getActivity())
                .load(R.mipmap.ic_me_bg)
                .asBitmap()
                .into(new BitmapImageViewTarget(mBlurView));
        loadDataFromSer();


    }

    private void initNewProjectRecl() {


        newrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        commonAdapter = new CommonAdapter<MvpRecommendBean.DataBean>(getActivity(), R.layout.item_mvp_recom_follow_list, list) {
            @Override
            protected void convert(ViewHolder viewHolder, MvpRecommendBean.DataBean item, int position) {
                //    viewHolder.setText(R.id.type, item.getCatname());
                viewHolder.setText(R.id.name, item.getNickname());


                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getCover_img(), 10);
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.head_image, item.getHead_url(), 10);

                //viewHolder.setText(R.id.share, item.getC_name());


            }
        };
        if (newrecyclerView != null) {
            newrecyclerView.setAdapter(commonAdapter);

        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnclickListeners() {
        tvTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
                setTextViewInlarge(0);
            }
        });
        tvTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
                setTextViewInlarge(1);
            }
        });
    }

    private void setTextViewInlarge(int position) {
        if (position == 0) {
            tabLine01.setVisibility(tabLine01.VISIBLE);
            tabLine02.setVisibility(tabLine02.INVISIBLE);
            tvTitle1.setTextColor(ContextCompat.getColor(getActivity(), R.color.color6));
            tvTitle2.setTextColor(ContextCompat.getColor(getActivity(), R.color.color9));
        }
        if (position == 1) {
            tabLine01.setVisibility(tabLine01.INVISIBLE);
            tabLine02.setVisibility(tabLine02.VISIBLE);
            tvTitle1.setTextColor(ContextCompat.getColor(getActivity(), R.color.color9));
            tvTitle2.setTextColor(ContextCompat.getColor(getActivity(), R.color.color6));
        }
    }

    private void loadDataFromSer() {


        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("u_id", "1");

        mHttpHelper.post(Api.URL + "tuijian", map, new BaseCallback<MvpRecommendBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, MvpRecommendBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    list = resultBean.getData();
                    adapter = new CardsAdapter(list, getActivity());
                    recyclerView.setAdapter(adapter);
                    mCardScaleHelper = new CardScaleHelper();
                    mCardScaleHelper.setCurrentItemPos(1);
                    //                    mCardScaleHelper.setPagePadding(10);
                    //                    mCardScaleHelper.setScale(0.8f);
                    //                    mCardScaleHelper.setShowLeftCardWidth(30);
                    mCardScaleHelper.attachToRecyclerView(recyclerView);
                    adapter.notifyDataSetChanged();

                    initNewProjectRecl();
                    commonAdapter.notifyDataSetChanged();

                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
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


}
