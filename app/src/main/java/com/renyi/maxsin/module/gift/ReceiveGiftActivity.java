package com.renyi.maxsin.module.gift;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.LoginActivity;
import com.renyi.maxsin.MainActivity;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.me.ClickListener;
import com.renyi.maxsin.module.me.MySeettingActivity;
import com.renyi.maxsin.module.release.ClipCoverImageActivity;
import com.renyi.maxsin.utils.SPUtils;

import butterknife.BindView;


public class ReceiveGiftActivity extends BaseActivity {

    @BindView(R.id.btimage01)
    ImageView btimage01;
    @BindView(R.id.btimage02)
    ImageView btimage02;
    @BindView(R.id.btimage03)
    ImageView btimage03;
    @BindView(R.id.btimage05)
    ImageView btimage05;
    @BindView(R.id.layout03)
    RelativeLayout layout03;
    private Dialog dialog,dialogSend;
    @BindView(R.id.imageshare)
    ImageView imageshare;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_receive_gift;
    }

    @Override
    protected void initView() {
        showTitleAndBack("邀请有礼");
        showOrHideSearchBt(true, "我的邀请记录");
        setclickListener(new ClickListener() {
            @Override
            public void getClickListener() {//dialog_me_gif_info_layout.xml
                finish();
//                Intent intent = new Intent();
//                intent.setClass(ReceiveGiftActivity.this, ReceiveGiftActivity.class);
            }
        });
    }

    @Override
    protected void loadData() {
        dialogInfo();
        dialogSend();
    }

    @Override
    protected void setOnClickListeners() {
        btimage01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialogSend.isShowing()) {
                    dialogSend.show();
                }
            }
        });
        btimage02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialogSend.isShowing()) {
                    dialogSend.show();
                }
            }
        });
        btimage03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialogSend.isShowing()) {
                    dialogSend.show();
                }
            }
        });
        btimage05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialogSend.isShowing()) {
                    dialogSend.show();
                }
            }
        });
        imageshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialogSend.isShowing()) {
                    dialogSend.show();
                }
            }
        });


        layout03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//活动规则
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        });
    }

    private void dialogSend() {
        dialogSend = new Dialog(ReceiveGiftActivity.this, R.style.MyDialogStyle);
        dialogSend.setContentView(R.layout.dialog_me_gif_send_layout);
        ImageView colse_image = dialogSend.findViewById(R.id.colse_image);

        colse_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogSend.isShowing()) {
                    dialogSend.dismiss();
                }

            }
        });

    }
    private void dialogInfo() {
        dialog = new Dialog(ReceiveGiftActivity.this, R.style.MyDialogStyle);
        dialog.setContentView(R.layout.dialog_me_gif_info_layout);
        ImageView colse_image = dialog.findViewById(R.id.colse_image);

        colse_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

            }
        });

    }
}
