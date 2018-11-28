package com.renyi.maxsin.module.Study;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.module.Study.bean.WeekDayBean;
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
import butterknife.ButterKnife;

public class ScheduleActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.week)
    TextView week;
    @BindView(R.id.back_rel)
    RelativeLayout backRel;
    @BindView(R.id.tab_recyelerview)
    RecyclerView tabRecyelerview;
    @BindView(R.id.content_layout)
    CoordinatorLayout contentLayout;
    private CommonAdapter mAdapter;
    List<WeekDayBean.DataBean.ReturnWeekDataBean> listAll = new ArrayList<>();
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        setContentView(R.layout.activity_schedule);
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), true);
        ButterKnife.bind(this);
        Glide.with(this).load("http://attach.bbs.miui.com/forum/201305/15/122800gyjaz7yjq1ydvawg.jpg").into(imageView);
        initView();
        getStringData();
    }

    private void initView() {
        tabRecyelerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new CommonAdapter<WeekDayBean.DataBean.ReturnWeekDataBean>(this, R.layout.item_list, listAll) {
            @Override
            protected void convert(ViewHolder holder, WeekDayBean.DataBean.ReturnWeekDataBean s, int position) {
                holder.setText(R.id.week, s.getWeek_data());
                holder.setText(R.id.tv_day, s.getDate_data());

                if (flag == position) {

                    if (s.getHas_course() == 1) {
                        holder.setBackgroundRes(R.id.statu_layout, R.drawable.shape_msg_status);
                    } else {
                        holder.setBackgroundRes(R.id.statu_layout, R.drawable.shape_msg_num);
                    }

                } else {

                    holder.setBackgroundRes(R.id.statu_layout, R.drawable.shap_no_course_status);
                }

                if (s.getHas_course() == 1) {
                    holder.setVisible(R.id.dot, true);
                } else {
                    holder.setVisible(R.id.dot, false);
                }
            }
        };
        tabRecyelerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                flag = position;
                mAdapter.notifyDataSetChanged();
                Bundle bundle = new Bundle();
                bundle.putString("cur_date", listAll.get(position).getDate_date());
                Intent intent = new Intent("broadcast.updatefirstpage");
                intent.putExtras(bundle);
                sendBroadcast(intent);


            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        backRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getStringData() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("stu_id", (String) SPUtils.get("sid", "0"));
        mHttpHelper.post("http://edu.mxsyzen.com/stuapigetdate", map, new BaseCallback<WeekDayBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, WeekDayBean resultBean) {

                if (resultBean.getCode().equals("800") && week != null) {
                    listAll.addAll(resultBean.getData().getReturn_week_data());
                    week.setText(resultBean.getData().getCur_date() + " " + resultBean.getData().getCur_year());

                    mAdapter.notifyDataSetChanged();
                    initFragmentView(Integer.parseInt(resultBean.getData().getCur_day_sign()));

                }
            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }

        });
    }

    private void initFragmentView(int a) {
        flag = a;
        CourseListFragment on = new CourseListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("cur_date", listAll.get(a).getDate_date());
        on.setArguments(bundle);
        transaction.add(R.id.first_layout, on, null);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
