package com.xjl.eimui_demo.Fragment;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.MessageStatus;
import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui_demo.bean.IMessage;

import java.util.ArrayList;
import java.util.List;

public class MuiltMessageFragment extends BaseMessageFragment {
    public MuiltMessageFragment() {
    }

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

        //图片短信实例
        IMessage imageMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_IMAGE);
        imageMessage1.setMediaFilePath(testImgUrl1);
        messages.add(imageMessage1);
        IMessage imageMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_IMAGE);
        imageMessage2.setMediaFilePath(testImgUrl2);
        imageMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(imageMessage2);

        //短视频短信实例
        IMessage videoMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_VIDEO);
        videoMessage1.setMediaFilePath(testImgUrl1);
        messages.add(videoMessage1);
        IMessage videoMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_VIDEO);
        videoMessage2.setMediaFilePath(testImgUrl2);
        videoMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(videoMessage2);

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

        //位置短信实例
        IMessage locationMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_LOCATION);
        locationMessage1.setContent("风暴龙王降临");
        locationMessage1.setSubContent("王者峡谷河道西北方向");
        messages.add(locationMessage1);

        IMessage locationMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_LOCATION);
        locationMessage2.setContent("黑暗暴君降临");
        locationMessage2.setSubContent("王者峡谷河道东南方向");
        locationMessage2.setMessageStatus(MessageStatus.SEND_FAILED);

        //文件短信实例
        IMessage fileMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_FILE);
        fileMessage1.setContent("Hello World.java");
        messages.add(fileMessage1);

        IMessage fileMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FILE);
        fileMessage2.setContent("Hello World.pptx");
        fileMessage2.setProgress(30);
        fileMessage2.setMessageStatus(MessageStatus.SEND_FAILED);
        messages.add(fileMessage2);

        //异常状态短信
        IMessage errorMessage1 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.RECEIVE_REDOWNLOAD);
        messages.add(errorMessage1);

        IMessage errorMessage2 = new IMessage(String.valueOf(++messageId), mine, other, MessageType.SEND_FAIL_MESSAGE);
        errorMessage2.setMessageStatus(MessageStatus.SEND_SUCCEED);
        messages.add(errorMessage2);

        messages.add(locationMessage2);

        adapter.setList(messages);
        adapter.setSelectedMode(true);
    }


}
