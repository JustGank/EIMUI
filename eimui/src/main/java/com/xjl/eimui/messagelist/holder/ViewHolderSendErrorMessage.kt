package com.xjl.eimui.messagelist.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.xjl.eimui.R
import com.xjl.eimui.databinding.ViewMessageErrorBinding
import com.xjl.eimui.messagelist.bean.EMessage

class ViewHolderSendErrorMessage<MESSAGE : EMessage>(context: Context, itemView: View) :
    MessageViewHolderBase<MESSAGE>(context, itemView) {

    override fun bindDateToChild(
        data: MESSAGE,
        mineContainer: ViewGroup,
        otherContainer: ViewGroup
    ) {
        val binding = ViewMessageErrorBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            root.setOnClickListener(this@ViewHolderSendErrorMessage)
            root.setOnLongClickListener(this@ViewHolderSendErrorMessage)
            mineContainer.addView(root)
        }
    }
}
