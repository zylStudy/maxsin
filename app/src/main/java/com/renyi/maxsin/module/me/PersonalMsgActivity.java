package com.renyi.maxsin.module.me;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.renyi.maxsin.BuildConfig;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.get.bean.ReturnBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.renyi.maxsin.view.headpic.ClipImageActivity;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static android.R.attr.type;

public class PersonalMsgActivity extends BaseActivity {

    @BindView(R.id.cover_image)
    ImageView coverImage;
    @BindView(R.id.head_image_rel)
    RelativeLayout headImageRel;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.name_rel)
    RelativeLayout nameRel;
    @BindView(R.id.sex_tv)
    TextView sexTv;
    @BindView(R.id.sex_rel)
    RelativeLayout sexRel;
    private Dialog dialog;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private File tempFile;
    Bundle bundle;
    String nameStr, sexStr;

    @Override
    protected int getLayoutId() {
        bundle = getIntent().getExtras();
        return R.layout.activity_persional_msg;
    }

    @Override
    protected void initView() {
        showTitleAndBack("个人信息");
        nameStr = bundle.getString("name");
        sexStr = bundle.getString("sex");
        nameTv.setText(nameStr);
        sexTv.setText(bundle.getString("sex"));
        Glide.with(this).load(bundle.getString("head_url")).asBitmap().centerCrop().into(new BitmapImageViewTarget(coverImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                //    circularBitmapDrawable.setCornerRadius(5);设置图片圆角
                coverImage.setImageDrawable(circularBitmapDrawable);
            }
        });

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setOnClickListeners() {
        headImageRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPic();
            }
        });
        nameRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalMsgActivity.this, ChangePersonalMsgActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", nameTv.getText().toString());
                bundle.putString("nameOrSex", "1");
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });

        sexRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalMsgActivity.this, ChangePersonalMsgActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sex", sexTv.getText().toString());
                bundle.putString("nameOrSex", "2");
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void selectPic() {
        dialog = new Dialog(PersonalMsgActivity.this, R.style.SelectImageDialogStyleBottom);
        dialog.setContentView(R.layout.dialog_select_pic_layout);
        TextView btnCarema = dialog.findViewById(R.id.btn_camera);
        TextView btnPhoto = dialog.findViewById(R.id.btn_photo);
        TextView btnCancel = dialog.findViewById(R.id.btn_cancel);
        dialog.setCancelable(true);
        dialog.show();
        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(PersonalMsgActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(PersonalMsgActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCamera();
                }
                dialog.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(PersonalMsgActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(PersonalMsgActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(PersonalMsgActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }


    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {


        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    Log.d("evan", "**********camera uri*******" + Uri.fromFile(tempFile).toString());
                    Log.d("evan", "**********camera path*******" + getRealFilePathFromUri(PersonalMsgActivity.this, Uri.fromFile(tempFile)));
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    Log.d("evan", "**********pick path*******" + getRealFilePathFromUri(PersonalMsgActivity.this, uri));
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(PersonalMsgActivity.this, uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    coverImage.setImageBitmap(bitMap);
                    //                    if (type == 1) {
                    //                        headImage1.setImageBitmap(bitMap);
                    //                    } else {
                    //                        headImage2.setImageBitmap(bitMap);
                    //                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......
                    postDate(cropImagePath);
                }
                break;

            case 0:  //修改昵称或者性别返回值
                if (resultCode == 11) {
                    nameTv.setText(intent.getExtras().getString("name"));

                } else if (resultCode == 12) {
                    sexTv.setText(intent.getExtras().getString("sex"));
                }
                break;
        }
    }


    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(PersonalMsgActivity.this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private void postDate(String head_url) {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("uid", (String) SPUtils.get("uid","0"));
        map.put("0head_url", head_url);
        map.put("key", Api.KEY);


        mHttpHelper.post(Api.URL + "save_headimg", map, new BaseCallback<ReturnBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ReturnBean resultBean) {

                if (resultBean.getCode().equals("800")) {


                } else {
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        setResult(0, intent);
    }
}
