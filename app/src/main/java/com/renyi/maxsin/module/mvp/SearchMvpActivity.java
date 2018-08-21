package com.renyi.maxsin.module.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.listview.CommonAdapter;
import com.renyi.maxsin.adapter.listview.ViewHolder;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.module.me.MeCenterActivity;
import com.renyi.maxsin.module.mvp.bean.PopularBeans;
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


public class SearchMvpActivity extends BaseActivity {


    int page = 1;
    CommonAdapter commonAdapter;
    List<PopularBeans.DataBean.ListBean> popularListAll = new ArrayList<>();
    @BindView(R.id.search_rellayout)
    RelativeLayout searchRel;
    @BindView(R.id.edit_info)
    EditText editInfo;
    @BindView(R.id.cancle_tv)
    TextView cancleTv;
    @BindView(R.id.listView)
    ListView listView;
    private boolean isBottom = false;
    PopularBeans resultBeans;

    @Override
    protected int getLayoutId() {


        return R.layout.activity_search_mvp;
    }

    @Override
    protected void initView() {
        IntentFilter filter = new IntentFilter("broadcast.update");
        registerReceiver(broadcastReceiverUpdate, filter);
        hideTitleAndBack();
        commonAdapter = new CommonAdapter<PopularBeans.DataBean.ListBean>(this, R.layout.item_mvp_search_list, popularListAll) {

            @Override
            protected void convert(final ViewHolder viewHolder, final PopularBeans.DataBean.ListBean item, final int position) {
                viewHolder.setText(R.id.name, item.getUser_name());

                viewHolder.setImageViewNetUrl(R.id.head_image, item.getHead_url());

                if (item.getIs_focus().equals("0")) {
                    viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_yes_bg);

                } else {
                    viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_no_bg);

                }

                viewHolder.setOnClickListener(R.id.followimage, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (item.getIs_focus().equals("1")) {
                            viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_yes_bg);
                            postFollowDate("1", popularListAll.get(position).getId());
                            // popularListAll.get(position).setIs_focus("0");
                        } else {
                            viewHolder.setBackgroundRes(R.id.followimage, R.mipmap.ic_follow_no_bg);
                            postFollowDate("2", popularListAll.get(position).getId());
                            //  popularListAll.get(position).setIs_focus("1");
                        }

                    }
                });


                viewHolder.setOnClickListener(R.id.head_image, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        readyGo(MeCenterActivity.class, popularListAll.get(position).getId());
                    }
                });
            }
        };

        listView.setAdapter(commonAdapter);


    }

    BroadcastReceiver broadcastReceiverUpdate = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String uid = intent.getExtras().getString("uid");
            for (int i = 0; i < popularListAll.size(); i++) {
                if (popularListAll.get(i).getId().equals(uid)) {
                    if (popularListAll.get(i).getIs_focus().equals("1")) {
                        popularListAll.get(i).setIs_focus("0");
                    } else {
                        popularListAll.get(i).setIs_focus("1");
                    }

                }
            }

            commonAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        searchRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editInfo.getText().toString().trim().equals("")) {
                    popularListAll.clear();
                    page = 1;
                    loadSearchDataFromSer(editInfo.getText().toString().trim());
                }
            }
        });
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isBottom
                        && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    isBottom = false;
                    page++;
                    if (resultBeans != null) {
                        int parseInt = resultBeans.getData().getPageInfo().getTotal_page();
                        if (page <= parseInt) {
                            loadSearchDataFromSer(editInfo.getText().toString().trim());
                        }
                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isBottom = true;
                }
            }
        });
    }

    protected void readyGo(Class<?> clazz, String id) {
        Intent intent = new Intent(this, clazz);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void loadSearchDataFromSer(String keywords) {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("u_id", (String) SPUtils.get("uid", ""));
        map.put("page", page + "");
        map.put("keywords", keywords);

        mHttpHelper.post(Api.URL + "searchuser", map, new BaseCallback<PopularBeans>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, PopularBeans resultBean) {
                resultBeans = resultBean;
                if (resultBean.getCode().equals("800")) {
                    popularListAll.addAll(resultBean.getData().getList());
                    commonAdapter.notifyDataSetChanged();
                    if (popularListAll.isEmpty()) {
                        showEmpty(true);
                    } else {
                        showEmpty(false);
                    }

                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }


        });
    }

    private void postFollowDate(String opt, final String uid) {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("my_id", (String) SPUtils.get("uid", ""));
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
                    Intent intent = new Intent("broadcast.update");
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", uid);
                    intent.putExtras(bundle);
                    sendBroadcast(intent);

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
        unregisterReceiver(broadcastReceiverUpdate);
    }


}
