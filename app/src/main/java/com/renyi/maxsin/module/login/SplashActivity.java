package com.renyi.maxsin.module.login;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.renyi.maxsin.LoginActivity;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.SplashAdapter;
import com.renyi.maxsin.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class SplashActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private List<ImageView> mlist;
    private LinearLayout llPoints;
    private int prePosition = 0;
    private ImageView imageView;
    private TextView open;
    private Animation loadAnimationBottomInto;
    private int imageText[] = {R.mipmap.sp1, R.mipmap.sp2, R.mipmap.sp3, R.mipmap.sp4};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        hideTitleAndBack();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imageView = (ImageView) findViewById(R.id.imageT);
        imageView.setBackgroundResource(imageText[0]);
        loadAnimationBottomInto = AnimationUtils.loadAnimation(this,
                R.anim.alpha_in);
        imageView.startAnimation(loadAnimationBottomInto);


        open = (TextView) findViewById(R.id.open);
        llPoints = (LinearLayout) findViewById(R.id.ll_points);
        getImages();

        llPoints.getChildAt(0).setBackgroundResource(R.mipmap.ic_splash_hl);
        SplashAdapter adapter = new SplashAdapter(mlist);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        open.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                //                    overridePendingTransition(R.anim.push_left_in,
                //                            R.anim.push_left_out);

            }
        });
    }

    private void getImages() {
        mlist = new ArrayList<>();
        //  BitmapUtils util = new BitmapUtils(this);
        int image[] = {R.mipmap.sp001, R.mipmap.sp002, R.mipmap.sp003};
        for (int i = 0; i < image.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            Glide.with(this).load(image[i]).into(iv);
            // util.display(iv, image[i]);
            mlist.add(iv);

            // 添加小圆点
            View view = new View(this);

            view.setBackgroundResource(R.mipmap.ic_splash_nor);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            params.leftMargin = 30;
            view.setLayoutParams(params);
            llPoints.addView(view);

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {//ic_splash_nor
        llPoints.getChildAt(prePosition).setBackgroundResource(R.mipmap.ic_splash_nor);
        llPoints.getChildAt(position)
                .setBackgroundResource(R.mipmap.ic_splash_hl);
        prePosition = position;
//        imageView.setBackgroundResource(imageText[position]);
//
//        imageView.startAnimation(loadAnimationBottomInto);

        if (position == 2) {
            open.startAnimation(loadAnimationBottomInto);
            open.setVisibility(open.VISIBLE);
        } else {
            open.setVisibility(open.INVISIBLE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
