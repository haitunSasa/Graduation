package com.graduation.ui.question.ask_question;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.graduation.R;
import com.graduation.base.BaseActivity;
import com.graduation.bean.Question;
import com.graduation.ui.main.MainActivity;
import com.graduation.utils.SharedPreUtil;

public class QuestionActivity extends BaseActivity<QuestionPresenter, QuestionModel> implements QuestionContact.View {

    private EditText et_question;

    private int userId;
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
        et_question =(EditText) findViewById(R.id.et_question);
        userId= SharedPreUtil.getInstance().getUser().getUserId();
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    public void ask_question(View v){
        String questionContent= et_question.getText().toString().trim();
        Question question =new Question();
        question.setQuestionContent(questionContent);
        question.setUserId(userId);
        question.setQuestionTypeId(2);
        question.setQuestionReward(10);

        String strEntity = JSON.toJSONString(question);
        mPresenter.askQuestion(strEntity);
    }

    @Override
    public void skip() {
        MainActivity.startAction(QuestionActivity.this);
        finish();
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
}
