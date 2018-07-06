package com.renyi.maxsin.module.release;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.renyi.maxsin.BuildConfig;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.BaseActivity;
import com.renyi.maxsin.module.me.ClickListener;
import com.renyi.maxsin.module.release.interfaces.RemoveImageInterface;
import com.renyi.maxsin.view.headpic.ClipImageActivity;
import com.renyi.maxsin.view.richtext.RichEditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;

import static android.R.attr.type;

public class ReleaseImageAndTextActivity extends BaseActivity {

    @BindView(R.id.edit_richtext)
    RichEditText richEditText;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.text)
    ImageView text;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.imagerel)
    RelativeLayout imagerel;
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
    ArrayList<String> selectedImages = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_image_and_text;
    }


    @Override
    protected void initView() {
        showTitleAndBack("发布");
        showOrHideSearchBt(true, "下一步");

        // 如果是编辑 就 先加载
        //        if(!TextUtils.isEmpty(html)){
        //            richEditText.showContent(richEditText, html);
        //        }
        richEditText.hideKeyBoard();
    }


    @Override
    protected void loadData() {

    }

    private ArrayList getImagePath() {

        Document doc = Jsoup.parse(getEditData());
        Elements elements = doc.select("img[src]");//获取到的值为所有的<img src="...">
        ArrayList<String> list = new ArrayList();
        int i = 0;
        for (Element element : elements) {
            String src = element.attr("src");//获取到src的值
            //   String imgUrl = src.replace("/fileimage","E:/uploadlove/fileimage");//替换字符中所有的fileimage
            list.add(i, src);//放入list中
            i++;
        }
        return list;
    }

    /**
     * 编辑完成保存，String 的内容就是编辑之后的 html
     */
    private String getEditData() {
        return richEditText.buildHtml(richEditText);

    }

    @Override
    protected void setOnClickListeners() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(scrollView.VISIBLE);
                imagerel.setVisibility(scrollView.GONE);
                selectPic();

            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(scrollView.VISIBLE);
                imagerel.setVisibility(scrollView.GONE);
            }
        });
        imagerel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(scrollView.VISIBLE);
                imagerel.setVisibility(scrollView.GONE);
            }
        });

        setclickListener(new ClickListener() {
            @Override
            public void getClickListener() {
                String result = richEditText.buildHtml(richEditText);
                if (!result.equals("")) {
                    if (getImagePath().size() != 0) {
                        Intent intent = new Intent();
                        intent.setClass(ReleaseImageAndTextActivity.this, ClipCoverImageActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("result", result);
                        bundle.putStringArrayList("pathList", getImagePath());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ReleaseImageAndTextActivity.this, "图片不能为空", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ReleaseImageAndTextActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });


        richEditText.setRemoveclickListener(new RemoveImageInterface() {
            @Override
            public void getRemoveImageInterface(String url) {
                for (int i = 0; i < selectedImages.size(); i++) {
                    if (selectedImages.get(i).equals(url)) {
                        selectedImages.remove(i);
                    }
                }
            }
        });
    }

    private void selectPic() {
        dialog = new Dialog(ReleaseImageAndTextActivity.this, R.style.MyDialogStyle);
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
                if (ContextCompat.checkSelfPermission(ReleaseImageAndTextActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ReleaseImageAndTextActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA},
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
                if (ContextCompat.checkSelfPermission(ReleaseImageAndTextActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ReleaseImageAndTextActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    // gotoPhoto();
                    startActivityForResult(BGAPhotoPickerActivity.newIntent(ReleaseImageAndTextActivity.this, null, 9, null, false), REQUEST_CODE_CHOOSE_PHOTO);


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
            Uri contentUri = FileProvider.getUriForFile(ReleaseImageAndTextActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
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
                    Log.d("evan", "**********camera path*******" + getRealFilePathFromUri(ReleaseImageAndTextActivity.this, Uri.fromFile(tempFile)));
                    // gotoClipActivity(Uri.fromFile(tempFile));
                    String cropImage = getRealFilePathFromUri(ReleaseImageAndTextActivity.this, Uri.fromFile(tempFile));
                    richEditText.insertImage(cropImage);
                }
                break;
            //            case REQUEST_PICK:  //调用系统相册返回
            //                if (resultCode == RESULT_OK) {
            //                    Uri uri = intent.getData();
            //                    Log.d("evan", "**********pick path*******" + getRealFilePathFromUri(ReleaseImageAndTextActivity.this, uri));
            //                    //gotoClipActivity(uri);
            //                    String cropImage = getRealFilePathFromUri(ReleaseImageAndTextActivity.this, uri);
            //                    richEditText.insertImage(cropImage);
            //
            //                }
            //                break;
            //            case REQUEST_CROP_PHOTO:  //剪切图片返回
            //                if (resultCode == RESULT_OK) {
            //                    final Uri uri = intent.getData();
            //                    if (uri == null) {
            //                        return;
            //                    }
            //                    String cropImagePath = getRealFilePathFromUri(ReleaseImageAndTextActivity.this, uri);
            //                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            //                    // coverImage.setImageBitmap(bitMap);
            //
            //                    //richEditText.insertImage(cropImagePath);
            //                    //                    if (type == 1) {
            //                    //                        headImage1.setImageBitmap(bitMap);
            //                    //                    } else {
            //                    //                        headImage2.setImageBitmap(bitMap);
            //                    //                    }
            //                    //此处后面可以将bitMap转为二进制上传后台网络
            //                    //......
            //                    //   postDate(cropImagePath);
            //                }
            //                break;
            case REQUEST_CODE_CHOOSE_PHOTO:  //选择多图返回

                if (intent != null) {
                    selectedImages = BGAPhotoPickerActivity.getSelectedImages(intent);
                    for (int i = 0; i < selectedImages.size(); i++) {
                        richEditText.insertImage(selectedImages.get(i));

                    }
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
        intent.setClass(ReleaseImageAndTextActivity.this, ClipImageActivity.class);
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


}
