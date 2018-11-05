package com.renyi.maxsin.module.Study;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.Study.bean.CourseListTeacherBean;
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

/**
 * Created by zhangyuliang on 2017/03/06 学生端首页
 */

public class CourseListFragment extends Basefragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter mAdapter;
    private List<CourseListTeacherBean.DataBean.CourseListBean> dataAll = new ArrayList<>();
    private String flag = "";

    BroadcastReceiver broadcastReceiverUpdate = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (dataAll != null && dataAll.size() != 0) {
                dataAll.clear();
                mAdapter.notifyDataSetChanged();
            }
            flag = intent.getExtras().getString("cur_date");
            getStringData();
        }
    };

    @Override
    protected int getLayoutId() {
        IntentFilter filter = new IntentFilter("broadcast.updatefirstpage");
        getActivity().registerReceiver(broadcastReceiverUpdate, filter);
        return R.layout.fragment_course_tab;
    }

    @Override
    protected void initView() {


        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }


        mAdapter = new CommonAdapter<CourseListTeacherBean.DataBean.CourseListBean>(getActivity(), R.layout.item_first_course_list_layout, dataAll) {
            @Override
            protected void convert(ViewHolder viewHolder, CourseListTeacherBean.DataBean.CourseListBean item, int position) {
                viewHolder.setText(R.id.name, item.getC_type());
                viewHolder.setText(R.id.course, item.getC_name());

                viewHolder.setText(R.id.time_tv, item.getC_start_time() + "-" + item.getC_end_time());
                viewHolder.setImageViewNetUrl(R.id.cover_image, item.getT_head_url());


                if (item.getC_status().equals("0")) {
                    viewHolder.setText(R.id.status, "待上课");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);

                }
                if (item.getC_status().equals("1")) {
                    viewHolder.setText(R.id.status, "上课中");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);

                }
                if (item.getC_status().equals("2")) {
                    viewHolder.setText(R.id.status, "待确认");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);

                }
                if (item.getC_status().equals("3")) {

                    viewHolder.setText(R.id.status, "学生缺席");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorOuteb4161);
                }
                if (item.getC_status().equals("4")) {
                    viewHolder.setText(R.id.status, "老师缺席");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorOuteb4161);

                }
                if (item.getC_status().equals("5")) {
                    viewHolder.setText(R.id.status, "老师调课");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);

                }
                if (item.getC_status().equals("6")) {
                    viewHolder.setText(R.id.status, "学生调课");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);

                }
                if (item.getC_status().equals("7")) {
                    viewHolder.setText(R.id.status, "待上传作业");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);

                }
                if (item.getC_status().equals("8")) {
                    viewHolder.setText(R.id.status, "待评价");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);

                }

                if (item.getC_status().equals("9")) {
                    viewHolder.setText(R.id.status, "已结束");
                    viewHolder.setTextColorRes(R.id.status, R.color.color9);
                }

            }
        };
        if (recyclerView != null) {
            recyclerView.setAdapter(mAdapter);

        }


    }


    @Override
    protected void loadData() {
        getStringData();
    }

    @Override
    protected void setOnclickListeners() {

        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("c_id", dataAll.get(position).getT_id());
                //进入课程详情
                Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void getStringData() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("cur_date", flag);
        map.put("stu_id", (String) SPUtils.get("sid", "0"));
        mHttpHelper.post("http://edu.mxsyzen.com/stuapinewindexcourse", map, new BaseCallback<CourseListTeacherBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, CourseListTeacherBean resultCodeList) {
                        if (resultCodeList.getCode().equals("800")) {
                            if (dataAll != null) {
                                dataAll.clear();
                            }
                            List<CourseListTeacherBean.DataBean.CourseListBean> course_list = resultCodeList.getData().getCourse_list();

                            dataAll.addAll(course_list);
                            if (dataAll != null && dataAll.size() != 0 && recyclerView != null) {
                                mAdapter.notifyDataSetChanged();
                                showEmpty(false);
                            } else {
                                showEmpty(true);
                            }
                        }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


}
