package com.renyi.maxsin.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.renyi.maxsin.R;

import butterknife.ButterKnife;

/**
 * Created by zhangyuliang on 2017/10/27.
 */

public abstract class Basefragment extends Fragment {
    protected Context mActivity;
    RelativeLayout contentLayout;
    RelativeLayout emptyLayout;
    private View view;

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void loadData();

    protected abstract void setOnclickListeners();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_base, null);

        setHasOptionsMenu(true);
        initIncludeView();
        ButterKnife.bind(this, view);
        initView();
        loadData();
        setOnclickListeners();
        return view;
    }

    protected void initIncludeView() {
        emptyLayout = view.findViewById(R.id.empty_layout);
        contentLayout = view.findViewById(R.id.content_layout);
        RelativeLayout contentView = (RelativeLayout) LayoutInflater.from(mActivity).inflate(getLayoutId(), null);
        contentLayout.addView(contentView);

    }


    public void showEmpty(boolean b) {
        if (b) {
            emptyLayout.setVisibility(emptyLayout.VISIBLE);
        } else {
            emptyLayout.setVisibility(emptyLayout.GONE);
        }

    }

    public void showToastLong(String str) {
        Toast.makeText(mActivity, str, Toast.LENGTH_LONG).show();

    }

    public void showToastShort(String str) {
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(getActivity()).unbind();
    }
}
