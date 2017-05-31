package com.graduation.ui.main.login;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.rx.RxSchedulers;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by liyan on 2017/5/10.
 */

public class LoginModel implements LoginContact.Model {
    @Override
    public Observable<BaseResponse<UsersInfo>> login(String user) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),user);
        return Api.getDefault(HostType.URL_TEST).login(body)
                //声明线程调度
                .compose(RxSchedulers.<BaseResponse<UsersInfo>>io_main());
    }
}
