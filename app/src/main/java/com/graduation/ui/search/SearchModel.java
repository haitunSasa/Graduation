package com.graduation.ui.search;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.rx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by liyan on 2017/5/12.
 */

public class SearchModel implements SearchContact.Model {
    @Override
    public Observable<BaseResponse<List<UsersInfo>>> search(String key) {
        return Api.getDefault(HostType.URL_TEST).search(key)
                //声明线程调度
                .compose(RxSchedulers.<BaseResponse<List<UsersInfo>>>io_main());
    }
}
