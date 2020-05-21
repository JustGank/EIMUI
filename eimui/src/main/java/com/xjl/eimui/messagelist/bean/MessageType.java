package com.xjl.eimui.messagelist.bean;

public enum MessageType {
    EVENT,
    SEND_TEXT,
    RECEIVE_TEXT,

    SEND_IMAGE,
    RECEIVE_IMAGE,

    SEND_VOICE,
    RECEIVE_VOICE,

    SEND_VIDEO,
    RECEIVE_VIDEO,

    SEND_LOCATION,
    RECEIVE_LOCATION,

    SEND_FILE,
    RECEIVE_FILE,

    SEND_CUSTOM,
    RECEIVE_CUSTOM;

    MessageType() {
    }
}
