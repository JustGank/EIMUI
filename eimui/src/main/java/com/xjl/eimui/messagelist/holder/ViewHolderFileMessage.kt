package com.xjl.eimui.messagelist.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.xjl.eimui.R
import com.xjl.eimui.databinding.ViewMessageFileBinding
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.MessageType

class ViewHolderFileMessage<MESSAGE : EMessage>(context: Context, itemView: View) :
    MessageViewHolderBase<MESSAGE>(context, itemView) {

    private val TAG = "FileViewHolder"

    override fun bindDateToChild(
        data: MESSAGE,
        mineContainer: ViewGroup,
        otherContainer: ViewGroup
    ) {
        val binding = ViewMessageFileBinding.inflate(LayoutInflater.from(context))

        binding.apply {
            val layoutParams1 = itemChatProgressBg1.layoutParams as LinearLayout.LayoutParams
            layoutParams1.weight = data.getProgress().toFloat()
            itemChatProgressBg1.layoutParams = layoutParams1

            val layoutParams2 = itemChatProgressBg2.layoutParams as LinearLayout.LayoutParams
            layoutParams2.weight = 100 - data.getProgress().toFloat()
            itemChatProgressBg2.layoutParams = layoutParams2

            itemChatFileContainer.setOnClickListener(this@ViewHolderFileMessage)
            itemChatFileContainer.setOnLongClickListener(this@ViewHolderFileMessage)

            itemChatFileName.text=data.getContent()

            itemChatFileDownload.visibility = if (data.getProgress() == 100) View.GONE else View.VISIBLE
            itemChatFileDownload.setOnClickListener(this@ViewHolderFileMessage)
            itemChatFileDownload.setOnLongClickListener(this@ViewHolderFileMessage)

            if (MessageType.isReceivedMessage(data.getMessageType())) {
                itemChatFileDownload.visibility = View.VISIBLE
                itemChatProgressBg1.setBackgroundResource(R.drawable.shape_solid_darkgray_stroke_null_corner)
                itemChatFileContainer.setBackgroundResource(R.drawable.chat_gray_left_bg)
                otherContainer.addView(binding.root)
            } else {
                itemChatFileDownload.visibility = View.GONE
                itemChatProgressBg1.setBackgroundResource(R.drawable.shape_solid_darkblue_stroke_null_corner)
                itemChatFileContainer.setBackgroundResource(R.drawable.chat_blue_right_bg)
                mineContainer.addView(binding.root)
            }
        }



    }


}
