package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.widget.ChatAudioView;
import com.xjl.eimui.util.DefaluLayoutParams;

import androidx.annotation.NonNull;

public class VoiceViewHolder<MESSAGE extends EMessage> extends BaseViewHolder {

    public VoiceViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        boolean isReceived = MessageType.isReceivedMessage(data.getMessageType());
        ChatAudioView chatAudioView = new ChatAudioView(context, !isReceived);
        chatAudioView.setTime((int) data.getDuration());
        int padding = DefaluLayoutParams.getDefaultMessagePadding(context);
        chatAudioView.setPadding(padding, padding, padding, padding);

        chatAudioView.setOnClickListener(this);
        chatAudioView.setOnLongClickListener(this);

        if (isReceived) {
            chatAudioView.setBackgroundResource(R.drawable.chat_gray_left_bg);
            otherContainer.addView(chatAudioView);
        } else {
            chatAudioView.setBackgroundResource(R.drawable.chat_blue_right_bg);
            mineContainer.addView(chatAudioView);
        }
    }
}
