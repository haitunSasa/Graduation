package com.graduation.ui.search;

import com.graduation.R;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.UsersInfo;
import com.graduation.rx.RxSubscriber;

import java.util.List;

/**
 * Created by liyan on 2017/5/12.
 */

public class SearchPresenter extends SearchContact.Presenter {
    @Override
    BaseResponse<List<UsersInfo>> search(String key) {
        mRxManage.add(mModel.search(key).subscribe(new RxSubscriber<BaseResponse<List<UsersInfo>>>(mContext) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(BaseResponse<List<UsersInfo>> response) {
                mView.displayResult(response);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
        return null;
    }
}
