package com.xjl.eimui.inputbar.recordstate

interface RecordStateListener {
    companion object {
        //1是开始录音 2是取消录音 3是发送录音
        const val START_RECORD = 1
        const val CANCEL_RECORD = 2
        const val RECORD_FINISH = 3
    }
    /**
     * 当前录音状态的回调
     */
    fun onRecordStateChange(currentState: Int)
    fun onFrequenceClick(downTime: Long, upTime: Long)
}
