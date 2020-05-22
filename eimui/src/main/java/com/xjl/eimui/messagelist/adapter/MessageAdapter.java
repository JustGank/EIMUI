package com.xjl.eimui.messagelist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.EUser;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.holder.BaseViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter<MESSAGE extends EMessage> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private boolean isSelectedMode = false;
    private int mSelectedItemCount;
    private List<EMessage> list;
    private EUser sender, receiver;

    public MessageAdapter(Context context, List<EMessage> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public MessageAdapter(Context context, List<EMessage> list, EUser sender, EUser receiver) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder=null;
        switch (viewType) {
            case MessageType.SEND_TEXT:
                break;
            case MessageType.RECEIVE_TEXT:
                break;
            case MessageType.SEND_IMAGE:
                break;
            case MessageType.RECEIVE_IMAGEv:
                break;
            case MessageType.SEND_VOICE:
                break;
            case MessageType.RECEIVE_VOICE:
                break;
            case MessageType.SEND_VIDEO:
                break;
            case MessageType.RECEIVE_VIDEO:
                break;
            case MessageType.SEND_FILE:
                break;
            case MessageType.RECEIVE_FILE:
                break;
            case MessageType.SEND_LOCATION:
                break;
            case MessageType.RECEIVE_LOCATION:
                break;
            default:
                baseViewHolder=createCustomeHolder(parent,viewType);
                break;

        }
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public BaseViewHolder createCustomeHolder(ViewGroup parent, int viewType) {
        return null;
    }

    ;


}
