package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;

import androidx.annotation.NonNull;

public class ViewHolderSendErrorMessage<MESSAGE extends EMessage> extends MessageViewHolderBase {

    private ImageView item_chat_error_imageview;

    private TextView item_chat_error_text;

    public ViewHolderSendErrorMessage(Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        LinearLayout contianer = (LinearLayout) getView(R.layout.view_message_error);
        item_chat_error_imageview = contianer.findViewById(R.id.item_chat_error_imageview);
        item_chat_error_text = contianer.findViewById(R.id.item_chat_error_text);

        contianer.setOnClickListener(this);
        contianer.setOnLongClickListener(this);

        mineContainer.addView(contianer);

    }
}
