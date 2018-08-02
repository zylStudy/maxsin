package com.renyi.maxsin.module.mvp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.FragmentVPagerAdapter;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.renyi.maxsin.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.module.me.MeCenterActivity;
import com.renyi.maxsin.module.me.ReleaseDetailsActivity;
import com.renyi.maxsin.module.mvp.bean.MvpRecommendBean;
import com.renyi.maxsin.module.mvp.bean.PopularBeans;
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

import static com.renyi.maxsin.R.id.viewpager;


/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class MvpFirstPageFragment extends Basefragment implements ViewPager.OnPageChangeListener {

    List<MvpRecommendBean.DataBean> listAll = new ArrayList<>();
    List<PopularBeans.DataBean.ListBean> popularListAll = new ArrayList<>();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.viewpager_include)
    RelativeLayout viewpagerInclude;
    int page = 1;

    PopularBeans popularBeans;
    CommonAdapter commonAdapter;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    LoadMoreWrapper mLoadMoreWrapper;
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    View tabLine01;
    View tabLine02;
    TextView tvTitle1;
    TextView tvTitle2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_firstmvp;
    }

    @Override
    protected void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        //        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //        //        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        commonAdapter = new CommonAdapter<PopularBeans.DataBean.ListBean>(getActivity(), R.layout.item_mvp_new_product_list, popularListAll) {

            @Override
            protected void convert(final ViewHolder viewHolder, final PopularBeans.DataBean.ListBean item, final int position) {
                viewHolder.setText(R.id.name, item.getNickname());
                viewHolder.setText(R.id.title, item.getTitle());
                viewHolder.setText(R.id.time, item.getAdd_time());
                viewHolder.setText(R.id.type, item.getTag_name());
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getCover_img(), 10);
                viewHolder.setImageViewNetUrl(R.id.head_image, item.getHead_url());

                if (item.getIs_focus().equals("0")) {
                    viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_yes_bg);

                } else {
                    viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_no_bg);

                }

                viewHolder.setOnClickListener(R.id.followimage, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getIs_focus().equals("1")) {
                            viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_yes_bg);
                            postFollowDate("1", popularListAll.get(position - 2).getId());
                            popularListAll.get(position - 2).setIs_focus("0");
                        } else {
                            viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_no_bg);
                            postFollowDate("2", popularListAll.get(position - 2).getId());
                            popularListAll.get(position - 2).setIs_focus("1");
                        }


                    }
                });

                viewHolder.setOnClickListener(R.id.cover_image, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        readyGo(ReleaseDetailsActivity.class, popularListAll.get(position - 2).getZp_id());
                    }
                });
                viewHolder.setOnClickListener(R.id.head_image, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        readyGo(MeCenterActivity.class, popularListAll.get(position - 2).getId());
                    }
                });

            }
        };
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);

    }


    @Override
    protected void loadData() {
        loadDataFromSer();
        loadPopularDataFromSer();
    }

    protected void readyGo(Class<?> clazz, String id) {
        Intent intent = new Intent(getActivity(), clazz);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    protected void setOnclickListeners() {


    }


    private void loadDataFromSer() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("u_id", (String) SPUtils.get("uid",""));

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
                    listAll.addAll(resultBean.getData());
                    commonAdapter.notifyDataSetChanged();
                    initHeaderAndFooter();

                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void loadPopularDataFromSer() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("u_id",  (String) SPUtils.get("uid",""));
        map.put("page", page + "");

        mHttpHelper.post(Api.URL + "newlist", map, new BaseCallback<PopularBeans>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, PopularBeans resultBean) {

                if (resultBean.getCode().equals("800")) {
                    popularBeans = resultBean;
                    popularListAll.addAll(popularBeans.getData().getList());
                    commonAdapter.notifyDataSetChanged();
                    mLoadMoreWrapper.notifyDataSetChanged();


                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }


        });
    }

    private void postFollowDate(String opt, String uid) {
        System.out.println("-------------------"+uid);
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("my_id", (String) SPUtils.get("uid",""));
        map.put("key", Api.KEY);
        map.put("other_id", uid);
        String url = "";
        if (opt.equals("1")) {
            url = "un_focus";
        } else {
            url = "focus_other";
        }
        mHttpHelper.post(Api.URL + url, map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {
                if (resultBean.getCode().equals("800")) {
                    //                    if (opt.equals("1")) {
                    //                        resultBeanData.getData().get(position).setFlag("0");
                    //                    } else {
                    //                        resultBeanData.getData().get(position).setFlag("1");
                    //                    }
                    commonAdapter.notifyDataSetChanged();
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private ViewPager mViewPager;
    private RelativeLayout viewPagerContainer;

    private void initHeaderAndFooter() {


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.include_mvp_header_layout, null);
        mViewPager = view.findViewById(viewpager);
        viewPagerContainer = view.findViewById(R.id.viewPagerContainer);
        mViewPager.setClipChildren(false);
        viewPagerContainer.setClipChildren(false);
        recyclerView.setClipChildren(false);


        mViewPager.setPageTransformer(true, new ScalePageTransformer());
        mViewPager.setOffscreenPageLimit(4);

        MvpBannerAdapter mvpBannerAdapter = new MvpBannerAdapter();

        mViewPager.setAdapter(mvpBannerAdapter);
        mHeaderAndFooterWrapper.addHeaderView(view);
        mViewPager.setCurrentItem(1);

        mViewPager.addOnPageChangeListener(this);
        View popularityView = LayoutInflater.from(getActivity()).inflate(R.layout.include_mvp_popularity_list, null);

        final ViewPager popularViewPager = popularityView.findViewById(R.id.viewpager_popularity);
        tabLine01 = popularityView.findViewById(R.id.tab_line01);
        tabLine02 = popularityView.findViewById(R.id.tab_line02);
        tvTitle1 = popularityView.findViewById(R.id.tv_title1);
        tvTitle2 = popularityView.findViewById(R.id.tv_title2);
        mFragments.add(FansNumFragment.getInstance("1"));
        mFragments.add(FansNumFragment.getInstance("2"));
        FragmentVPagerAdapter mAdapter = new FragmentVPagerAdapter(getFragmentManager(), mFragments);

        popularViewPager.setAdapter(mAdapter);
        popularViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                popularViewPager.setCurrentItem(position);
                setTextViewInlarge(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        popularViewPager.setCurrentItem(0);
        tvTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularViewPager.setCurrentItem(0);
                setTextViewInlarge(0);
            }
        });
        tvTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularViewPager.setCurrentItem(1);
                setTextViewInlarge(1);
            }
        });

        mHeaderAndFooterWrapper.addHeaderView(popularityView);
        recyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();

        mLoadMoreWrapper.setLoadMoreView(R.layout.tv);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (popularBeans != null) {
                    page++;
                    int parseInt = popularBeans.getData().getPageInfo().getTotal_page();
                    if (page <= parseInt) {
                        loadPopularDataFromSer();

                    }
                }

            }
        });
        recyclerView.setAdapter(mLoadMoreWrapper);
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
            return listAll == null ? 0 : listAll.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_mvp_recom_follow_list, null);

            TextView name = view.findViewById(R.id.name);
            final ImageView head_image = view.findViewById(R.id.head_image);
            final ImageView cover_image = view.findViewById(R.id.cover_image);


            Glide.with(getActivity()).load(listAll.get(position).getCover_img()).asBitmap().centerCrop().into(new BitmapImageViewTarget(cover_image) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    circularBitmapDrawable.setCornerRadius(12);
                    cover_image.setImageDrawable(circularBitmapDrawable);
                }
            });
            Glide.with(getActivity()).load(listAll.get(position).getHead_url()).asBitmap().centerCrop().into(new BitmapImageViewTarget(head_image) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    head_image.setImageDrawable(circularBitmapDrawable);
                }
            });

            name.setText(listAll.get(position).getNickname());


            head_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readyGo(MeCenterActivity.class, listAll.get(position).getId());


                }
            });
            cover_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readyGo(ReleaseDetailsActivity.class, listAll.get(position).getZp_id());

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

    //
    //    public static class MvpBannerAdapter extends RecyclingPagerAdapter {
    //
    //        private final List<MvpRecommendBean.DataBean> mList;
    //        private final Context mContext;
    //
    //        public MvpBannerAdapter(Context context) {
    //            mList = new ArrayList<>();
    //            mContext = context;
    //        }
    //
    //        public void addAll(List<MvpRecommendBean.DataBean> list) {
    //            mList.addAll(list);
    //            notifyDataSetChanged();
    //        }
    //
    //        @Override
    //        public View getView(int position, View convertView, ViewGroup container) {
    //            final ViewHolder holder;
    //
    //                holder = new ViewHolder();
    //                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mvp_recom_follow_list, null);
    //                holder.mImageView = convertView.findViewById(R.id.cover_image);
    //                holder.head_image = convertView.findViewById(head_image);
    //                holder.name = convertView.findViewById(R.id.name);
    //                holder.rellayout = convertView.findViewById(R.id.rellayout);
    //
    //
    //            holder.rellayout.setOnClickListener(new View.OnClickListener() {
    //                @Override
    //                public void onClick(View v) {
    //
    //                }
    //            });
    //            Glide.with(mContext).load(mList.get(position).getCover_img()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.mImageView) {
    //                @Override
    //                protected void setResource(Bitmap resource) {
    //                    RoundedBitmapDrawable circularBitmapDrawable =
    //                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
    //                    circularBitmapDrawable.setCornerRadius(12);
    //                    view.setImageDrawable(circularBitmapDrawable);
    //                }
    //            });
    //            Glide.with(mContext).load(mList.get(position).getHead_url()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.head_image) {
    //                @Override
    //                protected void setResource(Bitmap resource) {
    //                    RoundedBitmapDrawable circularBitmapDrawable =
    //                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
    //                    circularBitmapDrawable.setCircular(true);
    //                    holder.head_image.setImageDrawable(circularBitmapDrawable);
    //                }
    //            });
    //
    //
    //            holder.name.setText(mList.get(position).getNickname());
    //
    //            return convertView;
    //        }
    //
    //        @Override
    //        public int getCount() {
    //            return mList.size();
    //        }
    //
    //        public class ViewHolder {
    //            public ImageView mImageView, head_image;
    //            public TextView name;
    //            public RelativeLayout rellayout;
    //
    //
    //        }

}


