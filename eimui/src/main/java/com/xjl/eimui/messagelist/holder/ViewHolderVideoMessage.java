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

public class ViewHolderVideoMessage<MESSAGE extends EMessage> extends MessageViewHolderBase {

    public ImageView item_chat_video_cover;
    public ImageView item_chat_video_player;

    private int imageWidth, imageHeight;

    public ViewHolderVideoMessage(Context context, @NonNull View itemView) {
        super(context, itemView);

        imageWidth = ScreenUtil.dip2px(context, 120);
        imageHeight = ScreenUtil.dip2px(context, 180);

    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_message_video, null);
        item_chat_video_cover = (ImageView) relativeLayout.findViewById(R.id.item_chat_video_cover);
        Glide.with(context).load(data.getMediaFilePath()).centerCrop().into(item_chat_video_cover);
        item_chat_video_player = (ImageView) relativeLayout.findViewById(R.id.item_chat_video_player);

        item_chat_video_cover.setOnClickListener(this);
        item_chat_video_cover.setOnLongClickListener(this);

        item_chat_video_player.setOnClickListener(this);
        item_chat_video_player.setOnLongClickListener(this);

        if (MessageType.isReceivedMessage(data.getMessageType())) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            otherContainer.addView(relativeLayout, layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageHeight, imageWidth);
            mineContainer.addView(relativeLayout, layoutParams);
        }

    }
}
