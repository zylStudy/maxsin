package com.renyi.maxsin.module.maxsin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.MultiItemTypeAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.maxsin.bean.TeacherBeans;
import com.renyi.maxsin.module.release.interfaces.TagPositionInterface;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.view.tagview.FlowLayout;
import com.renyi.maxsin.view.tagview.TagAdapter;
import com.renyi.maxsin.view.tagview.TagFlowLayout;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class ComprehensiveSearchTeacherListFragment extends Basefragment {
    Bundle bundle;
    ArrayList<String> keywordsList;

    @BindView(R.id.recyclerViewList)
    RecyclerView recyclerView;
    @BindView(R.id.rel)
    RelativeLayout rel;
    @BindView(R.id.tag_flowlayout_aways)
    TagFlowLayout tagFlowlayoutAways;
    private CommonAdapter adapter;
    private int page = 1;
    private List<TeacherBeans.DataBean.ListBean> get_listAll = new ArrayList<>();
    private TeacherBeans.DataBean resultBeanData;
    String keywords = "";


    public static ComprehensiveSearchTeacherListFragment getInstance(ArrayList<String> type) {
        ComprehensiveSearchTeacherListFragment ewsFragment = new ComprehensiveSearchTeacherListFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("type", type);
        ewsFragment.setArguments(bundle);
        return ewsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bundle = getArguments();
        keywordsList = bundle.getStringArrayList("type");
        IntentFilter filter = new IntentFilter("broadcast.search");
        getActivity().registerReceiver(broadcastReceiverUpdate, filter);
    }

    BroadcastReceiver broadcastReceiverUpdate = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                Bundle bundle = intent.getExtras();
                if (bundle.getInt("flag")==0) {

                    rel.setVisibility(rel.GONE);
                    recyclerView.setVisibility(recyclerView.VISIBLE);

                    keywords = bundle.getString("keywords");
                    get_listAll.clear();
                    page = 1;
                    loadDataFromSer();
                }
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comprehensive_search;
    }

    @Override
    protected void initView() {

        TagAdapter tagAdapter = new TagAdapter<String>(keywordsList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tagFlowlayoutAways, false);
                tv.setText(s);
                return tv;
            }
        };
        tagFlowlayoutAways.setAdapter(tagAdapter);

        tagAdapter.setOnTagChangedListener(new TagPositionInterface() {
            @Override
            public void getTagPosition(int position) {
                //                clearList.add(0, list.get(position));
                //                list.remove(position);
                //                adapter.notifyDataChanged();
                keywords = keywordsList.get(position);
                rel.setVisibility(rel.GONE);
                recyclerView.setVisibility(recyclerView.VISIBLE);
                get_listAll.clear();
                page = 1;
                loadDataFromSer();
            }

            @Override
            public void getClearTagPosition(int position) {

            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new CommonAdapter<TeacherBeans.DataBean.ListBean>(getActivity(), R.layout.item_teacher_list, get_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, TeacherBeans.DataBean.ListBean item, int position) {
                viewHolder.setText(R.id.name, item.getName());
                viewHolder.setText(R.id.major, item.getMajor());
                viewHolder.setText(R.id.school, item.getEducollege());
                viewHolder.setText(R.id.info, item.getDesc());
                viewHolder.setShadowDrawable(R.id.rel, 200);
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.head_image, item.getPhoto(), 10);
            }
        };
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnclickListeners() {


        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Bundle bundle = new Bundle();
                bundle.putString("id", get_listAll.get(position).getId());
                Intent intent = null;
                intent = new Intent(getActivity(), TeacherDetailsActivity.class);
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
        map.put("keywords", keywords);
        map.put("type", "1");
        map.put("page", page + "");

        mHttpHelper.post(Api.URL + "search_all", map, new BaseCallback<TeacherBeans>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, TeacherBeans resultBean) {

                if (resultBean.getCode().equals("800")) {
                    resultBeanData = resultBean.getData();
                    get_listAll.addAll(resultBean.getData().getList());
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiverUpdate);
    }
}
