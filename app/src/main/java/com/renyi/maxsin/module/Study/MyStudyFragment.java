package com.renyi.maxsin.module.Study;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.Study.bean.MyStudyBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.renyi.maxsin.R.id.recyclerView;

/**
 * Created by zhangyuliang on 2018/3/22.
 * 我的学习
 */

public class MyStudyFragment extends Basefragment {

    @BindView(R.id.rel1)
    RelativeLayout rel1;
    @BindView(R.id.rel2)
    RelativeLayout rel2;
    @BindView(R.id.rel3)
    RelativeLayout rel3;
    @BindView(R.id.rel4)
    RelativeLayout rel4;
    private View includeView01, includeView02, includeView03, includeView04;
    private RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4;
    TextView projectType1, total1, projectType2, total2, projectType3, total3, projectType4, total4;
    private String is_music = "";

    public static MyStudyFragment getInstance() {
        return new MyStudyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mystudy;
    }

    @Override
    protected void initView() {
        is_music = SPUtils.get("is_music", "-1");
        includeLayout();
    }

    private void includeLayout() {
        LayoutInflater from = LayoutInflater.from(getActivity());
        includeView01 = from.inflate(R.layout.include_study_process_layout, null);
        recyclerView1 = includeView01.findViewById(recyclerView);
        projectType1 = includeView01.findViewById(R.id.project_type);
        total1 = includeView01.findViewById(R.id.total);

        includeView02 = from.inflate(R.layout.include_study_process_layout, null);
        recyclerView2 = includeView02.findViewById(recyclerView);
        projectType2 = includeView02.findViewById(R.id.project_type);
        total2 = includeView02.findViewById(R.id.total);

        includeView03 = from.inflate(R.layout.include_study_process_layout, null);
        recyclerView3 = includeView03.findViewById(recyclerView);
        projectType3 = includeView03.findViewById(R.id.project_type);
        total3 = includeView03.findViewById(R.id.total);

        includeView04 = from.inflate(R.layout.include_study_process_layout, null);
        recyclerView4 = includeView04.findViewById(recyclerView);
        projectType4 = includeView04.findViewById(R.id.project_type);
        total4 = includeView04.findViewById(R.id.total);
    }

    private void setDataToIncludeLayout(MyStudyBean myStudyBean) {
        MyStudyBean.DataBeanXXXX data = myStudyBean.getData();
        if (data.getPutong().getHas_flag().equals("1")) {
            rel1.addView(includeView01);
            projectType1.setText("普通项目");
            total1.setText(data.getPutong().getFinished() + "/" + data.getPutong().getTotal() + "已完成");
            recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            CommonAdapter listAdapter1 = new CommonAdapter<MyStudyBean.DataBeanXXXX.PutongBean.DataBean>(getActivity(), R.layout.item_study_mystudy_layout, data.getPutong().getData()) {
                @Override
                protected void convert(ViewHolder viewHolder, MyStudyBean.DataBeanXXXX.PutongBean.DataBean item, int position) {
                    viewHolder.setText(R.id.tv_study_base, item.getEntry_name());
                    viewHolder.setText(R.id.tv_study_base_status, "预计完成时间" + item.getPre_finished_time());
                    viewHolder.setText(R.id.tv_study_post, item.getPre_finished_time_text());

                    viewHolder.setText(R.id.tv_study_base_num, item.getProject_progress() + "%");
                    //  viewHolder.setText(R.id.time, item.getC_date() + " " + item.getC_start_time() + "-" + item.getC_end_time());
                    viewHolder.setCBProgress(R.id.tv_study_base_pro, item.getProject_progress());
                }
            };
            if (recyclerView1 != null) {
                recyclerView1.setAdapter(listAdapter1);
            }
        }
        if (data.getKeshi().getHas_flag().equals("1")) {
            rel2.addView(includeView02);
            projectType2.setText("课时项目");
            total2.setText(data.getKeshi().getFinished() + "/" + data.getKeshi().getTotal() + "已完成");

            recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            CommonAdapter listAdapter2 = new CommonAdapter<MyStudyBean.DataBeanXXXX.KeshiBean.DataBeanXX>(getActivity(), R.layout.item_study_mystudy_layout, data.getKeshi().getData()) {
                @Override
                protected void convert(ViewHolder viewHolder, MyStudyBean.DataBeanXXXX.KeshiBean.DataBeanXX item, int position) {
                    viewHolder.setText(R.id.tv_study_base, item.getEntry_name());
                    viewHolder.setText(R.id.tv_study_base_status, "预计完成时间" + item.getPre_finished_time());
                    viewHolder.setText(R.id.tv_study_post, item.getPre_finished_time_text());
                    viewHolder.setText(R.id.tv_study_base_num, item.getFinishend_count() + "/" + item.getTotal_number());
                    //  viewHolder.setText(R.id.time, item.getC_date() + " " + item.getC_start_time() + "-" + item.getC_end_time());
                    int pro = (int) ((item.getFinishend_count() * 1.0) / item.getTotal_number() * 100);
                    viewHolder.setCBProgress(R.id.tv_study_base_pro, pro);

                }
            };
            if (recyclerView2 != null) {
                recyclerView2.setAdapter(listAdapter2);

            }
        }
        if (data.getPaiban().getHas_flag().equals("1")) {
            rel3.addView(includeView03);

            if (is_music.equals("0")) {
                projectType3.setText("排版翻译");
            } else {
                projectType3.setText("录制课");
            }

            total3.setText(data.getPaiban().getFinished() + "/" + data.getPaiban().getTotal() + "已完成");

            recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            CommonAdapter listAdapter3 = new CommonAdapter<MyStudyBean.DataBeanXXXX.PaibanBean.DataBeanX>(getActivity(), R.layout.item_study_mystudy_layout, data.getPaiban().getData()) {
                @Override
                protected void convert(ViewHolder viewHolder, MyStudyBean.DataBeanXXXX.PaibanBean.DataBeanX item, int position) {
                    viewHolder.setText(R.id.tv_study_base, item.getEntry_name());
                    viewHolder.setText(R.id.tv_study_base_status, "预计完成时间" + item.getPre_finished_time());
                    viewHolder.setText(R.id.tv_study_post, item.getPre_finished_time_text());
                    viewHolder.setText(R.id.tv_study_base_num, item.getProject_progress() + "%");
                    //  viewHolder.setText(R.id.time, item.getC_date() + " " + item.getC_start_time() + "-" + item.getC_end_time());
                    viewHolder.setCBProgress(R.id.tv_study_base_pro, item.getProject_progress());

                }
            };
            if (recyclerView3 != null) {
                recyclerView3.setAdapter(listAdapter3);

            }
        }
        if (data.getBase().getHas_flag().equals("1")) {
            rel4.addView(includeView04);
            projectType4.setText("基础课");
            total4.setText(data.getBase().getFinished() + "/" + data.getBase().getTotal() + "已完成");
            recyclerView4.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            CommonAdapter listAdapter4 = new CommonAdapter<MyStudyBean.DataBeanXXXX.BaseBean.DataBeanXXX>(getActivity(), R.layout.item_study_mystudy_layout, data.getBase().getData()) {
                @Override
                protected void convert(ViewHolder viewHolder, MyStudyBean.DataBeanXXXX.BaseBean.DataBeanXXX item, int position) {
                    viewHolder.setText(R.id.tv_study_base, item.getEntry_name());
                    viewHolder.setText(R.id.tv_study_base_status, "预计完成时间" + item.getPre_finished_time());
                    viewHolder.setText(R.id.tv_study_post, item.getPre_finished_time_text());
                    viewHolder.setText(R.id.tv_study_base_num, item.getFinishend_count() + "/" + item.getTotal_number());
                    //  viewHolder.setText(R.id.time, item.getC_date() + " " + item.getC_start_time() + "-" + item.getC_end_time());
                    int pro = (int) ((item.getFinishend_count() * 1.0) / item.getTotal_number() * 100);
                    viewHolder.setCBProgress(R.id.tv_study_base_pro, pro);

                }
            };
            if (recyclerView4 != null) {
                recyclerView4.setAdapter(listAdapter4);

            }
        }

    }


    @Override
    protected void loadData() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("s_id", SPUtils.get("sid", "0"));
        map.put("is_music", is_music);

        mHttpHelper.post(Api.URL + "new_study", map, new BaseCallback<MyStudyBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, MyStudyBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    setDataToIncludeLayout(resultBean);
                } else {
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


    @Override
    protected void setOnclickListeners() {

    }


}
