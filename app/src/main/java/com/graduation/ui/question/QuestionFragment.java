package com.graduation.ui.question;

import android.view.View;
import android.widget.TextView;

import com.graduation.R;
import com.graduation.base.BaseFragment;
import com.graduation.ui.question.ask_question.QuestionActivity;

/**
 * Created by liyan on 2017/4/20.
 */

public class QuestionFragment extends BaseFragment {
    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_question;
    }

    @Override
    protected void initView(View view) {
        TextView tv_ask=(TextView) view.findViewById(R.id.tv_ask);
        tv_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionActivity activity=new QuestionActivity();
                activity.startAction(getActivity());
            }
        });
    }
}
