package com.graduation.ui.question.question_detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.R;
import com.graduation.base.BaseActivity;
import com.graduation.bean.AnswerUser;
import com.graduation.bean.BaseResponse;
import com.graduation.bean.QuestionUser;
import com.graduation.bean.Users;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.adapter.AnswerAdapter;
import com.graduation.ui.adapter.IRecyclerListener;
import com.graduation.ui.recycler.IRecyclerView;
import com.graduation.ui.recycler.LoadMoreFooterView;
import com.graduation.ui.recycler.OnRefreshListener;
import com.graduation.utils.SharedPreUtil;

import java.util.List;

import static com.graduation.bean.ErrCode.printErrCause;

public class QuestionDetailActivity extends BaseActivity<QuestionDetailPresenter,QuestionDetailModel> implements QuestionDetailContact.IView ,OnRefreshListener{
    private Context context;
    private String userName;
    private String questionContent;
    private int reward;
    private int questionIsAnswer;
    private QuestionUser questionUser;
    private ImageView iv_user_img;

    private List<AnswerUser> mData;
    private IRecyclerView irc;
    private AnswerAdapter mAdapter;

    private TextView tv_question;
    private TextView tv_user_name;
    private TextView tv_reward;
    private UsersInfo usersInfo;
    private Button btn_answer;

    public static void startAction(Context context, QuestionUser questionUser){
        Intent intent = new Intent(context,QuestionDetailActivity.class);
        intent.putExtra("questionUser",questionUser);
        context.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_question_detail;
    }

    @Override
    public void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        usersInfo=SharedPreUtil.getInstance().getUser();

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        irc = (IRecyclerView) findViewById(R.id.irc);

        iv_user_img = (ImageView) findViewById(R.id.iv_answer_img);
        tv_user_name = (TextView) findViewById(R.id.tv_name);
        tv_reward = (TextView) findViewById(R.id.tv_reward);
        tv_question = (TextView) findViewById(R.id.tv_question);
        btn_answer = (Button) findViewById(R.id.btn_answer);

        questionUser=(QuestionUser) getIntent().getSerializableExtra("questionUser");
        questionContent=questionUser.getQuestionContent();
        questionIsAnswer=questionUser.getQuestionIsAnswer();
        userName=questionUser.getUserName();
        reward=questionUser.getQuestionReward();

        if(usersInfo.getRole()==0){
            btn_answer.setText("认证专家");
            btn_answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 2017/5/11 认证专家
                }
            });
        }else {
            btn_answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 2017/5/11 回答问题
                }
            });
        }


        mAdapter = new AnswerAdapter(this, new IRecyclerListener() {
            @Override
            public void onClickListener(int pos) {
                //// TODO: 2017/5/10 偷听
               /* AnswerUser questionUser = mData.get(pos);
                QuestionDetailActivity.startAction(getActivity(), questionUser);*/
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        irc.setLayoutManager(linearLayoutManager);
        irc.setAdapter(mAdapter);
        irc.setOnRefreshListener(this);

        tv_user_name.setText(userName);
        tv_question.setText(questionContent);
        tv_reward.setText("￥"+reward);
        /*if(questionIsAnswer==1){*/
            getAnswer();

    }

    private void getAnswer() {
        mPresenter.getAnswer(usersInfo.getUserId(),questionUser.getQuestionId());
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {
        irc.setRefreshing(false);
    }

    @Override
    public void showErrorTip(String msg) {
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
    }


    @Override
    public void onRefresh() {
        mAdapter.getPageBean().setRefresh(true);
        //发起请求
        irc.setRefreshing(true);
        getAnswer();
    }

    @Override
    public void displayAnswer(BaseResponse<List<AnswerUser>> response) {
        if(response.flag==1) {
            mData = response.data;
            mAdapter.reset(mData);
        }else {
            Toast.makeText(this, printErrCause(response.errCode), Toast.LENGTH_SHORT).show();
        }
    }
}
