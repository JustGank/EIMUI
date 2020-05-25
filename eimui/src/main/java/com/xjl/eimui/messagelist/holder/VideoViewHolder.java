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
import com.xjl.eimui.util.ScreenUtils;

import androidx.annotation.NonNull;

public class VideoViewHolder<MESSAGE extends EMessage> extends BaseViewHolder {

    public ImageView item_chat_video_cover;
    public ImageView item_chat_video_player;

    private int imageWidth, imageHeight;

    public VideoViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);

        imageWidth = ScreenUtils.dp2px(context, 120);
        imageHeight = ScreenUtils.dp2px(context, 180);

    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_chat_video, null);
        item_chat_video_cover = (ImageView) relativeLayout.findViewById(R.id.item_chat_video_cover);
        Glide.with(context).load(data.getMediaFilePath()).centerCrop().into(item_chat_video_cover);
        item_chat_video_player = (ImageView) relativeLayout.findViewById(R.id.item_chat_video_player);

        if (MessageType.isReceivedMessage(data.getMessageType())) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            otherContainer.addView(relativeLayout, layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageHeight, imageWidth);
            mineContainer.addView(relativeLayout, layoutParams);
        }

    }
}
