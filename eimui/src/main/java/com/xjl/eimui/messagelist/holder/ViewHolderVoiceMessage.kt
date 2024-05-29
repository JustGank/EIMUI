package com.xjl.eimui.messagelist.holder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.xjl.eimui.R
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.MessageType
import com.xjl.eimui.messagelist.widget.ChatAudioView
import com.xjl.emedia.utils.ScreenUtil

class ViewHolderVoiceMessage<MESSAGE : EMessage>(context: Context, itemView: View) :
    MessageViewHolderBase<MESSAGE>(context, itemView) {
    override fun bindDateToChild(
        data: MESSAGE,
        mineContainer: ViewGroup,
        otherContainer: ViewGroup
    ) {
        val isReceived = MessageType.isReceivedMessage(data.getMessageType())
        val chatAudioView = ChatAudioView(context, !isReceived)
        chatAudioView.setTime(data.getDuration().toInt())
        val padding = ScreenUtil.dip2px(context, 5f)
        chatAudioView.setPadding(padding, padding, padding, padding)
        chatAudioView.setOnClickListener(this)
        chatAudioView.setOnLongClickListener(this)
        if (data.isPlaying()) {
            chatAudioView.startAnim()
        } else {
            chatAudioView.endAnim()
        }
        if (isReceived) {
            chatAudioView.setBackgroundResource(R.drawable.chat_gray_left_bg)
            otherContainer.addView(chatAudioView)
        } else {
            chatAudioView.setBackgroundResource(R.drawable.chat_blue_right_bg)
            mineContainer.addView(chatAudioView)
        }
    }
}
