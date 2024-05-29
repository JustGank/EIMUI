package com.xjl.eimui.inputbar.recordstate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.xjl.eimui.R
import com.xjl.eimui.entry.AudioRecordWaterWaveEntry
import com.xjl.eimui.inputbar.widget.WaterWaveView

class AudioRecordWaterWaveStateView : RelativeLayout, RecordStateView {

    private val STATE_NORMAL = 1
    private val STATE_CANCEL = 2
    private var CURRENT_STATE = -1

    lateinit var wave_view: WaterWaveView
    lateinit var chat_record_state_text: TextView
    private lateinit var audioRecordWaterWaveEntry: AudioRecordWaterWaveEntry

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.view_voice_record_water_wave_state, this)
        wave_view = findViewById(R.id.wave_view)
        wave_view.setColor("#717cfd")
        chat_record_state_text = findViewById(R.id.chat_record_state_text)

        audioRecordWaterWaveEntry= AudioRecordWaterWaveEntry(context)
        chat_record_state_text.text = audioRecordWaterWaveEntry.chatUpMoveCancel
    }

    override fun Show() {
        this.visibility = VISIBLE
        wave_view.start()
    }

    override fun normalRecord() {
        if (CURRENT_STATE == STATE_NORMAL) {
            return
        }
        CURRENT_STATE = STATE_NORMAL
        chat_record_state_text.text = audioRecordWaterWaveEntry.chatUpMoveCancel
    }

    override fun cancelRecord() {
        if (CURRENT_STATE == STATE_CANCEL) {
            return
        }
        CURRENT_STATE = STATE_CANCEL
        chat_record_state_text.text = audioRecordWaterWaveEntry.chatUpCancel
    }

    override fun dismiss() {
        wave_view.stop()
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

    fun setEntry(audioRecordWaterWaveEntry: AudioRecordWaterWaveEntry) {
        this.audioRecordWaterWaveEntry = audioRecordWaterWaveEntry
        if (CURRENT_STATE == STATE_CANCEL) {
            chat_record_state_text.text = audioRecordWaterWaveEntry.chatUpCancel
        } else {
            chat_record_state_text.text = audioRecordWaterWaveEntry.chatUpMoveCancel
        }
    }
}
