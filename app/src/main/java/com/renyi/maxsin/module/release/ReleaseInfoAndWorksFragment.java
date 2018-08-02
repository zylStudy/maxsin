package com.renyi.maxsin.module.release;

import android.content.Context;
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
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.release.bean.RelesseInfoAndWorksBean;
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
 * Created by zhangyuliang on 2018/3/22.他的作品和内容
 * 课程
 */

public class ReleaseInfoAndWorksFragment extends Basefragment {
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

    public static ReleaseInfoAndWorksFragment getInstance(String type, String id) {
        ReleaseInfoAndWorksFragment ewsFragment = new ReleaseInfoAndWorksFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("id", id);
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
        adapter = new CommonAdapter<RelesseInfoAndWorksBean.DataBean.GetListBean>(getActivity(), R.layout.item_release_info_list, get_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, RelesseInfoAndWorksBean.DataBean.GetListBean item, int position) {
                viewHolder.setText(R.id.title, item.getTitle());
                viewHolder.setText(R.id.time, item.getTag_name());
                viewHolder.setText(R.id.lookNum, item.getAdd_time());

                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getCover_img(), 10);


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

                //                Bundle bundle = new Bundle();
                //                bundle.putString("id", get_listAll.get(position).getId());
                //                //进入课程详情
                //                Intent intent = null;
                //                if (get_listAll.get(position).getLeibie().equals("2")) {
                //                    intent = new Intent(getActivity(), NewsDetailsActivity.class);
                //                }
                //                if (get_listAll.get(position).getLeibie().equals("3")) {
                //                    intent = new Intent(getActivity(), ActivityDetailsActivity.class);
                //                }
                //
                //                intent.putExtras(bundle);
                //                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        //

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
        map.put("uid", getArguments().getString("id"));
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
                    get_listAll.addAll(ReleaseInfoAndWorksFragment.this.get_list);
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
