package com.graduation.ui.main.login;

import com.graduation.base.BaseModel;
import com.graduation.base.BasePresenter;
import com.graduation.base.BaseView;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;

import rx.Observable;

/**
 * Created by liyan on 2017/5/10.
 */

public interface LoginContact {
    interface Model extends BaseModel {
        Observable<BaseResponse<UsersInfo>> login(String user);
    }
    interface View extends BaseView {
        void skip(BaseResponse<UsersInfo> response);
    }
    abstract class Presenter extends BasePresenter<View,Model>{
        abstract BaseResponse<UsersInfo> login(String user);
    }
}
