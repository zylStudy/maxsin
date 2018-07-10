package com.renyi.maxsin.module.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.renyi.maxsin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectTypeActivity extends AppCompatActivity {

    @BindView(R.id.act_image)
    ImageView actImage;
    @BindView(R.id.act_rel)
    RelativeLayout actRel;
    @BindView(R.id.work_image)
    ImageView workImage;
    @BindView(R.id.work_rel)
    RelativeLayout workRel;
    String flage = "1";
    @BindView(R.id.back_rel)
    RelativeLayout backRel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);
        ButterKnife.bind(this);
        setOnClickListeners();
    }


    protected void setOnClickListeners() {
        Bundle extras = getIntent().getExtras();
        if (extras.getString("flage").equals("1")) {
            actImage.setVisibility(actImage.VISIBLE);
            workImage.setVisibility(actImage.GONE);
        } else {
            actImage.setVisibility(actImage.GONE);
            workImage.setVisibility(actImage.VISIBLE);
        }


        actRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actImage.setVisibility(actImage.VISIBLE);
                workImage.setVisibility(actImage.GONE);
                flage = "1";
            }
        });
        workRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actImage.setVisibility(actImage.GONE);
                workImage.setVisibility(actImage.VISIBLE);
                flage = "2";
            }
        });
        backRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flage", flage);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Bundle bundle = new Bundle();
            bundle.putString("flage", flage);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(0, intent);
            finish();
        }
        return super.onKeyUp(keyCode, event);
    }
}
