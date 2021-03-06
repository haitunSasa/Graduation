package com.graduation.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.R;
import com.graduation.base.BaseActivity;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.main.login.LoginActivity;
import com.graduation.utils.SharedPreUtil;


/**
 * des:启动页
 * Created by xsf
 * on 2016.09.15:16
 */
public class SplashActivity extends BaseActivity {
    ImageView ivLogo;
    TextView tvName;

    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }


    @Override
    public void initView() {
        ivLogo=(ImageView)findViewById(R.id.iv_logo);
         tvName=(TextView)findViewById(R.id.tv_name);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                SharedPreUtil.initSharedPreference(getApplicationContext());
                UsersInfo user=SharedPreUtil.getInstance().getUser();
                if(user.getToken()==null) {
                    LoginActivity.startAction(SplashActivity.this);
                }else {
                    MainActivity.startAction(SplashActivity.this);
                }
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    @Override
    public void initPresenter() {

    }

}
