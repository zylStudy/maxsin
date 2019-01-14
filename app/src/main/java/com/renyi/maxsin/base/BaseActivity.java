package com.renyi.maxsin.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renyi.maxsin.R;
import com.renyi.maxsin.module.me.ClickListener;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void loadData();

    protected abstract void setOnClickListeners();

    RelativeLayout titleLayout;
    RelativeLayout contentLayout;
    RelativeLayout emptyLayout;
    TextView titleTv, operationTv;
    RelativeLayout backRel, searchRel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_base);
        titleLayout = (RelativeLayout) findViewById(R.id.title_layout);
        contentLayout = (RelativeLayout) findViewById(R.id.content_layout);
        emptyLayout = (RelativeLayout) findViewById(R.id.empty_layout);
        initIncludeView();
        ButterKnife.bind(this);
        initView();
        loadData();
        setOnClickListeners();

//        for (double i = 1; i <Double.MAX_VALUE ; i++) {
//            if (i == 11.13) {
//                System.out.println("Happy Birthday!Forever eighteen years old !");
//            }else{
//                System.out.println("Happy every day!");
//            }
//        }


    }

    protected void initIncludeView() {

        RelativeLayout contentView = (RelativeLayout) LayoutInflater.from(this).inflate(getLayoutId(), null);
        contentLayout.addView(contentView);

        RelativeLayout headView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.include_head_layout, null);
        titleTv = headView.findViewById(R.id.head_title_tv);
        operationTv = headView.findViewById(R.id.operation);
        backRel = headView.findViewById(R.id.back_rel);
        searchRel = headView.findViewById(R.id.search_rel);
        titleLayout.addView(headView);
    }


    public void showTitleAndBack(String titleStr) {
        titleTv.setText(titleStr);
        backRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    public void hideTitleAndBack() {
        titleLayout.removeAllViews();

    }

    public void showOrHideSearchBt(boolean b, String operationTvStr) {
        operationTv.setText(operationTvStr);
        if (b) {
            searchRel.setVisibility(searchRel.VISIBLE);
        } else {
            searchRel.setVisibility(searchRel.GONE);
        }

        searchRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.getClickListener();
            }
        });

    }

    public void setSearchBtColors(int colors, int size) {
        operationTv.setTextColor(ContextCompat.getColor(this, colors));
        operationTv.setTextSize(size);
    }

    public void showEmpty(boolean b) {
        if (b) {
            emptyLayout.setVisibility(emptyLayout.VISIBLE);
        } else {
            emptyLayout.setVisibility(emptyLayout.GONE);
        }

    }

    public void showToastLong(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();

    }

    public void showToastShort(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    private ClickListener clickListener;

    public void setclickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
