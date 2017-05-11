package com.graduation.ui.question.question_detail;

import com.graduation.R;
import com.graduation.bean.AnswerUser;
import com.graduation.bean.BaseResponse;
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
}
