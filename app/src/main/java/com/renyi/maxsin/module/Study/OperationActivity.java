package com.renyi.maxsin.module.Study;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.Study.bean.OperationBean;
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

import static com.renyi.maxsin.R.id.rel1;

/**
 * 操作记录
 */

public class OperationActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter adapter;
    private String c_id = "";
    List<OperationBean.DataBean> dataBeanListAll=new ArrayList<>();
    @Override
    protected int getLayoutId() {

        Intent intent = getIntent();
        if (intent != null) {
            c_id = intent.getExtras().getString("c_id");
        }

        return R.layout.activity_operation;
    }

    @Override
    protected void initView() {

        showTitleAndBack("操作记录");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new CommonAdapter<OperationBean.DataBean>(OperationActivity.this, R.layout.item_study_operation_list, dataBeanListAll) {
            @Override
            protected void convert(ViewHolder viewHolder,OperationBean.DataBean item, int position) {
                viewHolder.setText(R.id.tv_name, item.getHandle_name());
                viewHolder.setText(R.id.tv_operation, item.getHandle_content());
                viewHolder.setText(R.id.time, item.getHandle_time() );

            }
        };
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);


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
        map.put("c_id", c_id);

        mHttpHelper.post(Api.URL + "course_recorder", map, new BaseCallback<OperationBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, OperationBean resultBean) {

                if (resultBean.getCode().equals("800") && recyclerView != null) {

                    List<OperationBean.DataBean> dataBeanList = resultBean.getData();
                    dataBeanListAll.addAll(dataBeanList);
                    adapter.notifyDataSetChanged();


                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    protected void setOnClickListeners() {

    }


}
