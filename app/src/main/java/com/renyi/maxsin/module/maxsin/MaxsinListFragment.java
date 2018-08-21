package com.renyi.maxsin.module.maxsin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
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
    private List<RelesseInfoAndWorksBean.DataBean.GetListBean> get_list;
    private List<RelesseInfoAndWorksBean.DataBean.GetListBean> get_listAll = new ArrayList<>();
    RelesseInfoAndWorksBean.DataBean resultBeanData;

    public static MaxsinListFragment getInstance(String type, String id) {
        MaxsinListFragment ewsFragment = new MaxsinListFragment();
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
        commonAdapter = new com.renyi.maxsin.adapter.recyclerview.CommonAdapter<RelesseInfoAndWorksBean.DataBean.GetListBean>(getActivity(), R.layout.item_release_info_list, get_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, RelesseInfoAndWorksBean.DataBean.GetListBean item, int position) {
                viewHolder.setText(R.id.title, item.getTitle());
                                viewHolder.setText(R.id.time, item.getTag_name());
                                viewHolder.setText(R.id.lookNum, item.getAdd_time());

                                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getCover_img(), 10);

            }
        };
        if (recyclerView != null) {
            recyclerView.setAdapter(commonAdapter);

        }
//
    }


    protected void loadData() {
        loadDataFromSer();
    }


    protected void setOnclickListeners() {
        //        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
        //            @Override
        //            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        //
        //                //                Bundle bundle = new Bundle();
        //                //                bundle.putString("id", get_listAll.get(position).getId());
        //                //                //进入课程详情
        //                //                Intent intent = null;
        //                //                if (get_listAll.get(position).getLeibie().equals("2")) {
        //                //                    intent = new Intent(getActivity(), NewsDetailsActivity.class);
        //                //                }
        //                //                if (get_listAll.get(position).getLeibie().equals("3")) {
        //                //                    intent = new Intent(getActivity(), ActivityDetailsActivity.class);
        //                //                }
        //                //
        //                //                intent.putExtras(bundle);
        //                //                startActivity(intent);
        //            }
        //
        //            @Override
        //            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        //                return false;
        //            }
        //        });
        //

        //        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        //            @Override
        //            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        //                super.onScrollStateChanged(recyclerView, newState);
        //
        //                if (resultBeanData != null) {
        //                    page++;
        //                    int parseInt = Integer.parseInt(resultBeanData.getTotal_page());
        //                    if (page <= parseInt) {
        //                        loadDataFromSer();
        //                    }
        //                }
        //            }
        //
        //            @Override
        //            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //                super.onScrolled(recyclerView, dx, dy);
        //            }
        //        });
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
                    get_listAll.addAll(MaxsinListFragment.this.get_list);
                    if (get_listAll.size() != 0) {
                        commonAdapter.notifyDataSetChanged();
                    }
//                    if (get_listAll.size() != 0) {
//                        showEmpty(false);
//                    } else {
//                        showEmpty(true);
//                    }
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
        ButterKnife.bind(getActivity()).unbind();
    }
}
