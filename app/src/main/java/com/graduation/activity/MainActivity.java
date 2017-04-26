package com.graduation.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.graduation.R;
import com.graduation.app.AppConstant;
import com.graduation.bean.TabEntity;
import com.graduation.fragment.HomeFragment;
import com.graduation.fragment.QuestionFragment;
import com.graduation.fragment.SearchFragment;
import com.graduation.fragment.UserFragment;

import java.util.ArrayList;

import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页", "快问","找人","个人"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal,R.mipmap.ic_care_normal,R.mipmap.ic_girl_normal,R.mipmap.ic_video_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected,R.mipmap.ic_care_selected,R.mipmap.ic_girl_selected, R.mipmap.ic_video_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private SearchFragment searchFragment;
    private QuestionFragment questionFragment;
    private HomeFragment homeFragment;
    private UserFragment userFragment;
    private static int tabLayoutHeight;

    /**
     * 入口
     * @param activity
     */
/*    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                com.jaydenxiao.common.R.anim.fade_out);
    }*/

    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void initView() {
        //初始化菜单

        initTab();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化frament
        initFragment(savedInstanceState);

        tabLayout.measure(0,0);
        tabLayoutHeight=tabLayout.getMeasuredHeight();
        //监听菜单显示或隐藏
        mRxManager.on(AppConstant.MENU_SHOW_HIDE, new Action1<Boolean>() {
            @Override
            public void call(Boolean hideOrShow) {
                startAnimation(hideOrShow);
            }
        });
    }
    /**
     * 初始化tab
     */
    private void initTab() {
        tabLayout=(CommonTabLayout)findViewById(R.id.tab_layout);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag("searchFragment");
            questionFragment = (QuestionFragment) getSupportFragmentManager().findFragmentByTag("questionFragment");
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
            userFragment = (UserFragment) getSupportFragmentManager().findFragmentByTag("userFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            searchFragment = new SearchFragment();
            questionFragment = new QuestionFragment();
            homeFragment = new HomeFragment();
            userFragment = new UserFragment();

            transaction.add(R.id.fl_body, homeFragment, "homeFragment");
            transaction.add(R.id.fl_body, questionFragment, "questionFragment");
            transaction.add(R.id.fl_body, searchFragment, "searchFragment");
            transaction.add(R.id.fl_body, userFragment, "userFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                transaction.hide(questionFragment);
                transaction.hide(searchFragment);
                transaction.hide(userFragment);
                transaction.show(homeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //快问
            case 1:
                transaction.hide(searchFragment);
                transaction.hide(homeFragment);
                transaction.hide(userFragment);
                transaction.show(questionFragment);
                transaction.commitAllowingStateLoss();
                break;
            //搜索
            case 2:
                transaction.hide(homeFragment);
                transaction.hide(questionFragment);
                transaction.hide(userFragment);
                transaction.show(searchFragment);
                transaction.commitAllowingStateLoss();
                break;
            //个人
            case 3:
                transaction.hide(searchFragment);
                transaction.hide(questionFragment);
                transaction.hide(homeFragment);
                transaction.show(userFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    /**
     * 菜单显示隐藏动画
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide){
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if(!showOrHide){
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        }else{
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
        Log.i("MainActivity","onSaveInstanceState进来了1");
        if (tabLayout != null) {
            Log.i("MainActivity","onSaveInstanceState进来了2");
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }

}
