package com.graduation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.graduation.R;

public class QuestionActivity extends BaseActivity {

    public void startAction(Activity activity){
        Intent intent=new Intent(activity,QuestionActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_question;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView() {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
