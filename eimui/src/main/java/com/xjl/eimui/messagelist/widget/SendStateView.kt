package com.xjl.eimui.messagelist.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.xjl.eimui.databinding.ViewMessageSendStateBinding

/**
 * Created by x33664 on 2019/1/8.
 */
class SendStateView : RelativeLayout {

    lateinit var binding:ViewMessageSendStateBinding

    //0状态代表发送成功过 1代表发送中 2代表发送失败，2状态可点击 可重新发送
    private var currentState = 1

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        binding= ViewMessageSendStateBinding.inflate(LayoutInflater.from(context))
    }

    fun showProgressBar() {
        currentState = 1
        this.visibility = VISIBLE
        binding.apply {
           itemChatErrorText.visibility= View.GONE
            errorImg.visibility=View.GONE
            progressBar.visibility=View.VISIBLE
        }
    }

    fun showError() {
        currentState = 2
        this.visibility = VISIBLE
        binding.apply {
            itemChatErrorText.visibility= View.VISIBLE
            errorImg.visibility=View.VISIBLE
            progressBar.visibility=View.GONE
        }
    }

    fun dismiss() {
        currentState = 0
        this.visibility = GONE
        binding.apply {
            itemChatErrorText.visibility= View.GONE
            errorImg.visibility=View.GONE
            progressBar.visibility=View.GONE
        }
    }

    fun getCurrentState(): Int {
        return currentState
    }

    fun setCurrentState(stage: Int) {
        currentState = stage
        when (stage) {
            0 -> dismiss()
            1 -> showProgressBar()
            2 -> showError()
        }
    }
}
