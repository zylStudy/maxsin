package com.renyi.maxsin.module.me;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.Study.StudyActivity;
import com.renyi.maxsin.module.login.ResultBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class MeFragment extends Basefragment {


    @BindView(R.id.headImageView)
    ImageView headImageView;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.study_rel)
    RelativeLayout studyRel;
    @BindView(R.id.act_rel)
    RelativeLayout actRel;
    @BindView(R.id.collection_rel)
    RelativeLayout collectionRel;
    @BindView(R.id.set_rel)
    RelativeLayout setRel;
    @BindView(R.id.msg_rel)
    RelativeLayout msgRel;
    ResultBean.DataBean resultBeanData;
    @BindView(R.id.push_rel)
    RelativeLayout pushRel;
    @BindView(R.id.follow_rel)
    RelativeLayout followRel;
    @BindView(R.id.fan_rel)
    RelativeLayout fanRel;
    @BindView(R.id.rel)
    RelativeLayout rel;
    @BindView(R.id.activity_base)
    RelativeLayout activity_base;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.fan)
    TextView fan;
    @BindView(R.id.follow)
    TextView follow;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {

        IntentFilter filter = new IntentFilter("broadcast.updateMe");
        getActivity().registerReceiver(broadcastReceiverUpdate, filter);
        Glide.with(this).load(R.mipmap.ic_me_bg).asBitmap().into(ivShow);

    }

    BroadcastReceiver broadcastReceiverUpdate = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            loadDataString();
        }
    };

    @Override
    protected void loadData() {
        loadDataString();
    }

    @Override
    protected void setOnclickListeners() {

        msgRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyMessageActivity.class);
//                startActivity(intent);
                Map<String,Boolean >map =new HashMap<>();
                map.put(Conversation.ConversationType.GROUP.getName(),false);
                map.put(Conversation.ConversationType.PRIVATE.getName(),false);
                map.put(Conversation.ConversationType.DISCUSSION.getName(),false);
                map.put(Conversation.ConversationType.PUBLIC_SERVICE.getName(),false);
                map.put(Conversation.ConversationType.SYSTEM.getName(),false);

                RongIM.getInstance().startConversationList(getActivity(),map);
            }
        });
        pushRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyReleaseActivity.class);
                startActivity(intent);
            }
        });
        studyRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                if (resultBeanData.getAdd_flag().equals("2")) {
                    bundle.putString("sid", resultBeanData.getSid() );
                    SPUtils.put("sid", resultBeanData.getSid());
                }

                bundle.putString("flag", resultBeanData.getAdd_flag() );
                Intent intent = new Intent(getActivity(), StudyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
//        tvName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ReleaseImageAndTextActivity.class);
//                startActivity(intent);
//            }
//        });
        followRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flage", "2");
                Intent intent = new Intent(getActivity(), FollowActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
        fanRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flage", "1");
                Intent intent = new Intent(getActivity(), FollowActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });


        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBeanData.getUser_name() != null) {
                    Intent intent = new Intent(getActivity(), PersonalMsgActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", resultBeanData.getUser_name());
                    bundle.putString("head_url", resultBeanData.getHead_url());
                    bundle.putString("sex", resultBeanData.getSex());
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }

            }
        });
        collectionRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBeanData.getUser_name() != null) {
                    Intent intent = new Intent(getActivity(), CollectionActivity.class);
                    startActivity(intent);
                }

            }
        });
        actRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBeanData.getUser_name() != null) {
                    Intent intent = new Intent(getActivity(), MyActivityActivity.class);
                    startActivity(intent);
                }
            }
        });
        setRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBeanData.getUser_name() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", resultBeanData.getAccount());
                    Intent intent = new Intent(getActivity(), MySeettingActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 2);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0) {
            loadDataString();
        }
        if (requestCode == 2 && resultCode == 0) {
            loadDataString();
        }

    }


    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiverUpdate);

    }

    private void loadDataString() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("id", (String) SPUtils.get("uid", "0"));

        mHttpHelper.post(Api.URL + "my", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    activity_base.setVisibility(activity_base.VISIBLE);
                    resultBeanData = resultBean.getData();
                    tvName.setText(resultBeanData.getUser_name());

                    follow.setText(resultBeanData.getFocus_num());
                    fan.setText(resultBeanData.getFans_num());
                    popularity.setText(resultBeanData.getRenqi());

                    Glide.with(getActivity()).load(resultBeanData.getHead_url()).asBitmap().centerCrop().into(new BitmapImageViewTarget(headImageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            //    circularBitmapDrawable.setCornerRadius(5);设置图片圆角
                            headImageView.setImageDrawable(circularBitmapDrawable);
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




}
