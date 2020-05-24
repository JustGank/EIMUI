package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.EUser;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.widget.SendStateView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<MESSAGE extends EMessage> extends RecyclerView.ViewHolder {

    Context context;
    //是否被选中
    protected boolean mIsSelected;

    public TextView header;
    public ImageView is_select;
    public ImageView mine_avater;
    public TextView mine_name;
    public RelativeLayout mine_content_container;
    public RelativeLayout mine_container;
    public SendStateView state_view;
    public ImageView other_avater;
    public TextView other_name;
    public RelativeLayout other_content_container;
    public RelativeLayout other_container;
    public RelativeLayout chat_container;

    public BaseViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        this.header = (TextView) itemView.findViewById(R.id.header);
        this.is_select = (ImageView) itemView.findViewById(R.id.is_select);
        this.mine_avater = (ImageView) itemView.findViewById(R.id.mine_avater);
        this.mine_name = (TextView) itemView.findViewById(R.id.mine_name);
        this.mine_content_container = (RelativeLayout) itemView.findViewById(R.id.mine_content_container);
        this.mine_container = (RelativeLayout) itemView.findViewById(R.id.mine_container);
        this.state_view = (SendStateView) itemView.findViewById(R.id.state_view);
        this.other_avater = (ImageView) itemView.findViewById(R.id.other_avater);
        this.other_name = (TextView) itemView.findViewById(R.id.other_name);
        this.other_content_container = (RelativeLayout) itemView.findViewById(R.id.other_content_container);

        this.other_container = (RelativeLayout) itemView.findViewById(R.id.other_container);
        this.chat_container = (RelativeLayout) itemView.findViewById(R.id.chat_container);
    }


    public void bindData(MESSAGE data, boolean mIsSelected, EUser mine, EUser other) {
        this.mIsSelected = mIsSelected;
        //初始化头部分 一般用于显示时间或者提示性信息
        setHeader(data);
        //在选择模式下是否被选中
        setmIsSelected(data);
        //初始化聊天人员信息
        if (MessageType.isReceivedMessage(data.getMessageType())) {
            this.mine_container.setVisibility(View.GONE);
            this.other_container.setVisibility(View.VISIBLE);
            this.state_view.setVisibility(View.GONE);
            setOtheInfo(other);
        } else {
            this.mine_container.setVisibility(View.VISIBLE);
            this.other_container.setVisibility(View.GONE);
            setMessageStatus(data.getMessageStatus());
            setMineInfo(mine);
        }
        //将容器交给子类实现
        this.mine_content_container.removeAllViews();
        this.other_content_container.removeAllViews();
        bindDateToChild(data, this.mine_content_container, this.other_content_container);


    }


    public void setHeader(MESSAGE data) {
        this.header.setText(data.getTimeString());
    }

    public void setmIsSelected(MESSAGE data) {
        is_select.setBackgroundResource(data.isChecked() ? R.mipmap.item_checked : R.mipmap.item_uncheck);
    }

    public void setMineInfo(EUser user) {
        if (user == null) {
            this.mine_avater.setVisibility(View.GONE);
            this.mine_name.setVisibility(View.GONE);
        } else {
            if (TextUtils.isEmpty(user.getAvatarPath())) {
                this.mine_avater.setVisibility(View.GONE);
            } else {
                this.mine_avater.setVisibility(View.VISIBLE);
                Glide.with(this.mine_avater.getContext())
                        .load(user.getAvatarPath())
                        .into(this.mine_avater);
            }
            if (TextUtils.isEmpty(user.getNickname())) {
                this.mine_name.setVisibility(View.GONE);
            } else {
                this.mine_name.setVisibility(View.VISIBLE);
                this.mine_name.setText(user.getNickname());
            }
        }
    }

    public void setOtheInfo(EUser user) {
        if (user == null) {
            this.other_avater.setVisibility(View.GONE);
            this.other_name.setVisibility(View.GONE);
        } else {
            if (TextUtils.isEmpty(user.getAvatarPath())) {
                this.other_avater.setVisibility(View.GONE);
            } else {
                this.other_avater.setVisibility(View.VISIBLE);
                Glide.with(this.other_avater.getContext())
                        .load(user.getAvatarPath())
                        .into(this.other_avater);
            }

            if (TextUtils.isEmpty(user.getNickname())) {
                this.other_name.setVisibility(View.GONE);
            } else {
                this.other_name.setVisibility(View.VISIBLE);
                this.other_name.setText(user.getNickname());
            }
        }
    }

    public void setMessageStatus(MessageStatus status) {
        if (status == MessageStatus.SEND_SUCCEED) {
            this.state_view.setVisibility(View.GONE);
        } else if (status == MessageStatus.SEND_GOING) {
            this.state_view.setVisibility(View.VISIBLE);
            this.state_view.setCurrentState(1);
        }

    }


    public abstract void bindDateToChild(MESSAGE data, ViewGroup mineContainer, ViewGroup otherContainer);

    public View getView(int layoutId) {
        View view = null;
        if (context != null) {
            view = LayoutInflater.from(context).inflate(layoutId, null);
        }
        return view;
    }
}
