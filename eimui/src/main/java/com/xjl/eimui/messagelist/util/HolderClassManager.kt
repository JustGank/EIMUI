package com.xjl.eimui.messagelist.util

import android.util.Log
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.MessageType
import com.xjl.eimui.messagelist.holder.MessageViewHolderBase
import com.xjl.eimui.messagelist.holder.ViewHolderFileMessage
import com.xjl.eimui.messagelist.holder.ViewHolderImageMessage
import com.xjl.eimui.messagelist.holder.ViewHolderLocationMessage
import com.xjl.eimui.messagelist.holder.ViewHolderRedownLoadMessage
import com.xjl.eimui.messagelist.holder.ViewHolderSendErrorMessage
import com.xjl.eimui.messagelist.holder.ViewHolderTextMessage
import com.xjl.eimui.messagelist.holder.ViewHolderVideoMessage
import com.xjl.eimui.messagelist.holder.ViewHolderVoiceMessage

object HolderClassManager {

    private const val TAG = "HolderClassManager"

    var classMap: HashMap<Int, Class<out MessageViewHolderBase<out EMessage>>> = HashMap()

    init {
        classMap[MessageType.SEND_TEXT] = ViewHolderTextMessage::class.java
        classMap[MessageType.RECEIVE_TEXT] = ViewHolderTextMessage::class.java
        classMap[MessageType.SEND_IMAGE] = ViewHolderImageMessage::class.java
        classMap[MessageType.RECEIVE_IMAGE] = ViewHolderImageMessage::class.java
        classMap[MessageType.SEND_VOICE] = ViewHolderVoiceMessage::class.java
        classMap[MessageType.RECEIVE_VOICE] = ViewHolderVoiceMessage::class.java
        classMap[MessageType.SEND_VIDEO] = ViewHolderVideoMessage::class.java
        classMap[MessageType.RECEIVE_VIDEO] = ViewHolderVideoMessage::class.java
        classMap[MessageType.SEND_FILE] = ViewHolderFileMessage::class.java
        classMap[MessageType.RECEIVE_FILE] = ViewHolderFileMessage::class.java
        classMap[MessageType.SEND_LOCATION] = ViewHolderLocationMessage::class.java
        classMap[MessageType.RECEIVE_LOCATION] = ViewHolderLocationMessage::class.java
        classMap[MessageType.SEND_FAIL_MESSAGE] = ViewHolderSendErrorMessage::class.java
        classMap[MessageType.RECEIVE_REDOWNLOAD] = ViewHolderRedownLoadMessage::class.java
    }

    fun  getViewHolderClass(messageType: Int): Class<out MessageViewHolderBase<out EMessage>> {
        var holderClass = classMap[messageType]
        if (holderClass == null) {
            Log.e(TAG, "getViewHolderClass is null")
            holderClass = ViewHolderSendErrorMessage::class.java
        }
        return holderClass
    }


}
