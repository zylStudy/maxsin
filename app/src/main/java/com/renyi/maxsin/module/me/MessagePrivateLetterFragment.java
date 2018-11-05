package com.renyi.maxsin.module.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.get.ActivityDetailsActivity;
import com.renyi.maxsin.module.get.bean.GetBeans;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.renyi.maxsin.view.swipe.SwipeMenuLayout;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by zhangyuliang on 2018/3/22.私信
 */

public class MessagePrivateLetterFragment extends Basefragment {
    Bundle bundle;
    String type = "";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private CommonAdapter adapter;
    private int page = 1;
    private List<GetBeans.DataBean.GetListBean> get_list;
    private List<GetBeans.DataBean.GetListBean> get_listAll = new ArrayList<>();
    private GetBeans.DataBean resultBeanData;

    public static MessagePrivateLetterFragment getInstance(String type) {
        MessagePrivateLetterFragment ewsFragment = new MessagePrivateLetterFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ewsFragment.setArguments(bundle);
        return ewsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bundle = getArguments();
        type = bundle.getString("type");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new CommonAdapter<GetBeans.DataBean.GetListBean>(getActivity(), R.layout.item_me_message_private_list, get_listAll) {
            @Override
            protected void convert(final ViewHolder viewHolder, GetBeans.DataBean.GetListBean item,final int position) {

                ((SwipeMenuLayout) viewHolder.itemView).setIos(true).setLeftSwipe(true);

                viewHolder.setText(R.id.title, item.getTitle());
                viewHolder.setText(R.id.time,   item.getActstart());
                viewHolder.setText(R.id.info, item.getKeywords());
                if (position%3==0) {
                    viewHolder.setVisible(R.id.dot,false);
                }else{
                    viewHolder.setVisible(R.id.dot,true);
                }
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getThumb(), 10);

                viewHolder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = viewHolder.getLayoutPosition();
                        if (pos >= 0 && pos < mDatas.size()) {
                           // Toast.makeText(getContext(), "删除:" + pos, Toast.LENGTH_SHORT).show();
                            mDatas.remove(pos);
                            adapter.notifyItemRemoved(pos);//推荐用这个
                            //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                            //且如果想让侧滑菜单同时关闭，需要同时调用 ((SwipeMenuLayout) holder.itemView).quickClose();
                            //mAdapter.notifyDataSetChanged();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.rellayout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", get_listAll.get(position).getId());
                        //进入活动详情
                        Intent intent = null;
                        intent = new Intent(getActivity(), ActivityDetailsActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
        };
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    protected void loadData() {
        loadDataFromSer();
    }

    @Override
    protected void setOnclickListeners() {


        swipeRefreshLayout.setColorSchemeResources(

                android.R.color.holo_red_light,
                android.R.color.black,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {


                        if (get_listAll != null && get_listAll.size() != 0) {
                            get_listAll.clear();
                            adapter.notifyDataSetChanged();
                        }

                        page = 1;
                        loadDataFromSer();

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (resultBeanData != null) {
                    page++;
                    int parseInt = Integer.parseInt(resultBeanData.getTotal_page());
                    if (page <= parseInt) {
                        loadDataFromSer();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void loadDataFromSer() {


        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("active_flag", type);
        map.put("u_id", (String) SPUtils.get("uid","0"));
        map.put("cur_page", page + "");

        mHttpHelper.post(Api.URL + "active_list", map, new BaseCallback<GetBeans>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, GetBeans resultBean) {

                if (resultBean.getCode().equals("800")) {
                    resultBeanData = resultBean.getData();
                    get_list = resultBean.getData().getGet_list();
                    get_listAll.addAll(get_list);
                    if (get_listAll.size() != 0) {
                        adapter.notifyDataSetChanged();
                        showEmpty(false);
                    } else {
                        showEmpty(true);
                    }
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


}
