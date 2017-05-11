package com.graduation.ui.home;

import com.graduation.base.BaseModel;
import com.graduation.base.BasePresenter;
import com.graduation.base.BaseView;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.QuestionUser;

import java.util.List;

import rx.Observable;

/**
 * Created by liyan on 2017/5/3.
 */

public interface HomeContact {
    interface Model extends BaseModel {
        Observable<BaseResponse<List<QuestionUser>>> getLastQuestion();
    }

    interface View extends BaseView {
        void refreshView(BaseResponse<List<QuestionUser>> response);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract BaseResponse<List<QuestionUser>> getLastQuestion();
    }
}
