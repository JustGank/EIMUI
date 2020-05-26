package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class VoiceMessageFragment extends BaseMessageFragment {

    public VoiceMessageFragment(){}

    @Override
    void loadData() {
        List<EMessage> messages = new ArrayList<>();

        //声音短信实例
        IMessage voiceMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VOICE);
        voiceMessage1.setDuration(2);
        messages.add(voiceMessage1);
        IMessage voiceMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VOICE);
        voiceMessage2.setDuration(60);
        voiceMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(voiceMessage2);
        IMessage voiceMessage3 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VOICE);
        voiceMessage3.setDuration(60);
        voiceMessage3.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(voiceMessage3);

        adapter.setList(messages);
    }
}
