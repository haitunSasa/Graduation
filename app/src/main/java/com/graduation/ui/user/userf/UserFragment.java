package com.graduation.ui.user.userf;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.graduation.R;
import com.graduation.base.BaseFragment;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.main.login.LoginActivity;
import com.graduation.ui.user.user_info.UserInfoContact;
import com.graduation.ui.user.user_info.UserInfoActivity;
import com.graduation.utils.SharedPreUtil;

public class UserFragment extends BaseFragment implements  View.OnClickListener {
    private FragmentActivity mActivity;
    private RelativeLayout ll_user_setting,ll_user_exit;

    @Override
    protected void initPresenter() {
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView(View view) {
        mActivity = getActivity();
        UsersInfo usersInfo = SharedPreUtil.getInstance().getUser();
        TextView tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_user_name.setText(usersInfo.getUserName());
        ll_user_setting=(RelativeLayout) view.findViewById(R.id.ll_user_setting);
        ll_user_exit=(RelativeLayout) view.findViewById(R.id.ll_user_exit);
        ll_user_exit.setOnClickListener(this);
        ll_user_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_user_setting:
                Intent intent1 = new Intent();
                intent1.setClass(mActivity, UserInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_user_exit:
                AlertDialog.Builder build = new AlertDialog.Builder(mActivity);
                build.setTitle("退出")
                        .setMessage("确定要退出吗？")
                        .setPositiveButton("确认",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        UsersInfo userData = new UsersInfo();
                                        // 用户名，密码保存在SharedPreferences
                                        SharedPreUtil.getInstance().putUser(userData);
                                        Intent intent = new Intent();
                                        intent.setClass(mActivity, LoginActivity.class);
                                        startActivity(intent);
                                        mActivity.finish();
                                    }
                                })
                        .setNegativeButton("取消", null).show();
                break;
        }
    }
}
