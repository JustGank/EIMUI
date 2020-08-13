package com.xjl.eimui.messagelist.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.holder.MessageViewHolderBase;
import com.xjl.eimui.messagelist.holder.ViewHolderSendErrorMessage;
import com.xjl.eimui.messagelist.listener.OperationListener;
import com.xjl.eimui.messagelist.util.HolderClassManager;

import java.lang.reflect.Constructor;
import java.util.List;

public class EMessageAdapter<MESSAGE extends EMessage> extends RecyclerView.Adapter<MessageViewHolderBase> {

    private static final String TAG = "MessageAdapter";

    private Context context;
    private LayoutInflater inflater;
    private boolean isSelectedMode = false;
    private OperationListener<EMessage> operationListener;
    public List<MESSAGE> list;

    public EMessageAdapter(Context context, List<MESSAGE> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOperationListener(OperationListener<EMessage> operationListener) {
        this.operationListener = operationListener;
    }

    public OperationListener getOperationListener(){
        return this.operationListener;
    }

    @NonNull
    @Override
    public MessageViewHolderBase onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageViewHolderBase holder = getHolder(parent, HolderClassManager.INSTANCE.getViewHolderClass(viewType));
        Log.e(TAG, "holder class" + holder.getClass());
        if (this.operationListener != null)
            holder.setOperationListener(this.operationListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolderBase holder, int position) {
        MESSAGE data = list.get(position);
        //由于头部信息和上下两条数据可能有关系
        if (position == 0) {
            if (getItemCount() > 1) {
                holder.setHeader(data, null, list.get(1));
            } else {
                holder.setHeader(data, null, null);
            }
        } else {
            if (getItemCount() < 3) {
                holder.setHeader(data, list.get(position - 1), null);
            } else {
                if (position + 1 < list.size() && list.get(position + 1) != null) {
                    holder.setHeader(data, list.get(position - 1), list.get(position + 1));
                } else {
                    holder.setHeader(data, list.get(position - 1), null);
                }
            }
        }

        holder.bindData(list.get(position), isSelectedMode, position);
    }

    /**
     * 数据操作相关方法
     */
    public void setList(List<MESSAGE> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(MESSAGE message) {
        this.list.add(message);
        notifyDataSetChanged();
    }

    public void insertItem(MESSAGE message) {
        list.add(0, message);
        notifyDataSetChanged();
    }

    public String getMessageId(int position) {
        return list.get(position).getMsgId();
    }

    public void deleteItem(int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public void updataProgress(String msgId, int progress) {
        for (int i = 0; i < list.size(); i++) {
            MESSAGE message = list.get(i);
            if (message.getMsgId().equals(msgId)) {
                message.setProgress(progress);
                notifyItemChanged(i);
                break;
            }
        }
    }

    /**
     * 更改模式
     */
    public void setSelectedMode(boolean isSelectedMode) {
        this.isSelectedMode = isSelectedMode;
        notifyDataSetChanged();
    }

    public boolean getSelectedMode(){
        return isSelectedMode;
    }

    public void updateItemSelected(int position) {
        list.get(position).setSelected(!list.get(position).isSelected());
        notifyItemChanged(position);
    }

    public void updateMessageState(String msgId, int newState) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMsgId().equals(msgId)) {
                list.get(i).setMessageStatus(newState);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public void updatePlaying(int position, boolean playing) {
        getItem(position).setIsPlaying(playing);
        notifyItemChanged(position);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getMessageType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public EMessage getItem(int position) {
        return list.get(position);
    }

    public <HOLDER extends MessageViewHolderBase> MessageViewHolderBase getHolder(ViewGroup parent, Class<HOLDER> holderClass) {
        View view = inflater.inflate(R.layout.item_messagelist_container, parent, false);
        MessageViewHolderBase holder = null;
        try {
            Constructor<HOLDER> constructor = holderClass.getDeclaredConstructor(Context.class, View.class);
            holder = constructor.newInstance(context, view);

        } catch (Exception e) {
            Log.e(TAG, "getHolder Exception=" + e.getMessage());

        }
        if (holder == null) {
            holder = new ViewHolderSendErrorMessage(context, view);
        }

        return holder;
    }
}
