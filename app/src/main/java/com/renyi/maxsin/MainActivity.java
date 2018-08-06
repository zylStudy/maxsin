package com.renyi.maxsin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renyi.maxsin.module.get.GetFragment;
import com.renyi.maxsin.module.maxsin.MaxsinFragment;
import com.renyi.maxsin.module.me.MeFragment;
import com.renyi.maxsin.module.mvp.MvpPageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    //    @BindView(R.id.four_image_hl)
    //    ImageView fourImageHl;
    //    @BindView(R.id.four_image_nor)
    //    ImageView fourImageNor;
    //    @BindView(R.id.four_tv)
    //    TextView fourTv;
    //    @BindView(R.id.four_rel)
    //    RelativeLayout fourRel;
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
    private MvpPageFragment mvpFragment;
    private MaxsinFragment maxsinFragment;
    //private StudyFragment studyFragment;
    private MeFragment meFragment;

    public static final String GET_FRAGMENT = "GETFRAGMENT";
    public static final String GO_FRAGMENT = "GOFRAGMENT";
    public static final String MAXSIN_FRAGMENT = "MAXSINFRAGMENT";
    //public static final String STUDY_FRAGMENT = "STUDYFRAGMENT";
    public static final String ME_FRAGMENT = "MEFRAGMENT";
    public static MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        ButterKnife.bind(this);
        initView();
        setOnClickListeners();
    }


    private void initFragment() {
        getFragment = new GetFragment();
        mvpFragment = new MvpPageFragment();
        maxsinFragment = new MaxsinFragment();
        //studyFragment = new StudyFragment();
        meFragment = new MeFragment();

        mFragmentManager = getSupportFragmentManager();

        checkedFragment(getFragment, null, GET_FRAGMENT);
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
                checkedFragment(getFragment, null, GET_FRAGMENT);
                break;
            case R.id.two_rel:
                menuTagShowOrHide(1);
                checkedFragment(mvpFragment, null, GO_FRAGMENT);
                break;
            case R.id.three_rel:
                menuTagShowOrHide(2);


                checkedFragment(maxsinFragment, null, MAXSIN_FRAGMENT);

                break;

            //            case R.id.four_rel:
            //                menuTagShowOrHide(3);
            //                checkedFragment(studyFragment, null, STUDY_FRAGMENT);
            //
            //                break;
            case R.id.five_rel:
                menuTagShowOrHide(4);
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
}
