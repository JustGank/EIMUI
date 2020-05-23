package com.xjl.eimui.messagelist.bean;


import java.util.HashMap;

public interface EMessage {


    String getMsgId();

    EUser getMine();
    
    EUser getOther();

    String getTimeString();

    int getMessageType();

    MessageStatus getMessageStatus();

    String getContent();

    String getMediaFilePath();

    long getDuration();

    String getProgress();

    HashMap<String, String> getExtras();

    public boolean isChecked();

    public boolean isPlaying();

    public MessageStatus getMessageSendStaus();

}
