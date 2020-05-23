package com.xjl.eimui.messagelist.util;

import android.util.Log;

import com.xjl.eimui.messagelist.bean.MessageType;
import com.xjl.eimui.messagelist.holder.BaseViewHolder;
import com.xjl.eimui.messagelist.holder.ErrorViewHolder;
import com.xjl.eimui.messagelist.holder.FileViewHolder;
import com.xjl.eimui.messagelist.holder.ImageViewHolder;
import com.xjl.eimui.messagelist.holder.LocationViewHolder;
import com.xjl.eimui.messagelist.holder.TextViewHolder;
import com.xjl.eimui.messagelist.holder.VideoViewHolder;
import com.xjl.eimui.messagelist.holder.VoiceViewHolder;

import java.util.HashMap;

public enum HolderClassManager implements MessageType {

    INSTANCE;

    private static final String TAG = "HolderClassManager";

    HashMap<Integer, Class<? extends BaseViewHolder>> classMap;

    private HolderClassManager() {

        classMap = new HashMap<>();
        classMap.put(SEND_TEXT, TextViewHolder.class);
        classMap.put(RECEIVE_TEXT, TextViewHolder.class);

        classMap.put(SEND_IMAGE, ImageViewHolder.class);
        classMap.put(RECEIVE_IMAGE, ImageViewHolder.class);

        classMap.put(SEND_VOICE, VoiceViewHolder.class);
        classMap.put(RECEIVE_VOICE, VoiceViewHolder.class);

        classMap.put(SEND_VIDEO, VideoViewHolder.class);
        classMap.put(RECEIVE_VIDEO, VideoViewHolder.class);

        classMap.put(SEND_FILE, FileViewHolder.class);
        classMap.put(RECEIVE_FILE, FileViewHolder.class);

        classMap.put(SEND_LOCATION, LocationViewHolder.class);
        classMap.put(RECEIVE_LOCATION, LocationViewHolder.class);
    }

    public HashMap<Integer, Class<? extends BaseViewHolder>> getClassMap() {
        return classMap;
    }

    public Class<? extends BaseViewHolder> getViewHolderClass(int messageType) {
        Class<? extends BaseViewHolder> holderClass = classMap.get(messageType);
        if (holderClass == null) {
            Log.e(TAG, "getViewHolderClass is null");
            holderClass = ErrorViewHolder.class;
        }
        return holderClass;
    }


}
