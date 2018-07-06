package com.renyi.maxsin.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyuliang on 2017/10/31.
 */

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> list;
//    private List<Banner.DataBean> banner;
    private List<String> banner;
    private Context context;
    private ImageView iv;

    private String a;

    public BannerAdapter(List<String> banner, Context context) {
        // this.list = list;
        list = new ArrayList<ImageView>();
        this.banner = banner;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(banner.get(position % banner.size())).into(iv);


        list.add(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String menutype = banner.get(position % banner.size())
//                        .getMenutype();
//                String id = banner.get(position % banner.size()).getTypeid();
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();


//                if (menutype.equals("5")) {
                //                    bundle.putString("title", "App介绍");
                //                    bundle.putString("url", id);
                //                    intent.putExtras(bundle);
                //                    intent.setClass(context, OnLineAssessActivity.class);
                //                    context.startActivity(intent);
                //
                //                }


            }
        });

        container.addView(iv);

        return iv;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(list.get(position % list.size()));
    }
}
