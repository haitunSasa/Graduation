package com.graduation.ui.home.question_detail;

import com.graduation.base.BaseModel;
import com.graduation.base.BasePresenter;
import com.graduation.base.BaseView;
import com.graduation.bean.Answer;
import com.graduation.bean.AnswerUser;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.EavesdropperAnswer;

import java.util.List;

import rx.Observable;

/**
 * Created by liyan on 2017/5/10.
 */

public interface QuestionDetailContact {
    interface Model extends BaseModel{
        Observable<BaseResponse<List<AnswerUser>>> getAnswer(int userId,int questionId);
        Observable<BaseResponse> getUserAction(int userId,int questionId);
        Observable<BaseResponse<AnswerUser>> eavesdropper(String e);
    }
    interface IView extends BaseView{
        void displayAnswer(BaseResponse<List<AnswerUser>> response);
        void display(BaseResponse<AnswerUser> response);
    }
    abstract class Presenter extends BasePresenter<IView,Model> {
        public abstract BaseResponse<List<AnswerUser>> getAnswer(int userId,int questionId);
        public abstract BaseResponse getUserAction(int userId,int questionId);
        public abstract BaseResponse<AnswerUser> eavesdropper(String e);
    }
}
