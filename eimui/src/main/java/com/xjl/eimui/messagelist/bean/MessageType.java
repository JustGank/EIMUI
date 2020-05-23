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

}
