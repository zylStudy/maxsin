package com.renyi.maxsin.module.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.MultiItemTypeAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.get.NewsDetailsActivity;
import com.renyi.maxsin.module.get.bean.GetBeans;
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

/**
 * Created by zhangyuliang on 2018/3/22.资讯
 * 课程
 */

public class FansNumFragment extends Basefragment {
    Bundle bundle;
    String type = "";
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter adapter;
    private int page = 1;
    private List<GetBeans.DataBean.GetListBean> get_list;
    private List<GetBeans.DataBean.GetListBean> get_listAll = new ArrayList<>();
    private GetBeans.DataBean resultBeanData;

    public static FansNumFragment getInstance(String type) {
        FansNumFragment ewsFragment = new FansNumFragment();
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
        adapter = new CommonAdapter<GetBeans.DataBean.GetListBean>(getActivity(), R.layout.item_get_news_list, get_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, GetBeans.DataBean.GetListBean item, int position) {
                viewHolder.setText(R.id.title, item.getTitle());
                viewHolder.setText(R.id.time, item.getInputtime());
                viewHolder.setText(R.id.lookNum, item.getHits());

                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getThumb(), 10);

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
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Bundle bundle = new Bundle();
                bundle.putString("id", get_listAll.get(position).getId());
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void loadDataFromSer() {


        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("type", type);

        mHttpHelper.post(Api.URL + "mvplist", map, new BaseCallback<GetBeans>() {
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
