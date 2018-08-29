package com.renyi.maxsin.module.maxsin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.MultiItemTypeAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.module.me.ReleaseDetailsActivity;
import com.renyi.maxsin.module.mvp.bean.PopularBeans;
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
import butterknife.ButterKnife;

/**
 * Created by zhangyuliang on 2018/3/22.他的作品和内容
 * 课程
 */

public class MaxsinListFragment extends Fragment {
    Bundle bundle;
    String type = "";
    CommonAdapter commonAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int page = 1;
    private List<PopularBeans.DataBean.ListBean> get_listAll = new ArrayList<>();
    PopularBeans.DataBean resultBeanData;

    public static MaxsinListFragment getInstance(String type) {
        MaxsinListFragment ewsFragment = new MaxsinListFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.include_listview, null);

        setHasOptionsMenu(true);

        ButterKnife.bind(this, view);
        initView();
        loadData();
        setOnclickListeners();
        return view;
    }


    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        commonAdapter = new CommonAdapter<PopularBeans.DataBean.ListBean>(getActivity(), R.layout.item_mvp_new_product_list, get_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, PopularBeans.DataBean.ListBean item, int position) {
                viewHolder.setText(R.id.name, item.getUser_name());
                viewHolder.setText(R.id.title, item.getTitle());
                viewHolder.setText(R.id.time, item.getAdd_time());
                viewHolder.setText(R.id.type, item.getTag_name());
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getCover_img(), 10);
                viewHolder.setImageViewNetUrl(R.id.head_image, item.getHead_url());
                viewHolder.setVisible(R.id.followimage, false);
            }
        };
        if (recyclerView != null) {
            recyclerView.setAdapter(commonAdapter);

        }
    }


    protected void loadData() {
        loadDataFromSer();
    }


    protected void setOnclickListeners() {
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Bundle bundle = new Bundle();
                bundle.putString("id", get_listAll.get(position).getId());
                Intent intent = null;
                intent = new Intent(getActivity(), ReleaseDetailsActivity.class);
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
                    int parseInt = resultBeanData.getPageInfo().getTotal_page();
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
        map.put("tag_name", type);
        map.put("page", page + "");

        mHttpHelper.post(Api.URL + "showlist", map, new BaseCallback<PopularBeans>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, PopularBeans resultBean) {

                if (resultBean.getCode().equals("800")) {

                    resultBeanData = resultBean.getData();

                    get_listAll.addAll( resultBeanData.getList());
                    if (get_listAll.size() != 0) {
                        commonAdapter.notifyDataSetChanged();

                    }else{

                    }
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
