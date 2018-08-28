package com.renyi.maxsin.module.maxsin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.module.maxsin.bean.TagBeans;
import com.renyi.maxsin.module.me.ClickListener;
import com.renyi.maxsin.module.release.interfaces.TagPositionInterface;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
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

public class SelectTagActivity extends BaseActivity {
    @BindView(R.id.num)
    TextView tvNum;

    @BindView(R.id.tag_flowlayout)
    TagFlowLayout tagFlowlayout;
    @BindView(R.id.tag_flowlayout_aways)
    TagFlowLayout tagFlowlayoutAways;
    TagAdapter adapter2, adapter;

    private String[] mVals = new String[]

            {"平面设计", "UI设计", "插画", "摄影", "景观设计", "建筑设计"
            };
    private List<String> showUserList = new ArrayList<>();
    private List<String> showTuiList = new ArrayList<>();
    List<TagBeans.DataBean.UserTagsBean> userTagsList;
    List<TagBeans.DataBean.TuijianBean> tuiJianList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_tab;
    }

    @Override
    protected void initView() {
        showTitleAndBack("标签");
        showOrHideSearchBt(true, "确定");
        tvNum.setText("作品标签");

        //tagFlowlayout.setMaxSelectCount(5);


    }

    private void setTagView() {

        adapter = new TagAdapter<String>(showUserList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(SelectTagActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tagFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        };
        adapter2 = new TagAdapter<String>(showTuiList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(SelectTagActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tagFlowlayoutAways, false);
                tv.setText(s);
                return tv;
            }
        };
        tagFlowlayout.setAdapter(adapter);
        tagFlowlayoutAways.setAdapter(adapter2);
        adapter.setOnTagChangedListener(new TagPositionInterface() {
            @Override
            public void getTagPosition(int position) {
                showTuiList.add(0, showUserList.get(position));
                showUserList.remove(position);
                adapter.notifyDataChanged();
                adapter2.notifyDataChanged();
                tvNum.setText("作品标签" + "(" + showUserList.size() + ")");
            }

            @Override
            public void getClearTagPosition(int position) {

            }
        });
        adapter2.setOnTagChangedListener(new TagPositionInterface() {
            @Override
            public void getTagPosition(int position) {
                //  if (showUserList.size() < 5) {
                showUserList.add(0, showTuiList.get(position));
                tvNum.setText("作品标签" + "(" + showUserList.size() + ")");
                showTuiList.remove(position);
                adapter.notifyDataChanged();
                adapter2.notifyDataChanged();
                // }
            }

            @Override
            public void getClearTagPosition(int position) {

            }
        });
    }


    @Override
    protected void loadData() {
        getTag();
    }


    @Override
    protected void setOnClickListeners() {


        setclickListener(new ClickListener() {
            @Override
            public void getClickListener() {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("tab", (ArrayList<String>) showUserList);
                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
                if (showUserList.size() == 0) {
                    postTag("0");
                } else {
                    String str = "";
                    for (int i = 0; i < showUserList.size(); i++) {
                        for (int j = 0; j < userTagsList.size(); j++) {
                            if (showUserList.get(i).equals(userTagsList.get(j).getTag_name())) {
                                str = str + "," + userTagsList.get(j).getId();
                            }
                        }


                    }

                    for (int i = 0; i < showUserList.size(); i++) {
                        for (int j = 0; j < tuiJianList.size(); j++) {
                            if (showUserList.get(i).equals(tuiJianList.get(j).getTag_name())) {
                                str = str + "," + tuiJianList.get(j).getId();
                            }
                        }


                    }

                    postTag(str.substring(1, str.length()));

                }


            }
        });

    }

    private void postTag(String tagStr) {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("u_id", (String) SPUtils.get("uid", ""));
        map.put("key", Api.KEY);
        map.put("user_tags", tagStr);
        mHttpHelper.post(Api.URL + "saveTags", map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {
                if (resultBean.getCode().equals("800")) {

                } else {
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


    private void getTag() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("u_id", (String) SPUtils.get("uid", ""));
        map.put("key", Api.KEY);

        mHttpHelper.post(Api.URL + "editTags", map, new BaseCallback<TagBeans>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, TagBeans resultBean) {
                if (resultBean.getCode().equals("800")) {
                    if (resultBean.getData() != null) {

                        userTagsList = resultBean.getData().getUser_tags();
                        tuiJianList = resultBean.getData().getTuijian();
                        for (int i = 0; i < userTagsList.size(); i++) {
                            showUserList.add(userTagsList.get(i).getTag_name());
                        }

                        for (int i = 0; i < tuiJianList.size(); i++) {
                            showTuiList.add(tuiJianList.get(i).getTag_name());
                        }
                        setTagView();
                    }
                } else {
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


}
