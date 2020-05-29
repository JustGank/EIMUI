package com.xjl.eimui.inputbar.recordstate;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xjl.eimui.R;
import com.xjl.eimui.util.AudioResourceUtils;
import com.xjl.eimui.util.ToastUtils;

public class RecordTouchListener implements View.OnTouchListener {

    private static final String TAG = "RecordTouchListener";

    private RecordStateView recordState;

    private RecordStateListener recordStateListener;

    private int downY, movedY, upY;

    private long touchDownTime = 0;

    private long touchUpTime = 0;
    //手势上下滑动改变录音状态的距离
    private int stateChangeDistance=200;
    //频繁点击的时间间隙
    private int frequenceClickInterval=700;

    private Context context;

    public RecordTouchListener(Context context, RecordStateView recordState, RecordStateListener recordStateListener) {
        this.context = context;
        this.recordState = recordState;
        this.recordStateListener = recordStateListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownTime = System.currentTimeMillis();
                if (!AudioResourceUtils.validateMicAvailability()) {
                    ToastUtils.showMessage(context, context.getResources().getString(R.string.cant_record_audio));
                    return true;
                }
                //最后一次落下和上一次抬起是否间隔大一1秒
                if (touchUpTime == 0 || touchDownTime - touchUpTime > frequenceClickInterval) {
                    downY = (int) event.getY();

                    recordState.Show();
                    recordState.normalRecord();

                    if (null != recordStateListener) {
                        recordStateListener.onRecordStateChange(1);
                    }
                } else {
                    recordStateListener.onFrequenceClick(touchDownTime, touchUpTime);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                movedY = (int) event.getY();
                if (movedY < -stateChangeDistance) {
                    recordState.normalRecord();
                } else {
                    recordState.cancelRecord();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                touchUpTime = System.currentTimeMillis();
                upY = (int) event.getY();
                if (null != recordStateListener) {
                    recordStateListener.onRecordStateChange(upY < -stateChangeDistance ? 2 : 3);
                }
                recordState.dismiss();
                break;
        }
        return true;
    }

    public int getStateChangeDistance() {
        return stateChangeDistance;
    }

    public void setStateChangeDistance(int stateChangeDistance) {
        this.stateChangeDistance = stateChangeDistance;
    }

    public int getFrequenceClickInterval() {
        return frequenceClickInterval;
    }

    public void setFrequenceClickInterval(int frequenceClickInterval) {
        this.frequenceClickInterval = frequenceClickInterval;
    }

}
