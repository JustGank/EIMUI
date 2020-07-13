package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.EUser;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.listener.OperationListener;
import com.xjl.eimui.messagelist.widget.SendStateView;
import com.xjl.eimui.util.GlideHelper;

public abstract class MessageViewHolderBase<MESSAGE extends EMessage> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    Context context;
    //是否处于选中模式
    protected boolean isSelectedModel;

    public TextView header;
    public ImageView is_select;
    public ImageView mine_avater;
    public TextView mine_name;
    public RelativeLayout mine_content_container;
    public LinearLayout mine_container;
    public SendStateView state_view;
    public ImageView other_avater;
    public TextView other_name;
    public RelativeLayout other_content_container;
    public RelativeLayout other_container;
    public RelativeLayout chat_container;
    public OperationListener<MESSAGE> operationListener;
    public MESSAGE data;
    public int position;

    public MessageViewHolderBase(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        this.header = (TextView) itemView.findViewById(R.id.header);
        this.header.setOnClickListener(this);
        this.header.setOnLongClickListener(this);
        this.is_select = (ImageView) itemView.findViewById(R.id.is_select);
        this.is_select.setOnClickListener(this);
        this.is_select.setOnLongClickListener(this);
        this.mine_avater = (ImageView) itemView.findViewById(R.id.mine_avater);
        this.mine_avater.setOnClickListener(this);
        this.mine_avater.setOnLongClickListener(this);
        this.mine_name = (TextView) itemView.findViewById(R.id.mine_name);
        this.mine_name.setOnClickListener(this);
        this.mine_name.setOnLongClickListener(this);
        this.mine_content_container = (RelativeLayout) itemView.findViewById(R.id.mine_content_container);
        this.mine_container = (LinearLayout) itemView.findViewById(R.id.mine_container);
        this.state_view = (SendStateView) itemView.findViewById(R.id.state_view);
        this.state_view.setOnClickListener(this);
        this.state_view.setOnLongClickListener(this);
        this.other_avater = (ImageView) itemView.findViewById(R.id.other_avater);
        this.other_avater.setOnClickListener(this);
        this.other_avater.setOnLongClickListener(this);
        this.other_name = (TextView) itemView.findViewById(R.id.other_name);
        this.other_name.setOnClickListener(this);
        this.other_name.setOnLongClickListener(this);
        this.other_content_container = (RelativeLayout) itemView.findViewById(R.id.other_content_container);
        this.other_container = (RelativeLayout) itemView.findViewById(R.id.other_container);
        this.chat_container = (RelativeLayout) itemView.findViewById(R.id.chat_container);
    }

    public void setOperationListener(OperationListener<MESSAGE> operationListener) {
        this.operationListener = operationListener;
    }

    @Override
    public void onClick(View v) {
        if (operationListener == null) {
            return;
        }
        int viewId = v.getId();
        if (viewId == R.id.header) {
            operationListener.onHeaderClickListener(position, v, data);
        } else if (viewId == R.id.mine_avater || viewId == R.id.mine_name) {
            operationListener.onMineInfoClickListener(position, v, data);
        } else if (viewId == R.id.other_avater || viewId == R.id.other_name) {
            operationListener.onOtherInfoClickListener(position, v, data);
        } else if (viewId == R.id.state_view) {
            operationListener.onStateViewClickListener(position, v, data);
        } else {
            operationListener.onItemClickListener(position, v, data);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (operationListener == null) {
            return false;
        }

        int viewId = v.getId();
        if (viewId == R.id.header) {
            operationListener.onHeaderLongClickListener(position, v, data);
        } else if (viewId == R.id.mine_avater || viewId == R.id.mine_name) {
            operationListener.onMineInfoLongClickListener(position, v, data);
        } else if (viewId == R.id.other_avater || viewId == R.id.other_name) {
            operationListener.onOtherInfoLongClickListener(position, v, data);
        } else {
            operationListener.onItemLongClickListener(position, v, data);
        }
        return true;
    }

    public void bindData(MESSAGE data, boolean isSelectedModel, int position) {
        this.isSelectedModel = isSelectedModel;
        this.data = data;
        this.position = position;

        //在选择模式下是否被选中
        setmIsSelected(data);
        //初始化聊天人员信息
        if (MessageType.isReceivedMessage(data.getMessageType())) {
            this.mine_container.setVisibility(View.GONE);
            this.other_container.setVisibility(View.VISIBLE);
            this.state_view.setVisibility(View.GONE);
            setOtheInfo(data.getOther());
        } else {
            this.mine_container.setVisibility(View.VISIBLE);
            this.other_container.setVisibility(View.GONE);
            setMessageStatus(data.getMessageStatus());
            setMineInfo(data.getMine());
        }
        //将容器交给子类实现
        this.mine_content_container.removeAllViews();
        this.other_content_container.removeAllViews();

        bindDateToChild(data, this.mine_content_container, this.other_content_container);

    }


    public void setHeader(MESSAGE data, MESSAGE prevData, MESSAGE nextData) {
        if (nextData == null) {
            this.header.setVisibility(View.VISIBLE);
            this.header.setText(data.getHeaderString());
        } else {
            if (data.getHeaderString().equals(nextData.getHeaderString())) {
                this.header.setVisibility(View.GONE);

            } else {
                this.header.setVisibility(View.VISIBLE);
            }
            this.header.setText(data.getHeaderString());
        }

    }

    public void setmIsSelected(MESSAGE data) {
        is_select.setVisibility(isSelectedModel ? View.VISIBLE : View.GONE);
        is_select.setBackgroundResource(data.isSelected() ? R.mipmap.item_checked : R.mipmap.item_uncheck);
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
                        .apply(GlideHelper.INSTANCE.getErrorOptions(R.mipmap.avatar_spiderman))
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
                        .apply(GlideHelper.INSTANCE.getErrorOptions(R.mipmap.avatar_superman))
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

    public void setMessageStatus(int status) {
        if (status == MessageStatus.SEND_SUCCEED) {
            this.state_view.setVisibility(View.GONE);
        } else if (status == MessageStatus.SEND_GOING) {
            this.state_view.setVisibility(View.VISIBLE);
            this.state_view.setCurrentState(1);
        } else if (status == MessageStatus.SEND_FAILED) {
            this.state_view.setCurrentState(2);
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
