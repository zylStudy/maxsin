package com.renyi.maxsin.module.Study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.Study.bean.CourseDetailsBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class CourseDetailActivity extends BaseActivity {
    @BindView(R.id.cover_image)
    ImageView coverImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.course_type)
    TextView courseType;
    @BindView(R.id.course_name)
    TextView courseName;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.course_status)
    TextView courseStatus;
    @BindView(R.id.course_imp_rel)
    RelativeLayout courseImpRel;
    @BindView(R.id.homwork_comment_rel)
    RelativeLayout homworkCommentRel;
    @BindView(R.id.take_rel)
    RelativeLayout takeRel;
    private String c_id = "";

    //include_course_details_layout
    @Override
    protected int getLayoutId() {

        Intent intent = getIntent();
        if (intent != null) {
            c_id = intent.getExtras().getString("c_id");
        }
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initView() {
        showTitleAndBack("课程详情");
    }

    @Override
    protected void loadData() {
getDataFromServer();

    }

    private void getDataFromServer() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("c_id", c_id);

        mHttpHelper.post(Api.URL + "course_info", map, new BaseCallback<CourseDetailsBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, CourseDetailsBean resultBean) {

                if (resultBean.getCode().equals("800")) {

                    CourseDetailsBean.DataBean resultBeanData = resultBean.getData();
                    name.setText(resultBeanData.getT_name());
                    type.setText(resultBeanData.getTeacher_profess());
                    courseType.setText(resultBeanData.getC_type());
                    courseName.setText(resultBeanData.getC_name());
                    time.setText(resultBeanData.getC_date() + resultBeanData.getC_start_time() + "  " + resultBeanData.getC_end_time());
                    courseStatus.setText(resultBeanData.getC_status());
                    Glide.with(CourseDetailActivity.this).load(resultBeanData.getHead_img()).asBitmap().centerCrop().into(coverImage);


                    if (resultBeanData.getC_status().equals("0")) {
                        courseStatus.setText("待上课");
                        courseStatus.setTextColor(ContextCompat.getColor(CourseDetailActivity.this, R.color.colorOuteb4161));

                    }
                    if (resultBeanData.getC_status().equals("1")) {

                        courseStatus.setText("上课中");
                        courseStatus.setTextColor(ContextCompat.getColor(CourseDetailActivity.this, R.color.colorOuteb4161));


                    }
                    if (resultBeanData.getC_status().equals("2")) {
                        courseStatus.setText("待确认");
                        courseStatus.setTextColor(ContextCompat.getColor(CourseDetailActivity.this, R.color.colorOuteb4161));
                    }

                    if (resultBeanData.getC_status().equals("3") || resultBeanData.getC_status().equals("4")) {
                        courseStatus.setText("缺席");
                        courseStatus.setTextColor(ContextCompat.getColor(CourseDetailActivity.this, R.color.colorfe9334));
                    }
                    if (resultBeanData.getC_status().equals("5") || resultBeanData.getC_status().equals("6")) {

                        courseStatus.setText("调课");
                        courseStatus.setTextColor(ContextCompat.getColor(CourseDetailActivity.this, R.color.colorOuteb4161));


                    }

                    if (resultBeanData.getC_status().equals("8")) {


                        courseStatus.setText("待评价");
                        courseStatus.setTextColor(ContextCompat.getColor(CourseDetailActivity.this, R.color.colornor));


                    }
                    if (resultBeanData.getC_status().equals("9")) {


                        courseStatus.setText("已完成");
                        courseStatus.setTextColor(ContextCompat.getColor(CourseDetailActivity.this, R.color.color9));

                    }

                    if (!resultBeanData.getC_key().equals("")) {
                        View view = LayoutInflater.from(CourseDetailActivity.this).inflate(R.layout.include_course_details_layout, null);
                        TextView imp_course = view.findViewById(R.id.imp_course);
                        TextView home_work = view.findViewById(R.id.home_work);
                        courseImpRel.addView(view);
                        imp_course.setText(resultBeanData.getC_key());
                        home_work.setText(resultBeanData.getAdd_homework());
                    }
                    if (!resultBeanData.getHomework_evaluate().equals("")) {
                        View view = LayoutInflater.from(CourseDetailActivity.this).inflate(R.layout.include_course_comment_layout, null);
                        TextView comment_course = view.findViewById(R.id.comment_course);
                        homworkCommentRel.addView(view);
                        comment_course.setText(resultBeanData.getHomework_evaluate());
                    }


                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    protected void setOnClickListeners() {
        takeRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("c_id", c_id);

                Intent intent = new Intent(CourseDetailActivity.this,
                        OperationActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }


}
