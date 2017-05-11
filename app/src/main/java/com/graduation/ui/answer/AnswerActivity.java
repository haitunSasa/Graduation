package com.graduation.ui.answer;

import com.graduation.R;
import com.graduation.base.BaseActivity;
import com.graduation.bean.BaseResponse;

public class AnswerActivity extends BaseActivity<AnswerPresenter,AnswerModel> implements AnswerContact.View{
    @Override
    public int getLayoutId() {
        return R.layout.activity_answer;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void skip(BaseResponse response) {

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
