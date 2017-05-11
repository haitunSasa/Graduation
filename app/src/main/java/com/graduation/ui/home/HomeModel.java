package com.graduation.ui.home;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.QuestionUser;
import com.graduation.rx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by liyan on 2017/5/3.
 */

public class HomeModel implements HomeContact.Model {
    @Override
    public Observable<BaseResponse<List<QuestionUser>>> getLastQuestion() {
       // RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),question);
        return Api.getDefault(HostType.URL_TEST).getLastQuestion()
                //声明线程调度
                .compose(RxSchedulers.<BaseResponse<List<QuestionUser>>>io_main());
    }
}
