package com.graduation.ui.register;

import com.graduation.R;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.rx.RxSubscriber;

/**
 * Created by liyan on 2017/5/11.
 */

public class RegisterPresenter extends RegisterContact.Presenter {
    @Override
    BaseResponse<UsersInfo> register(String user) {
        mRxManage.add(mModel.register(user).subscribe(new RxSubscriber<BaseResponse<UsersInfo>>(mContext) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(BaseResponse<UsersInfo> response) {
                mView.skip(response);
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
