package com.renyi.maxsin.module.me;

import android.widget.EditText;
import android.widget.Toast;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class SuggestionActivity extends BaseActivity {

    @BindView(R.id.et_info)
    EditText etInfo;

    /**
     * 意见反馈
     */

    @Override
    protected int getLayoutId() {
        return R.layout.activity_suggestion;
    }

    @Override
    protected void initView() {
        showOrHideSearchBt(true, "提交");
        showTitleAndBack("意见反馈");
        setSearchBtColors(R.color.colorOuteb4161, 15);
    }

    @Override
    protected void loadData() {
        setclickListener(new ClickListener() {
            @Override
            public void getClickListener() {

                if (etInfo.getText().toString().trim().length() == 0) {
                    Toast.makeText(SuggestionActivity.this, "意见不能为空", Toast.LENGTH_SHORT).show();

                } else {
                    postDate();
                }


            }
        });
    }

    @Override
    protected void setOnClickListeners() {

    }

    private void postDate() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("uid", (String) SPUtils.get("uid",""));
        map.put("content", etInfo.getText().toString().trim());
        map.put("key", Api.KEY);


        mHttpHelper.post(Api.URL + "app_coments", map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    Toast.makeText(SuggestionActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(SuggestionActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

}
