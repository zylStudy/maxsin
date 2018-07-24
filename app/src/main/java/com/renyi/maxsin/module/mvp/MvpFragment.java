package com.renyi.maxsin.module.mvp;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.mvp.bean.MvpRecommendBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class MvpFragment extends Basefragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.viewPagerContainer)
    RelativeLayout viewPagerContainer;
    MyPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mvp;
    }

    @Override
    protected void initView() {
        viewpager.setClipChildren(false);
        //父容器一定要设置这个，否则看不出效果
        viewPagerContainer.setClipChildren(false);

        adapter = new MyPagerAdapter();
        if (viewpager != null) {
            viewpager.setAdapter(adapter);
        }

        //设置ViewPager切换效果，即实现画廊效果
        viewpager.setPageTransformer(true, new ZoomOutPageTransformer());
        //设置预加载数量
        viewpager.setOffscreenPageLimit(2);
        //设置每页之间的左右间隔
        viewpager.addOnPageChangeListener(this);
        loadDataFromSer();

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnclickListeners() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            //return list == null ? 0 : list.size();
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_get_act_list, null);
            //            TextView time = (TextView) view.findViewById(R.id.time);
            //            TextView info = (TextView) view.findViewById(R.id.info);
            //            TextView teacher = (TextView) view.findViewById(R.id.teacher);
            //            TextView tv04 = (TextView) view.findViewById(R.id.tv04);
            //            RelativeLayout tv = (RelativeLayout) view.findViewById(R.id.rel03);
            //
            //            time.setText(list.get(position).getEvaluat_time());
            //            info.setText(list.get(position).getEvaluat_result());
            //            teacher.setText(list.get(position).getEvaluat_teacher());


            //            view.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View v) {
            //                    //
            //                    //                    Intent intent = new Intent(MyAssessActivity.this,
            //                    //                            ProjectAssessDetailsActivity.class);
            //                    //                    intent.putExtras(bundle);
            //                    //                    startActivity(intent);
            //                }
            //            });
            //
                    ((ViewPager) container).addView(view);
            return view;
            //
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }


    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MAX_SCALE = 1f;
        private static final float MIN_SCALE = 0.8f;//0.85f

        @Override
        public void transformPage(View view, float position) {
            //setScaleY只支持api11以上
            if (position < -1) {
                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);
            } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            { // [-1,1]
                //              Log.e("TAG", view + " , " + position + "");
                float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
                view.setScaleX(scaleFactor);
                //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
                if (position > 0) {
                    view.setTranslationX(-scaleFactor * 2);
                } else if (position < 0) {
                    view.setTranslationX(scaleFactor * 2);
                }
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]

                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);

            }
        }

    }


    private void loadDataFromSer() {


        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("u_id", "1");

        mHttpHelper.post(Api.URL + "tuijian", map, new BaseCallback<MvpRecommendBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, MvpRecommendBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    //                    resultBeanData = resultBean.getData();
                    //                    get_list = resultBean.getData().getGet_list();
                    //                    get_listAll.addAll(get_list);
                    //                    if (get_listAll.size() != 0) {
                    //                        adapter.notifyDataSetChanged();
                    //                        showEmpty(false);
                    //                    } else {
                    //                        showEmpty(true);
                    //                    }
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

}
