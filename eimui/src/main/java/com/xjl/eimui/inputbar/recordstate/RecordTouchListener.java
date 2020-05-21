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

    private Context context;

    public RecordTouchListener(Context context, RecordStateView recordState, RecordStateListener recordStateListener) {
        this.context = context;
        this.recordState = recordState;
        this.recordStateListener = recordStateListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Log.e(TAG, "MotionEvent=" + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownTime = System.currentTimeMillis();
                if (!AudioResourceUtils.validateMicAvailability()) {
                    ToastUtils.showMessage(context, context.getResources().getString(R.string.cant_record_audio));
                    return true;
                }
                //最后一次落下和上一次抬起是否间隔大一1秒
                if (touchUpTime == 0 || touchDownTime - touchUpTime > 700) {
                    downY = (int) event.getY();
                    Log.e(TAG, "ACTION_DOWN downY=" + downY);

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
                if (movedY < -200) {
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
                    recordStateListener.onRecordStateChange(upY < -200 ? 2 : 3);
                }
                recordState.dismiss();
                break;
        }
        return true;
    }
}
