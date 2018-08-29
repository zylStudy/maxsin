package com.renyi.maxsin.module.maxsin;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.maxsin.bean.TeacherListBeans;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.DensityUtil;
import com.renyi.maxsin.utils.ShadowDrawable;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class TeacherDetailsActivity extends BaseActivity {
    String t_id = "";
    @BindView(R.id.cover_image)
    ImageView coverImage;
    @BindView(R.id.image_bg)
    ImageView imageBg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.major)
    TextView major;
    @BindView(R.id.school)
    TextView school;
    @BindView(R.id.begood)
    TextView begood;
    @BindView(R.id.info_rel)
    RelativeLayout infoRel;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.include_reward)
    RelativeLayout includeReward;
    @BindView(R.id.include_experience)
    RelativeLayout includeExperience;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private void setViewBindData(TeacherListBeans resultBean) {
        TeacherListBeans.DataBean dataBean = resultBean.getData();
        if (dataBean != null) {
            name.setText(dataBean.getName());
            major.setText(dataBean.getMajor());
            school.setText(dataBean.getEducollege());
            begood.setText("擅长课程：\n" + dataBean.getGoodcourse());
            info.setText(dataBean.getDesc());

            Glide.with(this).load(dataBean.getPhoto()).asBitmap().centerCrop().into(coverImage);

            //            {
            //                @Override
            //                protected void setResource(Bitmap resource) {
            //                    RoundedBitmapDrawable circularBitmapDrawable =
            //                            RoundedBitmapDrawableFactory.create(getResources(), resource);
            //                    circularBitmapDrawable.setCornerRadius(12);
            //                    coverImage.setImageDrawable(circularBitmapDrawable);
            //                }
            //            });


            if (dataBean.getPrize() != null && dataBean.getPrize().size() != 0) {
                View viewReward = LayoutInflater.from(this).inflate(R.layout.include_teacher_details_layout, null);
                RelativeLayout ecpRelReward = viewReward.findViewById(R.id.ecp_rel);
                TextView infoTvReward = viewReward.findViewById(R.id.info_tv);
                TextView type = viewReward.findViewById(R.id.type);
                type.setText("获得奖项");
                setShadowDrawable(ecpRelReward, 155);
                StringBuffer stringBufferReward = new StringBuffer();
                for (int i = 0; i < dataBean.getPrize().size(); i++) {
                    if (!dataBean.getPrize().get(i).equals("")) {
                        stringBufferReward.append(dataBean.getPrize().get(i) + "\n");
                    }

                }
                infoTvReward.setText(stringBufferReward.toString().substring(0, stringBufferReward.toString().length() - 2));

                includeReward.addView(viewReward);
            }
            if (dataBean.getExperience() != null && dataBean.getExperience().size() != 0) {
                View view = LayoutInflater.from(this).inflate(R.layout.include_teacher_details_layout, null);
                RelativeLayout ecpRel = view.findViewById(R.id.ecp_rel);
                TextView infoTv = view.findViewById(R.id.info_tv);
                TextView type = view.findViewById(R.id.type);
                type.setText("项目经历");
                setShadowDrawable(ecpRel, 155);
                StringBuffer stringBufferExperience = new StringBuffer();
                for (int i = 0; i < dataBean.getExperience().size(); i++) {
                    if (!dataBean.getExperience().get(i).equals("")) {
                        stringBufferExperience.append(dataBean.getExperience().get(i) + "\n");
                    }

                }
                infoTv.setText(stringBufferExperience.toString().substring(0, stringBufferExperience.toString().length() - 2));
                includeExperience.addView(view);

            }
            if (dataBean.getWorkpic() != null && dataBean.getWorkpic().size() != 0) {

                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                CommonAdapter adapter = new CommonAdapter<String>(this, R.layout.item_teacher_details_image_layout, dataBean.getWorkpic()) {
                    @Override
                    protected void convert(ViewHolder viewHolder, String item, int position) {

                        viewHolder.setCornerRadiusImageViewNetUrl(R.id.image, item, 10);
                    }
                };
                recyclerView.setAdapter(adapter);

            }
        }
    }

    @Override
    protected int getLayoutId() {
        t_id = getIntent().getExtras().getString("id");
        return R.layout.activity_teacher_details;
    }

    @Override
    protected void initView() {
        showTitleAndBack("导师详情");
        Glide.with(this).load(R.mipmap.ic_mvp_first_bg).asBitmap().into(imageBg);

    }

    protected void setShadowDrawable(View view, int high) {
        ShadowDrawable shadowDrawable = new ShadowDrawable();
        shadowDrawable.setColor(ContextCompat.getColor(this, R.color.colora))    //shadowcolor
                .setOffsetY(DensityUtil.dip2px(this, 5))    //阴影下偏移--offset of the shadow
                .setRadius(DensityUtil.dip2px(this, 8))     //四角半径--concern of the rectangle
                .setEdgeShadowWidth(DensityUtil.dip2px(this, 8))   //四周阴影半径-- the shadow of each edge of the rectangle
                .setFilterColor(0x56ffffff)                 //中间值，越大阴影越接近设置的值-- the slot to said how close to the shadowcolor
                .setTopMargin(DensityUtil.dip2px(this, 3))  //上间距--top margin
                .setParentHeight(DensityUtil.dip2px(this, high))  //设置要依附的View的高度 -- the height of parent view
                .attach(view)                                 //要在哪个View上面加阴影-- the shadow parent.※
                .build();

    }


    @Override
    protected void loadData() {
        loadDataFromSer();
    }

    @Override
    protected void setOnClickListeners() {

    }

    private void loadDataFromSer() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);

        map.put("t_id", t_id);
        mHttpHelper.post(Api.URL + "teacher_info", map, new BaseCallback<TeacherListBeans>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, TeacherListBeans resultBean) {

                if (resultBean.getCode().equals("800")) {
                    setViewBindData(resultBean);

                }

            }


            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


}
