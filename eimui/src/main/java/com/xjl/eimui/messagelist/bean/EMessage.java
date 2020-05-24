package com.xjl.eimui.messagelist.bean;


import java.util.HashMap;

public interface EMessage {

    String getMsgId();

    void setMine(EUser user);

    EUser getMine();

    void setOther(EUser user);

    EUser getOther();

    String getTimeString();

    int getMessageType();

    MessageStatus getMessageStatus();

    void setMessageStatus(MessageStatus status);

    String getContent();

    String getMediaFilePath();

    long getDuration();

    String getProgress();

    HashMap<String, String> getExtras();

    boolean isChecked();

    boolean isPlaying();

    MessageStatus getMessageSendStaus();

}
