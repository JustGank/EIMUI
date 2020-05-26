package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class FileMessageFragment extends BaseMessageFragment {

    public FileMessageFragment(){}

    @Override
    void loadData() {
        List<EMessage> messages = new ArrayList<>();

        IMessage fileMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_FILE);
        fileMessage1.setContent("Hello World.java");
        messages.add(fileMessage1);

        IMessage fileMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FILE);
        fileMessage2.setContent("Hello World.pptx");
        fileMessage2.setProgress(30);
        fileMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(fileMessage2);

        adapter.setList(messages);
    }
}
