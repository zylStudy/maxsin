package com.renyi.maxsin.module.Study;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.Study.bean.FirstPageBean;
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

/**
 * Created by zhangyuliang on 2018/9/19.
 */

public class ScheduleFragment extends Basefragment {
    @BindView(R.id.tab_recyelerview)
    RecyclerView tabRecyelerview;
    @BindView(R.id.recyelerview)
    RecyclerView recyelerview;
    @BindView(R.id.timetv)
    TextView timetv;
    @BindView(R.id.statusTv)
    TextView statusTv;
    @BindView(R.id.lefrel)
    RelativeLayout lefrel;
    @BindView(R.id.rigrel)
    RelativeLayout rigrel;
    @BindView(R.id.rel_layout)
    RelativeLayout relLayout;
    @BindView(R.id.tab_rel)
    RelativeLayout tabRel;
    private CommonAdapter mAdapter, mAdapterList;
    List<WeekDayBean.DataBean.ReturnWeekDataBean> listAll = new ArrayList<>();
    private String direct = "0", param_date = "";

    List<FirstPageBean.DataBean.HenToShuArrBean> hen_to_shu_arrAll = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_schedule;
    }

    @Override
    protected void initView() {

        initEventAndData();
    }

    @Override
    protected void loadData() {
        getStringData();
    }

    @Override
    protected void setOnclickListeners() {

    }

    private void getStringData() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("stu_id", (String) SPUtils.get("uid", ""));
        mHttpHelper.post("http://edu.mxsyzen.com/stuapigetdate", map, new BaseCallback<WeekDayBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, WeekDayBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    List<WeekDayBean.DataBean.ReturnWeekDataBean> return_week_data = resultBean.getData().getReturn_week_data();
                    listAll.addAll(return_week_data);
                    for (int i = listAll.size() - 1; i > 6; i--) {
                        listAll.remove(i);
                    }

                    mAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }

        });
    }

    protected void initEventAndData() {//

        tabRecyelerview.setLayoutManager(new GridLayoutManager(getActivity(), 7));
        mAdapter = new CommonAdapter<WeekDayBean.DataBean.ReturnWeekDataBean>(getActivity(), R.layout.item_list, listAll) {
            @Override
            protected void convert(ViewHolder holder, WeekDayBean.DataBean.ReturnWeekDataBean s, int position) {
                holder.setText(R.id.week, s.getWeek_data());
                holder.setText(R.id.tv_day, s.getDate_data());


                //                if (s.getData().getCur_day_sign().equals(position + "")) {
                //                    holder.setBackgroundRes(R.id.statu_layout, R.drawable.shape_msg_status);
                //                } else {
                //
                //                    holder.setBackgroundRes(R.id.statu_layout, R.drawable.shap_no_course_status);
                //                }


            }
        };
        tabRecyelerview.setAdapter(mAdapter);


        //-------------------------------------------


        recyelerview.setLayoutManager(new GridLayoutManager(getActivity(), 7));
        mAdapterList = new CommonAdapter<FirstPageBean.DataBean.HenToShuArrBean>(getActivity(), R.layout.item_first_page_list_course, hen_to_shu_arrAll) {
            @Override
            protected void convert(ViewHolder holder, FirstPageBean.DataBean.HenToShuArrBean bean, int position) {
                if (bean.getSname() == null) {
                    holder.setVisible(R.id.rel, false);

                } else {
                    holder.setText(R.id.name, bean.getSname());
                    holder.setText(R.id.time, bean.getC_start_time());
                    holder.setText(R.id.type, bean.getC_type());
                    holder.setVisible(R.id.rel, true);


                    if (bean.getC_status() == 0) {//shap_nor_post_homework_bg
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_post_homework_bg);

                    }
                    if (bean.getC_status() == 1) {
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_post_homework_bg);

                    }
                    if (bean.getC_status() == 2) {//shap_nor_learn_study_bg
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_post_homework_bg);

                    }
                    if (bean.getC_status() == 3) {

                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_learn_study_bg);
                    }
                    if (bean.getC_status() == 4) {
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_learn_study_bg);

                    }
                    if (bean.getC_status() == 5) {
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_post_homework_bg);

                    }
                    if (bean.getC_status() == 6) {
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_post_homework_bg);

                    }
                    if (bean.getC_status() == 7) {//待上传作业//shap_nor_study_bg
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_study_bg);

                    }
                    if (bean.getC_status() == 8) {
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_nor_study_bg);

                    }
                    if (bean.getC_status() == 9) {
                        holder.setBackgroundRes(R.id.rel, R.drawable.shap_arl_study_bg);
                    }


                }


            }
        };
        recyelerview.setAdapter(mAdapterList);

        mAdapterList.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {


                //                if (resultCodeList.getData().getHen_to_shu_arr().get(position).getSname() != null) {
                //                    //                    Bundle bundle = new Bundle();
                //                    //                    bundle.putString("cid", hen_to_shu_arrAll.get(position).getC_id() + "");
                //                    //                    bundle.putString("is_my_course", "1");
                //                    //                    Intent intent = new Intent(getActivity(),
                //                    //                            ClassCourseDetalisActivity.class);
                //                    //                    intent.putExtras(bundle);
                //                    //                    startActivity(intent);
                //                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyelerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx == 0) {
                    //  Toast.makeText(getActivity(), "向右手势11", Toast.LENGTH_SHORT).show();
                } else {
                    //  Toast.makeText(getActivity(), "向左手势11", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //        lefrel.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //
        //                if (resultCodeList.getData().getReturn_week_data() != null && resultCodeList.getData().getReturn_week_data().get(0) != null &&
        //                        resultCodeList.getData().getReturn_week_data().get(0).getDate_date() != null) {
        //                    direct = "-1";
        //                    param_date = resultCodeList.getData().getReturn_week_data().get(0).getDate_date();
        //                    getStringData();
        //                }
        //
        //
        //            }
        //        });
        //        rigrel.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                if (resultCodeList.getData().getReturn_week_data() != null && resultCodeList.getData().getReturn_week_data().get(6) != null &&
        //                        resultCodeList.getData().getReturn_week_data().get(6).getDate_date() != null) {
        //                    direct = "1";
        //                    param_date = resultCodeList.getData().getReturn_week_data().get(6).getDate_date();
        //                    getStringData();
        //                }
        //
        //
        //            }
        //        });

    }

}
