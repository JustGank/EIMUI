package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.widget.SendStateView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<MESSAGE extends EMessage> extends RecyclerView.ViewHolder {

    protected Context mContext;
    //当前的位置
    protected int mPosition;
    //是否被选中
    protected boolean mIsSelected;

    public TextView header;
    public ImageView accepter_avater;
    public TextView accepter_name;
    public RelativeLayout accepter_content_container;
    public RelativeLayout accepter_container;
    public ImageView sender_avater;
    public TextView sender_name;
    public RelativeLayout sender_content_container;
    public SendStateView state_view;
    public RelativeLayout sender_container;
    public RelativeLayout chat_container;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.header = (TextView) itemView.findViewById(R.id.header);
        this.accepter_avater = (ImageView) itemView.findViewById(R.id.accepter_avater);
        this.accepter_name = (TextView) itemView.findViewById(R.id.accepter_name);
        this.accepter_content_container = (RelativeLayout) itemView.findViewById(R.id.accepter_content_container);
        this.accepter_container = (RelativeLayout) itemView.findViewById(R.id.accepter_container);
        this.sender_avater = (ImageView) itemView.findViewById(R.id.sender_avater);
        this.sender_name = (TextView) itemView.findViewById(R.id.sender_name);
        this.sender_content_container = (RelativeLayout) itemView.findViewById(R.id.sender_content_container);
        this.state_view = (SendStateView) itemView.findViewById(R.id.state_view);
        this.sender_container = (RelativeLayout) itemView.findViewById(R.id.sender_container);
        this.chat_container = (RelativeLayout) itemView.findViewById(R.id.chat_container);
    }


    public void bindData(MESSAGE data, int mPosition) {
        this.header.setText(data.getTimeString());
        if(data.getMine()==null|| TextUtils.isEmpty(data.getMine().getAvatarPath())){

        }
    }

    public abstract void bindDateToChild(MESSAGE data, ViewGroup container, int mPosition);


}
