package com.graduation.ui.question.question_detail;

import com.graduation.base.BaseModel;
import com.graduation.base.BasePresenter;
import com.graduation.base.BaseView;
import com.graduation.bean.AnswerUser;
import com.graduation.bean.BaseResponse;
import com.graduation.ui.home.HomeContact;

import java.util.List;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by liyan on 2017/5/10.
 */

public interface QuestionDetailContact {
    interface Model extends BaseModel{
        Observable<BaseResponse<List<AnswerUser>>> getAnswer(int userId,int questionId);
    }
    interface IView extends BaseView{
        void displayAnswer(BaseResponse<List<AnswerUser>> response);
    }
    abstract class Presenter extends BasePresenter<IView,Model> {
        public abstract BaseResponse<List<AnswerUser>> getAnswer(int userId,int questionId);
    }
}
