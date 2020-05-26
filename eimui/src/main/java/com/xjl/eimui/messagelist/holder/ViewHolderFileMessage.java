package com.xjl.eimui.messagelist.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xjl.eimui.R;
import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageType;

import androidx.annotation.NonNull;

public class ViewHolderFileMessage<MESSAGE extends EMessage> extends MessageViewHolderBase {

    private static final String TAG = "FileViewHolder";

    private View item_chat_progress_bg1, item_chat_progress_bg2;
    private RelativeLayout item_chat_file_container;
    private ImageView chat_file;
    private TextView filename;
    private ImageView download;

    public ViewHolderFileMessage(Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        LinearLayout container = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_message_file, null);
        item_chat_progress_bg1 = container.findViewById(R.id.item_chat_progress_bg1);
        item_chat_progress_bg2 = container.findViewById(R.id.item_chat_progress_bg2);

        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) item_chat_progress_bg1.getLayoutParams();
        layoutParams1.weight = data.getProgress();
        item_chat_progress_bg1.setLayoutParams(layoutParams1);

        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) item_chat_progress_bg2.getLayoutParams();
        layoutParams2.weight = 100-data.getProgress();
        item_chat_progress_bg2.setLayoutParams(layoutParams2);

        item_chat_file_container = container.findViewById(R.id.item_chat_file_container);
        chat_file = container.findViewById(R.id.item_chat_file_cover);
        filename = container.findViewById(R.id.item_chat_file_name);
        filename.setText(data.getContent());
        download = container.findViewById(R.id.item_chat_file_download);

        item_chat_file_container.setOnClickListener(this);
        item_chat_file_container.setOnLongClickListener(this);
        download.setOnClickListener(this);
        download.setOnLongClickListener(this);

        if (MessageType.isReceivedMessage(data.getMessageType())) {
            download.setVisibility(View.VISIBLE);
            item_chat_progress_bg1.setBackgroundResource(R.drawable.shape_solid_darkgray_stroke_null_corner);
            item_chat_file_container.setBackgroundResource(R.drawable.chat_gray_left_bg);
            otherContainer.addView(container);
        } else {
            download.setVisibility(View.GONE);
            item_chat_progress_bg1.setBackgroundResource(R.drawable.shape_solid_darkblue_stroke_null_corner);
            item_chat_file_container.setBackgroundResource(R.drawable.chat_blue_right_bg);
            mineContainer.addView(container);
        }


    }

}
