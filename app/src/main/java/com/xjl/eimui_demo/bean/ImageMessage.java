package com.xjl.eimui_demo.bean;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.EUser;
import com.xjl.eimui.messagelist.bean.MessageStatus;

import java.util.HashMap;

public class ImageMessage implements EMessage {

    public String id;

    private String filePath;

    private int messageType;

    private MessageStatus status;

    private EUser mine, other;

    public ImageMessage(String id, String filePath, EUser mine, EUser other, int messageType) {
        this.id = id;
        this.filePath = filePath;
        this.mine = mine;
        this.other = other;
        this.messageType = messageType;
    }

    @Override
    public String getMsgId() {
        return id;
    }

    @Override
    public void setMine(EUser user) {
        this.mine = user;
    }

    @Override
    public EUser getMine() {
        return this.mine;
    }

    @Override
    public void setOther(EUser user) {
        this.other = user;
    }

    @Override
    public EUser getOther() {
        return this.other;
    }

    @Override
    public String getTimeString() {
        return "2020-05-05 18:18:18";
    }

    @Override
    public int getMessageType() {
        return messageType;
    }

    @Override
    public MessageStatus getMessageStatus() {
        return status;
    }

    @Override
    public void setMessageStatus(MessageStatus status) {
        this.status = status;
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public String getMediaFilePath() {
        return filePath;
    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public String getProgress() {
        return null;
    }

    @Override
    public HashMap<String, String> getExtras() {
        return null;
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public MessageStatus getMessageSendStaus() {
        return status;
    }
}
