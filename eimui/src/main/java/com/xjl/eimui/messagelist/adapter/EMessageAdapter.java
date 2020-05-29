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
import com.xjl.eimui.messagelist.bean.EUser;
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
    private int mSelectedItemCount;
    private List<MESSAGE> list;
    private EUser mine, other;
    private OperationListener<EMessage> operationListener;

    public EMessageAdapter(Context context, List<MESSAGE> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public EMessageAdapter(Context context, List<MESSAGE> list, EUser mine, EUser other) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.mine = mine;
        this.other = other;
    }

    public void setOperationListener(OperationListener<EMessage> operationListener) {
        this.operationListener = operationListener;
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
        MESSAGE data= list.get(position);
        //由于头部信息和上下两条数据可能有关系
        if(position==0){
            if(getItemCount()>1){
                holder.setHeader(data,null,list.get(1));
            }else
            {
                holder.setHeader(data,null,null);
            }
        }else
        {
            if(getItemCount()>2){
                holder.setHeader(data,list.get(position-1),null);
            }else
            {
                if(list.get(position+1)!=null){
                    holder.setHeader(data,list.get(position-1),list.get(position+1));
                }else
                {
                    holder.setHeader(data,list.get(position-1),null);
                }
            }
        }

        holder.bindData(list.get(position), isSelectedMode, mine, other, position);
    }

    public void setList(List<MESSAGE> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(MESSAGE message) {
        this.list.add(message);
        notifyDataSetChanged();
    }

    public void insertItem(MESSAGE message){

    }

    public void setSelectedMode(boolean isSelectedMode) {
        this.isSelectedMode = isSelectedMode;
        notifyDataSetChanged();
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
