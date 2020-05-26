package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class ImageMessageFragment extends BaseMessageFragment {

    public ImageMessageFragment() {
    }


    @Override
    void loadData() {

        List<EMessage> messages = new ArrayList<>();

        //图片短信实例
        IMessage imageMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_IMAGE);
        imageMessage1.setMediaFilePath(testImgUrl1);
        messages.add(imageMessage1);
        IMessage imageMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_IMAGE);
        imageMessage2.setMediaFilePath(testImgUrl2);
        imageMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(imageMessage2);

        adapter.setList(messages);

    }
}
