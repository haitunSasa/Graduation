package com.graduation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.R;
import com.graduation.bean.UsersInfo;
import com.graduation.ui.adapter.listener.IRecyclerListener;
import com.graduation.ui.recycler.BaseRecyclerViewAdapter;

/**
 * Created by liyan on 2017/5/12.
 */

public class SearchAdapter extends BaseRecyclerViewAdapter<UsersInfo,SearchViewHolder>{
    private IRecyclerListener listener;
    public SearchAdapter(Context context,IRecyclerListener listener) {
        super(context);
        this.listener=listener;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_list, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        UsersInfo usersInfo = data.get(position);
        final int pos=position;
        holder.tv_user_name.setText(usersInfo.getUserName());
        if(usersInfo.getUserIntroduction()!=null) {
            holder.tv_introduction.setText(usersInfo.getUserIntroduction());
        }
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(pos);
            }
        });

    }
}
class SearchViewHolder extends RecyclerView.ViewHolder{
    ImageView iv_user_img;
    View v;
    TextView tv_user_name;
    TextView tv_introduction;

    public SearchViewHolder(View view) {
        super(view);
        v=view;
        iv_user_img = (ImageView) view.findViewById(R.id.iv_user_img);
        tv_user_name = (TextView) view.findViewById(R.id.tv_name);
        tv_introduction = (TextView) view.findViewById(R.id.tv_introduction);
    }
}
