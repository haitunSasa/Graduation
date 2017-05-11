package com.graduation.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.graduation.R;
import com.graduation.base.BaseActivity;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.ErrCode;
import com.graduation.bean.Users;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.activity.MainActivity;
import com.graduation.ui.register.RegisterActivity;
import com.graduation.utils.SharedPreUtil;

/**
 * Created by liyan on 2017/4/20.
 */

public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements LoginContact.View{

    private final static String TAG = "LoginActivity";
    private ProgressDialog mDialog;
    private String cellphone, password;
    private EditText edit_userName;
    private EditText edit_password;
    private String imgUrl;
    private TextInputLayout accountWrapper;
    private TextInputLayout passwordWrapper;
    private TextView tv_find_password;


    /**
     * 入口
     * @param context
     */
    public static void startAction(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
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
            Users user =new Users();
            user.setUserAccount(cellphone);
            user.setUserPassword(password);
            String u= JSON.toJSONString(user);
            mPresenter.login(u);
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void skip(BaseResponse<UsersInfo> response) {
        if(response.flag==1){
            UsersInfo userData=response.data;
            SharedPreUtil.getInstance().putUser(userData);
            MainActivity.startAction(this);
            finish();
        }else {
            Toast.makeText(this, ErrCode.printErrCause(response.errCode), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
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

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
}
