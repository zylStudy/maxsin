package com.renyi.maxsin.module.maxsin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.MultiItemTypeAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.maxsin.bean.MaxsinUniversityRankBeans;
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

/**
 * Created by zhangyuliang on 2018/3/22.
 * 课程
 */

public class UniversitiesRankingActivity extends BaseActivity {

    @BindView(R.id.type01)
    TextView type01;
    @BindView(R.id.typeimage01)
    ImageView typeimage01;
    @BindView(R.id.rel01)
    RelativeLayout rel01;
    @BindView(R.id.type02)
    TextView type02;
    @BindView(R.id.typeimage02)
    ImageView typeimage02;
    @BindView(R.id.rel02)
    RelativeLayout rel02;

    @BindView(R.id.typeimage03)
    ImageView typeimage03;
    @BindView(R.id.rel03)
    RelativeLayout rel03;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.back_rel_layout)
    RelativeLayout backRel;
    @BindView(R.id.search)
    RelativeLayout searchRel;
    @BindView(R.id.type03)
    TextView type03;
    @BindView(R.id.head_title)
    TextView headTitle;
    private List<MaxsinUniversityRankBeans.DataBean.ColleglistBean> course_listAll = new ArrayList<>();
    private List<MaxsinUniversityRankBeans.DataBean.ColleglistBean> course_list;
    CommonAdapter listAdapter, popuAdapter;
    int page = 1;
    MaxsinUniversityRankBeans resultBeans;
    String t_value = "-1", c_type = "-1", c_status = "-1";
    int i_value = 0, i_type = 0, i_status = 0, flage = 0;
    RecyclerView popuRecyclerView;
    PopupWindow popView;
    Map<String, String> popMap = new HashMap<>();
    List<String> popList = new ArrayList();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_maxsin_examplea;
    }

    @Override
    protected void initView() {
        hideTitleAndBack();
        type03.setText("不限专业");
        headTitle.setText("院校排名");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listAdapter = new CommonAdapter<MaxsinUniversityRankBeans.DataBean.ColleglistBean>(this, R.layout.item_rank_list, course_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, MaxsinUniversityRankBeans.DataBean.ColleglistBean item, int position) {
                viewHolder.setText(R.id.school, item.getChname());
                viewHolder.setText(R.id.eschool, item.getEnname());
                viewHolder.setText(R.id.major, item.getMajor());
                viewHolder.setText(R.id.address, item.getLocation());
                viewHolder.setText(R.id.time, item.getApplyendtime());
                viewHolder.setText(R.id.difficulty, item.getApplydifficulty());
                viewHolder.setShadowDrawable(R.id.head_rel);
                viewHolder.setText(R.id.language, "TOEFL:" + item.getToefl() + " | IELTS:" + item.getIelts());
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.head_image, item.getLogopic(), 10);
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.image_cover, item.getShowpic(), 10);
                // viewHolder.setImageViewNetUrlRound(R.id.image_cover, item.getCover(),40);

            }
        };
        if (recyclerView != null) {
            recyclerView.setAdapter(listAdapter);

        }


    }

    @Override
    protected void loadData() {

        loadDataFromSer();

    }


    private void loadDataFromSer() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);

        map.put("current_page", page + "");
        if (!c_type.equals("-1")) {
            map.put("country", c_type);
        }
        if (!t_value.equals("-1")) {
            map.put("degree", t_value);
        }
        if (!c_status.equals("-1")) {
            map.put("major", c_status);
        }
        mHttpHelper.post(Api.URL + "college_list", map, new BaseCallback<MaxsinUniversityRankBeans>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, MaxsinUniversityRankBeans resultBean) {

                if (resultBean.getCode().equals("800")) {
                    resultBeans = resultBean;
                    course_list = resultBean.getData().getColleglist();

                    course_listAll.addAll(course_list);
                    if (course_listAll.size() != 0) {
                        listAdapter.notifyDataSetChanged();
                        showEmpty(false);
                    } else {
                        showEmpty(true);
                    }
                } else {
                    showEmpty(true);
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    protected void setOnClickListeners() {

        initPopu();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (resultBeans != null) {
                    page++;
                    int parseInt = resultBeans.getData().getTotal_page();
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

        rel01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flage = 1;
                popList.clear();
                popMap.clear();

                popList.add("不限国家");
                popList.add("美国");
                popList.add("英国");
                popList.add("澳大利亚");
                popList.add("法国");
                popList.add("德国");
                popList.add("意大利");
                popList.add("加拿大");
                popList.add("欧洲");

                popMap.put("不限国家", "-1");
                popMap.put("美国", "1");
                popMap.put("英国", "2");
                popMap.put("澳大利亚", "3");
                popMap.put("法国", "4");
                popMap.put("德国", "5");
                popMap.put("意大利", "6");
                popMap.put("加拿大", "7");
                popMap.put("欧洲", "8");

                popView.showAsDropDown(rel01);
                typeimage01.setBackgroundResource(R.mipmap.ic_maxsin_up_bg);
                popuAdapter.notifyDataSetChanged();


            }
        });
        rel02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flage = 2;
                popList.clear();
                popMap.clear();
                popList.add("不限学历");
                popList.add("本科");
                popList.add("研究生");


                popMap.put("不限学历", "-1");
                popMap.put("本科", "1");
                popMap.put("研究生", "2");


                popView.showAsDropDown(rel02);
                typeimage02.setBackgroundResource(R.mipmap.ic_maxsin_up_bg);
                popuAdapter.notifyDataSetChanged();


            }
        });
        rel03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flage = 3;
                popList.clear();
                popMap.clear();

                popList.add("不限专业");
                popList.add("服装设计");
                popList.add("平面设计");
                popList.add("景观设计");
                popList.add("建筑设计");
                popList.add("室内设计");
                popList.add("工业设计");
                popList.add("交互设计");
                popList.add("插画设计");
                popList.add("其他");

                popMap.put("不限专业", "-1");
                popMap.put("服装设计", "1");
                popMap.put("平面设计", "2");
                popMap.put("景观设计", "3");
                popMap.put("建筑设计", "4");
                popMap.put("室内设计", "5");
                popMap.put("工业设计", "6");
                popMap.put("交互设计", "7");
                popMap.put("插画设计", "8");
                popMap.put("其他", "9");

                popView.showAsDropDown(rel03);
                typeimage03.setBackgroundResource(R.mipmap.ic_maxsin_up_bg);
                popuAdapter.notifyDataSetChanged();


            }
        });

        popView.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                typeimage01.setBackgroundResource(R.mipmap.ic_maxsin_down_bg);
                typeimage02.setBackgroundResource(R.mipmap.ic_maxsin_down_bg);
                typeimage03.setBackgroundResource(R.mipmap.ic_maxsin_down_bg);
            }
        });

        listAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", course_listAll.get(position).getId());
                Intent intent = new Intent(UniversitiesRankingActivity.this, UniversityDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
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
        searchRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UniversitiesRankingActivity.this, SearchUniversityActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initPopu() {
        popView = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(R.layout.popview_list_layout, null);
        popuRecyclerView = view.findViewById(R.id.typeRecyclerView);

        popView.setContentView(view);
        popView.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);// 设置菜单的宽度（需要和菜单于右边距的距离搭配，可以自己调到合适的位置）
        popView.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        popView.setFocusable(true);// 获取焦点
        popView.setTouchable(true); // 设置PopupWindow可触摸
        popView.setOutsideTouchable(true);
        setPopViewAdapter();
        TextView dis_tv = view.findViewById(R.id.dis_tv);
        dis_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popView.isShowing()) {
                    popView.dismiss();
                }
            }
        });
    }


    private void setPopViewAdapter() {
        popuRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        popuAdapter = new CommonAdapter<String>(this, R.layout.item_study_course_type_select, popList) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.type, item);

                if (flage == 1) {

                    if (i_value == position) {
                        viewHolder.setVisible(R.id.change, true);
                    } else {
                        viewHolder.setVisible(R.id.change, false);
                    }
                }

                if (flage == 2) {
                    if (i_type == position) {
                        viewHolder.setVisible(R.id.change, true);
                    } else {
                        viewHolder.setVisible(R.id.change, false);
                    }
                }


                if (flage == 3) {
                    if (i_status == position) {
                        viewHolder.setVisible(R.id.change, true);
                    } else {
                        viewHolder.setVisible(R.id.change, false);
                    }
                }

            }
        };
        popuRecyclerView.setAdapter(popuAdapter);
        popuAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
                                               @Override
                                               public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                                   if (popView.isShowing()) {
                                                       popView.dismiss();
                                                   }
                                                   if (flage == 1) {
                                                       i_value = position;

                                                       String str_t_value = popList.get(position);
                                                       type01.setText(str_t_value);
                                                       t_value = popMap.get(str_t_value);

                                                   }
                                                   if (flage == 2) {
                                                       i_type = position;
                                                       String str_t_value = popList.get(position);
                                                       type02.setText(str_t_value);
                                                       c_type = popMap.get(str_t_value);

                                                   }
                                                   if (flage == 3) {
                                                       i_status = position;

                                                       String str_t_value = popList.get(position);
                                                       type03.setText(str_t_value);
                                                       c_status = popMap.get(str_t_value);

                                                   }
                                                   if (course_list != null) {
                                                       course_list.clear();
                                                       course_listAll.clear();

                                                   }
                                                   page = 1;
                                                   loadDataFromSer();

                                               }

                                               @Override
                                               public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder,
                                                                              int position) {
                                                   return false;
                                               }
                                           }

        );
    }


}
