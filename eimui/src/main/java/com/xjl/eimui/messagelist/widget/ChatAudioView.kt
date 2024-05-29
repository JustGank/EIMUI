package com.xjl.eimui.messagelist.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.xjl.eimui.R
import com.xjl.emedia.utils.ScreenUtil

/**
 * Created by x33664 on 2019/3/8.
 */
class ChatAudioView : RelativeLayout {

    private val TAG = "ChatAudioView"

    private lateinit var audio_container: RelativeLayout
    private lateinit var voice_icon: ImageView
    private lateinit var time: TextView
    private val minLengthDP = 80
    private val maxLengthDP = 240
    private val minRecordTimes = 10
    private val maxRecordTimes = 60
    private var minLength = 0
    private var maxLength = 0
    private var objectAnimator: ObjectAnimator? = null
    private var isRight = true

    constructor(context: Context?) : super(context) {
        minLength = ScreenUtil.dip2px(getContext(), minLengthDP.toFloat())
        maxLength = ScreenUtil.dip2px(getContext(), maxLengthDP.toFloat())
        initView(isRight)
    }

    constructor(context: Context?, isRight: Boolean) : super(context) {
        this.isRight = isRight
        minLength = ScreenUtil.dip2px(getContext(), minLengthDP.toFloat())
        maxLength = ScreenUtil.dip2px(getContext(), maxLengthDP.toFloat())
        initView(this.isRight)
    }

    private fun initView(mine: Boolean) {
        if (mine) {
            LayoutInflater.from(context).inflate(R.layout.view_message_audio_sender, this)
        } else {
            LayoutInflater.from(context).inflate(R.layout.view_message_audio_recepter, this)
        }
        audio_container = findViewById(R.id.audio_container)
        voice_icon = findViewById(R.id.voice_icon)
        time = findViewById(R.id.time)
    }

    /**
     * 设置时间后动态设置item的长度
     */
    fun setTime(timeUnitSeconds: Int) {
        time.text = "$timeUnitSeconds"
        val length = if (timeUnitSeconds < minRecordTimes) {
            minLength
        } else if (timeUnitSeconds < maxRecordTimes) {
            val scale =
                (timeUnitSeconds - minRecordTimes) / (maxRecordTimes - minRecordTimes).toFloat()
            (minLength + scale * (maxLength - minLength)).toInt()
        } else {
            maxLength
        }
        audio_container.layoutParams = LayoutParams(length, LayoutParams.WRAP_CONTENT)
    }

    fun startAnim() {
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(voice_icon, "alpha", 1f, 0f, 1f)
            objectAnimator?.repeatMode = ValueAnimator.RESTART
            objectAnimator?.repeatCount = -1
            objectAnimator?.setDuration(2000)
        }
        if (objectAnimator?.isRunning == true) {
            return
        }
        objectAnimator?.start()
    }

    fun endAnim() {
        objectAnimator?.let {
            it.cancel()
            objectAnimator = null
        }
        voice_icon.setBackgroundResource(R.mipmap.item_chat_voice)
    }

    val isPlaying: Boolean
        get() = if (objectAnimator != null) {
            objectAnimator!!.isRunning
        } else false

}
