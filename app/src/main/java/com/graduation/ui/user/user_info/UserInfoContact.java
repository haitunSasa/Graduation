package com.graduation.ui.user.user_info;

import com.graduation.base.BaseModel;
import com.graduation.base.BasePresenter;
import com.graduation.base.BaseView;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.search.SearchContact;

import rx.Observable;

/**
 * Created by liyan on 2017/5/12.
 */

public interface UserInfoContact {
    interface Model extends BaseModel {
        Observable<BaseResponse<UsersInfo>> getUserInfo(int userId,String token);

        Observable<BaseResponse<UsersInfo>> changeInfo(String userInfo);
    }
    interface View extends BaseView {
        void refresh(BaseResponse<UsersInfo> response);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
       abstract BaseResponse<UsersInfo> getUserInfo(int userId,String token);
       abstract BaseResponse<UsersInfo> changeInfo(String userInfo);
    }
}
