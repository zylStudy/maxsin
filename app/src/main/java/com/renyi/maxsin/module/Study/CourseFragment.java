package com.renyi.maxsin.module.Study;

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
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.Study.bean.CoursesListBean;
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

public class CourseFragment extends Basefragment {

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
    @BindView(R.id.type03)
    TextView type03;
    @BindView(R.id.typeimage03)
    ImageView typeimage03;
    @BindView(R.id.rel03)
    RelativeLayout rel03;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<CoursesListBean.DataBean.CourseListBean> course_listAll = new ArrayList<>();
    private List<CoursesListBean.DataBean.CourseListBean> course_list;
    CommonAdapter listAdapter, popuAdapter;
    int page = 1;
    CoursesListBean.DataBean coursesBean;
    String t_value = "-1", c_type = "-1", c_status = "-1";
    int i_value = 0, i_type = 0, i_status = 0, flage = 0;
    RecyclerView popuRecyclerView;
    PopupWindow popView;
    Map<String, String> popMap = new HashMap<>();
    List<String> popList = new ArrayList();
    public static CourseFragment getInstance() {

        return new CourseFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        listAdapter = new CommonAdapter<CoursesListBean.DataBean.CourseListBean>(getActivity(), R.layout.item_study_course_list, course_listAll) {
            @Override
            protected void convert(ViewHolder viewHolder, CoursesListBean.DataBean.CourseListBean item, int position) {
                viewHolder.setText(R.id.name, item.getUser_name());
                viewHolder.setText(R.id.major, item.getProfessional());
                viewHolder.setText(R.id.course_type, item.getC_type());
                viewHolder.setText(R.id.course_name, item.getC_name());
                viewHolder.setText(R.id.time, item.getC_date() + " " + item.getC_start_time() + "-" + item.getC_end_time());
                viewHolder.setImageViewNetUrl(R.id.cover_image, item.getHead_url());
                //                if (item.getIs_first().equals("1")) {
                //                    viewHolder.setBackgroundRes(R.id.flage_image, R.mipmap.ic_is_first_course_bg);
                //                } else {
                //                    viewHolder.setBackgroundRes(R.id.flage_image, 0);
                //                }
                //
                if (item.getC_status().equals("0")) {
                    viewHolder.setText(R.id.status, "待上课");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorOuteb4161);

                }
                if (item.getC_status().equals("1")) {
                    viewHolder.setText(R.id.status, "上课中");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorOuteb4161);

                }
                if (item.getC_status().equals("2")) {
                    viewHolder.setText(R.id.status, "待确认");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorOuteb4161);

                }
                if (item.getC_status().equals("3")) {

                    viewHolder.setText(R.id.status, "缺席");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);
                }
                if (item.getC_status().equals("4")) {
                    viewHolder.setText(R.id.status, "缺席");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorfe9334);

                }
                if (item.getC_status().equals("5")) {
                    viewHolder.setText(R.id.status, "调课");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorOuteb4161);

                }
                if (item.getC_status().equals("6")) {
                    viewHolder.setText(R.id.status, "调课");
                    viewHolder.setTextColorRes(R.id.status, R.color.colorOuteb4161);

                }
                //                if (item.getC_status().equals("7")) {
                //                    viewHolder.setText(R.id.status, "待上传作业");
                //                    viewHolder.setTextColorRes(R.id.status, R.color.colornor);
                //
                //                }
                if (item.getC_status().equals("8")) {
                    viewHolder.setText(R.id.status, "待评价");
                    viewHolder.setTextColorRes(R.id.status, R.color.colornor);

                }
                if (item.getC_status().equals("9")) {
                    viewHolder.setText(R.id.status, "已完成");
                    viewHolder.setTextColorRes(R.id.status, R.color.color9);
                }
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
        map.put("student_id", "1");

        map.put("current_page", page + "");
        if (!c_type.equals("-1")) {
            map.put("c_type", c_type);
        }
        if (!t_value.equals("-1")) {
            map.put("t_value", t_value);
        }
        if (!c_status.equals("-1")) {
            map.put("c_status", c_status);
        }
        mHttpHelper.post(Api.URL + "course_list", map, new BaseCallback<CoursesListBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, CoursesListBean resultBean) {

                if (resultBean.getCode().equals("800")) {

                    coursesBean = resultBean.getData();

                    course_list = resultBean.getData().getCourse_list();
                    course_listAll.addAll(course_list);
                    if (course_listAll.size() != 0) {
                        listAdapter.notifyDataSetChanged();
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
    protected void setOnclickListeners() {

        initPopu();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (coursesBean != null) {
                    page++;
                    int parseInt = coursesBean.getLast_page();
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

                popList.add("全部");
                popList.add("本日");
                popList.add("本周");
                popList.add("本月");
                popMap.put("全部", "-1");
                popMap.put("本日", "1");
                popMap.put("本周", "2");
                popMap.put("本月", "3");

                popView.showAsDropDown(rel01);
                typeimage01.setBackgroundResource(R.mipmap.ic_selec_up);
                popuAdapter.notifyDataSetChanged();


            }
        });
        rel02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flage = 2;
                popList.clear();
                popMap.clear();

                popList.add("全部");
                popList.add("基础课");
                popList.add("项目课");
                popList.add("排版课");
                popList.add("课时项目");
                popMap.put("全部", "-1");
                popMap.put("基础课", "1");
                popMap.put("项目课", "2");
                popMap.put("排版课", "3");
                popMap.put("课时项目", "4");

                popView.showAsDropDown(rel02);
                typeimage02.setBackgroundResource(R.mipmap.ic_selec_up);
                popuAdapter.notifyDataSetChanged();


            }
        });
        rel03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flage = 3;
                popList.clear();
                popMap.clear();

                popList.add("全部");
                popList.add("待上课");
                popList.add("上课中");
                popList.add("待确认");
                popList.add("待评价");
                popList.add("已完成");
                popList.add("缺席");
                popList.add("调课");

                popMap.put("全部", "-1");
                popMap.put("待上课", "0");
                popMap.put("上课中", "1");
                popMap.put("待确认", "2");
                popMap.put("待评价", "8");
                popMap.put("已完成", "9");
                popMap.put("缺席", "10");
                popMap.put("调课", "11");

                popView.showAsDropDown(rel03);
                typeimage03.setBackgroundResource(R.mipmap.ic_selec_up);
                popuAdapter.notifyDataSetChanged();


            }
        });

        popView.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                typeimage01.setBackgroundResource(R.mipmap.ic_selec_down);
                typeimage02.setBackgroundResource(R.mipmap.ic_selec_down);
                typeimage03.setBackgroundResource(R.mipmap.ic_selec_down);
            }
        });

        listAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("c_id", course_listAll.get(position).getC_id());
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

    private void initPopu() {
        popView = new PopupWindow();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popview_list_layout, null);
        popuRecyclerView = view.findViewById(R.id.typeRecyclerView);

        popView.setContentView(view);
        popView.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);// 设置菜单的宽度（需要和菜单于右边距的距离搭配，可以自己调到合适的位置）
        popView.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        popView.setFocusable(true);// 获取焦点
        popView.setTouchable(true); // 设置PopupWindow可触摸
        popView.setOutsideTouchable(true);
        setPopViewAdapter();
    }


    private void setPopViewAdapter() {
        popuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        popuAdapter = new CommonAdapter<String>(getActivity(), R.layout.item_study_course_type_select, popList) {
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
