package com.graduation.ui.main.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.graduation.R;
import com.graduation.base.BaseActivity;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.ErrCode;
import com.graduation.bean.Users;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.main.MainActivity;
import com.graduation.utils.SharedPreUtil;

import java.lang.ref.WeakReference;

/**
 * Created by liyan on 2017/4/20.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter,RegisterModel> implements RegisterContact.View{
    private ProgressDialog mDialog;
    private String cellphone, password, confirmPassword;
    private EditText edit_cellphone, edit_password, edit_confirm_password;
    private register_handler registerHandler;
    private TextInputLayout cellphoneWrapper, passwordWrapper, confirmPasswordWrapper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView() {

        cellphoneWrapper = (TextInputLayout) findViewById(R.id.cellphoneWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.nicknameWrapper);
        confirmPasswordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        edit_cellphone = (EditText) findViewById(R.id.edit_register_cellphone);
        edit_password = (EditText) findViewById(R.id.edit_register_password);
        edit_confirm_password = (EditText) findViewById(R.id.edit_register_confirm_password);

        edit_cellphone.setFocusableInTouchMode(false);
        edit_password.setFocusableInTouchMode(false);
        edit_confirm_password.setFocusableInTouchMode(false);
        edit_cellphone.addTextChangedListener(phoneWatcher);
        edit_password.addTextChangedListener(nameWatcher);
        edit_confirm_password.addTextChangedListener(pwdWatcher);

        edit_cellphone.setOnTouchListener(touchlistener);
        edit_password.setOnTouchListener(touchlistener);
        edit_confirm_password.setOnTouchListener(touchlistener);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        registerHandler = new register_handler(this);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }


    private TextWatcher phoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().trim().equals("")){
                cellphoneWrapper.setErrorEnabled(true);
                cellphoneWrapper.setError("请输入手机号");
            }else{
                cellphoneWrapper.setErrorEnabled(false);
            }
        }
    };


    private TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().trim().equals("")){
                passwordWrapper.setErrorEnabled(true);
                passwordWrapper.setError("请输入密码");
            }else{
                passwordWrapper.setErrorEnabled(false);
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
            if(s.toString().trim().equals("")){
                confirmPasswordWrapper.setErrorEnabled(true);
                confirmPasswordWrapper.setError("请输入确认密码");
            }else{
                confirmPasswordWrapper.setErrorEnabled(false);
            }
        }
    };

    private View.OnTouchListener touchlistener = new View.OnTouchListener() {
        @Override
        public boolean  onTouch(View v, MotionEvent event) {
            int touch_flag=0;
            switch (v.getId()) {
                // 当点击了相应tab时，选中相应tab
                case R.id.edit_register_cellphone:
                    edit_cellphone.setFocusableInTouchMode(true);
                    break;
                case R.id.edit_register_password:
                    edit_password.setFocusableInTouchMode(true);
                    break;
                case R.id.edit_register_confirm_password:
                    edit_confirm_password.setFocusableInTouchMode(true);
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void register(View v) {
        hideKeyboard();
        cellphone = edit_cellphone.getText().toString();
        password = edit_password.getText().toString();
        confirmPassword = edit_confirm_password.getText().toString();
        if(cellphone.isEmpty()||cellphone.equals("")){
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT)
                    .show();
            cellphoneWrapper.setErrorEnabled(true);
            cellphoneWrapper.setError("请输入手机号");

        }else if(password.isEmpty()|| password.equals("")){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT)
                    .show();
            passwordWrapper.setErrorEnabled(true);
            passwordWrapper.setError("请输入密码");

        }else if(!cellphone.matches("^1[34578]\\d{9}$")){
            Toast.makeText(this, "请输入11位的手机号！", Toast.LENGTH_SHORT)
                    .show();
            cellphoneWrapper.setErrorEnabled(true);
            cellphoneWrapper.setError("请输入11位的手机号");
        }else if(!password.matches("^[a-zA-Z][\\w/^]{7,31}$")){
            Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT)
                    .show();
            passwordWrapper.setErrorEnabled(true);
            passwordWrapper.setError("请输入正确的密码");
        }else if(confirmPassword.isEmpty()|| !confirmPassword.equals(password)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT)
                    .show();
            confirmPasswordWrapper.setErrorEnabled(true);
            confirmPasswordWrapper.setError("两次密码不一致");
        }else {
            Users user =new Users();
            user.setUserAccount(cellphone);
            user.setUserPassword(password);
            String u= JSON.toJSONString(user);
            mPresenter.register(u);
        }
    }



    @Override
    public void skip(BaseResponse<UsersInfo> response) {
        if(response.flag==1){
            UsersInfo usersInfo=response.data;
            SharedPreUtil.getInstance().putUser(usersInfo);
            MainActivity.startAction(this);
        }else {
            Toast.makeText(this, ErrCode.printErrCause(response.errCode), Toast.LENGTH_SHORT).show();
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

    // Handler
    static class register_handler extends Handler {
        WeakReference<RegisterActivity> mActivity;

        register_handler(RegisterActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            RegisterActivity theActivity = mActivity.get();
            switch (msg.what) {
                case 0:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "注册成功！", Toast.LENGTH_SHORT).show();
                    theActivity.finish();
                    break;
                case 1:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "该手机号已经注册！", Toast.LENGTH_SHORT)
                            .show();
                    theActivity.cellphoneWrapper.setErrorEnabled(true);
                    theActivity.cellphoneWrapper.setError("该手机号已经注册！");
                    break;
                case 2:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "网络连接超时了，请稍后重试", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case 4:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "输入手机号格式有误！", Toast.LENGTH_SHORT)
                            .show();
                    theActivity.cellphoneWrapper.setErrorEnabled(true);
                    theActivity.cellphoneWrapper.setError("输入手机号格式有误!");
                    break;
                case 5:
                    theActivity.mDialog.cancel();
                    Toast.makeText(theActivity, "输入密码格式错误！", Toast.LENGTH_SHORT)
                            .show();
                    theActivity.passwordWrapper.setErrorEnabled(true);
                    theActivity.passwordWrapper.setError("输入密码格式错误");
                    break;
            }
        }
    }
}
