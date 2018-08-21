package com.renyi.maxsin.module.maxsin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.renyi.maxsin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyuliang on 2018/3/22.他的作品和内容
 * 课程
 */

public class UniversityDetailsFragment extends Fragment {
    Bundle bundle;
    String type = "";
    @BindView(R.id.webview)
    WebView webview;
    public final static String CSS_STYLE = "<style>* {font-size:14px;line-height:20px;}p {color:#666666;}</style>";


    public static UniversityDetailsFragment getInstance(String info) {
        UniversityDetailsFragment ewsFragment = new UniversityDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", info);
        ewsFragment.setArguments(bundle);
        return ewsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bundle = getArguments();
        type = bundle.getString("type");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_universitydetails, null);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        initView();
        setOnclickListeners();
        return view;
    }


    protected void initView() {
        webview.loadDataWithBaseURL(null, CSS_STYLE + type + "", "text/html", "utf-8", null);

    }


    protected void setOnclickListeners() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(getActivity()).unbind();
    }


}
