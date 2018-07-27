package com.renyi.maxsin.module.mvp;

import android.graphics.Bitmap;
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
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.renyi.maxsin.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.mvp.bean.MvpRecommendBean;
import com.renyi.maxsin.module.mvp.bean.PopularBeans;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
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

public class MvpFirstPageFragment extends Basefragment implements ViewPager.OnPageChangeListener{

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
            protected void convert(ViewHolder viewHolder, PopularBeans.DataBean.ListBean item, int position) {
                viewHolder.setText(R.id.name, item.getNickname());
                viewHolder.setText(R.id.title, item.getTitle());
                viewHolder.setText(R.id.time, item.getAdd_time());
                viewHolder.setText(R.id.type, item.getTag_name());
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getCover_img(), 10);
                viewHolder.setImageViewNetUrl(R.id.head_image, item.getHead_url());
            }
        };
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);

    }


    @Override
    protected void loadData() {
        loadDataFromSer();
        loadPopularDataFromSer();
    }

    @Override
    protected void setOnclickListeners() {


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
        map.put("u_id", "1");
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

    private ViewPager mViewPager;
    private RelativeLayout viewPagerContainer;

    private void initHeaderAndFooter() {


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.include_mvp_header_layout, null);
        mViewPager = view.findViewById(viewpager);
        viewPagerContainer = view.findViewById(R.id.viewPagerContainer);
        mViewPager.setClipChildren(false);
        viewPagerContainer.setClipChildren(false);
        recyclerView.setClipChildren(false);
        mViewPager.addOnPageChangeListener(this);
        View popularityView = LayoutInflater.from(getActivity()).inflate(R.layout.include_mvp_popularity_list, null);


        mViewPager.setPageTransformer(true, new ScalePageTransformer());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(2);
        MvpBannerAdapter mvpBannerAdapter = new MvpBannerAdapter();

        mViewPager.setAdapter(mvpBannerAdapter);
        mHeaderAndFooterWrapper.addHeaderView(view);
        mHeaderAndFooterWrapper.addHeaderView(popularityView);
        recyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
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


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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


