package com.graduation.ui.home;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.graduation.R;
import com.graduation.base.BaseFragment;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.QuestionUser;
import com.graduation.ui.LoadingTip;
import com.graduation.ui.adapter.IRecyclerListener;
import com.graduation.ui.adapter.QuestionRecyclerAdapter;
import com.graduation.ui.question.question_detail.QuestionDetailActivity;
import com.graduation.ui.recycler.IRecyclerView;
import com.graduation.ui.recycler.LoadMoreFooterView;
import com.graduation.ui.recycler.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter, HomeModel> implements HomeContact.View ,OnRefreshListener{
    private IRecyclerView irc;
    private QuestionRecyclerAdapter mAdapter;
    private List<QuestionUser> mData;
    private TextView tv_question;
    private TextView tv_answer;
    private TextView tv_user_name;
    private TextView tv_answer_name;
    LoadingTip loadedTip;
    private QuestionUser questionUser = new QuestionUser();

    List<QuestionUser> questions = new ArrayList<>();

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view) {
        irc = (IRecyclerView) view.findViewById(R.id.irc);
        loadedTip = (LoadingTip)view.findViewById(R.id.loadedTip);
        questionUser = new QuestionUser();
        mAdapter = new QuestionRecyclerAdapter(getContext(), new IRecyclerListener() {
            @Override
            public void onClickListener(int pos) {
                QuestionUser questionUser = mData.get(pos);
                QuestionDetailActivity.startAction(getActivity(), questionUser);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        irc.setLayoutManager(linearLayoutManager);
        irc.setAdapter(mAdapter);
        irc.setOnRefreshListener(this);
        getData();
    }

    @Override
    public void showLoading(String title) {
        if(mAdapter.getPageBean().isRefresh())
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
    }

    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
        irc.setRefreshing(false);
    }

    @Override
    public void showErrorTip(String msg) {
        if( mAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
            loadedTip.setTips(msg);
            irc.setRefreshing(false);
        }else{
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
        }
    }

    @Override
    public void refreshView(BaseResponse<List<QuestionUser>> response) {
        mData=response.data;
        mAdapter.reset(mData);

    }

    public void getData() {
        mPresenter.getLastQuestion();
    }
    @Override
    public void onRefresh() {
        mAdapter.getPageBean().setRefresh(true);
        //发起请求
        irc.setRefreshing(true);
        mPresenter.getLastQuestion();
    }
}
