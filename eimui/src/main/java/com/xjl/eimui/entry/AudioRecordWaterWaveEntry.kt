package com.xjl.eimui.entry

import android.content.Context
import com.xjl.eimui.R

class AudioRecordWaterWaveEntry {

    var chatUpMoveCancel: String
    var chatUpCancel: String

    constructor(context: Context) {
        chatUpMoveCancel = context.resources.getString(R.string.chat_up_move_cancel)
        chatUpCancel = context.resources.getString(R.string.chat_up_cancel)
    }

    constructor(chatUpMoveCancel: String, chatUpCancel: String) {
        this.chatUpMoveCancel = chatUpMoveCancel
        this.chatUpCancel = chatUpCancel
    }
}
