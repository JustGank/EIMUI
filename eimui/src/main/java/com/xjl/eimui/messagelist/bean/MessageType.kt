package com.xjl.eimui.messagelist.bean;

/**
 * SEND 与 RECEIVE 都是以当前用户为基准
 */
public interface MessageType {

    int SEND_TEXT = 1;
    int RECEIVE_TEXT = 2;

    int SEND_IMAGE = 3;
    int RECEIVE_IMAGE = 4;

    int SEND_VOICE = 5;
    int RECEIVE_VOICE = 6;

    int SEND_VIDEO = 7;
    int RECEIVE_VIDEO = 8;

    int SEND_FILE = 9;
    int RECEIVE_FILE = 10;

    int SEND_LOCATION = 11;
    int RECEIVE_LOCATION = 12;

    int SEND_FAIL_MESSAGE = 13;
    int RECEIVE_REDOWNLOAD = 14;

    /**
     * 根据设定单数类型 为发送出去的信息
     */
    static boolean isReceivedMessage(int type) {
        boolean isReceived = true;
        if (type % 2 == 1) {
            isReceived = false;
        }
        return isReceived;

    }
}
