package com.graduation.ui.question.ask_question;

import com.graduation.R;
import com.graduation.rx.RxSubscriber;

/**
 * Created by liyan on 2017/5/2.
 */

public class QuestionPresenter extends QuestionContact.Presenter{

    @Override
    public void askQuestion(String question) {
        mRxManage.add(mModel.askQuestion(question)
                .subscribe(new RxSubscriber<Void>(mContext) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(Void newsSummaries) {
                mView.skip();
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
