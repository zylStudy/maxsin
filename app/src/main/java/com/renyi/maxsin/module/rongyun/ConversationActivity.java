package com.renyi.maxsin.module.rongyun;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.R;


public class ConversationActivity extends FragmentActivity {

    TextView headTitleTv;
    RelativeLayout backRel;

    /**
     * 会话类型
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);

        headTitleTv = (TextView) findViewById(R.id.head_title_tv);
        backRel = (RelativeLayout) findViewById(R.id.back_rel);
        headTitleTv.setText(getIntent().getData().getQueryParameter("title"));

        backRel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();
            }
        });

    }


}