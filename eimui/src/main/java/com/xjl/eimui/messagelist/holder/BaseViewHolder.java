package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<MESSAGE> extends RecyclerView.ViewHolder {

    protected Context mContext;
    //当前的位置
    protected int mPosition;
    //是否被选中
    protected boolean mIsSelected;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindData(MESSAGE data,int mPosition);

}
