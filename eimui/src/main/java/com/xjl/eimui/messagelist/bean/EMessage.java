package com.xjl.eimui.messagelist.bean;


import java.util.HashMap;

public interface EMessage {

    /**
     * Message id.
     * @return unique
     */
    String getMsgId();

    /**
     * Get user info of message.
     * @return UserInfo of message
     */
    EUser getFromUser();

    EUser getMine();

    /**
     * Time string that display in message list.
     * @return Time string
     */
    String getTimeString();

    /**
     * Type of message
     * @return integer
     */
    int getViewType();

    /**
     * Status of message, enum.
     */
    MessageStatus getMessageStatus();
    /**
     * Text of message.
     * @return text
     */
    String getContent();

    /**
     * If message type is photo, voice, video or file,
     * get file path through this method.
     * @return file path
     */
    String getMediaFilePath();

    /**
     * If message type is voice or video, get duration through this method.
     * @return duration of audio or video, TimeUnit: SECONDS.
     */
    long getDuration();

    String getProgress();

    /**
     * Return extra key value of message
     * @return {@link HashMap<>}
     */
    HashMap<String, String> getExtras();

    public boolean isChecked();

    public boolean isPlaying();

    public MessageStatus getMessageSendStaus();



}
