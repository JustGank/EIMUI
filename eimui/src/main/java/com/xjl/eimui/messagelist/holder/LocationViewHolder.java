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

import com.bumptech.glide.Glide;
import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.util.ScreenUtils;

import androidx.annotation.NonNull;

public class LocationViewHolder<MESSAGE extends EMessage> extends BaseViewHolder {

    private TextView title;

    private TextView subTitle;

    private ImageView cover;

    public LocationViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        LinearLayout container = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_chat_location, null);
        this.title = container.findViewById(R.id.title);
        this.title.setText(data.getContent());

        this.subTitle = container.findViewById(R.id.sub_title);
        if (TextUtils.isEmpty(data.getSubContent())) {
            this.subTitle.setVisibility(View.GONE);
        } else {
            this.subTitle.setVisibility(View.VISIBLE);
            this.subTitle.setText(data.getSubContent());
        }

        this.cover = container.findViewById(R.id.cover);
        Glide.with(context).load(data.getMediaFilePath()).centerCrop().into(this.cover);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ScreenUtils.dp2px(context, 220), RelativeLayout.LayoutParams.WRAP_CONTENT);

        if (MessageType.isReceivedMessage(data.getMessageType())) {
            otherContainer.addView(container,layoutParams);
        } else {
            mineContainer.addView(container,layoutParams);
        }


    }

}
