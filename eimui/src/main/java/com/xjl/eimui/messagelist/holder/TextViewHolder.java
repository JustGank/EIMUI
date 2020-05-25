package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageType;

import androidx.annotation.NonNull;

public class TextViewHolder extends BaseViewHolder {

    public TextViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        TextView textView = (TextView) getView(R.layout.view_chat_text);
        textView.setText(data.getContent());
        if (data.getMessageType() == MessageType.SEND_TEXT) {
            textView.setBackgroundResource(R.drawable.chat_gray_left_bg);
            mineContainer.addView(textView);
        } else {
            textView.setBackgroundResource(R.drawable.chat_blue_right_bg);
            otherContainer.addView(textView);
        }
    }

}
