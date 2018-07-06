package com.renyi.maxsin.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zhangyuliang on 2016/12/7.
 */

public class SplashAdapter extends PagerAdapter {
    private List<ImageView> list;

    public SplashAdapter(List<ImageView> list) {
        this.list = list;

    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(list.get(position));


        return list.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}

