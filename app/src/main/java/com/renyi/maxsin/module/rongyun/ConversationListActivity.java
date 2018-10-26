package com.renyi.maxsin.module.rongyun;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.R;


public class ConversationListActivity extends FragmentActivity {
    TextView headTitleTv;
    RelativeLayout backRel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);
        headTitleTv = findViewById(R.id.head_title_tv);
        backRel = findViewById(R.id.back_rel);
        headTitleTv.setText("消息列表");

        backRel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();
            }
        });
    }


}
