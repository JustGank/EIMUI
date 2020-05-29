package com.xjl.eimui.messagelist.bean;


import java.util.HashMap;

public interface EMessage {

    String getMsgId();

    void setMine(EUser user);

    EUser getMine();

    void setOther(EUser user);

    EUser getOther();

    int getMessageType();

    String getHeaderString();

    String getContent();

    String getSubContent();

    String getMediaFilePath();

    long getDuration();

    int getProgress();

    void setProgress(int progress);

    int getMessageStatus();

    void setMessageStatus(int status);

    boolean isSelected();

    boolean isPlaying();

    HashMap<String, String> getExtras();

}
