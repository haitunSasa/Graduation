package com.graduation.ui.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.R;
import com.graduation.base.BaseFragment;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.ErrCode;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.adapter.listener.IRecyclerListener;
import com.graduation.ui.adapter.SearchAdapter;
import com.graduation.ui.recycler.IRecyclerView;

import java.util.List;

// TODO: 2017/5/12 修改接口之后改
public class SearchFragment extends BaseFragment<SearchPresenter,SearchModel> implements SearchContact.View{
    private EditText et_search;
    private TextView tv_search;
    private Toolbar toolbar;
    private IRecyclerView irc;
    private String userName;
    private SearchAdapter mAdapter;

    private List<UsersInfo> mData;

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(View view) {
        et_search=(EditText) view.findViewById(R.id.et_search);
        irc=(IRecyclerView)view.findViewById(R.id.irc);
        tv_search=(TextView)view.findViewById(R.id.tv_search);
        toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        toolbar.setTitle("搜索");
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    //TODO回车键按下时要执行的操作
                    String key=et_search.getText().toString().trim();
                    mPresenter.search(key);
                }
                return false;
            }
        });

        mAdapter = new SearchAdapter(getContext(), new IRecyclerListener() {
            @Override
            public void onClickListener(int pos) {
                //UsersInfo questionUser = mData.get(pos);
               // QuestionDetailActivity.startAction(getActivity(), questionUser);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        irc.setLayoutManager(linearLayoutManager);
        irc.setAdapter(mAdapter);
    }


    @Override
    public void displayResult(BaseResponse<List<UsersInfo>> response) {
        if(response.flag==1){
            if(response.data==null){
                tv_search.setText("无此用户，换个关键词吧");
            }else{
                tv_search.setText("以下是搜索内容");
            }
            mData=response.data;
            mAdapter.reset(mData);
        }else {
            Toast.makeText(getContext(), ErrCode.printErrCause(response.errCode), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
