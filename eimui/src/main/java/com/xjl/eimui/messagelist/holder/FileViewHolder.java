package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageType;

import androidx.annotation.NonNull;

public class FileViewHolder<MESSAGE extends EMessage> extends BaseViewHolder {

    private View process_bg;
    private LinearLayout file_container;
    private ImageView chat_file;
    private TextView filename;
    private ImageView download;

    public FileViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        LinearLayout container = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_chat_file_other, null);
        process_bg = container.findViewById(R.id.process_bg);
        file_container = container.findViewById(R.id.file_container);
        chat_file = container.findViewById(R.id.chat_file);
        filename = container.findViewById(R.id.filename);
        filename.setText(data.getContent());

        download = container.findViewById(R.id.download);

        if (MessageType.isReceivedMessage(data.getMessageType())) {
            download.setVisibility(View.VISIBLE);
            file_container.setBackgroundResource(R.drawable.chat_gray_left_bg);
            otherContainer.addView(container);
        } else {
            download.setVisibility(View.GONE);
            file_container.setBackgroundResource(R.drawable.chat_blue_right_bg);
            mineContainer.addView(container);
        }



    }

}
