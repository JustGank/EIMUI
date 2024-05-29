package com.xjl.eimui.inputbar.recordstate

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.xjl.eimui.R
import com.xjl.eimui.util.AudioResourceUtils
import com.xjl.eimui.util.ToastUtils.showMessage

class RecordTouchListener(
    val context: Context,
    val recordState: RecordStateView,
    var recordStateListener: RecordStateListener?
) : OnTouchListener {

    private val TAG = "RecordTouchListener"

    private var downY = 0
    private var movedY = 0
    private var upY = 0
    private var touchDownTime: Long = 0
    private var touchUpTime: Long = 0

    //手势上下滑动改变录音状态的距离
    var stateChangeDistance = 200

    //频繁点击的时间间隙
    var frequenceClickInterval = 700
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchDownTime = System.currentTimeMillis()
                if (!AudioResourceUtils.validateMicAvailability(v.context)) {
                    showMessage(context, context.resources.getString(R.string.cant_record_audio))
                    return true
                }
                //最后一次落下和上一次抬起是否间隔大一1秒
                if (touchUpTime == 0L || touchDownTime - touchUpTime > frequenceClickInterval) {
                    downY = event.y.toInt()
                    recordState.Show()
                    recordState.normalRecord()
                    recordStateListener?.onRecordStateChange(1)
                } else {
                    recordStateListener!!.onFrequenceClick(touchDownTime, touchUpTime)
                }
            }

            MotionEvent.ACTION_MOVE -> {
                movedY = event.y.toInt() - downY
                if (movedY > -stateChangeDistance) {
                    recordState.normalRecord()
                } else {
                    recordState.cancelRecord()
                }
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                touchUpTime = System.currentTimeMillis()
                upY = event.y.toInt()
                recordStateListener?.onRecordStateChange(if (upY < -stateChangeDistance) 2 else 3)
                recordState.dismiss()
            }
        }
        return true
    }


}
