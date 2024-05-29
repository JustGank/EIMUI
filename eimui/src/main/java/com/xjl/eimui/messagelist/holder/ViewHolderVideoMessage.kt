package com.xjl.eimui.messagelist.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.xjl.eimui.R
import com.xjl.eimui.databinding.ViewMessageVideoBinding
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.MessageType
import com.xjl.eimui.util.GlideHelper
import com.xjl.emedia.utils.ScreenUtil

class ViewHolderVideoMessage<MESSAGE : EMessage>(context: Context, itemView: View) :
    MessageViewHolderBase<MESSAGE>(context, itemView) {

    private val imageWidth: Int
    private val imageHeight: Int

    init {
        imageWidth = ScreenUtil.dip2px(context, 120f)
        imageHeight = ScreenUtil.dip2px(context, 180f)
    }

    override fun bindDateToChild(
        data: MESSAGE,
        mineContainer: ViewGroup,
        otherContainer: ViewGroup
    ) {
        val binding = ViewMessageVideoBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            Glide.with(context).load(data.getMediaFilePath())
                .apply(GlideHelper.INSTANCE.onCenterCrop).into(itemChatVideoCover)

            itemChatVideoCover.setOnClickListener(this@ViewHolderVideoMessage)
            itemChatVideoCover.setOnLongClickListener(this@ViewHolderVideoMessage)
            itemChatVideoPlayer.setOnClickListener(this@ViewHolderVideoMessage)
            itemChatVideoPlayer.setOnLongClickListener(this@ViewHolderVideoMessage)
            if (MessageType.isReceivedMessage(data.getMessageType())) {
                val layoutParams = RelativeLayout.LayoutParams(imageWidth, imageHeight)
                otherContainer.addView(root, layoutParams)
            } else {
                val layoutParams = RelativeLayout.LayoutParams(imageHeight, imageWidth)
                mineContainer.addView(root, layoutParams)
            }
        }
    }
}
