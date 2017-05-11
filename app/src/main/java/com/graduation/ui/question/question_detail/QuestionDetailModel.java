package com.graduation.ui.question.question_detail;

import com.graduation.api.Api;
import com.graduation.api.HostType;
import com.graduation.bean.Answer;
import com.graduation.bean.AnswerUser;
import com.graduation.bean.BaseResponse;
import com.graduation.rx.RxSchedulers;

import java.util.List;

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
}
