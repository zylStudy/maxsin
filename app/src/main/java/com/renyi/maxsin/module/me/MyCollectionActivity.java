package com.renyi.maxsin.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.MultiItemTypeAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.NewsDetailsActivity;
import com.renyi.maxsin.module.get.bean.GetBeans;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MyCollectionActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    private CommonAdapter adapter;
    private int page = 1;
    private List<GetBeans.DataBean.GetListBean> get_list;
    private List<GetBeans.DataBean.GetListBean> get_listAll = new ArrayList<>();
    private GetBeans.DataBean resultBeanData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initView() {
        showTitleAndBack("我的收藏");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new CommonAdapter<GetBeans.DataBean.GetListBean>(this, R.layout.item_me_coll_list, get_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, GetBeans.DataBean.GetListBean item, int position) {
                viewHolder.setText(R.id.title, item.getTitle());
                viewHolder.setText(R.id.info, item.getKeywords());
                viewHolder.setText(R.id.likenum, item.getCatid());
                viewHolder.setText(R.id.joinnum, item.getInputtime());

                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover, item.getThumb(), 10);

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
    protected void setOnClickListeners() {
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Bundle bundle = new Bundle();
                bundle.putString("id", get_listAll.get(position).getId());
                //进入资讯详情
                Intent intent = new Intent(MyCollectionActivity.this, NewsDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
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

    }

    private void loadDataFromSer() {


        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("u_id", (String) SPUtils.get("uid",""));
        map.put("cur_page", page + "");

        mHttpHelper.post(Api.URL + "collect_list", map, new BaseCallback<GetBeans>() {
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
