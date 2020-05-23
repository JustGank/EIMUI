package com.xjl.eimui.messagelist.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.EUser;
import com.xjl.eimui.messagelist.holder.BaseViewHolder;
import com.xjl.eimui.messagelist.holder.ErrorViewHolder;
import com.xjl.eimui.messagelist.util.HolderClassManager;

import java.lang.reflect.Constructor;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EMessageAdapter<MESSAGE extends EMessage> extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "MessageAdapter";

    private Context context;
    private LayoutInflater inflater;
    private boolean isSelectedMode = false;
    private int mSelectedItemCount;
    private List<EMessage> list;
    private EUser sender, receiver;

    public EMessageAdapter(Context context, List<EMessage> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public EMessageAdapter(Context context, List<EMessage> list, EUser sender, EUser receiver) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getHolder(parent, HolderClassManager.INSTANCE.getViewHolderClass(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getMessageType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public <HOLDER extends BaseViewHolder> BaseViewHolder getHolder(ViewGroup parent, Class<HOLDER> holderClass) {
        View view = inflater.inflate(R.layout.item_messagelist_container, null);
        BaseViewHolder holder=null;
        try {
            Constructor<HOLDER> constructor = holderClass.getDeclaredConstructor(Context.class, View.class);
            holder =constructor.newInstance(context, view);

        } catch (Exception e) {
            Log.e(TAG, "getHolder Exception=" + e.getMessage());

        }
        if(holder==null)
        {
            holder=new ErrorViewHolder(context,view);
        }

        return holder;
    }

}
