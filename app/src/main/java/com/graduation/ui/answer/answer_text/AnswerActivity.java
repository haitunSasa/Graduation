package com.graduation.ui.answer.answer_text;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.graduation.R;
import com.graduation.app.AppManager;
import com.graduation.base.BaseActivity;
import com.graduation.bean.Answer;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.ErrCode;
import com.graduation.bean.QuestionUser;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.home.question_detail.QuestionDetailActivity;
import com.graduation.utils.SharedPreUtil;

public class AnswerActivity extends BaseActivity<AnswerPresenter, AnswerModel> implements AnswerContact.View {
    private EditText et_answer;
    private UsersInfo usersInfo;
    private QuestionUser questionUser;

    public static void starAction(Context context, QuestionUser question) {
        Intent intent = new Intent(context, AnswerActivity.class);
        intent.putExtra("questionUser", question);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_answer;
    }

    @Override
    public void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        questionUser = (QuestionUser) getIntent().getSerializableExtra("questionUser");
        et_answer = (EditText) findViewById(R.id.et_answer);
        usersInfo = SharedPreUtil.getInstance().getUser();
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void skip(BaseResponse response) {
        if (response.flag == 1) {
            AppManager.getAppManager().finishActivity(QuestionDetailActivity.class);
            QuestionDetailActivity.startAction(this,questionUser);
            finish();
            //QuestionDetailActivity.startAction(mContext);
        } else {
            Toast.makeText(this, ErrCode.printErrCause(response.errCode), Toast.LENGTH_SHORT).show();
        }
    }

    public void answer(View view) {
        if(!et_answer.getText().toString().trim().equals("")&&et_answer.getText().toString().trim().length()>10) {
            Answer answer = new Answer();
            answer.setAnswerContent(et_answer.getText().toString().trim());
            answer.setUserId(usersInfo.getUserId());
            answer.setQuestionId(questionUser.getQuestionId());
            String str_answer = JSON.toJSONString(answer);
            mPresenter.answerQuestion(str_answer);
        }else {
            Toast.makeText(this,"字数需超过10个字",Toast.LENGTH_SHORT).show();
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
}
