package com.xjl.eimui.inputbar.recordstate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.xjl.eimui.R
import com.xjl.eimui.entry.AudioRecordWaterWaveEntry

/**
 * 仿微信录音控件 可以实时显示当前录音的分贝大小
 */
class AudioReocrdWeChatWaveView : RelativeLayout, RecordStateView {

    private val TAG = "AudioReocrdWeChatWaveVi"

    private val STATE_NORMAL = 1
    private val STATE_CANCEL = 2
    private var CURRENT_STATE = -1

    lateinit var iv_voice_ing: ImageView
    lateinit var tv_voice_ing: TextView 

    private lateinit var audioRecordWaterWaveEntry: AudioRecordWaterWaveEntry

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context) {
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_voice_record_wechat_wave_state, this)
        iv_voice_ing = view.findViewById(R.id.iv_voice_ing)
        tv_voice_ing = view.findViewById(R.id.tv_voice_ing)
        
        audioRecordWaterWaveEntry= AudioRecordWaterWaveEntry(context)
        tv_voice_ing.text = audioRecordWaterWaveEntry.chatUpMoveCancel
        iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing1)
    }

    override fun Show() {
        this.visibility = VISIBLE
        iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing1)
    }

    override fun normalRecord() {
        if (CURRENT_STATE == STATE_NORMAL) {
            return
        }
        CURRENT_STATE = STATE_NORMAL
        tv_voice_ing.text = audioRecordWaterWaveEntry.chatUpMoveCancel
    }

    override fun cancelRecord() {
        if (CURRENT_STATE == STATE_CANCEL) {
            return
        }
        CURRENT_STATE = STATE_CANCEL
        iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_cancel)
        tv_voice_ing.text = audioRecordWaterWaveEntry.chatUpCancel
    }

    override fun dismiss() {
        this.visibility = GONE
    }

    override fun currentState(): Int {
        return CURRENT_STATE
    }

    override fun nextState() {
        CURRENT_STATE = if (CURRENT_STATE == STATE_NORMAL) STATE_CANCEL else STATE_NORMAL
        if (CURRENT_STATE == STATE_NORMAL) {
            normalRecord()
        } else {
            cancelRecord()
        }
    }

    fun refreshCurrenDB(d: Int) {
        if (CURRENT_STATE == STATE_CANCEL) {
            return
        }
        post {
            if (d < 60) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing1)
            } else if (d < 65) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing2)
            } else if (d < 70) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing3)
            } else if (d < 75) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing4)
            } else if (d < 80) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing5)
            } else if (d < 85) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing6)
            } else if (d < 90) {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing7)
            } else {
                iv_voice_ing.setBackgroundResource(R.mipmap.ic_voice_ing8)
            }
        }
    }

    fun setEntry(audioRecordWaterWaveEntry: AudioRecordWaterWaveEntry) {
        this.audioRecordWaterWaveEntry = audioRecordWaterWaveEntry
        if (CURRENT_STATE == STATE_CANCEL) {
            tv_voice_ing.text = audioRecordWaterWaveEntry.chatUpCancel
        } else {
            tv_voice_ing.text = audioRecordWaterWaveEntry.chatUpMoveCancel
        }
    }


}
