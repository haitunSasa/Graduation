package com.graduation.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.graduation.R;
import com.graduation.rx.RxManager;
import com.graduation.app.AppManager;


/**
 * Created by liyan on 2017/3/2.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;
    public RxManager mRxManager;
    //private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager=new RxManager();
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        setContentView(getLayoutId());
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext=this;
        this.initView();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
        AppManager.getAppManager().finishActivity(this);
    }
    public void back(View v) {
        finish();
    }

    /*************子类需要实现的方法*********************/
    //获取布局文件
    public abstract int getLayoutId() ;
    //初始化视图
    public abstract void initView();

}
