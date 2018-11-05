package com.renyi.maxsin.module.release;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.module.me.ClickListener;
import com.renyi.maxsin.module.release.interfaces.TagPositionInterface;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.renyi.maxsin.view.tagview.FlowLayout;
import com.renyi.maxsin.view.tagview.TagAdapter;
import com.renyi.maxsin.view.tagview.TagFlowLayout;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.shaohui.advancedluban.Luban;
import rx.functions.Action1;

public class SelectTagTitleActivity extends BaseActivity {
    ArrayList<String> pathList;
    ArrayList<File> pathListPost = new ArrayList<>();
    String htmlStr = "", coverImagePath = "";
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_info)
    EditText etInfo;
    @BindView(R.id.et_tag)
    EditText etTag;
    @BindView(R.id.bt_clear_phone)
    ImageView btClearPhone;
    @BindView(R.id.bt_sure)
    TextView btSure;
    @BindView(R.id.tv1)
    TextView tvNum;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.tag_flowlayout)
    TagFlowLayout tagFlowlayout;
    @BindView(R.id.tag_flowlayout_aways)
    TagFlowLayout tagFlowlayoutAways;
    TagAdapter adapter2, adapter;
    @BindView(R.id.push_rel)
    RelativeLayout pushRel;
    String flage = "1", tage = "";
    private String[] mVals = new String[]

            {"平面设计", "服装设计", "建筑设计", "插画设计", "景观设计", "交互设计", "纯艺术", "工业设计", "室内设计", "摄影"
          , "版画","产品设计","艺术管理","装置艺术","动画设计" };
    private List<File> fileList = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private List<String> clearList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_tag_title;
    }

    @Override
    protected void initView() {
        showTitleAndBack("编辑信息");
        showOrHideSearchBt(true, "发布");
        tvNum.setText("作品标签（0/5）");


        for (int i = 0; i < mVals.length; i++) {
            clearList.add(mVals[i]);
        }
        Intent intent = getIntent();
        if (intent != null) {
            pathList = intent.getStringArrayListExtra("pathList");
            htmlStr = intent.getExtras().getString("htmlStr");
            coverImagePath = intent.getExtras().getString("coverImagePath");
            for (int i = 0; i < pathList.size(); i++) {
                fileList.add(new File(pathList.get(i)));
            }
            compressImageList(fileList);
        }
        tagFlowlayout.setMaxSelectCount(5);

        adapter = new TagAdapter<String>(list) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(SelectTagTitleActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tagFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        };
        adapter2 = new TagAdapter<String>(clearList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(SelectTagTitleActivity.this);
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tagFlowlayoutAways, false);
                tv.setText(s);
                return tv;
            }
        };
        tagFlowlayout.setAdapter(adapter);
        tagFlowlayoutAways.setAdapter(adapter2);
    }

    //图片压缩
    private void compressImageList(List<File> cFileList) {
        Luban.compress(this, cFileList)
                .setMaxSize(200)
                .putGear(Luban.THIRD_GEAR)
                .setCompressFormat(Bitmap.CompressFormat.JPEG)
                .asListObservable()
                .doOnRequest(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                    }
                })
                .subscribe(new Action1<List<File>>() {
                    @Override
                    public void call(List<File> files) {
                        pathListPost.addAll(files);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });


    }

    @Override
    protected void loadData() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0) {
            flage = data.getExtras().getString("flage");
            if (flage.equals("1")) {
                type.setText("我的发布");
            } else {
                type.setText("我的作品");
            }
        }
    }

    @Override
    protected void setOnClickListeners() {
        pushRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTagTitleActivity.this, SelectTypeActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("flage", flage);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
        adapter.setOnTagChangedListener(new TagPositionInterface() {
            @Override
            public void getTagPosition(int position) {
                clearList.add(0, list.get(position));
                list.remove(position);
                adapter.notifyDataChanged();
                adapter2.notifyDataChanged();
                tvNum.setText("作品标签" + "(" + list.size() + "/5)");
            }

            @Override
            public void getClearTagPosition(int position) {

            }
        });
        adapter2.setOnTagChangedListener(new TagPositionInterface() {
            @Override
            public void getTagPosition(int position) {
                if (list.size() < 5) {
                    list.add(0, clearList.get(position));
                    tvNum.setText("作品标签" + "(" + list.size() + "/5)");
                    clearList.remove(position);
                    adapter.notifyDataChanged();
                    adapter2.notifyDataChanged();
                }
            }

            @Override
            public void getClearTagPosition(int position) {

            }
        });
        setclickListener(new ClickListener() {
            @Override
            public void getClickListener() {

                if (etTitle.getText().toString().isEmpty()) {
                    //                    tage
                    Toast.makeText(SelectTagTitleActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();

                } else {
                    if (etInfo.getText().toString().isEmpty()) {
                        Toast.makeText(SelectTagTitleActivity.this, "描述不能为空", Toast.LENGTH_SHORT).show();

                    } else {
                        if (list.size() == 0) {
                            Toast.makeText(SelectTagTitleActivity.this, "标签不能为空", Toast.LENGTH_SHORT).show();

                        } else {

                            StringBuffer stringBuffer = new StringBuffer();
                            for (int i = 0; i < list.size(); i++) {
                                stringBuffer.append(list.get(i) + "，");
                            }
                            tage = new String(stringBuffer);
                            tage = tage.substring(0, tage.length() - 1);
                            postDate();
                        }
                    }
                }


            }
        });
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etTag.getText().toString().trim())) {

                    if (list.size() < 5) {
                        for (int i = 0; i < clearList.size(); i++) {
                            if (clearList.get(i).equals(etTag.getText().toString().trim())) {
                                clearList.remove(i);
                            }
                        }

                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).equals(etTag.getText().toString().trim())) {
                                list.remove(i);
                                Toast.makeText(SelectTagTitleActivity.this, "标签已被添加", Toast.LENGTH_SHORT).show();
                            }
                        }

                        list.add(etTag.getText().toString().trim());
                        tvNum.setText("作品标签" + "(" + list.size() + "/5)");
                        etTag.setText("");
                        adapter.notifyDataChanged();
                        adapter2.notifyDataChanged();

                    } else {
                        Toast.makeText(SelectTagTitleActivity.this, "最多只能添加5个标签", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(SelectTagTitleActivity.this, "输入标签不能为空", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void postDate() {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("user_id", (String) SPUtils.get("uid", "0"));
        map.put("key", Api.KEY);
        map.put("title", etTitle.getText().toString().trim());
        map.put("description", etInfo.getText().toString().trim());
        map.put("type_id", flage);
        map.put("tag_name", tage);
        map.put("c_content", htmlStr);
        map.put("0cover_img", coverImagePath);
        for (int i = 0; i < pathListPost.size(); i++) {
            map.put("0" + i, pathListPost.get(i).getPath());
        }

        mHttpHelper.post(Api.URL + "up_contents", map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {
                if (resultBean.getCode().equals("800")) {
                    ClipCoverImageActivity.clipCoverImageActivity.finish();
                    ReleaseImageAndTextActivity.releaseImageAndTextActivity.finish();
                    finish();

                } else {
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


}
