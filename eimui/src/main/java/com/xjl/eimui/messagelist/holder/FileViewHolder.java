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

public class FileViewHolder<MESSAGE extends EMessage> extends BaseViewHolder {

    private static final String TAG = "FileViewHolder";

    private View item_chat_progress_bg;
    private RelativeLayout item_chat_file_container;
    private ImageView chat_file;
    private TextView filename;
    private ImageView download;

    public FileViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        LinearLayout container = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_message_file, null);
        item_chat_progress_bg = container.findViewById(R.id.item_chat_progress_bg);
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
            item_chat_progress_bg.setBackgroundResource(R.drawable.shape_solid_darkgray_stroke_null_corner);
            item_chat_file_container.setBackgroundResource(R.drawable.chat_gray_left_bg);
            otherContainer.addView(container);
        } else {
            download.setVisibility(View.GONE);
            item_chat_progress_bg.setBackgroundResource(R.drawable.shape_solid_darkblue_stroke_null_corner);
            item_chat_file_container.setBackgroundResource(R.drawable.chat_blue_right_bg);
            mineContainer.addView(container);
        }

        item_chat_file_container.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int tempWidth = item_chat_file_container.getWidth() * data.getProgress() / 100;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) item_chat_progress_bg.getLayoutParams();
            layoutParams.width = tempWidth;
            item_chat_progress_bg.setLayoutParams(layoutParams);
        });

    }

}
