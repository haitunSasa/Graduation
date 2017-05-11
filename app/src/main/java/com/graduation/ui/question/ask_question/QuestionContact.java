package com.graduation.ui.question.ask_question;

import android.view.View;

import com.graduation.base.BaseModel;
import com.graduation.base.BasePresenter;
import com.graduation.base.BaseView;
import com.graduation.bean.Question;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by liyan on 2017/5/2.
 */

public interface QuestionContact {

    interface Model extends BaseModel {
        //提问
        Observable<Void> askQuestion(String question);
    }

    interface View extends BaseView {
        void skip();
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        //提问
        public abstract void askQuestion(String question);

    }

}
