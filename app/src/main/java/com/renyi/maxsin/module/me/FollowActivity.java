package com.renyi.maxsin.module.me;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.me.bean.FollowBeans;
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

public class FollowActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    CommonAdapter adapter;
    int page = 1;
    List<FollowBeans.DataBeanX.DataBean> dataBeen = new ArrayList<>();
    List<FollowBeans.DataBeanX.DataBean> get_listAll = new ArrayList<>();
    FollowBeans.DataBeanX resultBeanData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initView() {
        showTitleAndBack("我的关注");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new CommonAdapter<FollowBeans.DataBeanX.DataBean>(this, R.layout.item_me_act_list, get_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, FollowBeans.DataBeanX.DataBean item, int position) {
                viewHolder.setText(R.id.title, item.getUser_name());
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item.getHead_url(), 10);
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
        map.put("u_id", "22");
        map.put("current_page", page + "");

        mHttpHelper.post(Api.URL + "fans_list", map, new BaseCallback<FollowBeans>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, FollowBeans resultBean) {

                if (resultBean.getCode().equals("800")) {
                    resultBeanData = resultBean.getData();
                    dataBeen = resultBean.getData().getData();
                    get_listAll.addAll(dataBeen);
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

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_follow);
    //    }
}
