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
import com.renyi.maxsin.module.maxsin.bean.MaxsinUniversityRankBeans;
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


public class SearchUniversityActivity extends BaseActivity {

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
    private List<MaxsinUniversityRankBeans.DataBean.ColleglistBean> course_listAll = new ArrayList<>();
    private List<MaxsinUniversityRankBeans.DataBean.ColleglistBean> course_list;
    MaxsinUniversityRankBeans resultBeans;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_mvp;
    }

    @Override
    protected void initView() {
        editInfo.setHint("请输入院校名称");
        hideTitleAndBack();

        commonAdapter = new CommonAdapter<MaxsinUniversityRankBeans.DataBean.ColleglistBean>(this, R.layout.item_rank_list, course_listAll) {

            @Override
            protected void convert(final ViewHolder viewHolder, final MaxsinUniversityRankBeans.DataBean.ColleglistBean item, final int position) {
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
            }
        };

        listView.setAdapter(commonAdapter);


    }
//    public boolean checkSam(String stringA, String stringB) {
//        char[] c1 = stringA.toCharArray();
//        char[] c2 = stringB.toCharArray();
//        Arrays.sort(c1);
//        Arrays.sort(c2);
//        return Arrays.equals(c1, c2);
//    }

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
                    KeyboardUtils.hideSoftInput(SearchUniversityActivity.this);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                readyGo(UniversityDetailsActivity.class, course_listAll.get(position).getId());
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


        mHttpHelper.post(Api.URL + "searchCollege", map, new BaseCallback<MaxsinUniversityRankBeans>() {
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
