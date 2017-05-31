package com.graduation.ui.user.user_info;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.rx.RxSchedulers;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by liyan on 2017/5/12.
 */

public class UserInfoModel implements UserInfoContact.Model{
    @Override
    public Observable<BaseResponse<UsersInfo>> getUserInfo(int userId, String token) {
        return Api.getDefault(HostType.URL_TEST).getUserInfo(userId,token)
                .compose(RxSchedulers.<BaseResponse<UsersInfo>>io_main());
    }

    @Override
    public Observable<BaseResponse<UsersInfo>> changeInfo(String userInfo) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),userInfo);
        return Api.getDefault(HostType.URL_TEST).changeInfo(body)
                .compose(RxSchedulers.<BaseResponse<UsersInfo>>io_main());

    }
}
