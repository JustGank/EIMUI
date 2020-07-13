package com.xjl.eimui.inputbar.moreoperateion.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xjl.eimui.R;
import com.xjl.eimui.inputbar.moreoperateion.bean.ChatMoreBean;
import com.xjl.eimui.util.GlideHelper;

import java.util.List;

public class InputBarMoreDefaultAdapter extends RecyclerView.Adapter<InputBarMoreDefaultAdapter.ViewHolder> {

    private List<ChatMoreBean> list;

    private Activity activity;

    private LayoutInflater layoutInflater;

    public InputBarMoreDefaultAdapter(List<ChatMoreBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        this.layoutInflater = activity.getLayoutInflater();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_inputbar_more, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMoreBean chatMoreBean = list.get(position);

        Glide.with(activity)
                .load(chatMoreBean.resId)
                .apply(GlideHelper.INSTANCE.getOnCenterCrop())
                .into(holder.img);

        holder.img.setImageResource(chatMoreBean.resId);
        holder.title.setText(chatMoreBean.title);
        holder.container.setOnClickListener(v -> {
            chatMoreBean.operation.operate(v, position, activity);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ChatMoreBean getItem(int position) {
        return list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView title;
        public RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.img);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.container = (RelativeLayout) itemView.findViewById(R.id.container);
        }
    }


}
