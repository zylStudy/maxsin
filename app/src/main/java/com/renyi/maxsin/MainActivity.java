package com.renyi.maxsin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.renyi.maxsin.module.get.GetFragment;
import com.renyi.maxsin.module.maxsin.MaxsinFragment;
import com.renyi.maxsin.module.me.MeFragment;
import com.renyi.maxsin.module.mvp.MvpPageFragment;
import com.renyi.maxsin.module.release.ReleaseImageAndTextActivity;
import com.renyi.maxsin.module.rongyun.bean.TokenBean;
import com.renyi.maxsin.module.rongyun.bean.UserInfoBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_layout)
    FrameLayout mainActivityLayout;

    @BindView(R.id.first_image_hl)
    ImageView firstImageHl;
    @BindView(R.id.first_image_nor)
    ImageView firstImageNor;
    @BindView(R.id.first_tv)
    TextView firstTv;
    @BindView(R.id.first_rel)
    RelativeLayout firstRel;
    @BindView(R.id.two_image_hl)
    ImageView twoImageHl;
    @BindView(R.id.two_image_nor)
    ImageView twoImageNor;
    @BindView(R.id.two_tv)
    TextView twoTv;
    @BindView(R.id.two_rel)
    RelativeLayout twoRel;
    @BindView(R.id.three_image_hl)
    ImageView threeImageHl;
    @BindView(R.id.three_image_nor)
    ImageView threeImageNor;
    @BindView(R.id.three_rel)
    RelativeLayout threeRel;
    @BindView(R.id.three_tv)
    TextView threeTv;
    @BindView(R.id.four_image_hl)
    ImageView fourImageHl;
    @BindView(R.id.four_image_nor)
    ImageView fourImageNor;
    //    @BindView(R.id.four_tv)
    //    TextView fourTv;
    @BindView(R.id.four_rel)
    RelativeLayout fourRel;
    @BindView(R.id.five_image_hl)
    ImageView fiveImageHl;
    @BindView(R.id.five_image_nor)
    ImageView fiveImageNor;
    @BindView(R.id.five_tv)
    TextView fiveTv;
    @BindView(R.id.five_rel)
    RelativeLayout fiveRel;
    List<ImageView> iHlList, iNorList;
    List<TextView> tvList;


    private FragmentManager mFragmentManager;
    private String currentFragment = "";
    private long firstTime = 0;
    private GetFragment getFragment;
    private MvpPageFragment mvpPageFragment;
    private MaxsinFragment maxsinFragment;
    //private StudyFragment studyFragment;
    private MeFragment meFragment;

    public static final String GET_FRAGMENT = "GETFRAGMENT";
    public static final String GO_FRAGMENT = "GOFRAGMENT";
    public static final String MAXSIN_FRAGMENT = "MAXSINFRAGMENT";
    //public static final String STUDY_FRAGMENT = "STUDYFRAGMENT";
    public static final String ME_FRAGMENT = "MEFRAGMENT";
    public static MainActivity mainActivity;
    private String uid = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        //        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_main);
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.white), true);
        mainActivity = this;
        uid = (String) SPUtils.get("uid", "0");
        ButterKnife.bind(this);
        getTokenString();
        initView();
        setOnClickListeners();
    }


    private void initFragment() {
        getFragment = new GetFragment();
        mvpPageFragment = new MvpPageFragment();
        maxsinFragment = new MaxsinFragment();
        //studyFragment = new StudyFragment();
        meFragment = new MeFragment();

        mFragmentManager = getSupportFragmentManager();

        checkedFragment(maxsinFragment, null, MAXSIN_FRAGMENT);
    }


    protected void initView() {

        initFragment();
        iHlList = new ArrayList<>();
        iHlList.add(firstImageHl);
        iHlList.add(twoImageHl);
        iHlList.add(threeImageHl);
        //iHlList.add(fourImageHl);
        iHlList.add(fiveImageHl);

        iNorList = new ArrayList<>();
        iNorList.add(firstImageNor);
        iNorList.add(twoImageNor);
        iNorList.add(threeImageNor);
        // iNorList.add(fourImageNor);
        iNorList.add(fiveImageNor);

        tvList = new ArrayList<>();
        tvList.add(firstTv);
        tvList.add(twoTv);
        tvList.add(threeTv);
        // tvList.add(fourTv);
        tvList.add(fiveTv);
    }


    protected void setOnClickListeners() {

    }

    public void checkedFragment(Fragment fragment, Bundle bundle, String tag) {
        if (currentFragment == tag)
            return;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        Fragment fragment2 = mFragmentManager
                .findFragmentByTag(currentFragment);
        if (fragment2 != null && fragment2.isAdded()) {
            mFragmentManager.beginTransaction().hide(fragment2).commit();

        }

        if (mFragmentManager.findFragmentByTag(tag) != null
                && fragment.isAdded()) {
            mFragmentManager.beginTransaction().show(fragment).commit();
        } else {
            fragment.setArguments(bundle);
            transaction.add(R.id.main_activity_layout, fragment, tag);
            transaction.commit();

        }
        currentFragment = tag;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_rel:
                menuTagShowOrHide(0);
                checkedFragment(maxsinFragment, null, MAXSIN_FRAGMENT);
                break;
            case R.id.two_rel:
                menuTagShowOrHide(1);
                checkedFragment(getFragment, null, GET_FRAGMENT);
                break;
            case R.id.three_rel:
                menuTagShowOrHide(2);
                checkedFragment(mvpPageFragment, null, GO_FRAGMENT);
                break;

            case R.id.four_rel:
                Intent intent = new Intent(MainActivity.this, ReleaseImageAndTextActivity.class);
                startActivity(intent);

                break;
            case R.id.five_rel:
                menuTagShowOrHide(3);
                checkedFragment(meFragment, null, ME_FRAGMENT);

                break;

            default:
                break;
        }

    }

    private void menuTagShowOrHide(int a) {

        for (int i = 0; i < 4; i++) {
            if (i == a) {
                iNorList.get(i).setVisibility(iNorList.get(i).INVISIBLE);
                iHlList.get(i).setVisibility(iHlList.get(i).VISIBLE);
                tvList.get(i).setTextColor(ContextCompat.getColor(this, R.color.colorf0));

            } else {
                iNorList.get(i).setVisibility(iNorList.get(i).VISIBLE);
                iHlList.get(i).setVisibility(iHlList.get(i).INVISIBLE);
                tvList.get(i).setTextColor(ContextCompat.getColor(this, R.color.color9));

            }
        }

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(this, "再次点击退出应用", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {

                System.exit(0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }


    private void connectRongIMToSdk(String token) {

        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onTokenIncorrect() {

            }
        });

    }


    private void getTokenString() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("uid", uid);

        mHttpHelper.post(Api.URL + "getToken", map, new BaseCallback<TokenBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, TokenBean resultBean) {
                if (resultBean.getCode() == 200 && !resultBean.getToken().isEmpty()) {
                    connectRongIMToSdk(resultBean.getToken());
                    getUserInfoString();
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void getUserInfoString() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("uid", uid);

        mHttpHelper.post(Api.URL + "getInfo", map, new BaseCallback<UserInfoBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, UserInfoBean resultBean) {

                if (resultBean.getCode().equals("800")) {

                    RongIM.getInstance()
                            .refreshUserInfoCache(
                                    new UserInfo(
                                            (String) SPUtils.get("uid", uid),
                                            resultBean.getData().getName(),
                                            Uri.parse(resultBean.getData().getHeadphoto())));

                    RongIM.getInstance()
                            .setMessageAttachedUserInfo(true);
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }
}
