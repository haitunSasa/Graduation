package com.graduation.ui.home;

import com.graduation.R;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.QuestionUser;
import com.graduation.rx.RxSubscriber;

import java.util.List;

/**
 * Created by liyan on 2017/5/3.
 */

public class HomePresenter extends HomeContact.Presenter{
    @Override
    public BaseResponse getLastQuestion() {
        mRxManage.add(mModel.getLastQuestion().subscribe(new RxSubscriber<BaseResponse<List<QuestionUser>>>(mContext) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(BaseResponse<List<QuestionUser>> listBaseResponse) {
                mView.refreshView(listBaseResponse);
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
