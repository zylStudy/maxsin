package com.renyi.maxsin.module.maxsin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentAdapter;
import com.renyi.maxsin.module.maxsin.bean.BannerBeans;
import com.renyi.maxsin.module.maxsin.bean.TabBeans;
import com.renyi.maxsin.module.me.UserProtocolOrIntroduceActivity;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.renyi.maxsin.view.viewpagergallery.ScalePageTransformer;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class MaxsinFragment extends Fragment implements OnTabSelectListener, ViewPager.OnPageChangeListener {

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
    @BindView(R.id.teacher_rel)
    RelativeLayout teacherRel;
    @BindView(R.id.rank)
    RelativeLayout rankRel;
    @BindView(R.id.product_rel)
    RelativeLayout productRel;

    @BindView(R.id.viewPagerContainer)
    RelativeLayout viewPagerContainer;
    List<String> titles = new ArrayList<>();
    @BindView(R.id.ll_points)
    LinearLayout llPoints;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.add_image)
    ImageView addImage;
    MvpBannerAdapter mvpBannerAdapter;
    List<BannerBeans.DataBean> bannerList = new ArrayList<>();
    int flag = 0;
    List<Fragment> fragments = new ArrayList<>();
    FragmentAdapter adatper;
    private int previousSelectPosition = 1;
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

    }

    protected void loadData() {
        getTab();
        getBanner();
    }

    protected void setTabView(TabBeans tabBeans) {
        if (tabBeans.getData() != null) {
            for (int i = 0; i < tabBeans.getData().size(); i++) {
                titles.add(tabBeans.getData().get(i).getTag_name());
                fragments.add(MaxsinListFragment.getInstance(titles.get(i)));
            }
        }
        adatper = new FragmentAdapter(getChildFragmentManager(), fragments, titles);

        vpContlayout.setAdapter(adatper);
        tab.setViewPager(vpContlayout);
        tab.setOnTabSelectListener(this);
        vpContlayout.setOffscreenPageLimit(titles.size());
        vpContlayout.setCurrentItem(0);
        setTextViewInlarge(0);
        vpContlayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                flag = position;
                setTextViewInlarge(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectTagActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            List<String> tab = data.getExtras().getStringArrayList("tab");
            if (tab != null && tab.size() != 0) {
                for (int j = titles.size()-1; j >3; j--) {
                    titles.remove(j);
                    fragments.remove(j);
                }
                for (int i = 0; i < tab.size(); i++) {
                    fragments.add(MaxsinListFragment.getInstance(tab.get(tab.size() - 1)));
                    titles.add(tab.get(i));
                }
                notifyDataSetChanged();
            }
        }

        if (requestCode == 0 && resultCode == 2) {

            for (int i = titles.size()-1; i >3 ; i--) {
                titles.remove(i);
                fragments.remove(i);
            }
            notifyDataSetChanged();
        }
    }

    protected void notifyDataSetChanged() {
        tab.notifyDataSetChanged();

        adatper.notifyDataSetChanged();
        setTextViewInlarge(0);
        vpContlayout.setCurrentItem(0);
        vpContlayout.setOffscreenPageLimit(titles.size());
    }

    protected void setOnclickListeners() {
        caseRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(StudentExampleactivity.class, "");
            }
        });
        rankRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(UniversitiesRankingActivity.class, "");
            }
        });
        teacherRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(TeacherActivity.class, "");
            }
        });
        productRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flage", "3");
                bundle.putString("url", "appH5");
                Intent intent = new Intent(getActivity(), UserProtocolOrIntroduceActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(ComprehensiveSearchActivity.class, "");
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
        flag = position;
        setTextViewInlarge(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    private void getBanner() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        mHttpHelper.post(Api.URL + "banner_list", map, new BaseCallback<BannerBeans>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, BannerBeans resultBean) {
                if (resultBean.getCode().equals("800")) {
                    setBannerImageVew(resultBean);
                } else {

                }

            }


            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void getTab() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("u_id", (String) SPUtils.get("uid", ""));
        mHttpHelper.post(Api.URL + "showTags", map, new BaseCallback<TabBeans>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, TabBeans resultBean) {
                if (resultBean.getCode().equals("800")) {
                    setTabView(resultBean);
                } else {

                }

            }


            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


    private void setBannerImageVew(BannerBeans resultBean) {

        if (resultBean.getData() != null) {
            bannerList = resultBean.getData();
            mViewPager.setClipChildren(false);
            viewPagerContainer.setClipChildren(false);
            mViewPager.setPageTransformer(true, new ScalePageTransformer());
            mViewPager.setOffscreenPageLimit(3);

            mvpBannerAdapter = new MvpBannerAdapter();
            mViewPager.setAdapter(mvpBannerAdapter);
            mvpBannerAdapter.setOnNotifyChanged();
            mViewPager.setCurrentItem(1);

            mViewPager.addOnPageChangeListener(this);


            for (int i = 0; i < bannerList.size(); i++) {

                View view = new View(getActivity());
                view.setBackgroundResource(R.mipmap.ic_splash_nor);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(15, 15);
                lp.leftMargin = 15;
                view.setLayoutParams(lp);
                llPoints.addView(view);
            }
            llPoints.getChildAt(1).setBackgroundResource(R.mipmap.ic_splash_hl);
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    llPoints.getChildAt(previousSelectPosition).setBackgroundResource(
                            R.mipmap.ic_splash_nor);
                    llPoints.getChildAt(position % bannerList.size()).setBackgroundResource(
                            R.mipmap.ic_splash_hl);
                    previousSelectPosition = position % bannerList.size();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class MvpBannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        public void setOnNotifyChanged() {
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_banner_layout, null);

            final ImageView cover_image = view.findViewById(R.id.cover_image);
            Glide.with(getActivity()).load(bannerList.get(position % bannerList.size()).getImg_src()).asBitmap().centerCrop().into(new BitmapImageViewTarget(cover_image) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    circularBitmapDrawable.setCornerRadius(12);
                    cover_image.setImageDrawable(circularBitmapDrawable);
                }
            });

            container.addView(view);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.bind(getActivity()).unbind();
    }


    private void setTextViewInlarge(int a) {

        for (int i = 0; i < titles.size(); i++) {
            if (i == a) {
                tab.getTitleView(i).setTextSize(16);
            } else {
                tab.getTitleView(i).setTextSize(13);
            }

        }

    }
}