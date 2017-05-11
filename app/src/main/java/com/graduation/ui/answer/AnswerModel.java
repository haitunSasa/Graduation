package com.graduation.ui.answer;

import com.graduation.bean.BaseResponse;

import rx.Observable;

/**
 * Created by liyan on 2017/5/11.
 */

public class AnswerModel implements AnswerContact.Model {
    @Override
    public Observable<BaseResponse> answerQuestion(String answer) {
        return null;
    }
}
