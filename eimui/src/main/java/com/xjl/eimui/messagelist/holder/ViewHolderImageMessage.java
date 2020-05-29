package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.util.ImageSizeHelper;
import com.xjl.emedia.utils.ScreenUtil;

import androidx.annotation.NonNull;

public class ViewHolderImageMessage<MESSAGE extends EMessage> extends MessageViewHolderBase {

    private int slide1, slide2;
    RelativeLayout.LayoutParams layoutParams1, layoutParams2;

    public ViewHolderImageMessage(Context context, @NonNull View itemView) {
        super(context, itemView);
        slide1 = ScreenUtil.dip2px(context, 120);
        slide2 = ScreenUtil.dip2px(context, 180);

        layoutParams1 = new RelativeLayout.LayoutParams(slide1, slide2);
        layoutParams2 = new RelativeLayout.LayoutParams(slide2, slide1);
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
            otherContainer.addView(imageView, layoutParams1);
        } else {
            mineContainer.addView(imageView, layoutParams2);
        }

    }
}
