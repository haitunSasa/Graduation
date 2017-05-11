package com.graduation.ui.register;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.rx.RxSchedulers;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by liyan on 2017/5/11.
 */

public class RegisterModel implements RegisterContact.Model {
    @Override
    public Observable<BaseResponse<UsersInfo>> register(String user) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),user);
        return Api.getDefault(HostType.URL_TEST).register(body)
                //声明线程调度
                .compose(RxSchedulers.<BaseResponse<UsersInfo>>io_main());
    }
}
