package com.renyi.maxsin.module.me;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.renyi.maxsin.R;
import com.renyi.maxsin.base.Basefragment;
import com.renyi.maxsin.module.Study.StudyActivity;
import com.renyi.maxsin.module.login.ResultBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.SPUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by zhangyuliang on 2018/3/22.
 */

public class MeFragment extends Basefragment {


    @BindView(R.id.headImageView)
    ImageView headImageView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.image_go)
    ImageView tvMsg;
    @BindView(R.id.study_rel)
    RelativeLayout studyRel;
    @BindView(R.id.act_rel)
    RelativeLayout actRel;
    @BindView(R.id.collection_rel)
    RelativeLayout collectionRel;
    @BindView(R.id.set_rel)
    RelativeLayout setRel;
    @BindView(R.id.msg_rel)
    RelativeLayout msgRel;
    ResultBean.DataBean resultBeanData;
    @BindView(R.id.push_rel)
    RelativeLayout pushRel;
    @BindView(R.id.follow_rel)
    RelativeLayout followRel;
    @BindView(R.id.fan_rel)
    RelativeLayout fanRel;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.fan)
    TextView fan;
    @BindView(R.id.follow)
    TextView follow;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        IntentFilter filter = new IntentFilter("broadcast.updateMe");
        getActivity().registerReceiver(broadcastReceiverUpdate, filter);
    }

    BroadcastReceiver broadcastReceiverUpdate = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            loadDataString();
        }
    };

    @Override
    protected void loadData() {
        loadDataString();
    }

    @Override
    protected void setOnclickListeners() {

        headImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Intent intent = new Intent(getActivity(), MeCenterActivity.class);
                //                startActivity(intent);
            }
        });
        msgRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyMessageActivity.class);
                startActivity(intent);
            }
        });
        pushRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyReleaseActivity.class);
                startActivity(intent);
            }
        });
        studyRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudyActivity.class);
                startActivity(intent);
            }
        });
//        tvName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ReleaseImageAndTextActivity.class);
//                startActivity(intent);
//            }
//        });
        followRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flage", "2");
                Intent intent = new Intent(getActivity(), FollowActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
        fanRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("flage", "1");
                Intent intent = new Intent(getActivity(), FollowActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });


        tvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBeanData.getUser_name() != null) {
                    Intent intent = new Intent(getActivity(), PersonalMsgActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", resultBeanData.getUser_name());
                    bundle.putString("head_url", resultBeanData.getHead_url());
                    bundle.putString("sex", resultBeanData.getSex());
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                }

            }
        });
        collectionRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBeanData.getUser_name() != null) {
                    Intent intent = new Intent(getActivity(), CollectionActivity.class);
                    startActivity(intent);
                }

            }
        });
        actRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBeanData.getUser_name() != null) {
                    Intent intent = new Intent(getActivity(), MyActivityActivity.class);
                    startActivity(intent);
                }

            }
        });
        setRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBeanData.getUser_name() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", resultBeanData.getAccount());
                    Intent intent = new Intent(getActivity(), MySeettingActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 2);
                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0) {
            loadDataString();
        }
        if (requestCode == 2 && resultCode == 0) {
            loadDataString();
        }

    }


    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiverUpdate);

    }

    private void loadDataString() {
        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        Map<String, String> map = new HashMap<>();
        map.put("key", Api.KEY);
        map.put("id", (String) SPUtils.get("uid", ""));

        mHttpHelper.post(Api.URL + "my", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {

                    resultBeanData = resultBean.getData();
                    tvName.setText(resultBeanData.getUser_name());

                    follow.setText(resultBeanData.getFocus_num());
                    fan.setText(resultBeanData.getFans_num());
                    popularity.setText(resultBeanData.getRenqi());

                    Glide.with(getActivity()).load(resultBeanData.getHead_url()).asBitmap().centerCrop().into(new BitmapImageViewTarget(headImageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            //    circularBitmapDrawable.setCornerRadius(5);设置图片圆角
                            headImageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
                } else {

                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


    //    private void selectPic() {
    //        dialog = new Dialog(getActivity(), R.style.SelectImageDialogStyleBottom);
    //        dialog.setContentView(R.layout.dialog_select_pic_layout);
    //        TextView btnCarema =   dialog.findViewById(R.id.btn_camera);
    //        TextView btnPhoto =  dialog.findViewById(R.id.btn_photo);
    //        TextView btnCancel =   dialog.findViewById(R.id.btn_cancel);
    //        dialog.setCancelable(true);
    //        dialog.show();
    //        btnCarema.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                //权限判断
    //                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
    //                        != PackageManager.PERMISSION_GRANTED) {
    //                    //申请WRITE_EXTERNAL_STORAGE权限
    //                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
    //                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
    //                } else {
    //                    //跳转到调用系统相机
    //                    gotoCamera();
    //                }
    //                dialog.dismiss();
    //            }
    //        });
    //        btnPhoto.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                //权限判断
    //                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
    //                        != PackageManager.PERMISSION_GRANTED) {
    //                    //申请READ_EXTERNAL_STORAGE权限
    //                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
    //                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
    //                } else {
    //                    //跳转到相册
    //                    gotoPhoto();
    //                }
    //                dialog.dismiss();
    //            }
    //        });
    //        btnCancel.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                dialog.dismiss();
    //            }
    //        });
    //    }
    //
    //    /**
    //     * 跳转到相册
    //     */
    //    private void gotoPhoto() {
    //        Log.d("evan", "*****************打开图库********************");
    //        //跳转到调用系统图库
    //        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    //        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    //    }
    //
    //
    //    /**
    //     * 跳转到照相机
    //     */
    //    private void gotoCamera() {
    //        Log.d("evan", "*****************打开相机********************");
    //        //创建拍照存储的图片文件
    //        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
    //
    //        //跳转到调用系统相机
    //        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    //            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
    //            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
    //            Uri contentUri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
    //            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
    //        } else {
    //            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
    //        }
    //        startActivityForResult(intent, REQUEST_CAPTURE);
    //    }
    //
    //    @Override
    //    public void onCreate(@Nullable Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //    }
    //
    //
    //    /**
    //     * 检查文件是否存在
    //     */
    //    private static String checkDirPath(String dirPath) {
    //        if (TextUtils.isEmpty(dirPath)) {
    //            return "";
    //        }
    //        File dir = new File(dirPath);
    //        if (!dir.exists()) {
    //            dir.mkdirs();
    //        }
    //        return dirPath;
    //    }
    //
    //
    //    @Override
    //    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    //        switch (requestCode) {
    //            case REQUEST_CAPTURE: //调用系统相机返回
    //                if (resultCode == RESULT_OK) {
    //                    Log.d("evan", "**********camera uri*******" + Uri.fromFile(tempFile).toString());
    //                    Log.d("evan", "**********camera path*******" + getRealFilePathFromUri(getActivity(), Uri.fromFile(tempFile)));
    //                    gotoClipActivity(Uri.fromFile(tempFile));
    //                }
    //                break;
    //            case REQUEST_PICK:  //调用系统相册返回
    //                if (resultCode == RESULT_OK) {
    //                    Uri uri = intent.getData();
    //                    Log.d("evan", "**********pick path*******" + getRealFilePathFromUri(getActivity(), uri));
    //                    gotoClipActivity(uri);
    //                }
    //                break;
    //            case REQUEST_CROP_PHOTO:  //剪切图片返回
    //                if (resultCode == RESULT_OK) {
    //                    final Uri uri = intent.getData();
    //                    if (uri == null) {
    //                        return;
    //                    }
    //                    String cropImagePath = getRealFilePathFromUri(getActivity(), uri);
    //                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
    //                    headImageView.setImageBitmap(bitMap);
    //                    //                    if (type == 1) {
    //                    //                        headImage1.setImageBitmap(bitMap);
    //                    //                    } else {
    //                    //                        headImage2.setImageBitmap(bitMap);
    //                    //                    }
    //                    //此处后面可以将bitMap转为二进制上传后台网络
    //                    //......
    //
    //                }
    //                break;
    //        }
    //    }
    //
    //
    //    /**
    //     * 打开截图界面
    //     */
    //    public void gotoClipActivity(Uri uri) {
    //        if (uri == null) {
    //            return;
    //        }
    //        Intent intent = new Intent();
    //        intent.setClass(getActivity(), ClipImageActivity.class);
    //        intent.putExtra("type", type);
    //        intent.setData(uri);
    //        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    //    }
    //
    //
    //    /**
    //     * 根据Uri返回文件绝对路径
    //     * 兼容了file:///开头的 和 content://开头的情况
    //     *
    //     * @param context
    //     * @param uri
    //     * @return the file path or null
    //     */
    //    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
    //        if (null == uri) return null;
    //        final String scheme = uri.getScheme();
    //        String data = null;
    //        if (scheme == null)
    //            data = uri.getPath();
    //        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
    //            data = uri.getPath();
    //        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
    //            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
    //            if (null != cursor) {
    //                if (cursor.moveToFirst()) {
    //                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
    //                    if (index > -1) {
    //                        data = cursor.getString(index);
    //                    }
    //                }
    //                cursor.close();
    //            }
    //        }
    //        return data;
    //    }

}
