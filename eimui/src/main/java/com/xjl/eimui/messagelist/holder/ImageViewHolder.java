package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.emedia.utils.ScreenUtil;

import androidx.annotation.NonNull;

public class ImageViewHolder<MESSAGE extends EMessage> extends BaseViewHolder {

    private int imageWidth, imageHeight;

    public ImageViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);
        imageWidth = ScreenUtil.dip2px(context, 120);
        imageHeight = ScreenUtil.dip2px(context, 180);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.view_message_image, null);
        Glide.with(context)
                .load(data.getMediaFilePath())
                .centerCrop()
                .into(imageView);
        imageView.setOnClickListener(this);
        imageView.setOnLongClickListener(this);
        if (MessageType.isReceivedMessage(data.getMessageType())) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            otherContainer.addView(imageView, layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageHeight, imageWidth);
            mineContainer.addView(imageView, layoutParams);
        }
    }
}
