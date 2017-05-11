package com.graduation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.R;
import com.graduation.bean.QuestionUser;
import com.graduation.ui.recycler.BaseRecyclerViewAdapter;

/**
 * Created by liyan on 2017/5/3.
 */

public class QuestionRecyclerAdapter extends BaseRecyclerViewAdapter<QuestionUser, ViewHolder> {
    IRecyclerListener listener;
    public QuestionRecyclerAdapter(Context context,IRecyclerListener listener) {
        super(context);
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        QuestionUser questionUser = data.get(position);
        final int pos=position;
        holder.tv_user_name.setText(questionUser.getUserName());
        holder.tv_reward.setText("￥"+questionUser.getQuestionReward());
        holder.tv_question.setText(questionUser.getQuestionContent());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(pos);
            }
        });
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    ImageView iv_user_img;
    View v;
    TextView tv_question;
    TextView tv_user_name;
    TextView tv_reward;

    public ViewHolder(View view) {
        super(view);
        v=view;
        iv_user_img = (ImageView) view.findViewById(R.id.iv_answer_img);
        tv_user_name = (TextView) view.findViewById(R.id.tv_name);
        tv_reward = (TextView) view.findViewById(R.id.tv_reward);
        tv_question = (TextView) view.findViewById(R.id.tv_question);

    }
/*
    public    ViewHolder(View rootView, MyItemClickListener listener, MyItemLongClickListener longClickListener){
        super(rootView);
        iv = (ImageView) rootView.findViewById(R.id.item_iv);
        tv=(TextView) rootView.findViewById(R.id.item_tv);
        this.mListener=listener;
        this.mLongClickListener=longClickListener;
        rootView.setOnClickListener(this);
        rootView.setOnLongClickListener(this);
        }

    @Override
    public void onClick(View view) {
        if(mListener!=null){
            mListener.onItemClick(view,getAdapterPosition());
        }
    }*/

}

