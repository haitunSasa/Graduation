package com.graduation.ui.search;

import com.graduation.base.BaseModel;
import com.graduation.base.BasePresenter;
import com.graduation.base.BaseView;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by liyan on 2017/5/12.
 */

public interface SearchContact {
    interface Model extends BaseModel{
        Observable<BaseResponse<List<UsersInfo>>> search(String key);
    }
    interface View extends BaseView{
        void displayResult(BaseResponse<List<UsersInfo>> response);
    }
    abstract class Presenter extends BasePresenter<View,Model>{
        abstract BaseResponse<List<UsersInfo>> search(String key);
    }
}
