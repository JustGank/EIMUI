package com.xjl.eimui.messagelist.holder

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.xjl.eimui.R
import com.xjl.eimui.databinding.ViewMessageLocationBinding
import com.xjl.eimui.messagelist.bean.EMessage
import com.xjl.eimui.messagelist.bean.MessageType
import com.xjl.eimui.util.GlideHelper
import com.xjl.emedia.utils.ScreenUtil

class ViewHolderLocationMessage<MESSAGE : EMessage>(context: Context, itemView: View) :
    MessageViewHolderBase<MESSAGE>(context, itemView) {
    private var cover: ImageView? = null

    override fun bindDateToChild(
        data: MESSAGE,
        mineContainer: ViewGroup,
        otherContainer: ViewGroup
    ) {

        val binding = ViewMessageLocationBinding.inflate(LayoutInflater.from(context))

        binding.apply {
            title.text = data.content
            if (TextUtils.isEmpty(data.subContent)) {
                subTitle.visibility = View.GONE
            } else {
                subTitle.visibility = View.VISIBLE
                subTitle.text = data.subContent
            }
            Glide.with(context).load(data.mediaFilePath)
                .apply(GlideHelper.INSTANCE.onCenterCrop)
                .into(cover)
            root.setOnClickListener(this@ViewHolderLocationMessage)
            root.setOnLongClickListener(this@ViewHolderLocationMessage)
            val layoutParams = RelativeLayout.LayoutParams(
                ScreenUtil.dip2px(context, 220f),
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            if (MessageType.isReceivedMessage(data.messageType)) {
                otherContainer.addView(root, layoutParams)
            } else {
                mineContainer.addView(root, layoutParams)
            }
        }







    }
}
