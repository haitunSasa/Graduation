package com.graduation.ui.user.user_info;

import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.rx.RxSubscriber;

/**
 * Created by liyan on 2017/5/12.
 */

public class UserInfoPresenter extends UserInfoContact.Presenter{
    @Override
    BaseResponse<UsersInfo> getUserInfo(int userId, String token) {
        mRxManage.add(mModel.getUserInfo(userId,token).subscribe(new RxSubscriber<BaseResponse<UsersInfo>>(mContext,false) {
            @Override
            protected void _onNext(BaseResponse<UsersInfo> response) {

            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
        return null;
    }

    @Override
    BaseResponse<UsersInfo> changeInfo(String userInfo) {
        mRxManage.add(mModel.changeInfo(userInfo).subscribe(new RxSubscriber<BaseResponse<UsersInfo>>(mContext) {
            @Override
            protected void _onNext(BaseResponse<UsersInfo> response) {
                mView.refresh(response);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
        return null;
    }
}
