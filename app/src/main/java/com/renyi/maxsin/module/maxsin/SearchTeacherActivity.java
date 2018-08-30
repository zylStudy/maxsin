package com.renyi.maxsin.module.maxsin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.listview.CommonAdapter;
import com.renyi.maxsin.adapter.listview.ViewHolder;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.maxsin.bean.TeacherBeans;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.KeyboardUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class SearchTeacherActivity extends BaseActivity {


    int page = 1;
    CommonAdapter commonAdapter;

    @BindView(R.id.search_rellayout)
    RelativeLayout searchRel;
    @BindView(R.id.edit_info)
    EditText editInfo;
    @BindView(R.id.cancle_tv)
    TextView cancleTv;
    @BindView(R.id.listView)
    ListView listView;
    private boolean isBottom = false;
    private List<TeacherBeans.DataBean.ListBean> course_listAll = new ArrayList<>();
    TeacherBeans resultBeans;

    @Override
    protected int getLayoutId() {


        return R.layout.activity_search_mvp;
    }

    @Override
    protected void initView() {
        editInfo.setHint("请输入导师姓名");
        hideTitleAndBack();

        commonAdapter = new CommonAdapter<TeacherBeans.DataBean.ListBean>(this, R.layout.item_teacher_list, course_listAll) {

            @Override
            protected void convert(final ViewHolder viewHolder, TeacherBeans.DataBean.ListBean item, final int position) {
                viewHolder.setText(R.id.name, item.getName());
                viewHolder.setText(R.id.major, item.getMajor());
                viewHolder.setText(R.id.school, item.getEducollege());
                viewHolder.setText(R.id.info, item.getDesc());
                viewHolder.setShadowDrawable(R.id.rel, 200);
                viewHolder.setCornerRadiusImageViewNetUrl(R.id.head_image, item.getPhoto(), 10);
            }
        };

        listView.setAdapter(commonAdapter);


    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        searchRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editInfo.getText().toString().trim().equals("")) {
                    course_listAll.clear();
                    commonAdapter.notifyDataSetChanged();
                    page = 1;
                    loadDataFromSer(editInfo.getText().toString().trim());
                    KeyboardUtils.hideSoftInput(SearchTeacherActivity.this);

                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                readyGo(TeacherDetailsActivity.class, course_listAll.get(position).getId());
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
                            loadDataFromSer(editInfo.getText().toString().trim());
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

    private void loadDataFromSer(String str) {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("page", page + "");

        map.put("keywords", str);


        mHttpHelper.post(Api.URL + "search_teacher", map, new BaseCallback<TeacherBeans>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, TeacherBeans resultBean) {

                if (resultBean.getCode().equals("800")) {
                    resultBeans = resultBean;
                    List<TeacherBeans.DataBean.ListBean> listBeanList = resultBean.getData().getList();

                    course_listAll.addAll(listBeanList);
                    if (course_listAll.size() != 0) {
                        commonAdapter.notifyDataSetChanged();
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


}
