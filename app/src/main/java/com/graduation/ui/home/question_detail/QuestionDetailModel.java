package com.graduation.ui.home.question_detail;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.bean.Answer;
import com.graduation.bean.AnswerUser;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.EavesdropperAnswer;
import com.graduation.rx.RxSchedulers;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by liyan on 2017/5/10.
 */

public class QuestionDetailModel implements QuestionDetailContact.Model {

    @Override
    public Observable<BaseResponse<List<AnswerUser>>> getAnswer(int userId, int questionId) {
        return Api.getDefault(HostType.URL_TEST).getAnswer(userId, questionId)
                //声明线程调度
                .compose(RxSchedulers.<BaseResponse<List<AnswerUser>>>io_main());
    }

    @Override
    public Observable<BaseResponse> getUserAction(int userId, int questionId) {
        return Api.getDefault(HostType.URL_TEST).getUserAction(userId, questionId)
                //声明线程调度
                .compose(RxSchedulers.<BaseResponse>io_main());
    }

    @Override
    public Observable<BaseResponse<AnswerUser>> eavesdropper(String e) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),e);

        return Api.getDefault(HostType.URL_TEST).eavesdropper(body)
                //声明线程调度
                .compose(RxSchedulers.<BaseResponse<AnswerUser>>io_main());
    }
}
