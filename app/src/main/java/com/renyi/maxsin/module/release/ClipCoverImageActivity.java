package com.renyi.maxsin.module.release;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renyi.maxsin.BuildConfig;
import com.renyi.maxsin.R;
import com.renyi.maxsin.adapter.recyclerview.CommonAdapter;
import com.renyi.maxsin.adapter.recyclerview.base.ViewHolder;
import com.renyi.maxsin.view.headpic.ClipViewLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClipCoverImageActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ClipImageActivity";
    @BindView(R.id.back_rel)
    RelativeLayout backRel;
    @BindView(R.id.next_rel)
    RelativeLayout nextRel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.add_image)
    ImageView addImage;
    private ClipViewLayout clipViewLayout1;
    private ClipViewLayout clipViewLayout2;
    //类别 1: 圆形, 2:正方形
    private int type = 2;
    Uri uri;
    CommonAdapter adapter;
    ArrayList<String> pathList;
    String htmlStr = "";
    private int a = 0;
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
    //图片多选
    private static final int REQUEST_CODE_CHOOSE_PHOTO = 1;
    private File tempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_clip_cover_image);
        ButterKnife.bind(this);
        pathList = getIntent().getStringArrayListExtra("pathList");
        htmlStr = getIntent().getExtras().getString("result");
        uri = Uri.parse(pathList.get(0));
        initView();
        setSelectImageView();

    }

    private void setSelectImageView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new CommonAdapter<String>(this, R.layout.item_release_cover_image_list, pathList) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, final int position) {

                viewHolder.setCornerRadiusImageViewNetUrl(R.id.cover_image, item, 10);

                if (a == position) {
                    viewHolder.setVisible(R.id.tv, true);
                } else {
                    viewHolder.setVisible(R.id.tv, false);
                }

                viewHolder.setOnClickListener(R.id.cover_image, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a = position;
                        clipViewLayout2.setVisibility(View.GONE);
                        clipViewLayout2.setVisibility(View.VISIBLE);
                        clipViewLayout2.setImageSrc(Uri.parse(pathList.get(position)));
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);

        }
    }


    protected void initView() {


        clipViewLayout1 = (ClipViewLayout) findViewById(R.id.clipViewLayout1);
        clipViewLayout2 = (ClipViewLayout) findViewById(R.id.clipViewLayout2);
        //        //设置点击事件监听器
        backRel.setOnClickListener(this);
        addImage.setOnClickListener(this);
        nextRel.setOnClickListener(this);

        clipViewLayout2.setVisibility(View.VISIBLE);
        clipViewLayout1.setVisibility(View.GONE);
        //设置图片资源

        clipViewLayout2.setImageSrc(uri);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_rel:
                finish();
                break;
            case R.id.add_image:
                //添加图片
                selectPic();
                break;
            case R.id.next_rel:
                String coverImagePath = generateUriAndReturn();
                if (!TextUtils.isEmpty(coverImagePath)) {
                    Intent intent = new Intent();
                    intent.setClass(ClipCoverImageActivity.this, SelectTagTitleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("coverImagePath", coverImagePath);
                    bundle.putString("htmlStr", htmlStr);
                    bundle.putStringArrayList("pathList", pathList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }


    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private String generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        if (type == 1) {
            zoomedCropBitmap = clipViewLayout1.clip();
        } else {
            zoomedCropBitmap = clipViewLayout2.clip();
        }
        if (zoomedCropBitmap == null) {
            Log.e("android", "zoomedCropBitmap == null");
            return "";
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return getRealFilePathFromUri(ClipCoverImageActivity.this, mSaveUri);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {


        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    String cropImage = getRealFilePathFromUri(ClipCoverImageActivity.this, Uri.fromFile(tempFile));
                    pathList.add(0,cropImage);
                    adapter.notifyDataSetChanged();


                    clipViewLayout2.setVisibility(View.GONE);
                    clipViewLayout2.setVisibility(View.VISIBLE);
                    clipViewLayout2.setImageSrc(Uri.parse(cropImage));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    String cropImage = getRealFilePathFromUri(ClipCoverImageActivity.this, uri);
                    pathList.add(0,cropImage);
                    adapter.notifyDataSetChanged();
                    clipViewLayout2.setVisibility(View.GONE);
                    clipViewLayout2.setVisibility(View.VISIBLE);
                    clipViewLayout2.setImageSrc(Uri.parse(cropImage));
                }
                break;


        }
    }

    private void selectPic() {
        dialog = new Dialog(ClipCoverImageActivity.this, R.style.MyDialogStyle);
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
                if (ContextCompat.checkSelfPermission(ClipCoverImageActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ClipCoverImageActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA},
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
                if (ContextCompat.checkSelfPermission(ClipCoverImageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ClipCoverImageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},
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
     * 跳转到照相机
     */
    private void gotoCamera() {
        //创建拍照存储的图片文件
        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(ClipCoverImageActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }


    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
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
}
