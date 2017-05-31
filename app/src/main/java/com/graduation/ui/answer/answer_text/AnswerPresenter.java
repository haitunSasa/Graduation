package com.graduation.ui.answer.answer_text;

import com.graduation.R;
import com.graduation.bean.BaseResponse;
import com.graduation.rx.RxSubscriber;

/**
 * Created by liyan on 2017/5/11.
 */

public class AnswerPresenter extends AnswerContact.Presenter {
    @Override
    BaseResponse answerQuestion(String answer) {
        mRxManage.add(mModel.answerQuestion(answer).subscribe(new RxSubscriber<BaseResponse>(mContext) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }
            @Override
            protected void _onNext(BaseResponse response) {
                mView.skip(response);
            }
            @Override
            protected void _onError(String message) {

            }
        }));
        return null;
    }
}
