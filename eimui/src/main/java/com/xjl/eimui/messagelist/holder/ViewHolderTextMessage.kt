package com.xjl.eimui.messagelist.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xjl.eimui.R
import com.xjl.eimui.databinding.ViewMessageTextBinding
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.MessageType

class ViewHolderTextMessage<MESSAGE : EMessage>(context: Context, itemView: View) :
    MessageViewHolderBase<MESSAGE>(context, itemView) {
    override fun bindDateToChild(
        data: MESSAGE,
        mineContainer: ViewGroup,
        otherContainer: ViewGroup
    ) {
        val binding = ViewMessageTextBinding.inflate(LayoutInflater.from(context))
        binding.itemChatTextview.apply {
            text = data.content
            setOnClickListener(this@ViewHolderTextMessage)
            setOnLongClickListener(this@ViewHolderTextMessage)
            if (data.messageType == MessageType.SEND_TEXT) {
                setBackgroundResource(R.drawable.chat_gray_left_bg)
                mineContainer.addView(this)
            } else {
                setBackgroundResource(R.drawable.chat_blue_right_bg)
                otherContainer.addView(this)
            }
        }
    }
}
