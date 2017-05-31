package com.graduation.ui.user.user_info;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.graduation.R;
import com.graduation.base.BaseActivity;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.DatePickerFragment;
import com.graduation.utils.SharedPreUtil;

public class UserInfoActivity extends BaseActivity<UserInfoPresenter,UserInfoModel> implements UserInfoContact.View,View.OnClickListener,DatePickerFragment.TheListener{
    private TextView tv_userName, tv_cellphone, tv_userSex, tv_userAge;
    private ProgressDialog mDialog;
    private ImageView iv_userImg;
    private String[] items = new String[]{"从相册中选择", "拍照"};
    private String[] gender_items = new String[]{"男", "女"};
    private String photoStr = "";

    /* 修改内容 */
    private String userName = "NoName";
    private String birthday;
    private int sex = 0;
    private String imgUrl;
    private UsersInfo user;
    private String account="";
    private String token;
    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        user= SharedPreUtil.getInstance().getUser();
        tv_cellphone=(TextView)findViewById(R.id.tv_change_cellphone) ;
        tv_userName=(TextView)findViewById(R.id.tv_change_userName) ;
        tv_userSex=(TextView)findViewById(R.id.tv_change_userSex) ;
        tv_userAge=(TextView)findViewById(R.id.tv_change_userAge) ;
        findViewById(R.id.rl_change_userImg).setOnClickListener(this);
        findViewById(R.id.rl_change_cellphone).setOnClickListener(this);
        findViewById(R.id.rl_change_userName).setOnClickListener(this);
        findViewById(R.id.rl_change_userSex).setOnClickListener(this);
        findViewById(R.id.rl_change_userAge).setOnClickListener(this);
        findViewById(R.id.rl_change_pwd).setOnClickListener(this);
        initV(user);
    }
    public void initV(UsersInfo user){
        if (user.getUserAccount() != null) {
            account = user.getUserAccount();
            sex = user.getUserSex();
            userName = user.getUserName();
            birthday = user.getUserbirthday();
            imgUrl = user.getUserImg();
            token = user.getToken();

            tv_cellphone.setText(account);
            tv_userName.setText(userName);
            if (sex == 1) {
                tv_userSex.setText("男");
            } else if (sex == 0) {
                tv_userSex.setText("女");
            }
            if (birthday.contains(" ")) {
                int index = birthday.indexOf(" ");
                birthday = birthday.substring(0, index);
            }
            tv_userAge.setText(birthday);
        }
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // TODO:  用户头像和手机号
            /*case R.id.rl_change_userImg:
                showImageDialog();
                break;*/
            /*case R.id.rl_change_cellphone:
				EditInfoActivity.actionStart(this, 1);
				break;*/
            case R.id.rl_change_userName:
                //EditInfoActivity.actionStart(this, 2);
                final EditText rename = new EditText(UserInfoActivity.this);
                AlertDialog.Builder build1 = new AlertDialog.Builder(UserInfoActivity.this);
                build1.setTitle("修改用户名")
                        .setView(rename)
                        .setPositiveButton(
                                "确认",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        if (!rename.getText().toString().equals(null) && !rename.getText().toString().equals("")) {
                                            userName = rename.getText().toString();
                                            user.setUserName(userName);
                                            toChange();
                                        } else {
                                            Toast.makeText(UserInfoActivity.this, "请输入更改的用户名", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                        .setNegativeButton("取消", null).show();
                break;
            case R.id.rl_change_userSex:
                showGenderDialog();
                break;
            case R.id.rl_change_userAge:
                String date = birthday.replace("-", "");
                Log.i("date", date);
                int year = Integer.valueOf(date.substring(0, 4));
                int mouth = Integer.valueOf(date.substring(4, 6));
                int day = Integer.valueOf(date.substring(6));
                DialogFragment fragment = DatePickerFragment.getInstance(year, mouth, day);
                fragment.show(getSupportFragmentManager(), "datePicker");
                break;
            case R.id.rl_change_pwd:
                // TODO: 2017/5/17 修改密码
                /*Intent intent = new Intent();
                intent.setClass(this, ChangePwdActivity.class);
                startActivity(intent);*/
            default:
                break;
        }
    }
    private void toChange() {
        String user = JSON.toJSONString(this.user);
        mPresenter.changeInfo(user);
    }
    private void showGenderDialog() {
        new AlertDialog.Builder(this).setTitle("修改性别")
                .setItems(gender_items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            sex = 1;
                        } else {
                            sex = 0;
                        }
                        user.setUserSex((short)sex);
                        toChange();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    public void returnDate(String date) {
        if (!birthday.equals(date)) {
            birthday = date;
            this.user.setUserbirthday(date);
            toChange();
        }
    }

    @Override
    public void refresh(BaseResponse<UsersInfo> response) {
        UsersInfo u=response.data;
        initV(u);
    }
}
