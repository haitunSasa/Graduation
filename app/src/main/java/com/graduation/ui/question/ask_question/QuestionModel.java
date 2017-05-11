package com.graduation.ui.question.ask_question;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.rx.RxSchedulers;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by liyan on 2017/5/2.
 */

public class QuestionModel implements QuestionContact.Model {
    @Override
    public Observable<Void> askQuestion(String question) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),question);
        return Api.getDefault(HostType.URL_TEST).askQuestion(body)
        //声明线程调度
        .compose(RxSchedulers.<Void>io_main());
    }
}
