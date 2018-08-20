package com.renyi.maxsin.module.maxsin;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.renyi.maxsin.R;
import com.renyi.maxsin.utils.DensityUtil;
import com.renyi.maxsin.utils.ShadowDrawable;

public class StudentExampleDetailsactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_example_detailsactivity);
        initViews();
    }
    private int dip2px(float dpValue) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int            px      = (int) (dpValue * (metrics.densityDpi / 160f));
        return px;
    }
    private void initViews() {
        View bg = findViewById(R.id.rel);
        ImageView image = (ImageView) findViewById(R.id.image);


//        CornerBean bean7 = new CornerBean();
//        bean7.resId = R.mipmap.ic_me_act_bg;
//        bean7.type = RoundCornersTransformation.CornerType.ALL;
//
//        RoundCornersTransformation transformation =
//                new RoundCornersTransformation(this,
//                        DensityUtil.dip2px(this, 50),
//                        bean7.type);
//        Glide.with(this)
//                .load( bean7.resId)
//                .bitmapTransform(transformation)
//                .into(image);




        ShadowDrawable shadowDrawable = new ShadowDrawable();
        shadowDrawable.setColor(ContextCompat.getColor(this, R.color.colora))    //shadowcolor
                .setOffsetY(DensityUtil.dip2px(this, 5))    //阴影下偏移--offset of the shadow
                .setRadius(DensityUtil.dip2px(this, 8))     //四角半径--concern of the rectangle
                .setEdgeShadowWidth(DensityUtil.dip2px(this, 8))   //四周阴影半径-- the shadow of each edge of the rectangle
                .setFilterColor(0x56ffffff)                 //中间值，越大阴影越接近设置的值-- the slot to said how close to the shadowcolor
                .setTopMargin(DensityUtil.dip2px(this, 3))  //上间距--top margin
                .setParentHeight(DensityUtil.dip2px(this, 200))  //设置要依附的View的高度 -- the height of parent view
                .attach(bg)                                 //要在哪个View上面加阴影-- the shadow parent.※
                .build();                                   //显示，必调-- to show the shadow.※
    }
}
