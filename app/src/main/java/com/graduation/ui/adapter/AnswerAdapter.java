package com.graduation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.R;
import com.graduation.bean.AnswerUser;
import com.graduation.ui.adapter.listener.AnswerListener;
import com.graduation.ui.adapter.listener.IRecyclerListener;
import com.graduation.ui.recycler.BaseRecyclerViewAdapter;

/**
 * Created by liyan on 2017/5/10.
 */

public class AnswerAdapter extends BaseRecyclerViewAdapter<AnswerUser, AnswerViewHolder> {
    AnswerListener listener;
    public AnswerAdapter(Context context, AnswerListener listener) {
        super(context);
        this.listener=listener;
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_list, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        AnswerUser answerUser = data.get(position);
        final int pos=position;
        holder.tv_user_name.setText(answerUser.getUserName());
        if(answerUser.getRole()==1){
            holder.tv_reward.setText("专家");
        }else {
            holder.tv_reward.setText("");
        }
        if(answerUser.isCouldListen()){
            holder.tv_question.setText(answerUser.getAnswerContent());
        }else {
            holder.tv_question.setText("一元偷偷看");
            holder.tv_question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.eavesdropper(pos);
                }
            });
        }
    }
}

class AnswerViewHolder extends RecyclerView.ViewHolder{
    ImageView iv_user_img;
    View v;
    TextView tv_question;
    TextView tv_user_name;
    TextView tv_reward;

    public AnswerViewHolder(View view) {
        super(view);
        v=view;
        iv_user_img = (ImageView) view.findViewById(R.id.iv_answer_img);
        tv_user_name = (TextView) view.findViewById(R.id.tv_name);
        tv_reward = (TextView) view.findViewById(R.id.tv_reward);
        tv_question = (TextView) view.findViewById(R.id.tv_question);

    }
}