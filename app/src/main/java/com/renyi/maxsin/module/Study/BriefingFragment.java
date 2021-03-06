package com.renyi.maxsin.module.Study;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.get.bean.BriefingBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.renyi.maxsin.view.CBProgressBar;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.renyi.maxsin.R.id.tv_study_typesetting_project;

/**
 * Created by zhangyuliang on 2018/3/22.
 * 简报
 */

public class BriefingFragment extends Basefragment {


    @BindView(R.id.cover_image)
    ImageView coverImage;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.class_num)
    TextView classNum;
    @BindView(R.id.time_num)
    TextView timeNum;
    @BindView(R.id.stu_num)
    TextView stuNum;
    @BindView(R.id.tv_study_base)
    TextView tvStudyBase;
    @BindView(R.id.tv_study_base_status)
    TextView tvStudyBaseStatus;
    @BindView(R.id.tv_study_base_num)
    TextView tvStudyBaseNum;
    @BindView(R.id.tv_study_base_pro)
    CBProgressBar tvStudyBasePro;
    @BindView(R.id.tv_study_base_project)
    TextView tvStudyBaseProject;
    @BindView(R.id.tv_study_base_project_status)
    TextView tvStudyBaseProjectStatus;
    @BindView(R.id.tv_study_base_project_num)
    TextView tvStudyBaseProjectNum;
    @BindView(R.id.tv_study_base_project_pro)
    CBProgressBar tvStudyBaseProjectPro;
    @BindView(R.id.tv_study_course_project)
    TextView tvStudyCourseProject;
    @BindView(R.id.tv_study_course_project_status)
    TextView tvStudyCourseProjectStatus;
    @BindView(R.id.tv_study_course_project_num)
    TextView tvStudyCourseProjectNum;
    @BindView(R.id.tv_study_course_project_pro)
    CBProgressBar tvStudyCourseProjectPro;
    @BindView(tv_study_typesetting_project)
    TextView tvStudyTypesettingProject;
    @BindView(R.id.tv_study_typesetting_project_status)
    TextView tvStudyTypesettingProjectStatus;
    @BindView(R.id.tv_study_typesetting_project_num)
    TextView tvStudyTypesettingProjectNum;

    @BindView(R.id.tv_study_typesetting_project_pro)
    CBProgressBar tvStudyTypesettingProjectPro;
    @BindView(R.id.activity_base)
    RelativeLayout activityBase;
    @BindView(R.id.calendar)
    RelativeLayout calendarRel;
    @BindView(R.id.rel01)
    RelativeLayout rel01;
    @BindView(R.id.rel02)
    RelativeLayout rel02;
    @BindView(R.id.rel03)
    RelativeLayout rel03;
    @BindView(R.id.rel04)
    RelativeLayout rel04;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    String is_music = "0";

    public static BriefingFragment getInstance() {

        return new BriefingFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_briefing;
    }

    @Override
    protected void initView() {
        is_music = SPUtils.get("is_music", "-1");
        swipeRefreshLayout.setColorSchemeResources(

                android.R.color.holo_red_light,
                android.R.color.black,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        loadData();

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        });
        Glide.with(getActivity()).load(R.mipmap.ic_personal_page_bg).asBitmap().centerCrop().into(new BitmapImageViewTarget(coverImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                circularBitmapDrawable.setCornerRadius(13);//设置图片圆角
                coverImage.setImageDrawable(circularBitmapDrawable);
            }
        });
        tvStudyBasePro.setHorizonStroke(false);
        tvStudyBaseProjectPro.setHorizonStroke(false);
        tvStudyCourseProjectPro.setHorizonStroke(false);
        tvStudyTypesettingProjectPro.setHorizonStroke(false);
        tvStudyBasePro.setMax(100);
        tvStudyBaseProjectPro.setMax(100);
        tvStudyCourseProjectPro.setMax(100);
        tvStudyTypesettingProjectPro.setMax(100);


    }

    private void setViewHideOrShow(RelativeLayout view) {
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        linearParams.height = 0;// 控件的高度强制设成0
        view.setLayoutParams(linearParams);
    }

    @Override
    protected void loadData() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("is_music", is_music );
        map.put("stu_id", (String) SPUtils.get("sid", "0"));
        mHttpHelper.post(Api.URL + "learn_brief", map, new BaseCallback<BriefingBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, BriefingBean resultBean) {

                if (resultBean.getCode().equals("800")&& name != null) {

                    BriefingBean.DataBean data = resultBean.getData();
                    if (data.getBase_data().getHas_flag().equals("0")) {
                        setViewHideOrShow(rel01);
                    }
                    if (data.getKeshi_data().getHas_flag().equals("0")) {
                        setViewHideOrShow(rel02);
                    }
                    if (data.getPutong_data().getHas_flag().equals("0")) {
                        setViewHideOrShow(rel03);
                    }
                    if (data.getPaiban_data().getHas_flag().equals("0")) {
                        setViewHideOrShow(rel04);
                    }
                    swipeRefreshLayout.setVisibility(swipeRefreshLayout.VISIBLE);

                    name.setText("亲爱的美行学员\n" + data.getS_name() + "，你好");
                    classNum.setText(data.getC_total());
                    timeNum.setText(data.getC_not_done());
                    stuNum.setText(data.getC_have_done());


                    tvStudyBaseStatus.setText(data.getBase_data().getPersent() + "已完成");
                    tvStudyBaseProjectStatus.setText(data.getKeshi_data().getPersent() + "已完成");
                    tvStudyCourseProjectStatus.setText(data.getPutong_data().getPersent() + "已完成");
                    tvStudyTypesettingProjectStatus.setText(data.getPaiban_data().getPersent() + "已完成");

                    String basePro = data.getBase_data().getPersent_num();
                    String keshiPro = data.getKeshi_data().getPersent_num();
                    String putongPro = data.getPutong_data().getPersent_num();
                    String paibanPro = data.getPaiban_data().getPersent_num();


                    tvStudyBaseNum.setText(basePro);
                    tvStudyBaseProjectNum.setText(keshiPro);
                    tvStudyCourseProjectNum.setText(putongPro);
                    tvStudyTypesettingProjectNum.setText(paibanPro);


                    if (is_music .equals("1")) {
                        tvStudyTypesettingProject.setText("录制课进度");
                    } else {
                        tvStudyTypesettingProject.setText("排版课进度");
                    }

                    int iBasePro = 0;
                    int ikeshiPro = 0;
                    int iputongPro = 0;
                    int ipaibanPro = 0;
                    try {
                        if (!basePro.equals("")) {
                            iBasePro = Integer.parseInt(basePro.split("%")[0]);
                        }
                        if (!keshiPro.equals("")) {
                            ikeshiPro = Integer.parseInt(keshiPro.split("%")[0]);
                        }
                        if (!putongPro.equals("")) {
                            iputongPro = Integer.parseInt(putongPro.split("%")[0]);
                        }
                        if (!paibanPro.equals("")) {
                            ipaibanPro = Integer.parseInt(paibanPro.split("%")[0]);
                        }


                    } catch (NullPointerException e) {

                    }

                    tvStudyBasePro.setProgress(iBasePro);
                    tvStudyBaseProjectPro.setProgress(ikeshiPro);
                    tvStudyCourseProjectPro.setProgress(iputongPro);
                    tvStudyTypesettingProjectPro.setProgress(ipaibanPro);


                    Glide.with(getActivity()).load(data.getS_head()).asBitmap().centerCrop().into(new BitmapImageViewTarget(headImage) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            //    circularBitmapDrawable.setCornerRadius(5);设置图片圆角
                            headImage.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    protected void setOnclickListeners() {
        calendarRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        ScheduleActivity.class);
                startActivity(intent);
            }
        });
    }


}
