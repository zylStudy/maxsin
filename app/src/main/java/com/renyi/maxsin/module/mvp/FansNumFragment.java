package com.renyi.maxsin.module.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.MultiItemTypeAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.me.MeCenterActivity;
import com.renyi.maxsin.module.mvp.bean.MvpRecommendBean;
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

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter adapter;

    private List<MvpRecommendBean.DataBean> get_listAll = new ArrayList<>();

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
        return R.layout.fragment_popul;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adapter = new CommonAdapter<MvpRecommendBean.DataBean>(getActivity(), R.layout.item_mvp_popu_list, get_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, MvpRecommendBean.DataBean item, int position) {
                viewHolder.setText(R.id.name, item.getNickname());
                if (item.getHead_url() == null || item.getHead_url().equals("")) {

                } else {
                    viewHolder.setImageCircular(R.id.head_image, item.getHead_url());
                }
                if (position == 0) {
                    viewHolder.setBackgroundRes(R.id.hide_image, R.mipmap.ic_mvp_fans_num01);
                }
                if (position == 1) {
                    viewHolder.setBackgroundRes(R.id.hide_image, R.mipmap.ic_mvp_fans_num02);
                }
                if (position == 2) {
                    viewHolder.setBackgroundRes(R.id.hide_image, R.mipmap.ic_mvp_fans_num03);
                }

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
                Intent intent = new Intent(getActivity(), MeCenterActivity.class);
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
        mHttpHelper.post(Api.URL + "mvplist", map, new BaseCallback<MvpRecommendBean>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, MvpRecommendBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    get_listAll.addAll(resultBean.getData());
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
