package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class VideoMessageFragment extends BaseMessageFragment {
    @Override
    void loadData() {
        List<EMessage> messages = new ArrayList<>();

        //短视频短信实例
        IMessage videoMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VIDEO);
        videoMessage1.setMediaFilePath(testImgUrl1);
        messages.add(videoMessage1);
        IMessage videoMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VIDEO);
        videoMessage2.setMediaFilePath(testImgUrl2);
        videoMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(videoMessage2);

        adapter.setList(messages);
    }
}
