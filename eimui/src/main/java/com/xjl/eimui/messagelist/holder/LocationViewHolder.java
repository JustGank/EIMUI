package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xjl.eimui.messagelist.bean.EMessage;

import androidx.annotation.NonNull;

public class LocationViewHolder<MESSAGE extends EMessage> extends BaseViewHolder {

    public LocationViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {

    }

}
