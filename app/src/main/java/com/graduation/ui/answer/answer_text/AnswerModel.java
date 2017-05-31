package com.graduation.ui.answer.answer_text;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.bean.BaseResponse;
import com.graduation.rx.RxSchedulers;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by liyan on 2017/5/11.
 */

public class AnswerModel implements AnswerContact.Model {
    @Override
    public Observable<BaseResponse> answerQuestion(String answer) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),answer);
        return Api.getDefault(HostType.URL_TEST).answerQuestion(body)
                //声明线程调度
                .compose(RxSchedulers.<BaseResponse>io_main());
    }
}
