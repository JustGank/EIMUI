package com.xjl.eimui.messagelist.bean

/**
 * SEND 与 RECEIVE 都是以当前用户为基准
 */
class MessageType {

    companion object{
        const val SEND_TEXT = 1
        const val RECEIVE_TEXT = 2
        const val SEND_IMAGE = 3
        const val RECEIVE_IMAGE = 4
        const val SEND_VOICE = 5
        const val RECEIVE_VOICE = 6
        const val SEND_VIDEO = 7
        const val RECEIVE_VIDEO = 8
        const val SEND_FILE = 9
        const val RECEIVE_FILE = 10
        const val SEND_LOCATION = 11
        const val RECEIVE_LOCATION = 12
        const val SEND_FAIL_MESSAGE = 13
        const val RECEIVE_REDOWNLOAD = 14

        /**
         * 根据设定单数类型 为发送出去的信息
         */
        fun isReceivedMessage(type: Int): Boolean = type % 2 == 0
    }
}
