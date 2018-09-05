package com.renyi.maxsin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;
import com.renyi.maxsin.module.login.BindingPhoneNumeberActivity;
import com.renyi.maxsin.module.login.ResultBean;
import com.renyi.maxsin.net.Api;
import com.renyi.maxsin.net.BaseCallback;
import com.renyi.maxsin.net.OkHttpHelper;
import com.renyi.maxsin.utils.KeyboardUtils;
import com.renyi.maxsin.utils.SPUtils;
import com.renyi.maxsin.view.loginview.DrawableTextView;
import com.renyi.maxsin.view.loginview.Keyboard;
import com.renyi.maxsin.view.loginview.KeyboardWatcher;
import com.renyi.maxsin.view.loginview.PayEditText;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;

import static android.R.attr.action;


public class LoginActivity extends AppCompatActivity implements KeyboardWatcher.SoftKeyboardStateListener, Handler.Callback {
    public static LoginActivity loginActivity;
    private DrawableTextView logo;
    private int screenHeight = 0;//屏幕高度
    private float scale = 0.8f; //logo缩放比例
    private View body;
    private KeyboardWatcher keyboardWatcher;
    Map<String, String> map = new HashMap<>();
    private String nickname = "", head_url = "", gender = "";
    private static final String[] KEY = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "<<", "0", "完成"
    };
    private PayEditText payEditText;
    private Keyboard keyboard;
    private RelativeLayout rel_phone, code_phone, rel_login;
    private Keyboard keyboardView_pay;
    private TextView bt_back, bt_psw_admin, bt_code, tv_phone, code_time;
    private ImageView bt_next, bt_clear_phone, bt_clear_login_phone, bt_clear_login_psd, bt_next_login, image_weibo, image_weixin, image_qq;
    private EditText et_phone, et_phone_login, et_psd_login;
    private TimeCount time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginActivity = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_login);
        initView();
        setSubView();
        initEvent();
    }

    private void initView() {
        time = new TimeCount(60000, 1000);
        rel_phone = (RelativeLayout) findViewById(R.id.rel_phone);
        code_phone = (RelativeLayout) findViewById(R.id.code_phone);
        rel_login = (RelativeLayout) findViewById(R.id.rel_login);
        keyboardView_pay = (Keyboard) findViewById(R.id.keyboardView_pay);

        //输入手机号之后获取验证码，进入验证码界面按钮
        bt_next = (ImageView) findViewById(R.id.bt_next);
        //点击清除获取验证码手机号
        bt_clear_phone = (ImageView) findViewById(R.id.bt_clear_phone);
        //点击清除登录手机号
        bt_clear_login_phone = (ImageView) findViewById(R.id.bt_clear_login_phone);
        //点击清除登录密码
        bt_clear_login_psd = (ImageView) findViewById(R.id.bt_clear_login_psd);

        //登录按钮 image_weibo,image_weixin,image_qq
        bt_next_login = (ImageView) findViewById(R.id.bt_next_login);

        image_weibo = (ImageView) findViewById(R.id.image_weibo);
        image_weixin = (ImageView) findViewById(R.id.image_weixin);
        image_qq = (ImageView) findViewById(R.id.image_qq);

        bt_back = (TextView) findViewById(R.id.bt_back);
        bt_psw_admin = (TextView) findViewById(R.id.bt_psw_admin);
        bt_code = (TextView) findViewById(R.id.bt_code);
        tv_phone = (TextView) findViewById(R.id.tv_phone);

        code_time = (TextView) findViewById(R.id.code_time);

        //获取验证码手机号
        et_phone = (EditText) findViewById(R.id.et_phone);

        //登录账号手机号
        et_phone_login = (EditText) findViewById(R.id.et_phone_login);
        //登录密码
        et_psd_login = (EditText) findViewById(R.id.et_psd_login);


        logo = (DrawableTextView) findViewById(R.id.logo);
        keyboardWatcher = new KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
        body = findViewById(R.id.body);

        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度

        payEditText = (PayEditText) findViewById(R.id.PayEditText_pay);
        keyboard = (Keyboard) findViewById(R.id.keyboardView_pay);

        setOnClickListenersTo();
    }

    private void setOnClickListenersTo() {

        code_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.clear();
                map.put("login_flag", "1");
                map.put("login_account", et_phone.getText().toString().trim());
                map.put("passwd", "-1");
                login(map);
            }
        });


        //第一个布局进入验证码按钮

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_phone.setText("已经发送短信验证码到+86" + et_phone.getText().toString());
                String str_et_phone = et_phone.getText().toString().trim();
                if (str_et_phone.length() == 11) {
                    map.clear();
                    map.put("login_flag", "1");
                    map.put("login_account", str_et_phone);
                    map.put("passwd", "-1");
                    login(map);

                } else if (str_et_phone.length() < 11) {
                    Toast.makeText(LoginActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                }

                for (int i = 0; i <= payEditText.getText().toString().length(); i++) {
                    payEditText.remove();
                }
            }
        });

        //验证码界面，返回第一个布局
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.cancel();

                rel_phone.setVisibility(rel_phone.VISIBLE);
                code_phone.setVisibility(code_phone.GONE);
                keyboardView_pay.setVisibility(keyboardView_pay.GONE);
                for (int i = 0; i <= payEditText.getText().toString().length(); i++) {
                    payEditText.remove();
                }
            }
        });

        //第一个布局，账号密码登录按钮
        bt_psw_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_phone.setVisibility(rel_phone.GONE);
                rel_login.setVisibility(rel_login.VISIBLE);

            }
        });

        //登录
        bt_next_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_phone_login.getText().toString().trim().length() == 11) {
                    if (et_psd_login.getText().toString().trim().length() >= 6) {
                        map.clear();
                        map.put("login_flag", "5");
                        map.put("login_account", et_phone_login.getText().toString().trim());
                        map.put("passwd", et_psd_login.getText().toString().trim());
                        login(map);
                    } else {
                        Toast.makeText(getApplication(), "您输入的密码不合法" + payEditText.getText(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplication(), "您输入的手机号不合法" + payEditText.getText(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        //账号密码登录界面使用验证码登录
        bt_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_phone.setVisibility(rel_phone.VISIBLE);

                rel_login.setVisibility(rel_login.GONE);

            }
        });


        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!TextUtils.isEmpty(s) && bt_clear_phone.getVisibility() == View.GONE) {
                    bt_clear_phone.setVisibility(View.VISIBLE);
                    bt_next.setBackgroundResource(R.mipmap.ic_login_next_bg);
                    bt_next.setClickable(false);
                } else if (TextUtils.isEmpty(s)) {
                    bt_next.setClickable(false);
                    bt_clear_phone.setVisibility(View.GONE);
                    bt_next.setBackgroundResource(R.mipmap.ic_login_next_bg);
                } else if (s.toString().length() == 11 && s.toString().charAt(0) == '1' && s.toString().charAt(1) != '1' && s.toString().charAt(1) != '0' && s.toString().charAt(1) != '2') {
                    bt_next.setClickable(true);
                    bt_clear_phone.setVisibility(View.VISIBLE);
                    bt_next.setBackgroundResource(R.mipmap.ic_login_next_sel_bg);
                } else if (s.toString().length() != 11) {
                    bt_next.setClickable(true);
                    bt_next.setBackgroundResource(R.mipmap.ic_login_next_bg);
                }
            }
        });

        //点击清除，清除获取验证码的手机号
        bt_clear_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_phone.setText("");
            }
        });


        bt_clear_login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_phone_login.setText("");
            }
        });

        bt_clear_login_psd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_psd_login.setText("");
            }
        });

        et_phone_login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && bt_clear_login_phone.getVisibility() == View.GONE) {
                    bt_clear_login_phone.setVisibility(View.VISIBLE);
                    bt_next_login.setBackgroundResource(R.mipmap.ic_login_next_bg);
                    bt_next_login.setClickable(false);
                } else if (TextUtils.isEmpty(s)) {
                    bt_next_login.setClickable(false);
                    bt_clear_login_phone.setVisibility(View.GONE);
                    bt_next_login.setBackgroundResource(R.mipmap.ic_login_next_bg);
                } else if (et_phone_login.getText().toString().trim().length() == 11 && et_psd_login.getText().toString().trim().length() >= 6) {
                    bt_next_login.setClickable(true);
                    bt_clear_login_phone.setVisibility(View.VISIBLE);
                    bt_next_login.setBackgroundResource(R.mipmap.ic_login_next_sel_bg);
                } else if (et_phone_login.getText().toString().trim().length() != 0) {
                    bt_clear_login_phone.setVisibility(View.VISIBLE);
                }
            }
        });
        et_psd_login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && bt_clear_login_psd.getVisibility() == View.GONE) {
                    bt_clear_login_psd.setVisibility(View.VISIBLE);
                    bt_next_login.setBackgroundResource(R.mipmap.ic_login_next_bg);
                    bt_next_login.setClickable(false);
                } else if (TextUtils.isEmpty(s)) {
                    bt_next_login.setClickable(false);
                    bt_clear_login_psd.setVisibility(View.GONE);
                    bt_next_login.setBackgroundResource(R.mipmap.ic_login_next_bg);
                } else if (et_phone_login.getText().toString().trim().length() == 11 && et_psd_login.getText().toString().trim().length() >= 6) {
                    bt_next_login.setClickable(true);
                    bt_clear_login_psd.setVisibility(View.VISIBLE);
                    bt_next_login.setBackgroundResource(R.mipmap.ic_login_next_sel_bg);
                } else if (et_psd_login.getText().toString().trim().length() != 0) {
                    bt_clear_login_psd.setVisibility(View.VISIBLE);
                    bt_next_login.setBackgroundResource(R.mipmap.ic_login_next_bg);
                }
            }
        });
        //  第三方登录
//        image_weibo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Platform mPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
//                mPlatform.SSOSetting(true);
//                setThreedLogin(mPlatform, 4);
//
//            }
//
//
//        });
        image_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform mPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
                mPlatform.SSOSetting(true);
                setThreedLogin(mPlatform, 4);

            }


        });
        image_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.SSOSetting(true);
                setThreedLogin(wechat, 2);
            }
        });
//        image_qq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Platform qq = ShareSDK.getPlatform(QQ.NAME);
//                qq.SSOSetting(true);
//                setThreedLogin(qq, 3);
//            }
//        });

    }

    private void setThreedLogin(Platform mPlatform, final int flage) {

        mPlatform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable t) {
                Message msg = new Message();
                msg.what = flage;
                msg.arg1 = 2;
                msg.arg2 = action;
                msg.obj = t;

                UIHandler.sendMessage(msg, LoginActivity.this);
            }

            @Override
            public void onComplete(Platform platform, int arg1, HashMap<String, Object> arg2) {
                Message msg = new Message();
                msg.what = flage;
                msg.arg1 = 1;
                msg.arg2 = action;
                msg.obj = platform;
                UIHandler.sendMessage(msg, LoginActivity.this);   //发送消息
            }

            @Override
            public void onCancel(Platform platform, int arg1) {
                Message msg = new Message();
                msg.what = flage;
                msg.arg1 = 3;
                msg.arg2 = action;
                msg.obj = platform;
                UIHandler.sendMessage(msg, LoginActivity.this);
            }
        });
        mPlatform.authorize();//单独授权,OnComplete返回的hashmap是空的
        mPlatform.showUser(null);//授权并获取用户信息
    }

    private void setSubView() {
        //设置键盘
        keyboard.setKeyboardKeys(KEY);
    }

    private void initEvent() {
        keyboard.setOnClickKeyboardListener(new Keyboard.OnClickKeyboardListener() {
            @Override
            public void onKeyClick(int position, String value) {
                if (position < 11 && position != 9) {
                    payEditText.add(value);
                } else if (position == 9) {
                    payEditText.remove();
                } else if (position == 11) {
                    //当点击完成的时候，也可以通过payEditText.getText()获取密码，此时不应该注册OnInputFinishedListener接口
                    //                    Toast.makeText(getApplication(), "您的密码是：" + payEditText.getText(), Toast.LENGTH_SHORT).show();
                    //                    finish();
                }
            }
        });

        /**
         * 当密码输入完成时的回调
         */
        payEditText.setOnInputFinishedListener(new PayEditText.OnInputFinishedListener() {
            @Override
            public void onInputFinished(String code) {
                if (code.length() == 6) {
                    codeLogin(code);
                }
            }
        });
    }

    /**
     * 缩小
     *
     * @param view
     */
    public void zoomIn(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY);

        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();

    }

    /**
     * f放大
     *
     * @param view
     */
    public void zoomOut(final View view) {
        if (view.getTranslationY() == 0) {
            return;
        }
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        keyboardWatcher.removeSoftKeyboardStateListener(this);
        loginActivity = null;
        time.cancel();
    }


    @Override
    public void onSoftKeyboardOpened(int keyboardSize) {
        int[] location = new int[2];
        body.getLocationOnScreen(location); //获取body在屏幕中的坐标,控件左上角
        int x = location[0];
        int y = location[1];
        int bottom = screenHeight - (y + body.getHeight());
        if (keyboardSize > bottom) {
            ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", 0.0f, -(keyboardSize - bottom));
            mAnimatorTranslateY.setDuration(300);
            mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimatorTranslateY.start();
            zoomIn(logo, keyboardSize - bottom);

        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", body.getTranslationY(), 0);
        mAnimatorTranslateY.setDuration(300);
        mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimatorTranslateY.start();
        zoomOut(logo);

    }

    private void login(final Map map) {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        map.put("device_type", "1");
        map.put("key", Api.KEY);


        mHttpHelper.post(Api.URL + "login", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {
                if (map.get("login_flag").equals("1")) {
                    if (resultBean.getCode().equals("800")) {
                        rel_phone.setVisibility(rel_phone.GONE);
                        code_phone.setVisibility(code_phone.VISIBLE);
                        KeyboardUtils.hideSoftInput(LoginActivity.this);
                        keyboardView_pay.setVisibility(keyboardView_pay.VISIBLE);

                        time.start();
                        SPUtils.put("uid", resultBean.getData().getU_id());
                    } else {

                    }
                } else if (map.get("login_flag").equals("5")) {
                    if (resultBean.getCode().equals("800")) {
                        if (MainActivity.mainActivity == null) {
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                            SPUtils.put("isLogin", false);
                            SPUtils.put("uid", resultBean.getData().getU_id());
                            finish();
                        }

                    } else if (resultBean.getCode().equals("909")) {
                        //手机号非法
                        Toast.makeText(LoginActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    } else if (resultBean.getCode().equals("903")) {
                        //密码错误
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    } else if (resultBean.getCode().equals("902")) {
                        //密码错误
                        Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (resultBean.getCode().equals("800")) {
                        //判断第三方登录是否绑定了手机号
                        if (resultBean.getData().getHas_mobile_flag().equals("1")) {
                            if (MainActivity.mainActivity == null) {
                                Intent intent = new Intent(LoginActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                                SPUtils.put("isLogin", false);
                                SPUtils.put("uid", resultBean.getData().getU_id());
                                finish();
                            }
                        } else {

                            if (BindingPhoneNumeberActivity.bindingPhoneNumeberActivity == null) {

                                Bundle bundle = new Bundle();
                                bundle.putString("login_flag", map.get("login_flag") + "");
                                bundle.putString("login_account", map.get("login_account") + "");
                                bundle.putString("nickname", nickname);
                                bundle.putString("head_url", head_url);
                                bundle.putString("gender", gender);

                                Intent intent = new Intent(LoginActivity.this,
                                        BindingPhoneNumeberActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }


                    } else {
                        //手机号非法
                        Toast.makeText(LoginActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    }


                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });

    }

    private void codeLogin(String code) {

        OkHttpHelper mHttpHelper = OkHttpHelper.getinstance();
        map.put("code", code);
        map.put("key", Api.KEY);
        map.put("mobile", et_phone.getText().toString().trim());


        mHttpHelper.post(Api.URL + "check_code", map, new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {

                if (resultBean.getCode().equals("800")) {
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    SPUtils.put("isLogin", false);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }


    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.arg1) {
            case 1: { // 成功
                Toast.makeText(LoginActivity.this, "授权登陆成功", Toast.LENGTH_SHORT).show();

                //获取用户资料
                Platform platform = (Platform) msg.obj;
                int what = msg.what;

                String userId = platform.getDb().getUserId();//获取用户账号
                nickname = platform.getDb().getUserName();//获取用户名字
                head_url = platform.getDb().getUserIcon();//获取用户头像
                gender = platform.getDb().getUserGender(); //获取用户性别，m = 男, f = 女，如果微信没有设置性别,默认返回null
                //下面就可以利用获取的用户信息登录自己的服务器或者做自己想做的事啦!

                map.clear();
                map.put("login_flag", what + "");
                map.put("login_account", userId);
                map.put("passwd", "-1");
                login(map);


            }
            break;
            case 2: { // 失败
                Toast.makeText(LoginActivity.this, "授权登陆失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case 3: { // 取消
                Toast.makeText(LoginActivity.this, "授权登陆取消", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            code_time.setEnabled(false);
            code_time.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
            code_time.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.color8));

        }

        @Override
        public void onFinish() {
            code_time.setText("重新获取验证码");
            code_time.setEnabled(true);
            code_time.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.colornor));

        }
    }
}
