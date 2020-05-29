package com.xjl.eimui.messagelist.bean;

public interface MessageStatus {
    int CREATED = 1;

    int SEND_GOING = 2;

    int SEND_SUCCEED = 3;
    int SEND_FAILED = 4;
    int SEND_DRAFT = 5;

    int RECEIVE_GOING = 6;
    int RECEIVE_SUCCEED = 7;
    int RECEIVE_FAILED = 8;
}
