package com.xjl.eimui.inputbar.recordstate

interface RecordStateView {
    fun Show()
    fun normalRecord()
    fun cancelRecord()
    fun dismiss()
    fun currentState(): Int
    fun nextState()
}
