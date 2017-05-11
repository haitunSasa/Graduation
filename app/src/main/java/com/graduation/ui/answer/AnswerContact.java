package com.graduation.ui.answer;

import com.graduation.base.BaseModel;
import com.graduation.base.BasePresenter;
import com.graduation.base.BaseView;
import com.graduation.bean.BaseResponse;

import rx.Observable;

/**
 * Created by liyan on 2017/5/11.
 */

public interface AnswerContact {
    interface Model extends BaseModel{
        Observable<BaseResponse> answerQuestion(String answer);
    }
    interface View extends BaseView {
        void skip(BaseResponse response);
    }
    abstract class Presenter extends BasePresenter<View,Model>{
        abstract BaseResponse answerQuestion(String answer);
    }

}
