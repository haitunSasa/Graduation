package com.graduation.ui.main.login;

import com.graduation.R;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.rx.RxSubscriber;

/**
 * Created by liyan on 2017/5/10.
 */

public class LoginPresenter extends LoginContact.Presenter {
    @Override
    BaseResponse<UsersInfo> login(String user) {
        mRxManage.add(mModel.login(user).subscribe(new RxSubscriber<BaseResponse<UsersInfo>>(mContext) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }
            @Override
            protected void _onNext(BaseResponse<UsersInfo> usersInfoBaseResponse) {
                mView.skip(usersInfoBaseResponse);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
        return null;
    }
}
