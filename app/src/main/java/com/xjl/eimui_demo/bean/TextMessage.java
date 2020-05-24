package com.xjl.eimui_demo.bean;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.EUser;
import com.xjl.eimui.messagelist.bean.MessageStatus;

import java.util.HashMap;

public class TextMessage implements EMessage {

    private String id;

    private String content;

    private int messageType;

    private EUser mine, other;

    private MessageStatus status;

    public TextMessage(String id, String content, EUser mine, EUser other, int messageType) {
        this.id = id;
        this.content = content;
        this.mine = mine;
        this.other = other;
        this.messageType = messageType;
    }

    @Override
    public String getMsgId() {
        return id;
    }

    @Override
    public void setMine(EUser mine) {
        this.mine = mine;
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
        return content;
    }

    @Override
    public String getMediaFilePath() {
        return null;
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
        return null;
    }
}
