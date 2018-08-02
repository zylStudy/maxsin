package com.renyi.maxsin.module.release;

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
import com.renyi.maxsin.module.me.ReleaseDetailsActivity;
import com.renyi.maxsin.module.release.bean.RelesseInfoAndWorksBean;
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

/**
 * Created by zhangyuliang on 2018/3/22.我的作品和内容
 * 课程
 */

public class MyReleaseInfoAndWorksFragment extends Basefragment {
    Bundle bundle;
    String type = "";
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter adapter;
    private int page = 1;
    private List<RelesseInfoAndWorksBean.DataBean.GetListBean> get_list;
    private List<RelesseInfoAndWorksBean.DataBean.GetListBean> get_listAll = new ArrayList<>();
    RelesseInfoAndWorksBean.DataBean resultBeanData;

    public static MyReleaseInfoAndWorksFragment getInstance(String type) {
        MyReleaseInfoAndWorksFragment ewsFragment = new MyReleaseInfoAndWorksFragment();
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


                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new CommonAdapter<RelesseInfoAndWorksBean.DataBean.GetListBean>(getActivity(), R.layout.item_me_rele_list, get_listAll) {
            @Override
            protected void convert(final ViewHolder viewHolder, RelesseInfoAndWorksBean.DataBean.GetListBean item, final int position) {

                ((SwipeMenuLayout) viewHolder.itemView).setIos(true).setLeftSwipe(true);
                viewHolder.setText(R.id.title, item.getTitle());
                viewHolder.setText(R.id.likenum, item.getTag_name());
                viewHolder.setText(R.id.joinnum, item.getAdd_time());
                viewHolder.setText(R.id.info, item.getDescription());

                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover, item.getCover_img(), 10);
                viewHolder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = viewHolder.getLayoutPosition();
                        if (pos >= 0 && pos < get_listAll.size()) {
                            // Toast.makeText(getContext(), "删除:" + pos, Toast.LENGTH_SHORT).show();

                            adapter.notifyItemRemoved(pos);//推荐用这个
                            //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                            //且如果想让侧滑菜单同时关闭，需要同时调用 ((SwipeMenuLayout) holder.itemView).quickClose();
                            //mAdapter.notifyDataSetChanged();
                            delteData(get_listAll.get(pos).getId());
                            get_listAll.remove(pos);
                            if (resultBeanData != null) {
                                page++;
                                int parseInt = Integer.parseInt(resultBeanData.getTotal_page());
                                if (page <= parseInt) {
                                    loadDataFromSer();
                                }
                            }

                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.rellayout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", get_listAll.get(position).getId());

                        Intent intent = null;
                        intent = new Intent(getActivity(), ReleaseDetailsActivity.class);
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
        map.put("cat_id", type);
        map.put("uid", (String) SPUtils.get("uid",""));
        map.put("cur_page", page + "");

        mHttpHelper.post(Api.URL + "work_list", map, new BaseCallback<RelesseInfoAndWorksBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, RelesseInfoAndWorksBean resultBean) {

                if (resultBean.getCode().equals("800")) {

                    resultBeanData = resultBean.getData();

                    get_list = resultBeanData.getGet_list();
                    get_listAll.addAll(MyReleaseInfoAndWorksFragment.this.get_list);
                    if (get_listAll.size() != 0) {
                        adapter.notifyDataSetChanged();
                    }
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void delteData(String id) {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("content_id", id);

        mHttpHelper.post(Api.URL + "del_work", map, new BaseCallback<RelesseInfoAndWorksBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, RelesseInfoAndWorksBean resultBean) {

                if (resultBean.getCode().equals("800")) {

                    resultBeanData = resultBean.getData();

                    get_list = resultBeanData.getGet_list();
                    get_listAll.addAll(MyReleaseInfoAndWorksFragment.this.get_list);
                    if (get_listAll.size() != 0) {
                        adapter.notifyDataSetChanged();
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
