package com.graduation.ui.home.question_detail;

import com.graduation.R;
import com.graduation.bean.Answer;
import com.graduation.bean.AnswerUser;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.EavesdropperAnswer;
import com.graduation.rx.RxSubscriber;

import java.util.List;

/**
 * Created by liyan on 2017/5/10.
 */

public class QuestionDetailPresenter extends QuestionDetailContact.Presenter{
    @Override
    public BaseResponse<List<AnswerUser>> getAnswer(int userId, int questionId) {
        mRxManage.add(mModel.getAnswer(userId,questionId).subscribe(new RxSubscriber<BaseResponse<List<AnswerUser>>>(mContext) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(BaseResponse<List<AnswerUser>> listBaseResponse) {
                mView.displayAnswer(listBaseResponse);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
        return null;
    }

    @Override
    public BaseResponse getUserAction(int userId, int questionId) {
        mRxManage.add(mModel.getUserAction(userId, questionId).subscribe(new RxSubscriber<BaseResponse>(mContext,false) {
            @Override
            protected void _onNext(BaseResponse response) {

            }

            @Override
            protected void _onError(String message) {

            }
        }));
        return null;
    }

    @Override
    public BaseResponse<AnswerUser> eavesdropper(String e) {
        mRxManage.add(mModel.eavesdropper(e).subscribe(new RxSubscriber<BaseResponse<AnswerUser>>(mContext) {
            @Override
            protected void _onNext(BaseResponse<AnswerUser> response) {
                mView.display(response);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
        return null;
    }
}
