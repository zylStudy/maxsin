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
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.module.me.bean.FollowBeans;
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

public class FollowActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    CommonAdapter adapter;
    int page = 1;
    String flage = "";
    List<FollowBeans.DataBeanX.DataBean> dataBeen = new ArrayList<>();
    List<FollowBeans.DataBeanX.DataBean> get_listAll = new ArrayList<>();
    FollowBeans.DataBeanX resultBeanData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initView() {
        flage = getIntent().getExtras().getString("flage");

        if (flage.equals("2")) {
            showTitleAndBack("关注");
        } else {
            showTitleAndBack("粉丝");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new CommonAdapter<FollowBeans.DataBeanX.DataBean>(this, R.layout.item_me_follow_list, get_listAll) {
            @Override
            protected void convert(final ViewHolder viewHolder, final FollowBeans.DataBeanX.DataBean item, final int position) {
                viewHolder.setText(R.id.name, item.getUser_name());
                viewHolder.setText(R.id.project, "发布内容：" + item.getContent_count() + "  作品：" + item.getWork_count());
                viewHolder.setImageViewNetUrl(R.id.cover_image, item.getHead_url());

                if (flage.equals("2")) {

                    if (item.getFlag().equals("0")) {
                        viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_yes_bg);
                    } else {
                        viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_no_bg);
                    }

                } else {
                    if (item.getFlag().equals("0")) {
                        viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_yes_bg);
                    } else {
                        viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_no_bg);
                    }
                }
                viewHolder.setOnClickListener(R.id.followimage, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postDate(item.getFlag(), item.getId(), position);
                        if (item.getFlag().equals("0")) {
                            viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_yes_bg);
                        } else {
                            viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_no_bg);
                        }
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
        map.put("u_id", (String) SPUtils.get("uid",""));
        map.put("get_flag", flage);
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


    private void postDate(final String opt, final String uid, final int position) {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("my_id", (String) SPUtils.get("uid",""));
        map.put("key", Api.KEY);
        map.put("other_id", uid);
        String url = "";
        if (opt.equals("1")) {
            url = "un_focus";
        } else {
            url = "focus_other";
        }
        mHttpHelper.post(Api.URL + url, map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {
                if (resultBean.getCode().equals("800")) {
                    if (opt.equals("1")) {
                        resultBeanData.getData().get(position).setFlag("0");
                    } else {
                        resultBeanData.getData().get(position).setFlag("1");
                    }
                    Intent intent = new Intent("broadcast.update");
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", uid);
                    intent.putExtras(bundle);
                     sendBroadcast(intent);
                    adapter.notifyDataSetChanged();
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        setResult(2, intent);
    }

}
