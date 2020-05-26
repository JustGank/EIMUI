package com.xjl.eimui.messagelist.util;

import android.util.Log;

import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.holder.MessageViewHolderBase;
import com.xjl.eimui.messagelist.holder.ViewHolderRedownLoadMessage;
import com.xjl.eimui.messagelist.holder.ViewHolderSendErrorMessage;
import com.xjl.eimui.messagelist.holder.ViewHolderFileMessage;
import com.xjl.eimui.messagelist.holder.ViewHolderImageMessage;
import com.xjl.eimui.messagelist.holder.ViewHolderLocationMessage;
import com.xjl.eimui.messagelist.holder.ViewHolderTextMessage;
import com.xjl.eimui.messagelist.holder.ViewHolderVideoMessage;
import com.xjl.eimui.messagelist.holder.ViewHolderVoiceMessage;

import java.util.HashMap;

public enum HolderClassManager implements MessageType {

    INSTANCE;

    private static final String TAG = "HolderClassManager";

    HashMap<Integer, Class<? extends MessageViewHolderBase>> classMap;

    private HolderClassManager() {

        classMap = new HashMap<>();
        classMap.put(SEND_TEXT, ViewHolderTextMessage.class);
        classMap.put(RECEIVE_TEXT, ViewHolderTextMessage.class);

        classMap.put(SEND_IMAGE, ViewHolderImageMessage.class);
        classMap.put(RECEIVE_IMAGE, ViewHolderImageMessage.class);

        classMap.put(SEND_VOICE, ViewHolderVoiceMessage.class);
        classMap.put(RECEIVE_VOICE, ViewHolderVoiceMessage.class);

        classMap.put(SEND_VIDEO, ViewHolderVideoMessage.class);
        classMap.put(RECEIVE_VIDEO, ViewHolderVideoMessage.class);

        classMap.put(SEND_FILE, ViewHolderFileMessage.class);
        classMap.put(RECEIVE_FILE, ViewHolderFileMessage.class);

        classMap.put(SEND_LOCATION, ViewHolderLocationMessage.class);
        classMap.put(RECEIVE_LOCATION, ViewHolderLocationMessage.class);

        classMap.put(SEND_FAIL_MESSAGE, ViewHolderSendErrorMessage.class);
        classMap.put(RECEIVE_REDOWNLOAD, ViewHolderRedownLoadMessage.class);
    }

    public HashMap<Integer, Class<? extends MessageViewHolderBase>> getClassMap() {
        return classMap;
    }

    public Class<? extends MessageViewHolderBase> getViewHolderClass(int messageType) {
        Class<? extends MessageViewHolderBase> holderClass = classMap.get(messageType);
        if (holderClass == null) {
            Log.e(TAG, "getViewHolderClass is null");
            holderClass = ViewHolderSendErrorMessage.class;
        }
        return holderClass;
    }


}
