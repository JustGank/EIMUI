package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class TextMessageFragment extends BaseMessageFragment{

    public TextMessageFragment(){}

    @Override
    void loadData() {
        List<EMessage> messages = new ArrayList<>();
        //文字短信实例
        IMessage textMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_TEXT);
        textMessage1.setContent("Hello Bat Man");
        messages.add(textMessage1);

        IMessage textMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_TEXT);
        textMessage2.setContent("Hello Super Man");
        textMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(textMessage2);


        IMessage textMessage3 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_TEXT);
        textMessage3.setContent(longText);
        textMessage3.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(textMessage3);

        adapter.setList(messages);
    }
}
