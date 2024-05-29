package com.xjl.eimui.messagelist.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.xjl.eimui.R
import com.xjl.eimui.databinding.ViewMessageImageBinding
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.MessageType
import com.xjl.eimui.util.GlideHelper
import com.xjl.emedia.utils.ScreenUtil

class ViewHolderImageMessage<MESSAGE : EMessage>(context: Context, itemView: View) :
    MessageViewHolderBase<MESSAGE>(context, itemView) {
    private val slide1: Int
    private val slide2: Int
    var layoutParams1: RelativeLayout.LayoutParams
    var layoutParams2: RelativeLayout.LayoutParams

    init {
        slide1 = ScreenUtil.dip2px(context, 120f)
        slide2 = ScreenUtil.dip2px(context, 180f)
        layoutParams1 = RelativeLayout.LayoutParams(slide1, slide2)
        layoutParams2 = RelativeLayout.LayoutParams(slide2, slide1)
    }

    override fun bindDateToChild(
        data: MESSAGE,
        mineContainer: ViewGroup,
        otherContainer: ViewGroup
    ) {
        val binding = ViewMessageImageBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            Glide.with(context)
                .load(data.mediaFilePath)
                .apply(GlideHelper.INSTANCE.onCenterCrop)
                .into(binding.itemChatImageview)
            itemChatImageview.setOnClickListener(this@ViewHolderImageMessage)
            itemChatImageview.setOnLongClickListener(this@ViewHolderImageMessage)
            if (MessageType.isReceivedMessage(data.messageType)) {
                otherContainer.addView(itemChatImageview, layoutParams1)
            } else {
                mineContainer.addView(itemChatImageview, layoutParams2)
            }
        }
    }
}
