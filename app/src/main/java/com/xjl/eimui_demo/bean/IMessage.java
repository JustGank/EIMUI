package com.xjl.eimui_demo.bean;

import com.xjl.eimui.messagelist.bean.EMessage;
import com.xjl.eimui.messagelist.bean.EUser;
import com.xjl.eimui.messagelist.bean.MessageStatus;

import java.util.HashMap;

public class IMessage implements EMessage {

    private String id;

    private EUser mine, other;

    private int messageType;

    private String header = "2020-05-05 18:18:18";

    private String content;

    private String subContent;

    private String filePath;

    private long duration = 0;

    private int process = 0;

    private MessageStatus status;

    private boolean isSelected = false;

    private boolean isPlaying = false;

    private HashMap<String, String> extras;

    //TextMessage Construct
    public IMessage(String id, EUser mine, EUser other, int messageType) {
        this.id = id;
        this.mine = mine;
        this.other = other;
        this.messageType = messageType;
        this.extras = new HashMap<>();
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
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    @Override
    public String getHeaderString() {
        return header;
    }

    public void setHeaderString(String header) {
        this.header = header;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
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
    public String getMediaFilePath() {
        return filePath;
    }

    public void setMediaFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public long getDuration() {
        return duration > 0 ? duration : 1;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public int getProgress() {
        return process > 0 ? process : 1;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    @Override
    public HashMap<String, String> getExtras() {
        return extras;
    }

}
