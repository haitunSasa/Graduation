package com.graduation.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.R;

import java.lang.ref.WeakReference;

/**
 * Created by liyan on 2017/4/20.
 */

public class LoginActivity extends BaseActivityWithEditText{

    private final static String TAG = "LoginActivity";
    private ProgressDialog mDialog;
    private String cellphone, password;
    private EditText edit_userName;
    private EditText edit_password;
    private login_handler loginHandler;
    private String imgUrl;
    private TextInputLayout accountWrapper;
    private TextInputLayout passwordWrapper;
    private TextView tv_find_password;

    private String cipherText = "1234";
    private int flag;

    private int versionCode = 0;
    private String versionName = "";
    private String download = "";

    private Long timestamp;
    private String sign;
    private String token;
    private String account;


    private TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().trim().equals("")) {
                accountWrapper.setErrorEnabled(true);
                accountWrapper.setError("请输入手机号");
            } else {
                accountWrapper.setErrorEnabled(false);
            }
        }
    };

    private TextWatcher pwdWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().trim().equals("")) {
                passwordWrapper.setErrorEnabled(true);
                passwordWrapper.setError("请输入密码");
            } else {
                passwordWrapper.setErrorEnabled(false);
            }
        }
    };

    private View.OnTouchListener touchlistener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.edit_login_name:
                    edit_userName.setFocusableInTouchMode(true);
                    break;
                case R.id.edit_login_password:
                    edit_password.setFocusableInTouchMode(true);
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    public void register(View v) {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View v) {
        attemptLogin();
    }

    private void attemptLogin() {
        hideKeyboard();
        cellphone = edit_userName.getText().toString();
        password = edit_password.getText().toString();
        if (cellphone.isEmpty() || cellphone.equals("")) {
            Toast.makeText(this, "请输入手机号！", Toast.LENGTH_SHORT)
                    .show();
            accountWrapper.setError("请输入手机号");
        } else if (password.isEmpty() || password.equals("")) {
            Toast.makeText(this, "请输入密码！", Toast.LENGTH_SHORT)
                    .show();
            passwordWrapper.setError("请输入密码");
        } else if (!cellphone.matches("^1[34578]\\d{9}$")) {
            Toast.makeText(this, "请输入正确的手机号！", Toast.LENGTH_SHORT)
                    .show();
            accountWrapper.setErrorEnabled(true);
            accountWrapper.setError("请输入正确的手机号");
        } else if (!password.matches("^[a-zA-Z][\\w/^]{7,31}$")) {
            Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT)
                    .show();
            passwordWrapper.setError("请输入正确的密码");
        } else {
           /* if (!WTFApplication.isConnectingToInternet()) {
                Toast.makeText(LoginActivity.this, "未检测到网络，请打开网络连接",
                        Toast.LENGTH_SHORT).show();
            } else {
                // 开始获取数据线程
                mDialog = new ProgressDialog(this);
                mDialog.setMessage("登录中，请稍候...");
                mDialog.show();

            }*/
        }
    }

    // Handler
    static class login_handler extends Handler {
        WeakReference<LoginActivity> mActivity;

        login_handler(LoginActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final LoginActivity theActivity = mActivity.get();
            switch (msg.what) {
                case 0:
                    theActivity.loginAccount();
                    break;
                case 1:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "账户不存在，请重新确认", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case 2:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "请输入正确的密码", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case 3:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "登录失败了，请重试", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case 4:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "未连接网络，请先联网", Toast.LENGTH_SHORT)
                            .show();
                    break;

            }
        }
    }

    private void loginAccount() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        loginHandler = new login_handler(this);
        if (flag == 2) {
            Bundle bundle = getIntent().getBundleExtra("updateInfo");
            versionName = bundle.getString("versionName");
            versionCode = bundle.getInt("versionCode");
            download = bundle.getString("download");
            loginHandler.sendEmptyMessage(5);
        }
        tv_find_password = (TextView) findViewById(R.id.tv_find_password);
        accountWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        edit_userName = (EditText) findViewById(R.id.edit_login_name);
        edit_password = (EditText) findViewById(R.id.edit_login_password);
        edit_userName.setFocusableInTouchMode(false);
        edit_password.setFocusableInTouchMode(false);
        edit_userName.addTextChangedListener(nameWatcher);
        edit_password.addTextChangedListener(pwdWatcher);

        /*tv_find_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, FindPwdActivity.class);
                startActivity(intent);
            }
        });*/
        edit_userName.setOnTouchListener(touchlistener);
        edit_password.setOnTouchListener(touchlistener);

    }
}
